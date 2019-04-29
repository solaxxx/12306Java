package per.solax.framework.process.request.query;

import org.apache.http.client.methods.CloseableHttpResponse;
import per.solax.assist.base.url.UrlBaseEntity;
import per.solax.assist.base.url.Url;
import per.solax.framework.entity.RequestEntity;
import per.solax.framework.process.AbstractRequestProcess;
import per.solax.assist.util.HttpUtil;
import per.solax.assist.util.Log;

/**
 * @Author: solax
 * @Date: 2019/1/19
 */
public class QueryPassenger  extends AbstractRequestProcess {
    public QueryPassenger(RequestEntity requestEntity) {
        super(requestEntity);
    }

    public QueryPassenger(RequestEntity requestEntity, UrlBaseEntity urlEntity) {
        super(requestEntity, urlEntity);
    }

    @Override
    protected void before() {

    }

    @Override
    protected void send() {
        CloseableHttpResponse closeableHttpResponse = this.requestEntity.sessionHttpRequest.sendRequest(
                Url.getPassengeer
        );
        this.requestEntity.getResponseStore().setResultMap(HttpUtil.resultToMap(closeableHttpResponse));
    }

    @Override
    protected void after() {
        Log.debug("获取到的联系人信息：" +  this.requestEntity.getResponseStore().getResultMap());
    }
}
