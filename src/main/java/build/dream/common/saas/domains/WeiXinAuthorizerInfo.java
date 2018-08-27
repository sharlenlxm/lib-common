package build.dream.common.saas.domains;

import build.dream.common.basic.BasicDomain;

import java.math.BigInteger;
import java.util.Date;

public class WeiXinAuthorizerInfo extends BasicDomain {
    /**
     * 商户ID
     */
    private BigInteger tenantId;
    /**
     * 类型，1-公众号，2-小程序
     */
    private Integer authorizerType;
    /**
     * 授权方昵称
     */
    private String nickName;
    /**
     * 授权方头像
     */
    private String headImg;
    /**
     * 授权方公众号类型，0代表订阅号，1代表由历史老帐号升级后的订阅号，2代表服务号
     */
    private String serviceTypeInfo;
    /**
     * 授权方认证类型，-1代表未认证，0代表微信认证，1代表新浪微博认证，2代表腾讯微博认证，3代表已资质认证通过但还未通过名称认证，4代表已资质认证通过、还未通过名称认证，但通过了新浪微博认证，5代表已资质认证通过、还未通过名称认证，但通过了腾讯微博认证
     */
    private String verifyTypeInfo;
    /**
     * 授权方公众号的原始ID
     */
    private String originalId;
    /**
     * 公众号的主体名称
     */
    private String principalName;
    /**
     * 授权方公众号所设置的微信号，可能为空
     */
    private String alias;
    /**
     * 用以了解以下功能的开通状况（0代表未开通，1代表已开通）： open_store:是否开通微信门店功能 open_scan:是否开通微信扫商品功能 open_pay:是否开通微信支付功能 open_card:是否开通微信卡券功能 open_shake:是否开通微信摇一摇功能
     */
    private String businessInfo;
    /**
     * 二维码图片的URL
     */
    private String qrcodeUrl;
    /**
     * 帐号介绍
     */
    private String signature;
    /**
     * 小程序信息
     */
    private String miniProgramInfo;
    /**
     * app id
     */
    private String authorizationAppId;
    /**
     * 公众号授权给开发者的权限集列表，ID为1到15时分别代表： 1.消息管理权限 2.用户管理权限 3.帐号服务权限 4.网页服务权限 5.微信小店权限 6.微信多客服权限 7.群发与通知权限 8.微信卡券权限 9.微信扫一扫权限 10.微信连WIFI权限 11.素材管理权限 12.微信摇周边权限 13.微信门店权限 14.微信支付权限 15.自定义菜单权限 请注意： 1）该字段的返回不会考虑公众号是否具备该权限集的权限（因为可能部分具备），请根据公众号的帐号类型和认证情况，来判断公众号的接口权限。
     */
    private String funcInfo;

    public BigInteger getTenantId() {
        return tenantId;
    }

    public void setTenantId(BigInteger tenantId) {
        this.tenantId = tenantId;
    }

    public Integer getAuthorizerType() {
        return authorizerType;
    }

    public void setAuthorizerType(Integer authorizerType) {
        this.authorizerType = authorizerType;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getHeadImg() {
        return headImg;
    }

    public void setHeadImg(String headImg) {
        this.headImg = headImg;
    }

    public String getServiceTypeInfo() {
        return serviceTypeInfo;
    }

    public void setServiceTypeInfo(String serviceTypeInfo) {
        this.serviceTypeInfo = serviceTypeInfo;
    }

    public String getVerifyTypeInfo() {
        return verifyTypeInfo;
    }

    public void setVerifyTypeInfo(String verifyTypeInfo) {
        this.verifyTypeInfo = verifyTypeInfo;
    }

    public String getOriginalId() {
        return originalId;
    }

    public void setOriginalId(String originalId) {
        this.originalId = originalId;
    }

    public String getPrincipalName() {
        return principalName;
    }

    public void setPrincipalName(String principalName) {
        this.principalName = principalName;
    }

    public String getAlias() {
        return alias;
    }

    public void setAlias(String alias) {
        this.alias = alias;
    }

    public String getBusinessInfo() {
        return businessInfo;
    }

    public void setBusinessInfo(String businessInfo) {
        this.businessInfo = businessInfo;
    }

    public String getQrcodeUrl() {
        return qrcodeUrl;
    }

    public void setQrcodeUrl(String qrcodeUrl) {
        this.qrcodeUrl = qrcodeUrl;
    }

    public String getSignature() {
        return signature;
    }

    public void setSignature(String signature) {
        this.signature = signature;
    }

    public String getMiniProgramInfo() {
        return miniProgramInfo;
    }

    public void setMiniProgramInfo(String miniProgramInfo) {
        this.miniProgramInfo = miniProgramInfo;
    }

    public String getAuthorizationAppId() {
        return authorizationAppId;
    }

    public void setAuthorizationAppId(String authorizationAppId) {
        this.authorizationAppId = authorizationAppId;
    }

    public String getFuncInfo() {
        return funcInfo;
    }

    public void setFuncInfo(String funcInfo) {
        this.funcInfo = funcInfo;
    }

    public static class Builder {
        private final WeiXinAuthorizerInfo instance = new WeiXinAuthorizerInfo();

        public Builder tenantId(BigInteger tenantId) {
            instance.setTenantId(tenantId);
            return this;
        }

        public Builder authorizerType(Integer authorizerType) {
            instance.setAuthorizerType(authorizerType);
            return this;
        }

        public Builder nickName(String nickName) {
            instance.setNickName(nickName);
            return this;
        }

        public Builder headImg(String headImg) {
            instance.setHeadImg(headImg);
            return this;
        }

        public Builder serviceTypeInfo(String serviceTypeInfo) {
            instance.setServiceTypeInfo(serviceTypeInfo);
            return this;
        }

        public Builder verifyTypeInfo(String verifyTypeInfo) {
            instance.setVerifyTypeInfo(verifyTypeInfo);
            return this;
        }

        public Builder originalId(String originalId) {
            instance.setOriginalId(originalId);
            return this;
        }

        public Builder principalName(String principalName) {
            instance.setPrincipalName(principalName);
            return this;
        }

        public Builder alias(String alias) {
            instance.setAlias(alias);
            return this;
        }

        public Builder businessInfo(String businessInfo) {
            instance.setBusinessInfo(businessInfo);
            return this;
        }

        public Builder qrcodeUrl(String qrcodeUrl) {
            instance.setQrcodeUrl(qrcodeUrl);
            return this;
        }

        public Builder signature(String signature) {
            instance.setSignature(signature);
            return this;
        }

        public Builder miniProgramInfo(String miniProgramInfo) {
            instance.setMiniProgramInfo(miniProgramInfo);
            return this;
        }

        public Builder authorizationAppId(String authorizationAppId) {
            instance.setAuthorizationAppId(authorizationAppId);
            return this;
        }

        public Builder funcInfo(String funcInfo) {
            instance.setFuncInfo(funcInfo);
            return this;
        }

        public Builder id(BigInteger id) {
            instance.setId(id);
            return this;
        }

        public Builder createTime(Date createTime) {
            instance.setCreateTime(createTime);
            return this;
        }

        public Builder createUserId(BigInteger createUserId) {
            instance.setCreateUserId(createUserId);
            return this;
        }

        public Builder lastUpdateTime(Date lastUpdateTime) {
            instance.setLastUpdateTime(lastUpdateTime);
            return this;
        }

        public Builder lastUpdateUserId(BigInteger lastUpdateUserId) {
            instance.setLastUpdateUserId(lastUpdateUserId);
            return this;
        }

        public Builder lastUpdateRemark(String lastUpdateRemark) {
            instance.setLastUpdateRemark(lastUpdateRemark);
            return this;
        }

        public Builder deleteTime(Date deleteTime) {
            instance.setDeleteTime(deleteTime);
            return this;
        }

        public Builder deleted(boolean deleted) {
            instance.setDeleted(deleted);
            return this;
        }

        public WeiXinAuthorizerInfo build() {
            return instance;
        }
    }

    public static Builder builder() {
        return new Builder();
    }
}
