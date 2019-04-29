package per.solax.assist.base;

import per.solax.assist.util.Log;
import per.solax.assist.util.YamlReader;

/**
 * @Author: solax
 * @Date: 2019/1/15
 * global params
 *
 */
public class Global {

    private YamlReader application;

    private static Global global;

    private Global () {}

    public static Global getInstance () {
        if (global == null) {
            global = new Global();
        }
        return global;
    }


    public String getRuokuaiPassword () throws Exception {
        Object obj = application.getValueByKey("ruokuai", "password");
        if (obj == null) {
            Log.debug("未找到若快密码，需要在application.yaml中配置若快密码");
            throw new Exception();
        }
        return (String)obj;
    }

    public String getRuokuaiUserName () throws Exception {
        Object obj = application.getValueByKey("ruokuai", "username");
        if (obj == null) {
            Log.debug("未找到若快账号，需要在application.yaml中配置若快账号");
            throw new Exception();
        }
        return (String)obj;
    }

    public YamlReader getApplication() {
        return application;
    }

    public void setApplication(YamlReader application) {
        this.application = application;
    }
}
