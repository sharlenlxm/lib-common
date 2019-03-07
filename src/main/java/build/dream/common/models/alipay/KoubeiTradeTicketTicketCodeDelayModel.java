package build.dream.common.models.alipay;

import build.dream.common.constraints.InList;
import build.dream.common.models.BasicModel;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotNull;

public class KoubeiTradeTicketTicketCodeDelayModel extends BasicModel {
    @NotNull
    @JsonIgnore
    private String tenantId;

    @NotNull
    @JsonIgnore
    private String branchId;

    @NotNull
    @Length(max = 32)
    @JsonProperty(value = "request_id")
    private String requestId;

    @NotNull
    @Length(min = 19, max = 19)
    @JsonProperty(value = "end_date")
    private String endDate;

    @NotNull
    @Length(max = 12)
    @JsonProperty(value = "ticket_code")
    private String ticketCode;

    @NotNull
    @InList(value = {"INTERNAL_CODE", "EXTERNAL_CODE"})
    @JsonProperty(value = "code_type")
    private String codeType;

    @NotNull
    @Length(max = 32)
    @JsonProperty(value = "order_no")
    private String orderNo;

    public String getTenantId() {
        return tenantId;
    }

    public void setTenantId(String tenantId) {
        this.tenantId = tenantId;
    }

    public void setBranchId(String branchId) {
        this.branchId = branchId;
    }

    public String getBranchId() {
        return branchId;
    }

    public String getRequestId() {
        return requestId;
    }

    public void setRequestId(String requestId) {
        this.requestId = requestId;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public String getTicketCode() {
        return ticketCode;
    }

    public void setTicketCode(String ticketCode) {
        this.ticketCode = ticketCode;
    }

    public String getCodeType() {
        return codeType;
    }

    public void setCodeType(String codeType) {
        this.codeType = codeType;
    }

    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }

    public static class Builder {
        private final KoubeiTradeTicketTicketCodeDelayModel instance = new KoubeiTradeTicketTicketCodeDelayModel();

        public Builder tenantId(String tenantId) {
            instance.setTenantId(tenantId);
            return this;
        }

        public Builder branchId(String branchId) {
            instance.setBranchId(branchId);
            return this;
        }

        public Builder requestId(String requestId) {
            instance.setRequestId(requestId);
            return this;
        }

        public Builder endDate(String endDate) {
            instance.setEndDate(endDate);
            return this;
        }

        public Builder ticketCode(String ticketCode) {
            instance.setTicketCode(ticketCode);
            return this;
        }

        public Builder codeType(String codeType) {
            instance.setCodeType(codeType);
            return this;
        }

        public Builder orderNo(String orderNo) {
            instance.setOrderNo(orderNo);
            return this;
        }

        public KoubeiTradeTicketTicketCodeDelayModel build() {
            KoubeiTradeTicketTicketCodeDelayModel koubeiTradeTicketTicketCodeDelayModel = new KoubeiTradeTicketTicketCodeDelayModel();
            koubeiTradeTicketTicketCodeDelayModel.setTenantId(instance.getTenantId());
            koubeiTradeTicketTicketCodeDelayModel.setBranchId(instance.getBranchId());
            koubeiTradeTicketTicketCodeDelayModel.setRequestId(instance.getRequestId());
            koubeiTradeTicketTicketCodeDelayModel.setEndDate(instance.getEndDate());
            koubeiTradeTicketTicketCodeDelayModel.setTicketCode(instance.getTicketCode());
            koubeiTradeTicketTicketCodeDelayModel.setCodeType(instance.getCodeType());
            koubeiTradeTicketTicketCodeDelayModel.setOrderNo(instance.getOrderNo());
            return koubeiTradeTicketTicketCodeDelayModel;
        }
    }

    public static Builder builder() {
        return new Builder();
    }
}