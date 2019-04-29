package per.solax.framework.process.request.login;

import org.apache.http.client.methods.CloseableHttpResponse;
import per.solax.assist.base.url.UrlBaseEntity;
import per.solax.assist.base.url.Url;
import per.solax.framework.entity.RequestEntity;
import per.solax.framework.entity.ResponseStore;
import per.solax.framework.process.AbstractRequestProcess;
import per.solax.assist.util.CommonUtil;
import per.solax.assist.util.HttpParams;
import per.solax.assist.util.HttpUtil;
import per.solax.assist.util.Log;

import java.util.Map;

/**
 * @Author: solax
 * @Date: 2019/1/18
 */
public class UamAuthClient  extends AbstractRequestProcess {
    public UamAuthClient(RequestEntity requestEntity) {
        super(requestEntity);
    }

    public UamAuthClient(RequestEntity requestEntity, UrlBaseEntity urlEntity) {
        super(requestEntity, urlEntity);
    }

    @Override
    protected void before() {

    }

    @Override
    protected void send() {
        this.getNewAttTk();
    }

    @Override
    protected void after() {
        ResponseStore responseStore = this.requestEntity.getResponseStore();
        Map resultMap = responseStore.getResultMap();
        if (CommonUtil.requestSuccess(resultMap, 0)) {
            Log.debug("欢迎 " + resultMap.get("username") + "登陆");
            isError = false;
        } else {
            this.getNewAttTk();
            this.getUserInfo();
        }
    }

    private void getNewAttTk () {
        String newAppTk = (String)this.requestEntity.getResponseStore().getResultMap().get("newapptk");
        HttpParams httpParams = new HttpParams();
        httpParams.add("tk", newAppTk);
        CloseableHttpResponse response = this.requestEntity.getSessionHttpRequest().sendRequest(
                Url.uamAuthClient,
                httpParams.build()
        );
        this.requestEntity.getResponseStore().setResultMap(HttpUtil.resultToMap(response));
    }

    private void getUserInfo () {
        HttpParams httpParams = new HttpParams();
        CloseableHttpResponse response = this.requestEntity.getSessionHttpRequest().sendRequest(
                Url.getUserInfo,
                httpParams.build()
        );
        String result = HttpUtil.resultToString(response);
        if (result != null) {
            Log.debug("第二种用户信息获取，结果是：" + HttpUtil.resultToString(response) + "登陆");
            isError = false;
        }
    }
}
