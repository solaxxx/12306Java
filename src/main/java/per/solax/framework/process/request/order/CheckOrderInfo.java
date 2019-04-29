package per.solax.framework.process.request.order;

import org.apache.http.client.methods.CloseableHttpResponse;
import per.solax.assist.base.url.Url;
import per.solax.assist.util.CommonUtil;
import per.solax.assist.util.HttpParams;
import per.solax.assist.util.Log;
import per.solax.framework.entity.Order;
import per.solax.framework.entity.PassengerManager;
import per.solax.framework.entity.RequestEntity;
import per.solax.framework.process.AbstractRequestProcess;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author: solax
 * @Date: 2019/2/24
 */
public class CheckOrderInfo  extends AbstractRequestProcess {
    CloseableHttpResponse response;

    Order order;
    PassengerManager passengerManager;
    String passengerStr;
    String passengerStrOld;

    public CheckOrderInfo(RequestEntity requestEntity) {
        super(requestEntity);
    }

    @Override
    protected void before() {
        passengerManager    = requestEntity.getAccount().getPassengerManager();
        order.passengerManager = passengerManager;
        InitDc initDc = new InitDc(requestEntity);
        order.token = initDc.token;
    }

    @Override
    protected void send() {
        HttpParams httpParams = new HttpParams();
        httpParams.putAll(order.toCheckOrderInfoMap());
        response = this.requestEntity.getSessionHttpRequest().sendRequest(
                Url.checkOrderInfoEntity,
                httpParams.build()
        );
    }

    @Override
    protected void after() {
        Boolean needCode = false;
        Map map = this.getResultMap(response);
        Map data = (Map)map.get("data");
        String errMsg = data != null ? (String)data.get("errMsg"): null;
        List messages = (List)map.get("messages");
        if (map.get("submitStatus") != null) {
            Log.debug("车票提交通过，正在尝试排队");
            if (data != null) {
                if (data.get("ifShowPassCode").equals("Y")) {
                    needCode = true;
                }
                if (needCode) {
                    CheckRandCodeAnsyn randCodeAnsyn = new CheckRandCodeAnsyn(this.requestEntity);
                }
                //
            }
        } else if (CommonUtil.notEmpty(messages)) {
            Log.info("messages:" + messages.get(0));
        } else {
            Log.info("车票提交后报错");
        }
    }
}
