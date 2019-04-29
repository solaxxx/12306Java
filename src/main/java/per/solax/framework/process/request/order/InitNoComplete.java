package per.solax.framework.process.request.order;

import org.apache.http.client.methods.CloseableHttpResponse;
import per.solax.assist.base.url.Url;
import per.solax.assist.util.HttpParams;
import per.solax.framework.entity.RequestEntity;
import per.solax.framework.process.AbstractRequestProcess;

/**
 * @Author: solax
 * @Date: 2019/3/24
 */
public class InitNoComplete extends AbstractRequestProcess {

    public InitNoComplete(RequestEntity requestEntity) {
        super(requestEntity);
    }

    @Override
    protected void before() {

    }

    @Override
    protected void send() {
        HttpParams httpParams = new HttpParams();
        httpParams.add("_json_att", "_json_att");
        response = this.requestEntity.getSessionHttpRequest().sendRequest(
                Url.initNoCompleteUrlEntity,
                httpParams.build()
        );
    }

    @Override
    protected void after() {

    }
}