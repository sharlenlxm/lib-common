package build.dream.common.models.miya;

import build.dream.common.models.BasicModel;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotNull;

public class OrderQueryModel extends BasicModel {
    @NotNull
    private String tenantId;

    @NotNull
    private String branchId;

    @NotNull
    @Length(max = 10)
    private String a4;

    @NotNull
    @Length(max = 20)
    private String a5;

    @NotNull
    @Length(max = 32)
    private String b1;

    public String getTenantId() {
        return tenantId;
    }

    public void setTenantId(String tenantId) {
        this.tenantId = tenantId;
    }

    public String getBranchId() {
        return branchId;
    }

    public void setBranchId(String branchId) {
        this.branchId = branchId;
    }

    public String getA4() {
        return a4;
    }

    public void setA4(String a4) {
        this.a4 = a4;
    }

    public String getA5() {
        return a5;
    }

    public void setA5(String a5) {
        this.a5 = a5;
    }

    public String getB1() {
        return b1;
    }

    public void setB1(String b1) {
        this.b1 = b1;
    }

    public static class Builder {
        private final OrderQueryModel instance = new OrderQueryModel();

        public Builder tenantId(String tenantId) {
            instance.setTenantId(tenantId);
            return this;
        }

        public Builder branchId(String branchId) {
            instance.setBranchId(branchId);
            return this;
        }

        public Builder a4(String a4) {
            instance.setA4(a4);
            return this;
        }

        public Builder a5(String a5) {
            instance.setA5(a5);
            return this;
        }

        public Builder b1(String b1) {
            instance.setB1(b1);
            return this;
        }

        public OrderQueryModel build() {
            OrderQueryModel orderQueryModel = new OrderQueryModel();
            orderQueryModel.setTenantId(instance.getTenantId());
            orderQueryModel.setBranchId(instance.getBranchId());
            orderQueryModel.setA4(instance.getA4());
            orderQueryModel.setA5(instance.getA5());
            orderQueryModel.setB1(instance.getB1());
            return orderQueryModel;
        }
    }

    public static Builder builder() {
        return new Builder();
    }
}
