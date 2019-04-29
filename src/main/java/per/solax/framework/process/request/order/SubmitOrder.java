package per.solax.framework.process.request.order;

import org.apache.http.client.methods.CloseableHttpResponse;
import per.solax.assist.base.url.Url;
import per.solax.assist.base.url.UrlBaseEntity;
import per.solax.assist.util.HttpParams;
import per.solax.assist.util.HttpUtil;
import per.solax.assist.util.Log;
import per.solax.framework.entity.Order;
import per.solax.framework.entity.PassengerManager;
import per.solax.framework.entity.RequestEntity;
import per.solax.framework.entity.TicketQuery;
import per.solax.framework.process.AbstractRequestProcess;

import java.util.Map;

/**
 * @Author: solax
 * @Date: 2019/2/17
 */
public class SubmitOrder  extends AbstractRequestProcess {

    Order order;

    public SubmitOrder(RequestEntity requestEntity) {
        super(requestEntity);
    }

    @Override
    protected void before() {

    }

    @Override
    protected void send() {
        HttpParams httpParams = new HttpParams();
        httpParams.putAll(order.toNormalSubmitMap());
        CloseableHttpResponse response = this.requestEntity.getSessionHttpRequest().sendRequest(
                Url.submitOrderEntity,
                httpParams.build()
        );
        Map map = HttpUtil.resultToMap(response);
        String data = (String)map.get("data");
        if (data != null) {
            if (data.equals("N")) {

            } else {
                Log.info("出票失败");
            }
        } else {
            Log.info("提交车次返回信息：" + map.get("messages"));
        }
    }

    @Override
    protected void after() {

    }
}
