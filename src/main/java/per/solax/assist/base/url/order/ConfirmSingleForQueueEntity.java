package per.solax.assist.base.url.order;

import per.solax.assist.base.url.UrlBaseEntity;

/**
 * @Author: solax
 * @Date: 2019/3/19
 */
public class ConfirmSingleForQueueEntity extends UrlBaseEntity {

    String url = "https://kyfw.12306.cn/otn/confirmPassenger/confirmSingleForQueue";

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
