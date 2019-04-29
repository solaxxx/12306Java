package per.solax.framework.process.request.login;

import org.apache.http.client.methods.CloseableHttpResponse;
import per.solax.assist.base.url.UrlBaseEntity;
import per.solax.assist.base.url.Url;
import per.solax.assist.util.HttpParams;
import per.solax.assist.util.HttpUtil;
import per.solax.assist.util.Log;
import per.solax.framework.entity.RequestEntity;
import per.solax.framework.entity.ResponseStore;
import per.solax.framework.process.AbstractRequestProcess;

import java.util.Map;

/**
 * @Author: solax
 * @Date: 2019/1/17
 */
public class CodeCheck  extends AbstractRequestProcess {
    public CodeCheck(RequestEntity requestEntity) {
        super(requestEntity);
    }

    public CodeCheck(RequestEntity requestEntity, UrlBaseEntity urlEntity) {
        super(requestEntity, urlEntity);
    }

    @Override
    protected void before() {

    }

    @Override
    protected void send() {
        HttpParams httpParams = new HttpParams();
        httpParams.add("answer", this.requestEntity.getResponseStore().getLoginImageCodePos());
        httpParams.add("rand", "sjrand");
        httpParams.add("login_site", "E");
        CloseableHttpResponse closeableHttpResponse = this.requestEntity.sessionHttpRequest.sendRequest(
                Url.codeCheck,
                httpParams.build()
        );
        this.requestEntity.getResponseStore().setResultMap(HttpUtil.resultToMap(closeableHttpResponse));
    }

    @Override
    protected void after() {
        try {
            ResponseStore responseStore = this.requestEntity.getResponseStore();
            Map resultMap = responseStore.getResultMap();
            if (resultMap.get("result_code").equals("4") ) {
                this.isError = false;
                Log.debug("验证码通过，准备开始登陆");
            } else {
                Log.debug("验证码未通过，准备重新开始登陆流程");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
