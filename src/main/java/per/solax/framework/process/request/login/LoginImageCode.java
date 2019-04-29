package per.solax.framework.process.request.login;

import org.apache.http.client.methods.CloseableHttpResponse;
import per.solax.assist.base.url.UrlBaseEntity;
import per.solax.assist.base.url.Url;
import per.solax.assist.base.SystemConstants;
import per.solax.framework.entity.RequestEntity;
import per.solax.framework.process.AbstractRequestProcess;
import per.solax.assist.util.FileUtil;
import per.solax.assist.util.HttpParams;
import per.solax.assist.util.HttpUtil;
import per.solax.assist.util.Log;
import per.solax.assist.verificationCode.Ruokuai;
import per.solax.framework.process.request.common.ImageCode;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @Author: solax
 * @Date: 2019/1/14
 */
public class LoginImageCode extends AbstractRequestProcess {

    public LoginImageCode(RequestEntity requestEntity) {
        super(requestEntity, Url.loginImageCode);
    }

    public LoginImageCode(RequestEntity requestEntity, UrlBaseEntity urlEntity) {
        super(requestEntity);
        this.urlEntity = urlEntity;
    }

    @Override
    protected void before() {

    }

    @Override
    protected void send() {
        Map httpParams = new HashMap();
        httpParams.put("login_site", "E");
        httpParams.put("module", "login");
        httpParams.put("rand", "sjrand");
        httpParams.put("a", String.valueOf(new Date().getTime()) + String.valueOf(Math.random())); // a rand code
        ImageCode imageCode = new ImageCode(httpParams, Url.loginImageCode);
        String position = imageCode.getAnswer();
    }

    @Override
    protected void after() {

    }


    private Boolean getVerificationCode () {
        Boolean result = false;
        HttpParams httpParams = new HttpParams();
        httpParams.add("login_site", "E");
        httpParams.add("module", "login");
        httpParams.add("rand", "sjrand");
        httpParams.add("a", String.valueOf(new Date().getTime()) + String.valueOf(Math.random())); // a rand code
        CloseableHttpResponse response = this.requestEntity.sessionHttpRequest.sendRequest(
                this.urlEntity,
                httpParams.build());
        byte [] array = HttpUtil.resultToByteArray(response);
        // 验证码获取判断
        if (this.check(array)) {
            return result;
        }
        // 打码平台
        result = this.validateCode(array);
        return result;
    }

    private Boolean check (byte [] array) {
        Boolean result = false;
        if (array == null) {
            Log.debug("获取验证码失败，未得到图片流数据");
            return result;
        }
        return result;
    }

    private Boolean validateCode (byte [] arrays) {
        Boolean result = false;
        try {
            String path = "./" + this.requestEntity.getAccount().getUsername();
            String fileName = String.valueOf(new Date().getTime()) + "tkcode.png";
            FileUtil.saveLocal(path, fileName, arrays);
        } catch (Exception e) {
            e.printStackTrace();
            Log.debug("图片本地存储出现错误");
            return result;
        }
        try {
            Ruokuai ruokuai = new Ruokuai(arrays);
            String pos = ruokuai.getAnswer();
            if (pos == null) {
                Log.debug("获取若快验证码错误");
                return result;
            }
            this.requestEntity.getResponseStore().setLoginImageCodePos(pos);
        } catch (Exception e) {
            e.printStackTrace();
            Log.debug("若快打码时出错");
            return result;
        }
        result = true;
        return result;
    }
}
