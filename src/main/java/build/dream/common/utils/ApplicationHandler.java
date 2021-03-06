package build.dream.common.utils;

import build.dream.common.annotations.DateFormat;
import build.dream.common.annotations.InstantiateObjectIgnore;
import build.dream.common.annotations.InstantiateObjectKey;
import build.dream.common.annotations.JsonSchema;
import build.dream.common.api.ApiRest;
import build.dream.common.auth.CustomUserDetails;
import build.dream.common.constants.Constants;
import build.dream.common.constants.SessionConstants;
import build.dream.common.saas.domains.SystemUser;
import build.dream.common.saas.domains.Tenant;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.collections.MapUtils;
import org.apache.commons.lang.ArrayUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.math.NumberUtils;
import org.hibernate.validator.HibernateValidator;
import org.springframework.context.ApplicationContext;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpHeaders;
import org.springframework.util.ReflectionUtils;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.method.HandlerMethod;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.beans.BeanInfo;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.io.IOException;
import java.lang.reflect.*;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by liuyandong on 2017/3/24.
 */
public class ApplicationHandler {
    /**
     * 获取ServletRequestAttributes
     *
     * @return
     */
    public static ServletRequestAttributes getServletRequestAttributes() {
        return (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
    }

    /**
     * 获取HttpServletRequest
     *
     * @return
     */
    public static HttpServletRequest getHttpServletRequest() {
        return getServletRequestAttributes().getRequest();
    }

    /**
     * 获取HttpServletResponse
     *
     * @return
     */
    public static HttpServletResponse getHttpServletResponse() {
        return getServletRequestAttributes().getResponse();
    }

    /**
     * 获取请求参数
     *
     * @return
     */
    public static Map<String, String> getRequestParameters() {
        return getRequestParameters(getHttpServletRequest());
    }

    /**
     * 获取请求参数
     *
     * @param httpServletRequest
     * @return
     */
    public static Map<String, String> getRequestParameters(HttpServletRequest httpServletRequest) {
        Map<String, String> requestParameters = new LinkedHashMap<String, String>();
        Map<String, String[]> parameterMap = httpServletRequest.getParameterMap();
        for (Map.Entry<String, String[]> parameterEntry : parameterMap.entrySet()) {
            requestParameters.put(parameterEntry.getKey(), StringUtils.trimToEmpty(StringUtils.join(parameterEntry.getValue(), ",")));
        }
        return requestParameters;
    }

    /**
     * 获取请求体
     *
     * @return
     * @throws IOException
     */
    public static String getRequestBody() throws IOException {
        return getRequestBody(getHttpServletRequest(), Constants.CHARSET_NAME_UTF_8);
    }

    /**
     * 获取请求体
     *
     * @param charsetName
     * @return
     * @throws IOException
     */
    public static String getRequestBody(String charsetName) throws IOException {
        return getRequestBody(getHttpServletRequest(), charsetName);
    }

    /**
     * 获取请求体
     *
     * @param httpServletRequest
     * @param charsetName
     * @return
     * @throws IOException
     */
    public static String getRequestBody(HttpServletRequest httpServletRequest, String charsetName) throws IOException {
        return IOUtils.toString(httpServletRequest.getInputStream(), charsetName);
    }

    /**
     * 根据参数名称获取参数
     *
     * @param requestParameterName
     * @return
     */
    public static String getRequestParameter(String requestParameterName) {
        return getRequestParameter(getHttpServletRequest(), requestParameterName);
    }

    /**
     * 根据参数名称获取参数
     *
     * @param httpServletRequest
     * @param requestParameterName
     * @return
     */
    public static String getRequestParameter(HttpServletRequest httpServletRequest, String requestParameterName) {
        return StringUtils.trimToNull(StringUtils.join(httpServletRequest.getParameterValues(requestParameterName), ","));
    }

    /**
     * 获取session id
     *
     * @return
     */
    public static String getSessionId() {
        return getServletRequestAttributes().getSessionId();
    }

    /**
     * 获取ServletContext
     *
     * @return
     */
    public static ServletContext getServletContext() {
        return getServletContext(getHttpServletRequest());
    }

    /**
     * 获取ServletContext
     *
     * @param httpServletRequest
     * @return
     */
    public static ServletContext getServletContext(HttpServletRequest httpServletRequest) {
        return httpServletRequest.getServletContext();
    }

    /**
     * 获取客户点IP地址
     *
     * @return
     */
    public static String getRemoteAddress() {
        return getRemoteAddress(getHttpServletRequest());
    }

    /**
     * 获取客户点IP地址
     *
     * @param httpServletRequest
     * @return
     */
    public static String getRemoteAddress(HttpServletRequest httpServletRequest) {
        return httpServletRequest.getRemoteAddr();
    }

    /**
     * 获取 HttpSession
     *
     * @return
     */
    public static HttpSession getHttpSession() {
        return getHttpServletRequest().getSession();
    }

    /**
     * 获取请求的url
     *
     * @return
     */
    public static String getRequestUrl() {
        return getRequestUrl(getHttpServletRequest());
    }

    /**
     * 获取请求的url
     *
     * @param httpServletRequest
     * @return
     */
    public static String getRequestUrl(HttpServletRequest httpServletRequest) {
        return httpServletRequest.getRequestURL().toString();
    }

    /**
     * 获取请求的uri
     *
     * @return
     */
    public static String getRequestUri() {
        return getRequestUri(getHttpServletRequest());
    }

    /**
     * 获取请求的uri
     *
     * @param httpServletRequest
     * @return
     */
    public static String getRequestUri(HttpServletRequest httpServletRequest) {
        return httpServletRequest.getRequestURI();
    }

    /**
     * 获取请求的url包含queryString
     *
     * @param httpServletRequest
     * @return
     */
    public static String obtainRequestUrl(HttpServletRequest httpServletRequest) {
        String queryString = httpServletRequest.getQueryString();
        String url = httpServletRequest.getRequestURL().toString();
        if (StringUtils.isNotBlank(queryString)) {
            url = url + "?" + queryString;
        }
        return url;
    }

    /**
     * 获取请求的url包含queryString
     *
     * @return
     */
    public static String obtainRequestUrl() {
        return obtainRequestUrl(getHttpServletRequest());
    }

    /**
     * 实例化对象
     *
     * @param objectClass
     * @param parameters
     * @param <T>
     * @return
     * @throws Exception
     */
    public static <T> T instantiateObject(Class<T> objectClass, Map<String, String> parameters) throws Exception {
        return instantiateObject(objectClass, parameters, "");
    }

    /**
     * 实例化对象
     *
     * @param objectClass
     * @param parameters
     * @param prefix
     * @param <T>
     * @return
     * @throws Exception
     */
    public static <T> T instantiateObject(Class<T> objectClass, Map<String, String> parameters, String prefix) throws Exception {
        T object = objectClass.newInstance();
        Map<String, SimpleDateFormat> simpleDateFormatMap = new HashMap<String, SimpleDateFormat>();

        List<Field> fields = obtainAllFields(objectClass);
        for (Field field : fields) {
            int modifiers = field.getModifiers();
            if (Modifier.isStatic(modifiers) || Modifier.isFinal(modifiers) || Modifier.isNative(modifiers)) {
                continue;
            }
            if (field.getAnnotation(InstantiateObjectIgnore.class) != null) {
                continue;
            }

            InstantiateObjectKey instantiateObjectKey = field.getAnnotation(InstantiateObjectKey.class);

            String parameterName = null;
            if (instantiateObjectKey == null) {
                parameterName = field.getName();
            } else {
                parameterName = instantiateObjectKey.name();
            }
            String fieldValue = parameters.get(prefix + parameterName);
            if (StringUtils.isBlank(fieldValue)) {
                continue;
            }
            ReflectionUtils.makeAccessible(field);
            Class<?> fieldClass = field.getType();
            if (fieldClass == Byte.class || fieldClass == byte.class) {
                field.set(object, Byte.valueOf(fieldValue));
            } else if (fieldClass == Short.class || fieldClass == short.class) {
                field.set(object, Short.valueOf(fieldValue));
            } else if (fieldClass == Integer.class || fieldClass == int.class) {
                field.set(object, Integer.valueOf(fieldValue));
            } else if (fieldClass == Long.class || fieldClass == long.class) {
                field.set(object, Long.valueOf(fieldValue));
            } else if (fieldClass == Float.class || fieldClass == float.class) {
                field.set(object, Float.valueOf(fieldValue));
            } else if (fieldClass == Double.class || fieldClass == double.class) {
                field.set(object, Double.valueOf(fieldValue));
            } else if (fieldClass == Character.class || fieldClass == char.class) {
                field.set(object, fieldValue.charAt(0));
            } else if (fieldClass == String.class) {
                field.set(object, fieldValue);
            } else if (fieldClass == Boolean.class || fieldClass == boolean.class) {
                field.set(object, Boolean.valueOf(fieldValue));
            } else if (fieldClass == Date.class) {
                SimpleDateFormat simpleDateFormat = obtainSimpleDateFormat(simpleDateFormatMap, field);
                field.set(object, simpleDateFormat.parse(fieldValue));
            } else if (fieldClass == BigInteger.class) {
                field.set(object, BigInteger.valueOf(Long.valueOf(fieldValue)));
            } else if (fieldClass == BigDecimal.class) {
                field.set(object, BigDecimal.valueOf(Double.valueOf(fieldValue)));
            } else if (fieldClass == List.class) {
                Type type = field.getGenericType();
                if (type instanceof ParameterizedType) {
                    Type itemType = ((ParameterizedType) type).getActualTypeArguments()[0];
                    SimpleDateFormat simpleDateFormat = null;
                    if (itemType instanceof Date) {
                        simpleDateFormat = obtainSimpleDateFormat(simpleDateFormatMap, field);
                    }
                    field.set(object, buildArrayList(field, itemType, parameterName, fieldValue, ",", simpleDateFormat));
                }
            } else if (fieldClass == Map.class) {
                Type type = field.getGenericType();
                if (type instanceof ParameterizedType) {
                    Type[] actualTypeArguments = ((ParameterizedType) type).getActualTypeArguments();
                    Class<? extends Object> keyClass = (Class<? extends Object>) actualTypeArguments[0];
                    Class<? extends Object> valueClass = (Class<? extends Object>) actualTypeArguments[1];
                    field.set(object, JacksonUtils.readValueAsMap(fieldValue, keyClass, valueClass));
                }
            } else {
                boolean isJson = isJson(fieldValue);
                if (isJson) {
                    JsonSchema jsonSchema = field.getAnnotation(JsonSchema.class);
                    if (jsonSchema != null) {
                        ValidateUtils.isTrue(isRightJson(fieldValue, jsonSchema.value()), obtainParameterErrorMessage(parameterName), Constants.ERROR_CODE_INVALID_PARAMETER);
                    }
                    field.set(object, JacksonUtils.readValue(fieldValue, field.getType()));
                }
            }
        }
        return object;
    }

    /**
     * 获取所有属性，包含父类的属性
     *
     * @param objectClass
     * @return
     */
    private static List<Field> obtainAllFields(Class<?> objectClass) {
        ArrayList<Field> fields = new ArrayList<Field>();
        while (objectClass != Object.class) {
            fields.addAll(Arrays.asList(objectClass.getDeclaredFields()));
            objectClass = objectClass.getSuperclass();
        }
        return fields;
    }

    /**
     * 获取SimpleDateFormat
     *
     * @param simpleDateFormatMap
     * @param field
     * @return
     */
    public static SimpleDateFormat obtainSimpleDateFormat(Map<String, SimpleDateFormat> simpleDateFormatMap, Field field) {
        DateFormat dateFormat = field.getAnnotation(DateFormat.class);
        String datePattern = null;

        if (dateFormat != null) {
            datePattern = dateFormat.pattern();
        } else {
            datePattern = Constants.DEFAULT_DATE_PATTERN;
        }

        if (!simpleDateFormatMap.containsKey(datePattern)) {
            simpleDateFormatMap.put(datePattern, new SimpleDateFormat(datePattern));
        }
        return simpleDateFormatMap.get(datePattern);
    }

    /**
     * 构建List<Byte>
     *
     * @param fieldValue
     * @param separator
     * @return
     */
    public static List<Byte> buildByteArrayList(String fieldValue, String separator) {
        List<Byte> list = new ArrayList<Byte>();
        String[] array = fieldValue.split(separator);
        for (String str : array) {
            list.add(Byte.valueOf(str));
        }
        return list;
    }

    /**
     * 构建List<Short>
     *
     * @param fieldValue
     * @param separator
     * @return
     */
    public static List<Short> buildShortArrayList(String fieldValue, String separator) {
        List<Short> list = new ArrayList<Short>();
        String[] array = fieldValue.split(separator);
        for (String str : array) {
            list.add(Short.valueOf(str));
        }
        return list;
    }

    /**
     * 构建List<Integer>
     *
     * @param fieldValue
     * @param separator
     * @return
     */
    public static List<Integer> buildIntegerArrayList(String fieldValue, String separator) {
        List<Integer> list = new ArrayList<Integer>();
        String[] array = fieldValue.split(separator);
        for (String str : array) {
            list.add(Integer.valueOf(str));
        }
        return list;
    }

    /**
     * 构建List<Long>
     *
     * @param fieldValue
     * @param separator
     * @return
     */
    public static List<Long> buildLongArrayList(String fieldValue, String separator) {
        List<Long> list = new ArrayList<Long>();
        String[] array = fieldValue.split(separator);
        for (String str : array) {
            list.add(Long.valueOf(str));
        }
        return list;
    }

    /**
     * 构建List<Float>
     *
     * @param fieldValue
     * @param separator
     * @return
     */
    public static List<Float> buildFloatArrayList(String fieldValue, String separator) {
        List<Float> list = new ArrayList<Float>();
        String[] array = fieldValue.split(separator);
        for (String str : array) {
            list.add(Float.valueOf(str));
        }
        return list;
    }

    /**
     * 构建List<Double>
     *
     * @param fieldValue
     * @param separator
     * @return
     */
    public static List<Double> buildDoubleArrayList(String fieldValue, String separator) {
        List<Double> list = new ArrayList<Double>();
        String[] array = fieldValue.split(separator);
        for (String str : array) {
            list.add(Double.valueOf(str));
        }
        return list;
    }

    /**
     * 构建List<Character>
     *
     * @param fieldValue
     * @param separator
     * @return
     */
    public static List<Character> buildCharacterArrayList(String fieldValue, String separator) {
        List<Character> list = new ArrayList<Character>();
        String[] array = fieldValue.split(separator);
        for (String str : array) {
            list.add(str.charAt(0));
        }
        return list;
    }

    /**
     * 构建List<Boolean>
     *
     * @param fieldValue
     * @param separator
     * @return
     */
    public static List<Boolean> buildBooleanArrayList(String fieldValue, String separator) {
        List<Boolean> list = new ArrayList<Boolean>();
        String[] array = fieldValue.split(separator);
        for (String str : array) {
            list.add(Boolean.valueOf(str));
        }
        return list;
    }

    /**
     * 构建List<BigInteger>
     *
     * @param fieldValue
     * @param separator
     * @return
     */
    public static List<BigInteger> buildBigIntegerArrayList(String fieldValue, String separator) {
        List<BigInteger> list = new ArrayList<BigInteger>();
        String[] array = fieldValue.split(separator);
        for (String str : array) {
            list.add(BigInteger.valueOf(Long.valueOf(str)));
        }
        return list;
    }

    /**
     * 构建List<BigDecimal>
     *
     * @param fieldValue
     * @param separator
     * @return
     */
    public static List<BigDecimal> buildBigDecimalArrayList(String fieldValue, String separator) {
        List<BigDecimal> list = new ArrayList<BigDecimal>();
        String[] array = fieldValue.split(separator);
        for (String str : array) {
            list.add(BigDecimal.valueOf(Double.valueOf(str)));
        }
        return list;
    }

    /**
     * 构建List<Date>
     *
     * @param fieldValue
     * @param separator
     * @param simpleDateFormat
     * @return
     */
    public static List<Date> buildDateArrayList(String fieldValue, String separator, SimpleDateFormat simpleDateFormat) {
        List<Date> list = new ArrayList<Date>();
        String[] array = fieldValue.split(separator);
        try {
            for (String str : array) {
                list.add(simpleDateFormat.parse(str));
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return list;
    }

    /**
     * 构建List<String>
     *
     * @param fieldValue
     * @param separator
     * @return
     */
    public static List<String> buildStringArrayList(String fieldValue, String separator) {
        List<String> list = new ArrayList<String>();
        String[] array = fieldValue.split(separator);
        for (String str : array) {
            list.add(str);
        }
        return list;
    }

    /**
     * 构建List
     *
     * @param field
     * @param type
     * @param parameterName
     * @param fieldValue
     * @param separator
     * @param simpleDateFormat
     * @return
     */
    private static Object buildArrayList(Field field, Type type, String parameterName, String fieldValue, String separator, SimpleDateFormat simpleDateFormat) {
        List<? extends Object> list = null;
        if (type == Byte.class) {
            list = buildByteArrayList(fieldValue, separator);
        } else if (type == Short.class) {
            list = buildShortArrayList(fieldValue, separator);
        } else if (type == Integer.class) {
            list = buildIntegerArrayList(fieldValue, separator);
        } else if (type == Long.class) {
            list = buildLongArrayList(fieldValue, separator);
        } else if (type == Float.class) {
            list = buildFloatArrayList(fieldValue, separator);
        } else if (type == Double.class) {
            list = buildFloatArrayList(fieldValue, separator);
        } else if (type == Character.class) {
            list = buildCharacterArrayList(fieldValue, separator);
        } else if (type == Boolean.class) {
            list = buildBooleanArrayList(fieldValue, separator);
        } else if (type == BigInteger.class) {
            list = buildBigIntegerArrayList(fieldValue, separator);
        } else if (type == BigDecimal.class) {
            list = buildBigDecimalArrayList(fieldValue, separator);
        } else if (type == Date.class) {
            list = buildDateArrayList(fieldValue, separator, simpleDateFormat);
        } else if (type == String.class) {
            list = buildStringArrayList(fieldValue, separator);
        } else {
            boolean isJson = isJson(fieldValue);
            if (isJson) {
                JsonSchema jsonSchema = field.getAnnotation(JsonSchema.class);
                if (jsonSchema != null) {
                    ValidateUtils.isTrue(isRightJson(fieldValue, jsonSchema.value()), obtainParameterErrorMessage(parameterName), Constants.ERROR_CODE_INVALID_PARAMETER);
                }
                list = JacksonUtils.readValueAsList(fieldValue, (Class<? extends Object>) type);
            }
        }
        return list;
    }

    private static ApplicationContext applicationContext;
    private static ServletContext servletContext;

    public static void setApplicationContext(ApplicationContext applicationContext) {
        ApplicationHandler.applicationContext = applicationContext;
    }

    public static ApplicationContext getApplicationContext() {
        return applicationContext;
    }

    public static void setServletContext(ServletContext servletContext) {
        ApplicationHandler.servletContext = servletContext;
    }

    public static ServletContext obtainServletContext() {
        return servletContext;
    }

    public static <T> T getBean(Class<T> beanClass) {
        return applicationContext.getBean(beanClass);
    }

    public static Validator obtainValidator() {
        Validator validator = null;
        if (applicationContext.containsBean("validator")) {
            validator = (Validator) applicationContext.getBean("validator");
        } else {
            ValidatorFactory validatorFactory = Validation.byProvider(HibernateValidator.class).configure().buildValidatorFactory();
            validator = validatorFactory.getValidator();
        }
        return validator;
    }

    public static MessageSource obtainMessageSource() {
        return applicationContext.getBean(MessageSource.class);
    }

    public static void notNullAndPut(Map<String, Object> targetMap, String key, Object value, String message) {
        ValidateUtils.notNull(value, message, Constants.ERROR_CODE_INVALID_PARAMETER);
        targetMap.put(key, value);
    }

    public static void notBlankAndPut(Map<String, String> targetMap, String key, String value, String message) {
        ValidateUtils.isTrue(StringUtils.isNotBlank(value), message, Constants.ERROR_CODE_INVALID_PARAMETER);
        targetMap.put(key, value);
    }

    public static void ifNotNullPut(Map<String, Object> targetMap, String key, Object value) {
        if (value != null) {
            targetMap.put(key, value);
        }
    }

    public static void ifNotBlankPut(Map<String, String> targetMap, String key, String value) {
        if (StringUtils.isNotBlank(value)) {
            targetMap.put(key, value);
        }
    }

    public static void ifNotBlankPut(Map<String, String> sourceMap, Map<String, String> targetMap, String... keys) {
        for (String key : keys) {
            String value = sourceMap.get(key);
            if (StringUtils.isNotBlank(key)) {
                targetMap.put(key, value);
            }
        }
    }

    public static void ifNotBlankPut(Map<String, String> sourceMap, Map<String, String> targetMap, Map<String, String> keyMapping) {
        Set<Map.Entry<String, String>> entries = keyMapping.entrySet();
        for (Map.Entry<String, String> entry : entries) {
            String value = sourceMap.get(entry.getKey());
            if (StringUtils.isNotBlank(value)) {
                targetMap.put(entry.getValue(), value);
            }
        }
    }

    public static String obtainParameterErrorMessage(String parameterName) {
        String message = null;
        String deploymentEnvironment = ConfigurationUtils.getConfiguration(Constants.DEPLOYMENT_ENVIRONMENT);
        if (Constants.DEVELOPMENT.equals(deploymentEnvironment) || Constants.TEST.equals(deploymentEnvironment) || Constants.BETA.equals(deploymentEnvironment)) {
            message = String.format(Constants.PARAMETER_ERROR_MESSAGE_PATTERN, parameterName);
        } else {
            message = Constants.API_PARAMETER_ERROR_MESSAGE;
        }
        return message;
    }

    public static String obtainRequestMethod() {
        return getHttpServletRequest().getMethod();
    }

    public static BigInteger obtainUserId(String sessionId) {
        String userId = CacheUtils.obtainAttributeFromSession(sessionId, SessionConstants.KEY_USER_ID);
        if (StringUtils.isNotBlank(userId)) {
            return NumberUtils.createBigInteger(userId);
        }
        return null;
    }

    public static BigInteger obtainTenantId(String sessionId) {
        String tenantId = CacheUtils.obtainAttributeFromSession(sessionId, SessionConstants.KEY_TENANT_ID);
        if (StringUtils.isNotBlank(tenantId)) {
            return NumberUtils.createBigInteger(tenantId);
        }
        return null;
    }

    public static void forward(String controllerName, String actionName, Map<String, Object> attributes) throws ServletException, IOException {
        HttpServletRequest httpServletRequest = getHttpServletRequest();
        if (MapUtils.isNotEmpty(attributes)) {
            for (Map.Entry<String, Object> entry : attributes.entrySet()) {
                httpServletRequest.setAttribute(entry.getKey(), entry.getValue());
            }
        }
        HttpServletResponse httpServletResponse = getHttpServletResponse();
        httpServletRequest.getRequestDispatcher("/" + controllerName + "/" + actionName).forward(httpServletRequest, httpServletResponse);
    }

    public static void forward(String path) throws ServletException, IOException {
        HttpServletRequest httpServletRequest = getHttpServletRequest();
        HttpServletResponse httpServletResponse = getHttpServletResponse();
        httpServletRequest.getRequestDispatcher(path).forward(httpServletRequest, httpServletResponse);
    }

    public static String obtainHttpHeader(String name) {
        HttpServletRequest httpServletRequest = getHttpServletRequest();
        return httpServletRequest.getHeader(name);
    }

    public static String obtainBrowserType() {
        String userAgent = obtainHttpHeader(HttpHeaders.USER_AGENT);
        String browserType = null;
        if (StringUtils.isBlank(userAgent)) {
            browserType = Constants.BROWSER_TYPE_OTHER;
        } else {
            userAgent = userAgent.toLowerCase();
            if (userAgent.contains("alipay")) {
                browserType = Constants.BROWSER_TYPE_ALIPAY;
            } else if (userAgent.contains("micromessenger")) {
                browserType = Constants.BROWSER_TYPE_WEI_XIN;
            } else {
                browserType = Constants.BROWSER_TYPE_OTHER;
            }
        }
        return browserType;
    }

    public static String obtainBrowserPlatform() {
        String userAgent = obtainHttpHeader(HttpHeaders.USER_AGENT);
        String browserPlatform = null;
        if (StringUtils.isBlank(userAgent)) {
            browserPlatform = Constants.BROWSER_PLATFORM_OTHER;
        } else {
            userAgent = userAgent.toLowerCase();
            if (userAgent.contains("iphone") || userAgent.contains("android")) {
                browserPlatform = Constants.BROWSER_PLATFORM_PHONE;
            } else {
                browserPlatform = Constants.BROWSER_PLATFORM_PC;
            }
        }
        return browserPlatform;
    }

    public static void redirect(String url) throws IOException {
        ApplicationHandler.getHttpServletResponse().sendRedirect(url);
    }

    public static void notNull(Object object, String parameterName) {
        ValidateUtils.notNull(object, obtainParameterErrorMessage(parameterName), Constants.ERROR_CODE_INVALID_PARAMETER);
    }

    public static void isTrue(boolean expression, String parameterName) {
        ValidateUtils.isTrue(expression, obtainParameterErrorMessage(parameterName), Constants.ERROR_CODE_INVALID_PARAMETER);
    }

    public static void notEmpty(Object[] array, String parameterName) {
        ValidateUtils.notEmpty(array, obtainParameterErrorMessage(parameterName), Constants.ERROR_CODE_INVALID_PARAMETER);
    }

    public static void notEmpty(Collection collection, String parameterName) {
        ValidateUtils.notEmpty(collection, obtainParameterErrorMessage(parameterName), Constants.ERROR_CODE_INVALID_PARAMETER);
    }

    public static void notEmpty(Map map, String parameterName) {
        ValidateUtils.notEmpty(map, obtainParameterErrorMessage(parameterName), Constants.ERROR_CODE_INVALID_PARAMETER);
    }

    public static void notEmpty(String string, String parameterName) {
        ValidateUtils.notEmpty(string, obtainParameterErrorMessage(parameterName), Constants.ERROR_CODE_INVALID_PARAMETER);
    }

    public static void inArray(Object[] array, Object value, String name) {
        ValidateUtils.inArray(array, value, "参数(" + name + ")只能为【" + StringUtils.join(array, "，") + "】中的一个！", Constants.ERROR_CODE_INVALID_PARAMETER);
    }

    public static void inArray(Long[] array, Long value, String name) {
        ValidateUtils.inArray(array, value, "参数(" + name + ")只能为【" + StringUtils.join(array, "，") + "】中的一个！", Constants.ERROR_CODE_INVALID_PARAMETER);
    }

    public static void inArray(Integer[] array, Integer value, String name) {
        ValidateUtils.inArray(array, value, "参数(" + name + ")只能为【" + StringUtils.join(array, "，") + "】中的一个！", Constants.ERROR_CODE_INVALID_PARAMETER);
    }

    public static void inArray(Short[] array, Short value, String name) {
        ValidateUtils.inArray(array, value, "参数(" + name + ")只能为【" + StringUtils.join(array, "，") + "】中的一个！", Constants.ERROR_CODE_INVALID_PARAMETER);
    }

    public static void inArray(Character[] array, Character value, String name) {
        ValidateUtils.inArray(array, value, "参数(" + name + ")只能为【" + StringUtils.join(array, "，") + "】中的一个！", Constants.ERROR_CODE_INVALID_PARAMETER);
    }

    public static void inArray(Byte[] array, Byte value, String name) {
        ValidateUtils.inArray(array, value, "参数(" + name + ")只能为【" + StringUtils.join(array, "，") + "】中的一个！", Constants.ERROR_CODE_INVALID_PARAMETER);
    }

    public static void inArray(Double[] array, Double value, String name) {
        ValidateUtils.inArray(array, value, "参数(" + name + ")只能为【" + StringUtils.join(array, "，") + "】中的一个！", Constants.ERROR_CODE_INVALID_PARAMETER);
    }

    public static void inArray(Float[] array, Float value, String name) {
        ValidateUtils.inArray(array, value, "参数(" + name + ")只能为【" + StringUtils.join(array, "，") + "】中的一个！", Constants.ERROR_CODE_INVALID_PARAMETER);
    }

    public static void inArray(Boolean[] array, Boolean value, String name) {
        ValidateUtils.inArray(array, value, "参数(" + name + ")只能为【" + StringUtils.join(array, "，") + "】中的一个！", Constants.ERROR_CODE_INVALID_PARAMETER);
    }

    public static void validateJson(String jsonString, String schemaFilePath, String parameterName) {
        isJson(jsonString, parameterName);
        isTrue(isRightJson(jsonString, schemaFilePath), parameterName);
    }

    public static boolean isRightJson(String jsonString, String schemaFilePath) {
        return JsonSchemaValidateUtils.validate(jsonString, schemaFilePath);
    }

    public static void isJson(String jsonString, String parameterName) {
        notBlank(jsonString, parameterName);
        boolean isValidate = (jsonString.startsWith("{") && jsonString.endsWith("}")) || (jsonString.startsWith("[") && jsonString.endsWith("]"));
        isTrue(isValidate, parameterName);
    }

    public static boolean isJson(String jsonString) {
        if (StringUtils.isBlank(jsonString)) {
            return false;
        }
        return (jsonString.startsWith("{") && jsonString.endsWith("}")) || (jsonString.startsWith("[") && jsonString.endsWith("]"));
    }

    public static void notBlank(String string, String parameterName) {
        ValidateUtils.notBlank(string, obtainParameterErrorMessage(parameterName), Constants.ERROR_CODE_INVALID_PARAMETER);
    }

    public static void invalidateHttpSession() {
        getHttpSession().invalidate();
    }

    public static String getRequestHeader(String name) {
        return getRequestHeader(getHttpServletRequest(), name);
    }

    public static String getRequestHeader(HttpServletRequest httpServletRequest, String name) {
        return httpServletRequest.getHeader(name);
    }

    public static List<String> getRequestHeaders(String name) {
        return getRequestHeaders(getHttpServletRequest(), name);
    }

    public static List<String> getRequestHeaders(HttpServletRequest httpServletRequest, String name) {
        List<String> headers = new ArrayList<String>();
        Enumeration<String> enumeration = httpServletRequest.getHeaders(name);
        while (enumeration.hasMoreElements()) {
            headers.add(enumeration.nextElement());
        }
        return headers;
    }

    public static Map<String, List<String>> getRequestHeaders() {
        return getRequestHeaders(getHttpServletRequest());
    }

    public static Map<String, List<String>> getRequestHeaders(HttpServletRequest httpServletRequest) {
        Map<String, List<String>> headers = new HashMap<String, List<String>>();
        Enumeration<String> headerNames = httpServletRequest.getHeaderNames();
        while (headerNames.hasMoreElements()) {
            String name = headerNames.nextElement();
            headers.put(name, getRequestHeaders(httpServletRequest, name));
        }
        return headers;
    }

    public static List<Cookie> getCookies() {
        return getCookies(getHttpServletRequest());
    }

    public static List<Cookie> getCookies(HttpServletRequest httpServletRequest) {
        List<Cookie> cookieList = new ArrayList<Cookie>();
        Cookie[] cookies = httpServletRequest.getCookies();
        if (ArrayUtils.isEmpty(cookies)) {
            return cookieList;
        }
        for (Cookie cookie : cookies) {
            cookieList.add(cookie);
        }
        return cookieList;
    }

    public static Cookie getCookie(String name) {
        return getCookie(getHttpServletRequest(), name);
    }

    public static Cookie getCookie(HttpServletRequest httpServletRequest, String name) {
        Cookie[] cookies = httpServletRequest.getCookies();
        if (ArrayUtils.isEmpty(cookies)) {
            return null;
        }
        for (Cookie cookie : cookies) {
            if (name.equals(cookie.getName())) {
                return cookie;
            }
        }
        return null;
    }

    public static String getResponseHeader(String name) {
        return getResponseHeader(getHttpServletResponse(), name);
    }

    public static String getResponseHeader(HttpServletResponse httpServletResponse, String name) {
        return httpServletResponse.getHeader(name);
    }

    public static List<String> getResponseHeaders(String name) {
        return getResponseHeaders(getHttpServletResponse(), name);
    }

    public static List<String> getResponseHeaders(HttpServletResponse httpServletResponse, String name) {
        List<String> responseHeaders = new ArrayList<String>();

        Collection<String> headers = httpServletResponse.getHeaders(name);
        for (String header : headers) {
            responseHeaders.add(header);
        }
        return responseHeaders;
    }

    public static Map<String, List<String>> getResponseHeaders() {
        return getResponseHeaders(getHttpServletResponse());
    }

    public static Map<String, List<String>> getResponseHeaders(HttpServletResponse httpServletResponse) {
        Map<String, List<String>> responseHeaders = new HashMap<String, List<String>>();
        Collection<String> names = httpServletResponse.getHeaderNames();
        for (String name : names) {
            responseHeaders.put(name, getResponseHeaders(name));
        }
        return responseHeaders;
    }

    public static String today() {
        return new SimpleDateFormat("yyyyMMdd").format(new Date());
    }

    public static HandlerMethod obtainHandlerMethod() {
        return (HandlerMethod) getHttpServletRequest().getAttribute(Constants.HANDLER_METHOD);
    }

    public static <T> T obtainSessionAttribute(String name) {
        return obtainSessionAttribute(getHttpSession(), name);
    }

    public static <T> T obtainSessionAttribute(HttpSession httpSession, String name) {
        return (T) httpSession.getAttribute(name);
    }

    public static Map<String, Object> obtainAllSessionAttributes() {
        return obtainAllSessionAttributes(getHttpSession());
    }

    public static Map<String, Object> obtainAllSessionAttributes(HttpSession httpSession) {
        Enumeration<String> attributeNames = httpSession.getAttributeNames();
        Map<String, Object> attributesMap = new HashMap<String, Object>();
        while (attributeNames.hasMoreElements()) {
            String attributeName = attributeNames.nextElement();
            attributesMap.put(attributeName, httpSession.getAttribute(attributeName));
        }
        return attributesMap;
    }

    public static void setSessionAttribute(String name, String value) {
        setSessionAttribute(getHttpSession(), name, value);
    }

    public static void setSessionAttribute(HttpSession httpSession, String name, String value) {
        httpSession.setAttribute(name, value);
    }

    public static void setSessionAttributes(Map<String, Object> attributes) {
        setSessionAttributes(getHttpSession(), attributes);
    }

    public static void setSessionAttributes(HttpSession httpSession, Map<String, Object> attributes) {
        for (Map.Entry<String, Object> attribute : attributes.entrySet()) {
            httpSession.setAttribute(attribute.getKey(), attribute.getValue());
        }
    }

    public static String callMethod(MethodCaller methodCaller, String errorMessage, Map<String, String> requestParameters) {
        String controllerClassName = null;
        String actionMethodName = null;
        HandlerMethod handlerMethod = obtainHandlerMethod();
        if (handlerMethod != null) {
            controllerClassName = handlerMethod.getBeanType().getName();
            actionMethodName = handlerMethod.getMethod().getName();
        }
        return callMethod(methodCaller, errorMessage, controllerClassName, actionMethodName, requestParameters);
    }

    public static String callMethod(MethodCaller methodCaller, String errorMessage, String className, String methodName, Map<String, String> requestParameters) {
        ApiRest apiRest = null;
        try {
            apiRest = methodCaller.call();
        } catch (Exception e) {
            LogUtils.error(errorMessage, className, methodName, e, requestParameters);
            apiRest = new ApiRest(e);
        }
        return GsonUtils.toJson(apiRest);
    }

    public static <T> T clone(Class<T> beanClass, Object originalBean) {
        try {
            T t = beanClass.newInstance();
            BeanUtils.copyProperties(t, originalBean);
            return t;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static Map<String, Object> toMap(Object object) {
        Map<String, Object> map = new LinkedHashMap<String, Object>();
        for (Class<?> clazz = object.getClass(); clazz != Object.class; clazz = clazz.getSuperclass()) {
            Field[] fields = clazz.getDeclaredFields();
            for (Field field : fields) {
                int modifiers = field.getModifiers();
                if (Modifier.isStatic(modifiers) || Modifier.isFinal(modifiers) || Modifier.isNative(modifiers)) {
                    continue;
                }
                ReflectionUtils.makeAccessible(field);
                map.put(field.getName(), ReflectionUtils.getField(field, object));
            }
        }
        return map;
    }

    public static <T> T buildObject(Class<T> beanClass, Map<String, Object> properties) {
        try {
            T t = beanClass.newInstance();
            BeanInfo beanInfo = Introspector.getBeanInfo(beanClass);
            PropertyDescriptor[] propertyDescriptors = beanInfo.getPropertyDescriptors();
            for (PropertyDescriptor propertyDescriptor : propertyDescriptors) {
                Method writeMethod = propertyDescriptor.getWriteMethod();
                if (writeMethod == null) {
                    continue;
                }
                ReflectionUtils.makeAccessible(writeMethod);
                Object value = properties.get(propertyDescriptor.getName());
                if (value == null && ArrayUtils.contains(Constants.BASIC_DATA_TYPES, propertyDescriptor.getPropertyType())) {
                    continue;
                }

                ReflectionUtils.invokeMethod(writeMethod, t, value);
            }
            return t;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static BigInteger obtainUserId() {
        CustomUserDetails customUserDetails = WebSecurityUtils.obtainCustomUserDetails();
        SystemUser systemUser = customUserDetails.getSystemUser();
        return systemUser.getId();
    }

    public static BigInteger obtainTenantId() {
        CustomUserDetails customUserDetails = WebSecurityUtils.obtainCustomUserDetails();
        Tenant tenant = customUserDetails.getTenant();
        return tenant.getId();
    }

    public static String obtainTenantCode() {
        CustomUserDetails customUserDetails = WebSecurityUtils.obtainCustomUserDetails();
        Tenant tenant = customUserDetails.getTenant();
        return tenant.getCode();
    }

    public static BigInteger obtainBranchId() {
        CustomUserDetails customUserDetails = WebSecurityUtils.obtainCustomUserDetails();
        Map<String, Object> branchInfo = customUserDetails.getBranchInfo();
        return BigInteger.valueOf(MapUtils.getLongValue(branchInfo, "id"));
    }

    public static String obtainBranchCode() {
        CustomUserDetails customUserDetails = WebSecurityUtils.obtainCustomUserDetails();
        Map<String, Object> branchInfo = customUserDetails.getBranchInfo();
        return MapUtils.getString(branchInfo, "code");
    }

    public static String obtainPublicKey() {
        CustomUserDetails customUserDetails = WebSecurityUtils.obtainCustomUserDetails();
        return customUserDetails.getPublicKey();
    }

    public static String obtainPrivateKey() {
        CustomUserDetails customUserDetails = WebSecurityUtils.obtainCustomUserDetails();
        return customUserDetails.getPrivateKey();
    }
}