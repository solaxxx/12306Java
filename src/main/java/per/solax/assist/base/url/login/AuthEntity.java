package per.solax.assist.base.url.login;

import per.solax.assist.base.url.UrlBaseEntity;

/**
 * @Author: solax
 * @Date: 2019/1/16
 */
public class AuthEntity extends UrlBaseEntity {

    String url = "https://kyfw.12306.cn/passport/web/auth/uamtk";

    String referer = "https://kyfw.12306.cn/otn/passport?redirect=/otn/login/userLogin";

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
