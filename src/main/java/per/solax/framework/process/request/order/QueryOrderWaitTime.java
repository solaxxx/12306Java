package per.solax.framework.process.request.order;

import org.apache.http.client.methods.CloseableHttpResponse;
import per.solax.assist.base.url.Url;
import per.solax.assist.util.CommonUtil;
import per.solax.assist.util.HttpParams;
import per.solax.assist.util.Log;
import per.solax.framework.entity.RequestEntity;
import per.solax.framework.process.AbstractRequestProcess;

import java.util.Map;

/**
 * @Author: solax
 * @Date: 2019/3/20
 */
public class QueryOrderWaitTime  extends AbstractRequestProcess {

    CloseableHttpResponse response;

    public QueryOrderWaitTime(RequestEntity requestEntity) {
        super(requestEntity);
    }

    @Override
    protected void before() {

    }

    @Override
    protected void send() {
        // random={0}&tourFlag=dc&_json_att=",
        HttpParams httpParams = new HttpParams();
        httpParams.add("random", "random");
        httpParams.add("tourFlag", "tourFlag");
        httpParams.add("_json_att", "_json_att");
        response = this.requestEntity.getSessionHttpRequest().sendRequest(
                Url.queryOrderWaitTimeEntity,
                httpParams.build()
        );
    }

    @Override
    protected void after() {
        Map map = this.getResultMap(response);
        this.resolveResult(map, () -> {
            Boolean status = this.getStatus(map);
            if (status) {
                Map data = this.getData(map);
                if (CommonUtil.notEmpty(data)) {
                    String msg = this.getMsg(data);
                    String waitTime = (String)data.get("waitTime");
                    String orderId = (String)data.get("orderId");
                    if (CommonUtil.notEmpty(orderId)) {
                        // 发送邮件
                        // 发送微信
                    } else if (CommonUtil.notEmpty(msg)) {
                        Log.info("msg", msg);
                    } else if (CommonUtil.notEmpty(waitTime)) {
                        Log.info("waitTime", waitTime);
                    }
                }
            }
            return false;
        });
    }
}
