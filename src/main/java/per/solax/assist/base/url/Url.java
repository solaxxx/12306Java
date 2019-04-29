package per.solax.assist.base.url;

import per.solax.assist.base.url.login.*;
import per.solax.assist.base.url.order.*;

/**
 * @Author: solax
 * @Date: 2019/1/10
 */
public class Url {

    /**********************login**************************/
    public final static UrlBaseEntity auth =  new AuthEntity();

    public final static UrlBaseEntity login = new LoginEntity();

    public final static UrlBaseEntity leftTicketInit = new LeftTicketInitEntity();

    public final static UrlBaseEntity loginImageCode = new GetCodeImageEntity();

    public final static UrlBaseEntity codeCheck = new CodeCheckEntity();

    public final static UrlBaseEntity uamAuthClient = new UamAuthClientEntity();

    public final static UrlBaseEntity getUserInfo = new GetUserInfo();

    public final static String orderImageCode = "https://kyfw.12306.cn/otn/passcodeNew/getPassCodeNew";

    /**********************query***************************/

    public final static UrlBaseEntity queryTicket =  new QueryTicketEntity();


    /**********************order***************************/
    public final static UrlBaseEntity getPassengeer = new GetPassengerEntity();

    public final static UrlBaseEntity autoSubmitOrderEntity = new AutoSubmitOrderEntity();

    public final static UrlBaseEntity cancelNoCompleteMyOrderEntity = new CancelNoCompleteMyOrderEntity();

    public final static UrlBaseEntity checkOrderInfoEntity = new CheckOrderInfoEntity();

    public final static UrlBaseEntity confirmSingleForQueueAsysEntity = new ConfirmSingleForQueueAsysEntity();

    public final static UrlBaseEntity getQueueCountAsyncEntity = new GetQueueCountAsyncEntity();

    public final static UrlBaseEntity getQueueCountUrlEntity = new GetQueueCountUrlEntity();

    public final static UrlBaseEntity initNoCompleteUrlEntity = new InitNoCompleteUrlEntity();

    public final static UrlBaseEntity loginAysnSuggestEntity = new loginAysnSuggestEntity();

    public final static UrlBaseEntity submitOrderEntity = new SubmitOrderEntity();

    public final static UrlBaseEntity initDcEntity = new InitDcEntity();

    public final static UrlBaseEntity getPassCodeNewEntity = new GetPassCodeNewEntity();

    public final static UrlBaseEntity checkRandCodeAnsynEntity = new CheckRandCodeAnsynEntity();

    public final static UrlBaseEntity confirmSingleForQueueEntity = new ConfirmSingleForQueueEntity();

    public final static UrlBaseEntity queryOrderWaitTimeEntity = new QueryOrderWaitTimeEntity();





}
