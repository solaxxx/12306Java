package per.solax.framework.process.request.order;

import org.apache.http.client.methods.CloseableHttpResponse;
import per.solax.assist.base.url.Url;
import per.solax.assist.util.CommonUtil;
import per.solax.assist.util.HttpParams;
import per.solax.assist.util.Log;
import per.solax.framework.entity.Order;
import per.solax.framework.entity.RequestEntity;
import per.solax.framework.process.AbstractRequestProcess;

import java.util.Map;

/**
 * @Author: solax
 * @Date: 2019/3/19
 */
public class ConfirmSingleForQueue  extends AbstractRequestProcess {

    Order order;

    CloseableHttpResponse response;

    public ConfirmSingleForQueue(RequestEntity requestEntity) {
        super(requestEntity);
    }

    @Override
    protected void before() {

    }

    @Override
    protected void send() {

        HttpParams httpParams = new HttpParams();
        httpParams.putAll(order.toConfirmSingleForQueueMap());
        response = this.requestEntity.getSessionHttpRequest().sendRequest(
                Url.confirmSingleForQueueEntity,
                httpParams.build()
        );
    }

    @Override
    protected void after() {
        this.resolveResult(map, () -> {
            Boolean status = (Boolean) map.get("status");
            if (status) {
                Map data = (Map)map.get("data");
                Boolean submitStatus = (Boolean) data.get("submitStatus");
                if (CommonUtil.is(submitStatus)) {

                } else {
                    String errMsg = (String)data.get("errMsg");
                    if (CommonUtil.notEmpty(errMsg)) {
                        //rint(u"提交订单失败，{0}".format(c_data['errMsg']))
                        Log.info("提交订单失败, " + "errMsg");
                    } else {
                        Log.info(data.toString());
                        Log.info("订票失败!很抱歉,请重试提交预订功能!");
                    }
                }
            }
            return false;
        });
    }
}
