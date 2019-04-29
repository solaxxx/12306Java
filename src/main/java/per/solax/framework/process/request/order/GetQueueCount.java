package per.solax.framework.process.request.order;

import org.apache.http.client.methods.CloseableHttpResponse;
import per.solax.assist.base.url.Url;
import per.solax.assist.util.HttpParams;
import per.solax.assist.util.Log;
import per.solax.framework.entity.Order;
import per.solax.framework.entity.RequestEntity;
import per.solax.framework.process.AbstractRequestProcess;
import per.solax.framework.process.ResponseDeal;

import javax.xml.crypto.Data;
import java.util.Map;

/**
 * @Author: solax
 * @Date: 2019/3/10
 */
public class GetQueueCount  extends AbstractRequestProcess {

    CloseableHttpResponse response;

    Order order;

    public GetQueueCount(RequestEntity requestEntity) {
        super(requestEntity);
    }

    @Override
    protected void before() {

    }

    @Override
    protected void send() {
        HttpParams httpParams = new HttpParams();
        httpParams.putAll(order.toQueueMap());
        response = this.requestEntity.getSessionHttpRequest().sendRequest(
                Url.getQueueCountUrlEntity,
                httpParams.build()
        );
    }

    @Override
    protected void after() {
        Map map = this.getResultMap(response);
        this.resolveResult(map, () -> {
            Boolean status = (Boolean) map.get("status");
            if (status) {
                Map data = (Map) map.get("data");
                if (data == null) return false;
                Integer countT = (Integer) data.get("countT");
                String ticket = (String) data.get("ticket");
                if (countT != null) {
                    //print(u"排队成功, 你排在: {1}位, 当前余票还剩余: {0} 张".format(ticket_split, countT))
                    Log.info("排队成功, 你排在: " + ticket +"位, 前余票还剩余:" + countT +"张");
                    // confirmSingleForQueue


                } else {
                    Log.info("排队发现未知错误");
                }
            }
            return false;
        });
    }
}
