package build.dream.common.models.suning;

public class CommonParamsModel {
    private String appMethod;
    private String appRequestTime;
    private String format;
    private String appKey;
    private String appSecret;
    private String versionNo;
    private String signInfo;
    private String accessToken;

    public String getAppMethod() {
        return appMethod;
    }

    public void setAppMethod(String appMethod) {
        this.appMethod = appMethod;
    }

    public String getAppRequestTime() {
        return appRequestTime;
    }

    public void setAppRequestTime(String appRequestTime) {
        this.appRequestTime = appRequestTime;
    }

    public String getFormat() {
        return format;
    }

    public void setFormat(String format) {
        this.format = format;
    }

    public String getAppKey() {
        return appKey;
    }

    public void setAppKey(String appKey) {
        this.appKey = appKey;
    }

    public String getAppSecret() {
        return appSecret;
    }

    public void setAppSecret(String appSecret) {
        this.appSecret = appSecret;
    }

    public String getVersionNo() {
        return versionNo;
    }

    public void setVersionNo(String versionNo) {
        this.versionNo = versionNo;
    }

    public String getSignInfo() {
        return signInfo;
    }

    public void setSignInfo(String signInfo) {
        this.signInfo = signInfo;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public static class Builder {
        private final CommonParamsModel instance = new CommonParamsModel();

        public Builder appMethod(String appMethod) {
            instance.setAppMethod(appMethod);
            return this;
        }

        public Builder appRequestTime(String appRequestTime) {
            instance.setAppRequestTime(appRequestTime);
            return this;
        }

        public Builder format(String format) {
            instance.setFormat(format);
            return this;
        }

        public Builder appKey(String appKey) {
            instance.setAppKey(appKey);
            return this;
        }

        public Builder appSecret(String appSecret) {
            instance.setAppSecret(appSecret);
            return this;
        }

        public Builder versionNo(String versionNo) {
            instance.setVersionNo(versionNo);
            return this;
        }

        public Builder signInfo(String signInfo) {
            instance.setSignInfo(signInfo);
            return this;
        }

        public Builder accessToken(String accessToken) {
            instance.setAccessToken(accessToken);
            return this;
        }

        public CommonParamsModel build() {
            CommonParamsModel commonParamsModel = new CommonParamsModel();
            commonParamsModel.setAppMethod(instance.getAppMethod());
            commonParamsModel.setAppRequestTime(instance.getAppRequestTime());
            commonParamsModel.setFormat(instance.getFormat());
            commonParamsModel.setAppKey(instance.getAppKey());
            commonParamsModel.setAppSecret(instance.getAppSecret());
            commonParamsModel.setVersionNo(instance.getVersionNo());
            commonParamsModel.setSignInfo(instance.getSignInfo());
            commonParamsModel.setAccessToken(instance.getAccessToken());
            return commonParamsModel;
        }
    }

    public static Builder builder() {
        return new Builder();
    }
}