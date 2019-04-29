package per.solax.framework.entity;

import java.util.Map;

/**
 * @Author: solax
 * @Date: 2019/1/17
 */
public class ResponseStore {

    Map resultMap;

    String tk;

    // 登录时的验证码
    String loginImageCodePos;


    public String getTk() {
        return tk;
    }

    public void setTk(String tk) {
        this.tk = tk;
    }

    public String getLoginImageCodePos() {
        return loginImageCodePos;
    }

    public void setLoginImageCodePos(String loginImageCodePos) {
        this.loginImageCodePos = loginImageCodePos;
    }

    public Map getResultMap() {
        return resultMap;
    }

    public void setResultMap(Map resultMap) {
        this.resultMap = resultMap;
    }
}
