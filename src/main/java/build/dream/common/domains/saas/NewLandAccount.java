package build.dream.common.domains.saas;

import build.dream.common.basic.BasicDomain;

import java.math.BigInteger;

public class NewLandAccount extends BasicDomain {
    public static final String TABLE_NAME = "new_land_account";
    /**
     * 商户ID
     */
    private BigInteger tenantId;
    /**
     * 门店ID
     */
    private BigInteger branchId;
    /**
     * 新大陆支付商户号
     */
    private String mercId;
    /**
     * 新大陆支付设备号
     */
    private String trmNo;
    /**
     * 新大陆支付机构号
     */
    private String orgNo;
    /**
     * 新大陆支付密钥
     */
    private String secretKey;

    public BigInteger getTenantId() {
        return tenantId;
    }

    public void setTenantId(BigInteger tenantId) {
        this.tenantId = tenantId;
    }

    public BigInteger getBranchId() {
        return branchId;
    }

    public void setBranchId(BigInteger branchId) {
        this.branchId = branchId;
    }

    public String getMercId() {
        return mercId;
    }

    public void setMercId(String mercId) {
        this.mercId = mercId;
    }

    public String getTrmNo() {
        return trmNo;
    }

    public void setTrmNo(String trmNo) {
        this.trmNo = trmNo;
    }

    public String getOrgNo() {
        return orgNo;
    }

    public void setOrgNo(String orgNo) {
        this.orgNo = orgNo;
    }

    public String getSecretKey() {
        return secretKey;
    }

    public void setSecretKey(String secretKey) {
        this.secretKey = secretKey;
    }

    public static class Builder extends BasicDomain.Builder<Builder, NewLandAccount> {
        public Builder tenantId(BigInteger tenantId) {
            instance.setTenantId(tenantId);
            return this;
        }

        public Builder branchId(BigInteger branchId) {
            instance.setBranchId(branchId);
            return this;
        }

        public Builder mercId(String mercId) {
            instance.setMercId(mercId);
            return this;
        }

        public Builder trmNo(String trmNo) {
            instance.setTrmNo(trmNo);
            return this;
        }

        public Builder orgNo(String orgNo) {
            instance.setOrgNo(orgNo);
            return this;
        }

        public Builder secretKey(String secretKey) {
            instance.setSecretKey(secretKey);
            return this;
        }

        @Override
        public NewLandAccount build() {
            NewLandAccount newLandAccount = super.build();
            newLandAccount.setTenantId(instance.getTenantId());
            newLandAccount.setBranchId(instance.getBranchId());
            newLandAccount.setMercId(instance.getMercId());
            newLandAccount.setTrmNo(instance.getTrmNo());
            newLandAccount.setOrgNo(instance.getOrgNo());
            newLandAccount.setSecretKey(instance.getSecretKey());
            return newLandAccount;
        }
    }

    public static Builder builder() {
        return new Builder();
    }

    public static final class ColumnName extends BasicDomain.ColumnName {
        public static final String TENANT_ID = "tenant_id";
        public static final String BRANCH_ID = "branch_id";
        public static final String MERC_ID = "merc_id";
        public static final String TRM_NO = "trm_no";
        public static final String ORG_NO = "org_no";
        public static final String SECRET_KEY = "secret_key";
    }

    public static final class FieldName extends BasicDomain.FieldName {
        public static final String TENANT_ID = "tenantId";
        public static final String BRANCH_ID = "branchId";
        public static final String MERC_ID = "mercId";
        public static final String TRM_NO = "trmNo";
        public static final String ORG_NO = "orgNo";
        public static final String SECRET_KEY = "secretKey";
    }
}