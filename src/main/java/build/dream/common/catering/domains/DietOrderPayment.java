package build.dream.common.catering.domains;

import build.dream.common.basic.BasicDomain;
import build.dream.common.constants.Constants;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Date;

public class DietOrderPayment extends BasicDomain {
    /**
     * 商户ID
     */
    private BigInteger tenantId;
    /**
     * 商户编码
     */
    private String tenantCode;
    /**
     * 门店ID
     */
    private BigInteger branchId;
    /**
     * diet_order.id
     */
    private BigInteger dietOrderId;
    /**
     * 支付方式id
     */
    private BigInteger paymentId;
    /**
     * 支付方式编码
     */
    private String paymentCode;
    /**
     * 支付方式名称
     */
    private String paymentName;
    /**
     * 支付金额
     */
    private BigDecimal paidAmount;
    /**
     * 发生时间
     */
    private Date occurrenceTime;
    /**
     * 扩展信息，用于保存储值支付的兑换比例，微信支付、支付宝支付的支付场景
     */
    private String extraInfo = Constants.VARCHAR_DEFAULT_VALUE;
    /**
     * 本地ID
     */
    private String localId = Constants.VARCHAR_DEFAULT_VALUE;
    /**
     * 本地订单ID
     */
    private String localDietOrderId = Constants.VARCHAR_DEFAULT_VALUE;
    /**
     * 本地创建时间
     */
    private Date localCreateTime = Constants.DATETIME_DEFAULT_VALUE;
    /**
     * 本地最后更新时间
     */
    private Date localLastUpdateTime = Constants.DATETIME_DEFAULT_VALUE;

    public BigInteger getTenantId() {
        return tenantId;
    }

    public void setTenantId(BigInteger tenantId) {
        this.tenantId = tenantId;
    }

    public String getTenantCode() {
        return tenantCode;
    }

    public void setTenantCode(String tenantCode) {
        this.tenantCode = tenantCode;
    }

    public BigInteger getBranchId() {
        return branchId;
    }

    public void setBranchId(BigInteger branchId) {
        this.branchId = branchId;
    }

    public BigInteger getDietOrderId() {
        return dietOrderId;
    }

    public void setDietOrderId(BigInteger dietOrderId) {
        this.dietOrderId = dietOrderId;
    }

    public BigInteger getPaymentId() {
        return paymentId;
    }

    public void setPaymentId(BigInteger paymentId) {
        this.paymentId = paymentId;
    }

    public String getPaymentCode() {
        return paymentCode;
    }

    public void setPaymentCode(String paymentCode) {
        this.paymentCode = paymentCode;
    }

    public String getPaymentName() {
        return paymentName;
    }

    public void setPaymentName(String paymentName) {
        this.paymentName = paymentName;
    }

    public BigDecimal getPaidAmount() {
        return paidAmount;
    }

    public void setPaidAmount(BigDecimal paidAmount) {
        this.paidAmount = paidAmount;
    }

    public Date getOccurrenceTime() {
        return occurrenceTime;
    }

    public void setOccurrenceTime(Date occurrenceTime) {
        this.occurrenceTime = occurrenceTime;
    }

    public String getExtraInfo() {
        return extraInfo;
    }

    public void setExtraInfo(String extraInfo) {
        this.extraInfo = extraInfo;
    }

    public String getLocalId() {
        return localId;
    }

    public void setLocalId(String localId) {
        this.localId = localId;
    }

    public String getLocalDietOrderId() {
        return localDietOrderId;
    }

    public void setLocalDietOrderId(String localDietOrderId) {
        this.localDietOrderId = localDietOrderId;
    }

    public Date getLocalCreateTime() {
        return localCreateTime;
    }

    public void setLocalCreateTime(Date localCreateTime) {
        this.localCreateTime = localCreateTime;
    }

    public Date getLocalLastUpdateTime() {
        return localLastUpdateTime;
    }

    public void setLocalLastUpdateTime(Date localLastUpdateTime) {
        this.localLastUpdateTime = localLastUpdateTime;
    }

    public static class Builder {
        private final DietOrderPayment instance = new DietOrderPayment();

        public Builder tenantId(BigInteger tenantId) {
            instance.setTenantId(tenantId);
            return this;
        }

        public Builder tenantCode(String tenantCode) {
            instance.setTenantCode(tenantCode);
            return this;
        }

        public Builder branchId(BigInteger branchId) {
            instance.setBranchId(branchId);
            return this;
        }

        public Builder dietOrderId(BigInteger dietOrderId) {
            instance.setDietOrderId(dietOrderId);
            return this;
        }

        public Builder paymentId(BigInteger paymentId) {
            instance.setPaymentId(paymentId);
            return this;
        }

        public Builder paymentCode(String paymentCode) {
            instance.setPaymentCode(paymentCode);
            return this;
        }

        public Builder paymentName(String paymentName) {
            instance.setPaymentName(paymentName);
            return this;
        }

        public Builder paidAmount(BigDecimal paidAmount) {
            instance.setPaidAmount(paidAmount);
            return this;
        }

        public Builder occurrenceTime(Date occurrenceTime) {
            instance.setOccurrenceTime(occurrenceTime);
            return this;
        }

        public Builder extraInfo(String extraInfo) {
            instance.setExtraInfo(extraInfo);
            return this;
        }

        public Builder localId(String localId) {
            instance.setLocalId(localId);
            return this;
        }

        public Builder localDietOrderId(String localDietOrderId) {
            instance.setLocalDietOrderId(localDietOrderId);
            return this;
        }

        public Builder localCreateTime(Date localCreateTime) {
            instance.setLocalCreateTime(localCreateTime);
            return this;
        }

        public Builder localLastUpdateTime(Date localLastUpdateTime) {
            instance.setLocalLastUpdateTime(localLastUpdateTime);
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

        public Builder deleted(boolean deleted) {
            instance.setDeleted(deleted);
            return this;
        }

        public DietOrderPayment build() {
            return instance;
        }
    }

    public static Builder builder() {
        return new Builder();
    }

    public static final class ColumnName extends BasicDomain.ColumnName {
        public static final String TENANT_ID = "tenant_id";
        public static final String TENANT_CODE = "tenant_code";
        public static final String BRANCH_ID = "branch_id";
        public static final String DIET_ORDER_ID = "diet_order_id";
        public static final String PAYMENT_ID = "payment_id";
        public static final String PAYMENT_CODE = "payment_code";
        public static final String PAYMENT_NAME = "payment_name";
        public static final String PAID_AMOUNT = "paid_amount";
        public static final String OCCURRENCE_TIME = "occurrence_time";
        public static final String EXTRA_INFO = "extra_info";
        public static final String LOCAL_ID = "local_id";
        public static final String LOCAL_DIET_ORDER_ID = "local_diet_order_id";
        public static final String LOCAL_CREATE_TIME = "local_create_time";
        public static final String LOCAL_LAST_UPDATE_TIME = "local_last_update_time";
    }

    public static final class FieldName extends BasicDomain.FieldName {
        public static final String TENANT_ID = "tenantId";
        public static final String TENANT_CODE = "tenantCode";
        public static final String BRANCH_ID = "branchId";
        public static final String DIET_ORDER_ID = "dietOrderId";
        public static final String PAYMENT_ID = "paymentId";
        public static final String PAYMENT_CODE = "paymentCode";
        public static final String PAYMENT_NAME = "paymentName";
        public static final String PAID_AMOUNT = "paidAmount";
        public static final String OCCURRENCE_TIME = "occurrenceTime";
        public static final String EXTRA_INFO = "extraInfo";
        public static final String LOCAL_ID = "localId";
        public static final String LOCAL_DIET_ORDER_ID = "localDietOrderId";
        public static final String LOCAL_CREATE_TIME = "localCreateTime";
        public static final String LOCAL_LAST_UPDATE_TIME = "localLastUpdateTime";
    }
}
