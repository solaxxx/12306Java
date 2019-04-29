package per.solax.framework.process;

import org.apache.http.client.methods.CloseableHttpResponse;
import per.solax.assist.base.url.UrlBaseEntity;
import per.solax.assist.util.CommonUtil;
import per.solax.assist.util.HttpUtil;
import per.solax.assist.util.Log;
import per.solax.framework.entity.RequestEntity;
import per.solax.framework.entity.ResponseStore;

import java.util.Collections;
import java.util.Map;

/**
 * @Author: solax
 * @Date: 2019/1/13
 */
public abstract class AbstractRequestProcess extends AbstractRequestBase{

    protected CloseableHttpResponse response;

    public AbstractRequestProcess(RequestEntity requestEntity) {
        this.requestEntity = requestEntity;
        this.init();
    }

    public AbstractRequestProcess(RequestEntity requestEntity, UrlBaseEntity urlEntity) {
        this.urlEntity =  urlEntity;
        this.requestEntity = requestEntity;
        this.init();
    }

    private void init () {
        this.before();
        this.send();
        this.after();
    }

    protected abstract void before ();

    protected abstract void send ();

    protected abstract void after ();
}
