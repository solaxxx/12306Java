package per.solax.assist.base.url.login;

import per.solax.assist.base.url.UrlBaseEntity;

/**
 * @Author: solax
 * @Date: 2019/1/17
 */
public class CodeCheckEntity extends UrlBaseEntity {

    String url = "https://kyfw.12306.cn/passport/captcha/captcha-check";

    String referer = "https://kyfw.12306.cn/otn/login/init";

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
