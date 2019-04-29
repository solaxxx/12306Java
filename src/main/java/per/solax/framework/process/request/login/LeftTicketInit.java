package per.solax.framework.process.request.login;

import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import per.solax.assist.base.url.Url;
import per.solax.assist.util.HttpUtil;

/**
 * @Author: solax
 * @Date: 2019/1/11
 * init check user login
 *
 */
public class LeftTicketInit {

    CloseableHttpClient closeableHttpClient = null;

    public LeftTicketInit () {
        this.send();
    }

    void send () {
        HttpClientBuilder httpClientBuilder = HttpClientBuilder.create();
        closeableHttpClient = httpClientBuilder.build();
        HttpUtil.doGet(closeableHttpClient, Url.leftTicketInit);
    }

    public CloseableHttpClient getClient () {
        return closeableHttpClient;
    }

    /**
     * check user login
     * @return
     */
    Boolean hasLogin () {
        return false;
    }
}
