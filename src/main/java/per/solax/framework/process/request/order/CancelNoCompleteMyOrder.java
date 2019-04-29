package per.solax.framework.process.request.order;

import per.solax.assist.base.url.Url;
import per.solax.assist.util.CommonUtil;
import per.solax.assist.util.HttpParams;
import per.solax.assist.util.Log;
import per.solax.framework.entity.RequestEntity;
import per.solax.framework.process.AbstractRequestProcess;

import java.util.Map;

/**
 * @Author: solax
 * @Date: 2019/3/24
 */
public class CancelNoCompleteMyOrder  extends AbstractRequestProcess {

    String sequence_no;

    public CancelNoCompleteMyOrder(RequestEntity requestEntity) {
        super(requestEntity);
    }

    @Override
    protected void before() {

    }

    @Override
    protected void send() {
        HttpParams httpParams = new HttpParams();
        httpParams.add("sequence_no", sequence_no);
        httpParams.add("cancel_flag", "cancel_order");
        httpParams.add("_json_att", "");
        response = this.requestEntity.getSessionHttpRequest().sendRequest(
                Url.cancelNoCompleteMyOrderEntity,
                httpParams.build()
        );
    }

    @Override
    protected void after() {
/*        if cancelNoCompleteMyOrderResult.get("data", False) and cancelNoCompleteMyOrderResult["data"].get("existError", "N"):
        print(ticket.CANCEL_ORDER_SUCCESS.format(sequence_no))
        time.sleep(2)
        return True
        else:
        print(ticket.CANCEL_ORDER_FAIL.format(sequence_no))
        return False*/
        Map data = this.getData(map);
        String existError = (String)data.get("existError");
        if (CommonUtil.equals("N", existError)) {
            Log.info("取消订单成功", sequence_no);
        } else {
            Log.info("取消订单失败", sequence_no);
        }
    }
}
