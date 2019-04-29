package per.solax.framework.process.request.order;

import org.apache.http.client.methods.CloseableHttpResponse;
import per.solax.assist.base.url.Url;
import per.solax.assist.util.HttpParams;
import per.solax.framework.entity.RequestEntity;
import per.solax.framework.entity.Token;
import per.solax.framework.process.AbstractRequestProcess;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @Author: solax
 * @Date: 2019/3/3
 */
public class InitDc  extends AbstractRequestProcess {

    CloseableHttpResponse response;

    public Token token;

    public InitDc(RequestEntity requestEntity) {
        super(requestEntity);
    }

    @Override
    protected void before() {

    }

    @Override
    protected void send() {
        response = this.requestEntity.getSessionHttpRequest().sendRequest(
                Url.submitOrderEntity
        );
    }

    @Override
    protected void after() {
        Map result = new HashMap();
        String page = this.getPage(response);
        Pattern p1=Pattern.compile("var globalRepeatSubmitToken = '(\\S+)'");
        Pattern p2=Pattern.compile("var ticketInfoForPassengerForm=(\\{.+\\})?");
        Pattern p3=Pattern.compile("var orderRequestDTO=(\\{.+\\})?");
        Matcher m1=p1.matcher(page);
        Matcher m2=p2.matcher(page);
        Matcher m3=p3.matcher(page);
        String token_name                       = m1.pattern().pattern();
        String ticketInfoForPassengerForm_name  = m2.pattern().pattern();
        String order_request_params_name        = m3.pattern().pattern();
        if (ticketInfoForPassengerForm_name != null) {
            //result.put("ticketInfoForPassengerForm");
        } else {

        }
        // TODO
/*        return {
                "token": token,
                "ticketInfoForPassengerForm": ticketInfoForPassengerForm,
                "order_request_params": order_request_params,
                "session": self.session
        }*/
    }
}
