package per.solax.assist.base.url.order;

import per.solax.assist.base.url.UrlBaseEntity;

/**
 * @Author: solax
 * @Date: 2019/1/18
 */
public class CheckOrderInfoEntity  extends UrlBaseEntity {

    String url = "https://kyfw.12306.cn/otn/confirmPassenger/checkOrderInfo";

    String referer = "https://kyfw.12306.cn/otn/confirmPassenger/initDc";

    @Override
    public String getUrl() {
        return url;
    }

    @Override
    public void setUrl(String url) {
        this.url = url;
    }

    @Override
    public String getReferer() {
        return referer;
    }

    @Override
    public void setReferer(String referer) {
        this.referer = referer;
    }
}
