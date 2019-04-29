package per.solax.assist.base.url.order;

import per.solax.assist.base.url.UrlBaseEntity;

/**
 * @Author: solax
 * @Date: 2019/3/19
 *
 *
 */
public class CheckRandCodeAnsynEntity extends UrlBaseEntity {

    // a error word in this url "Ansyn". what are they think about?
    String url = "https://kyfw.12306.cn/otn/passcodeNew/checkRandCodeAnsyn";

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