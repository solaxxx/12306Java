package per.solax.gradle;

import per.solax.assist.base.Global;
import per.solax.framework.in.AbstractInitialize;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author: solax
 * @Date: 2019/1/10
 */
public class Initialize extends AbstractInitialize {

    @Override
    protected void setConfig() {
       // this.setUserList();
    }

    @Override
    public Map<String, Object> getConfig() {
        return this.config;
    }

    private void setUserList () {
        Map accountMap = new HashMap();
        accountMap.put("kamiao007007", "binghe64");
        accountMap.put("kamiao00701", "binghe64");
        if (this.config.get("accountList") == null) {
            this.config.put("accountList", accountMap);
        }
    }
}
