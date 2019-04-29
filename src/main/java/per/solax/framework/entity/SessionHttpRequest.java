package per.solax.framework.entity;

import org.apache.http.NameValuePair;
import org.apache.http.client.CookieStore;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.protocol.HttpClientContext;
import org.apache.http.impl.client.BasicCookieStore;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import per.solax.assist.base.url.UrlBaseEntity;
import per.solax.assist.base.url.Url;
import per.solax.assist.util.HttpUtil;

import java.util.List;

/**
 * @Author: solax
 * @Date: 2019/1/16
 */
public class SessionHttpRequest {

    HttpClientContext context = null;

    CloseableHttpClient httpClient = null;

    CookieStore cookieStore = null;

    public SessionHttpRequest () {
        this.init();
    }

    private void init () {
        context = HttpClientContext.create();
        cookieStore = new BasicCookieStore();
        HttpClientBuilder httpClientBuilder = HttpClientBuilder.create();
        httpClient = httpClientBuilder.setDefaultCookieStore(cookieStore).build();
        HttpUtil.doGet(httpClient, context, Url.leftTicketInit);
    }

    public CloseableHttpResponse sendRequest (UrlBaseEntity url, List<NameValuePair> list) {
        CloseableHttpResponse response;
        if (url.getPost()) {
            response =  HttpUtil.doPost(httpClient, context, url, list);
        } else {
            response =  HttpUtil.doGet(httpClient, context, url, list);
        }
        return response;
    }

    public CloseableHttpResponse sendRequest (UrlBaseEntity url) {
        CloseableHttpResponse response;
        if (url.getPost()) {
            response =  HttpUtil.doPost(httpClient, context, url);
        } else {
            response =  HttpUtil.doGet(httpClient, context, url);
        }
        return response;
    }

    public CloseableHttpResponse doGet (UrlBaseEntity url) {
        return HttpUtil.doGet(httpClient, context, url);
    }
}
