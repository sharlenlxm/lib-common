package build.dream.common.models.jddj;

import build.dream.common.constants.Constants;
import build.dream.common.models.BasicModel;
import build.dream.common.utils.ObjectUtils;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.validation.constraints.NotNull;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.text.SimpleDateFormat;
import java.util.Date;

public class JDDJBasicModel extends BasicModel {
    @NotNull
    @JsonIgnore
    private String venderId;

    @NotNull
    @JsonIgnore
    private String appKey;

    @NotNull
    @JsonIgnore
    private String appSecret;

    @NotNull
    @JsonIgnore
    private String timestamp = new SimpleDateFormat(Constants.DEFAULT_DATE_PATTERN).format(new Date());

    @NotNull
    @JsonIgnore
    private String format = "json";

    @NotNull
    @JsonIgnore
    private String v = "1.0";

    public String getVenderId() {
        return venderId;
    }

    public void setVenderId(String venderId) {
        this.venderId = venderId;
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

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public String getFormat() {
        return format;
    }

    public void setFormat(String format) {
        this.format = format;
    }

    public String getV() {
        return v;
    }

    public void setV(String v) {
        this.v = v;
    }

    protected abstract static class Builder<BT extends Builder<BT, MT>, MT extends JDDJBasicModel> {
        protected MT instance;
        private Class<MT> modelClass;

        public Builder() {
            Type type = this.getClass().getGenericSuperclass();
            ParameterizedType parameterizedType = (ParameterizedType) type;
            Type[] actualTypeArguments = parameterizedType.getActualTypeArguments();
            modelClass = (Class<MT>) actualTypeArguments[1];
            instance = ObjectUtils.newInstance(modelClass);
        }

        public BT venderId(String venderId) {
            instance.setVenderId(venderId);
            return (BT) this;
        }

        public BT appKey(String appKey) {
            instance.setAppKey(appKey);
            return (BT) this;
        }

        public BT appSecret(String appSecret) {
            instance.setAppSecret(appSecret);
            return (BT) this;
        }

        public BT timestamp(String timestamp) {
            instance.setTimestamp(timestamp);
            return (BT) this;
        }

        public BT format(String format) {
            instance.setFormat(format);
            return (BT) this;
        }

        public BT v(String v) {
            instance.setV(v);
            return (BT) this;
        }

        public MT build() {
            MT model = ObjectUtils.newInstance(modelClass);
            model.setVenderId(instance.getVenderId());
            model.setAppKey(instance.getAppKey());
            model.setAppSecret(instance.getAppSecret());
            model.setTimestamp(instance.getTimestamp());
            model.setFormat(instance.getFormat());
            model.setV(instance.getV());
            return model;
        }
    }
}
