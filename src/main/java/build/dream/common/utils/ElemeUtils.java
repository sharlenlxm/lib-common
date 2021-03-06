package build.dream.common.utils;

import build.dream.common.beans.WebResponse;
import build.dream.common.constants.Constants;
import build.dream.common.exceptions.CustomException;
import build.dream.common.saas.domains.ElemeToken;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.collections.MapUtils;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;
import java.util.UUID;

/**
 * Created by liuyandong on 2017/3/13.
 */
public class ElemeUtils {
    public static final Map<String, String> HEADERS = new HashMap<String, String>();

    static {
        HEADERS.put("Content-Type", "application/json;charset=utf-8");
    }

    public static String obtainTokenByCode(String code, String appKey, String appSecret, String redirectUrl) throws IOException {
        Map<String, String> askTokenRequestParameters = new HashMap<String, String>();
        askTokenRequestParameters.put("grant_type", "authorization_code");
        askTokenRequestParameters.put("code", code);
        askTokenRequestParameters.put("redirect_uri", redirectUrl);
        askTokenRequestParameters.put("client_id", appKey);

        Map<String, String> askTokenHeaders = new HashMap<String, String>();
        askTokenHeaders.put("Content-Type", "Content-Type: application/x-www-form-urlencoded; charset=utf-8");
        askTokenHeaders.put("Authorization", "Basic " + Base64.encodeBase64String(String.format("%s:%s", appKey, appSecret).getBytes(Constants.CHARSET_NAME_UTF_8)));

        String tokenUrl = ConfigurationUtils.getConfiguration(Constants.ELEME_SERVICE_URL) + "/token";
        WebResponse webResponse = OutUtils.doPostWithRequestParameters(tokenUrl, askTokenHeaders, askTokenRequestParameters);
        return webResponse.getResult();
    }

    public static String obtainTokenByRefreshToken(String refreshToken, String appKey, String appSecret) throws IOException {
        Map<String, String> askTokenRequestParameters = new HashMap<String, String>();
        askTokenRequestParameters.put("grant_type", "refresh_token");
        askTokenRequestParameters.put("refresh_token", refreshToken);

        Map<String, String> askTokenHeaders = new HashMap<String, String>();
        askTokenHeaders.put("Content-Type", "Content-Type: application/x-www-form-urlencoded; charset=utf-8");
        askTokenHeaders.put("Authorization", "Basic " + Base64.encodeBase64String(String.format("%s:%s", appKey, appSecret).getBytes(Constants.CHARSET_NAME_UTF_8)));

        String tokenUrl = ConfigurationUtils.getConfiguration(Constants.ELEME_SERVICE_URL) + "/token";
        WebResponse webResponse = OutUtils.doPostWithRequestParameters(tokenUrl, askTokenHeaders, askTokenRequestParameters);
        return webResponse.getResult();
    }

    public static boolean checkSignature(Map<String, Object> callbackMap, String appSecret) {
        Map<String, Object> sortedMap = new TreeMap<String, Object>(callbackMap);
        String signature = sortedMap.remove("signature").toString();
        StringBuilder stringBuilder = new StringBuilder();
        for (Map.Entry<String, Object> entry : sortedMap.entrySet()) {
            stringBuilder.append(entry.getKey()).append("=").append(entry.getValue());
        }
        stringBuilder.append(appSecret);
        return DigestUtils.md5Hex(stringBuilder.toString()).toUpperCase().equals(signature);
    }

    public static String obtainAccessToken(String tenantId, String branchId, Integer elemeAccountType) {
        String tokenJson = null;
        if (elemeAccountType == Constants.ELEME_ACCOUNT_TYPE_CHAIN_ACCOUNT) {
            tokenJson = CacheUtils.hget(Constants.KEY_ELEME_TOKENS, Constants.ELEME_TOKEN + "_" + tenantId);
        } else if (elemeAccountType == Constants.ELEME_ACCOUNT_TYPE_INDEPENDENT_ACCOUNT) {
            tokenJson = CacheUtils.hget(Constants.KEY_ELEME_TOKENS, Constants.ELEME_TOKEN + "_" + tenantId + "_" + branchId);
        }
        ValidateUtils.notNull(tokenJson, "未检索到访问令牌！");
        ElemeToken elemeToken = JacksonUtils.readValue(tokenJson, ElemeToken.class);
        return elemeToken.getAccessToken();
    }

    private static String generateSignature(String appKey, String appSecret, long timestamp, String action, String accessToken, Map<String, Object> params) {
        Map<String, Object> sorted = new TreeMap<String, Object>(params);
        sorted.put("app_key", appKey);
        sorted.put("timestamp", timestamp);
        StringBuilder stringBuilder = new StringBuilder();
        for (Map.Entry<String, Object> entry : sorted.entrySet()) {
            stringBuilder.append(entry.getKey()).append("=").append(GsonUtils.toJson(entry.getValue()));
        }
        return DigestUtils.md5Hex(String.format("%s%s%s%s", action, accessToken, stringBuilder, appSecret)).toUpperCase();
    }

    public static String constructRequestBody(String tenantId, String branchId, Integer elemeAccountType, String action, Map<String, Object> params) {
        String appKey = ConfigurationUtils.getConfiguration(Constants.ELEME_APP_KEY);
        String appSecret = ConfigurationUtils.getConfiguration(Constants.ELEME_APP_SECRET);
        Map<String, Object> metas = new HashMap<String, Object>();
        Long timestamp = System.currentTimeMillis() / 1000;
        metas.put("app_key", appKey);
        metas.put("timestamp", timestamp);
        if (params == null) {
            params = new HashMap<String, Object>();
        }
        String accessToken = obtainAccessToken(tenantId, branchId, elemeAccountType);
        String signature = generateSignature(appKey, appSecret, timestamp, action, accessToken, params);
        Map<String, Object> requestBody = new HashMap<String, Object>();
        String requestId = UUID.randomUUID().toString();
        requestBody.put("id", requestId);
        requestBody.put("action", action);
        requestBody.put("token", accessToken);
        requestBody.put("metas", metas);
        requestBody.put("params", params);
        requestBody.put("signature", signature);
        requestBody.put("nop", "1.0.0");
        return GsonUtils.toJson(requestBody);
    }

    private static Map<String, Object> doCallElemeSystem(String tenantId, String branchId, Integer elemeAccountType, String action, Map<String, Object> params) {
        String requestBody = constructRequestBody(tenantId, branchId, elemeAccountType, action, params);
        Map<String, String> callElemeSystemRequestParameters = new HashMap<String, String>();
        callElemeSystemRequestParameters.put("requestBody", requestBody);

        String url = ConfigurationUtils.getConfiguration(Constants.ELEME_SERVICE_URL) + "/api/v1/";
        WebResponse webResponse = OutUtils.doPostWithRequestBody(url, HEADERS, requestBody);
        String result = webResponse.getResult();
        Map<String, Object> resultMap = JacksonUtils.readValueAsMap(result, String.class, Object.class);
        return resultMap;
    }

    public static Map<String, Object> callElemeSystem(String tenantId, String branchId, Integer elemeAccountType, String action, Map<String, Object> params) {
        Map<String, Object> resultMap = doCallElemeSystem(tenantId, branchId, elemeAccountType, action, params);
        Map<String, Object> errorMap = MapUtils.getMap(resultMap, "error");
        if (MapUtils.isNotEmpty(errorMap)) {
            ValidateUtils.isTrue(false, MapUtils.getString(errorMap, "message"));
        }
        return MapUtils.getMap(resultMap, "result");
    }

    public static boolean verifyToken(String tenantId, String branchId, Integer elemeAccountType) {
        Map<String, Object> resultMap = doCallElemeSystem(tenantId, branchId, elemeAccountType, "eleme.user.getUser", null);
        Map<String, Object> errorMap = MapUtils.getMap(resultMap, "error");
        if (MapUtils.isEmpty(errorMap)) {
            return true;
        }

        String code = MapUtils.getString(errorMap, "code");
        if ("UNAUTHORIZED".equals(code)) {
            return false;
        }

        throw new CustomException(MapUtils.getString(errorMap, "message"));
    }
}
