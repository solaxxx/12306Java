package per.solax.assist.base.url;

/**
 * @Author: solax
 * @Date: 2019/1/16
 */
abstract public class UrlBaseEntity {

    String url;

    String referer = "https://kyfw.12306.cn/otn/login/init";

    String host = "kyfw.12306.cn";

    Boolean isPost = true;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getReferer() {
        return referer;
    }

    public void setReferer(String referer) {
        this.referer = referer;
    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public Boolean getPost() {
        return isPost;
    }

    public void setPost(Boolean post) {
        isPost = post;
    }
}
