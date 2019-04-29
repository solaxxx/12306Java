package per.solax.framework.process.request.login;

import org.apache.http.client.methods.CloseableHttpResponse;
import per.solax.assist.base.url.Url;
import per.solax.framework.entity.RequestEntity;
import per.solax.framework.entity.ResponseStore;
import per.solax.framework.process.AbstractRequestProcess;
import per.solax.assist.util.HttpParams;
import per.solax.assist.util.HttpUtil;
import per.solax.assist.util.Log;

import java.util.Map;

/**
 * @Author: solax
 * @Date: 2019/1/17
 */
public class Login  extends AbstractRequestProcess {
    public Login(RequestEntity requestEntity) {
        super(requestEntity);
    }

    @Override
    protected void before() {

    }

    @Override
    protected void send() {
        HttpParams httpParams = new HttpParams();
        httpParams.add("username", requestEntity.getAccount().getUsername());
        httpParams.add("password", requestEntity.getAccount().getPassword());
        httpParams.add("appid", "otn");
        CloseableHttpResponse response = this.requestEntity.getSessionHttpRequest().sendRequest(
                Url.login,
                httpParams.build()
        );
        this.requestEntity.getResponseStore().setResultMap(HttpUtil.resultToMap(response));
    }

    @Override
    protected void after() {
        ResponseStore responseStore = this.requestEntity.getResponseStore();
        Map resultMap = responseStore.getResultMap();
        Double d = (Double) resultMap.get("result_code");
        int code =  d.intValue();
        if (code == 0 ) { // 登陆成功
            Log.debug("登陆成功");
            isError = false;
        } else { // 登陆失败
            String message = (String)resultMap.get("result_message");
            Log.debug("登陆失败,错误信息：" + message);
        }
    }
}
