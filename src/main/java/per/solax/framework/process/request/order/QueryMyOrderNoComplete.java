package per.solax.framework.process.request.order;

import org.apache.http.client.methods.CloseableHttpResponse;
import per.solax.assist.base.url.Url;
import per.solax.assist.util.CommonUtil;
import per.solax.assist.util.HttpParams;
import per.solax.assist.util.Log;
import per.solax.framework.entity.RequestEntity;
import per.solax.framework.process.AbstractRequestProcess;

import java.util.List;
import java.util.Map;

/**
 * @Author: solax
 * @Date: 2019/3/24
 */
public class QueryMyOrderNoComplete  extends AbstractRequestProcess {

    List orderDBList;

    CloseableHttpResponse response;

    public QueryMyOrderNoComplete(RequestEntity requestEntity) {
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
                Url.queryOrderWaitTimeEntity,
                httpParams.build()
        );
    }

    @Override
    protected void after() {
/*        if queryMyOrderNoCompleteResult:
        if queryMyOrderNoCompleteResult.get("data", False) and queryMyOrderNoCompleteResult["data"].get("orderDBList", False):
        return queryMyOrderNoCompleteResult["data"]
        elif queryMyOrderNoCompleteResult.get("data", False) and queryMyOrderNoCompleteResult["data"].get("orderCacheDTO", False):
        if queryMyOrderNoCompleteResult["data"]["orderCacheDTO"].get("message", False):
        print(queryMyOrderNoCompleteResult["data"]["orderCacheDTO"]["message"]["message"])
        raise ticketNumOutException(
                queryMyOrderNoCompleteResult["data"]["orderCacheDTO"]["message"]["message"])
            else:
        if queryMyOrderNoCompleteResult.get("message", False):
        print(queryMyOrderNoCompleteResult.get("message", False))
        return False
                else:
        return False
        else:
        return False*/
        Map map = this.getResultMap(response);
        String message = this.getMessage(map);
        Map data = this.getData(map);
        if (CommonUtil.notEmpty(data)) {
            orderDBList = (List)data.get("orderDBList");
            if (!CommonUtil.notEmpty(orderDBList)) {
                Map orderCacheDTO = (Map)data.get("orderCacheDTO");
                if (CommonUtil.notEmpty(orderCacheDTO)) {
                    Map message1 = (Map)orderCacheDTO.get("message");
                    String message2 = (String)message1.get("message");
                    Log.info(message2);
                } else {
                    Log.info(message);
                }
            }
        }
    }
}
