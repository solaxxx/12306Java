package per.solax.framework.entity;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author: solax
 * @Date: 2019/3/3
 */
public class Token {

    public String token;

    TicketInfoForPassengerForm ticketInfoForPassengerForm;

    String orderRequestParams;

/*    public Map toMap () {
        Map map = new HashMap();
        map.put("token", token);
        map.put("ticketInfoForPassengerForm", ticketInfoForPassengerForm.toMap());
        map.put("order_request_params", orderRequestParams);
        return map;
    }*/

}
