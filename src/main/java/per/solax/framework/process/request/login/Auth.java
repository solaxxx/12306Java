package per.solax.framework.process.request.login;

import org.apache.http.client.methods.CloseableHttpResponse;
import per.solax.assist.base.url.Url;
import per.solax.framework.entity.RequestEntity;
import per.solax.framework.process.AbstractRequestProcess;
import per.solax.assist.util.HttpParams;
import per.solax.assist.util.HttpUtil;
import per.solax.assist.util.Log;

import java.util.Map;

/**
 * @Author: solax
 * @Date: 2019/1/13
 */
public class Auth extends AbstractRequestProcess {

    public Auth(RequestEntity requestEntity) {
        super(requestEntity);
    }

    @Override
    protected void before() {

    }

    @Override
    protected void send() {
        HttpParams httpParams = new HttpParams();
        httpParams.add("appid", "otn");
        CloseableHttpResponse response = this.requestEntity.sessionHttpRequest.sendRequest(
                Url.auth,
                httpParams.build());
        Map map = HttpUtil.resultToMap(response);
        this.requestEntity.getResponseStore().setResultMap(map);
    }

    @Override
    protected void after() {
        Log.debug("获取到tk,值为:" +  this.requestEntity.getResponseStore().getTk());
    }
}
