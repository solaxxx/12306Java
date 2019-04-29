package per.solax.framework.process;

import org.apache.http.client.methods.CloseableHttpResponse;
import per.solax.assist.base.url.UrlBaseEntity;
import per.solax.assist.util.CommonUtil;
import per.solax.assist.util.HttpUtil;
import per.solax.assist.util.Log;
import per.solax.framework.entity.RequestEntity;
import per.solax.framework.entity.ResponseStore;

import java.util.Map;

/**
 * @Author: solax
 * @Date: 2019/3/20
 */
public class AbstractRequestBase {

    protected UrlBaseEntity urlEntity;

    protected Map map;

    protected RequestEntity requestEntity = null;

    public Boolean isError = true;


    protected void filter () throws Exception {
        if (isError) throw new Exception(this.getClass().getName() + "发生错误");
    }

    RequestEntity getRequestEntity () {
        return requestEntity;
    }


    protected Map getResultMap () {
        ResponseStore responseStore = this.requestEntity.getResponseStore();
        Map resultMap = responseStore.getResultMap();
        return resultMap;
    }

    protected Map getResultMap (CloseableHttpResponse response) {
        return HttpUtil.resultToMap(response);
    }

    protected String getPage (CloseableHttpResponse response) {
        return HttpUtil.resultToString(response);
    }

    protected void resolveResult (Map responseData, ResponseDeal responseDeal) {
        Boolean deal = responseDeal.deal();
        if (!deal) {
            String message = (String)responseData.get("message");
            String validateMessages = (String)responseData.get("validateMessages");
            if (CommonUtil.notEmpty(message)) {
                Log.info(message);
            } else if (CommonUtil.notEmpty(validateMessages)) {
                Log.info(validateMessages);
            } else {
                Log.info("发生未知错误");
            }
        }
    }

    protected Boolean getStatus (Map map) {
        Boolean status = Boolean.parseBoolean((String)map.get("status"));
        return status;
    }

    protected Map getData (Map map) {
        Map dataResult = (Map)map.get("data");
        return dataResult;
    }

    protected String getMessage (Map map) {
        String message = (String)map.get("message");
        return message;
    }

    protected String getMsg (Map data) {
        String msg = (String) data.get("msg");
        return msg;
    }
}
