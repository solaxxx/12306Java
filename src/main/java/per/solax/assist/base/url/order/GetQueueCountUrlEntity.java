package per.solax.assist.base.url.order;

import per.solax.assist.base.url.UrlBaseEntity;

/**
 * @Author: solax
 * @Date: 2019/3/10
 */
public class GetQueueCountUrlEntity extends UrlBaseEntity {

    String url = "https://kyfw.12306.cn/otn/confirmPassenger/getQueueCount";

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