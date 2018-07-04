package build.dream.common.saas.domains;

import build.dream.common.basic.BasicDomain;
import build.dream.common.constants.Constants;

public class GoodsType extends BasicDomain {
    /**
     * 产品类型名称
     */
    private String name;
    /**
     * 描述
     */
    private String description = Constants.VARCHAR_DEFAULT_VALUE;
    /**
     * 该类型下是否只能有一个产品
     */
    private boolean single;
    /**
     * 续费时执行的sql
     */
    private String renewSql = Constants.VARCHAR_DEFAULT_VALUE;
    /**
     * 过期时执行的sql
     */
    private String disableSql = Constants.VARCHAR_DEFAULT_VALUE;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isSingle() {
        return single;
    }

    public void setSingle(boolean single) {
        this.single = single;
    }

    public String getRenewSql() {
        return renewSql;
    }

    public void setRenewSql(String renewSql) {
        this.renewSql = renewSql;
    }

    public String getDisableSql() {
        return disableSql;
    }

    public void setDisableSql(String disableSql) {
        this.disableSql = disableSql;
    }
}
