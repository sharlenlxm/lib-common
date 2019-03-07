package build.dream.common.models.alipay;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

public class AlipayFundCouponOrderDisburseModel extends AlipayBasicModel {
    @NotNull
    @Length(max = 64)
    @JsonProperty(value = "out_order_no")
    private String outOrderNo;

    @Length(max = 64)
    @JsonProperty(value = "deduct_auth_no")
    private String deductAuthNo;

    @Length(max = 64)
    @JsonProperty(value = "deduct_out_order_no")
    private String deductOutOrderNo;

    @NotNull
    @Length(max = 64)
    @JsonProperty(value = "out_request_no")
    private String outRequestNo;

    @NotNull
    @Length(max = 100)
    @JsonProperty(value = "order_title")
    private String orderTitle;

    @NotNull
    @DecimalMin(value = "0.01")
    @DecimalMax(value = "100000000.00")
    private BigDecimal amount;

    @Length(max = 32)
    @JsonProperty(value = "payee_user_id")
    private String payeeUserId;

    @Length(max = 100)
    @JsonProperty(value = "payee_logon_id")
    private String payeeLogonId;

    @Length(max = 5)
    @JsonProperty(value = "pay_timeout")
    private String payTimeout;

    @Length(max = 300)
    @JsonProperty(value = "extra_param")
    private String extraParam;

    public String getOutOrderNo() {
        return outOrderNo;
    }

    public void setOutOrderNo(String outOrderNo) {
        this.outOrderNo = outOrderNo;
    }

    public String getDeductAuthNo() {
        return deductAuthNo;
    }

    public void setDeductAuthNo(String deductAuthNo) {
        this.deductAuthNo = deductAuthNo;
    }

    public String getDeductOutOrderNo() {
        return deductOutOrderNo;
    }

    public void setDeductOutOrderNo(String deductOutOrderNo) {
        this.deductOutOrderNo = deductOutOrderNo;
    }

    public String getOutRequestNo() {
        return outRequestNo;
    }

    public void setOutRequestNo(String outRequestNo) {
        this.outRequestNo = outRequestNo;
    }

    public String getOrderTitle() {
        return orderTitle;
    }

    public void setOrderTitle(String orderTitle) {
        this.orderTitle = orderTitle;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public String getPayeeUserId() {
        return payeeUserId;
    }

    public void setPayeeUserId(String payeeUserId) {
        this.payeeUserId = payeeUserId;
    }

    public String getPayeeLogonId() {
        return payeeLogonId;
    }

    public void setPayeeLogonId(String payeeLogonId) {
        this.payeeLogonId = payeeLogonId;
    }

    public String getPayTimeout() {
        return payTimeout;
    }

    public void setPayTimeout(String payTimeout) {
        this.payTimeout = payTimeout;
    }

    public String getExtraParam() {
        return extraParam;
    }

    public void setExtraParam(String extraParam) {
        this.extraParam = extraParam;
    }

    public static class Builder {
        private final AlipayFundCouponOrderDisburseModel instance = new AlipayFundCouponOrderDisburseModel();

        public Builder tenantId(String tenantId) {
            instance.setTenantId(tenantId);
            return this;
        }

        public Builder branchId(String branchId) {
            instance.setBranchId(branchId);
            return this;
        }

        public Builder returnUrl(String returnUrl) {
            instance.setReturnUrl(returnUrl);
            return this;
        }

        public Builder notifyUrl(String notifyUrl) {
            instance.setNotifyUrl(notifyUrl);
            return this;
        }

        public Builder authToken(String authToken) {
            instance.setAuthToken(authToken);
            return this;
        }

        public Builder outOrderNo(String outOrderNo) {
            instance.setOutOrderNo(outOrderNo);
            return this;
        }

        public Builder deductAuthNo(String deductAuthNo) {
            instance.setDeductAuthNo(deductAuthNo);
            return this;
        }

        public Builder deductOutOrderNo(String deductOutOrderNo) {
            instance.setDeductOutOrderNo(deductOutOrderNo);
            return this;
        }

        public Builder outRequestNo(String outRequestNo) {
            instance.setOutRequestNo(outRequestNo);
            return this;
        }

        public Builder orderTitle(String orderTitle) {
            instance.setOrderTitle(orderTitle);
            return this;
        }

        public Builder amount(BigDecimal amount) {
            instance.setAmount(amount);
            return this;
        }

        public Builder payeeUserId(String payeeUserId) {
            instance.setPayeeUserId(payeeUserId);
            return this;
        }

        public Builder payeeLogonId(String payeeLogonId) {
            instance.setPayeeLogonId(payeeLogonId);
            return this;
        }

        public Builder payTimeout(String payTimeout) {
            instance.setPayTimeout(payTimeout);
            return this;
        }

        public Builder extraParam(String extraParam) {
            instance.setExtraParam(extraParam);
            return this;
        }

        public AlipayFundCouponOrderDisburseModel build() {
            AlipayFundCouponOrderDisburseModel alipayFundCouponOrderDisburseModel = new AlipayFundCouponOrderDisburseModel();
            alipayFundCouponOrderDisburseModel.setTenantId(instance.getTenantId());
            alipayFundCouponOrderDisburseModel.setBranchId(instance.getBranchId());
            alipayFundCouponOrderDisburseModel.setOutOrderNo(instance.getOutOrderNo());
            alipayFundCouponOrderDisburseModel.setDeductAuthNo(instance.getDeductAuthNo());
            alipayFundCouponOrderDisburseModel.setDeductOutOrderNo(instance.getDeductOutOrderNo());
            alipayFundCouponOrderDisburseModel.setOutRequestNo(instance.getOutRequestNo());
            alipayFundCouponOrderDisburseModel.setOrderTitle(instance.getOrderTitle());
            alipayFundCouponOrderDisburseModel.setAmount(instance.getAmount());
            alipayFundCouponOrderDisburseModel.setPayeeUserId(instance.getPayeeUserId());
            alipayFundCouponOrderDisburseModel.setPayeeLogonId(instance.getPayeeLogonId());
            alipayFundCouponOrderDisburseModel.setPayTimeout(instance.getPayTimeout());
            alipayFundCouponOrderDisburseModel.setExtraParam(instance.getExtraParam());
            return alipayFundCouponOrderDisburseModel;
        }
    }

    public static Builder builder() {
        return new Builder();
    }
}
