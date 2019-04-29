package per.solax.framework.process.request.order;

import org.apache.http.client.methods.CloseableHttpResponse;
import per.solax.assist.base.url.Url;
import per.solax.assist.util.CommonUtil;
import per.solax.assist.util.HttpParams;
import per.solax.assist.util.Log;
import per.solax.framework.entity.Order;
import per.solax.framework.entity.RequestEntity;
import per.solax.framework.process.AbstractRequestProcess;
import per.solax.framework.process.request.common.ImageCode;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @Author: solax
 * @Date: 2019/3/19
 */
public class CheckRandCodeAnsyn  extends AbstractRequestProcess {

    Order order;

    CloseableHttpResponse response;

    public CheckRandCodeAnsyn(RequestEntity requestEntity) {
        super(requestEntity);
    }

    @Override
    protected void before() {
        
    }

    @Override
    protected void send() {
/*        data = {
                "randCode": self.randCode,
                "rand": "randp",
                "_json_att": "",
                "REPEAT_SUBMIT_TOKEN": self.token
        }*/
        Map map = new HashMap();
        map.put("module", "passenger");
        map.put("rand", "randp");
        ImageCode imageCode = new ImageCode(map, Url.getPassCodeNewEntity);
        String position = imageCode.getAnswer();

        HttpParams httpParams = new HttpParams();
        httpParams.add("randCode", position);
        httpParams.add("rand", "randp");
        httpParams.add("_json_att", "");
        httpParams.add("REPEAT_SUBMIT_TOKEN", order.token.token);
        response = this.requestEntity.getSessionHttpRequest().sendRequest(
                Url.checkRandCodeAnsynEntity,
                httpParams.build()
        );

    }

    @Override
    protected void after() {
        Map map = this.getResultMap(response);
        Map data = (Map)map.get("data");
        String msg = (String)data.get("msg");
        if (CommonUtil.equals(msg, "TRUE")) {
            Log.info("验证码通过，正在提交订单");
        } else {
            Log.info("验证码有误, 再次尝试中..退出");
        }
    }
}
