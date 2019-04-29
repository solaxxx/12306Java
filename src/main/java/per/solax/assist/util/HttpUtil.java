package per.solax.assist.util;

import com.google.gson.Gson;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.protocol.HttpClientContext;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.util.EntityUtils;
import per.solax.assist.base.url.UrlBaseEntity;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author: solax
 * @Date: 2019/1/11
 */
public class HttpUtil {



    /**
     * get method
     * @param closeableHttpClient
     * @param urlBaseEntity)
     * @return
     */
    public static CloseableHttpResponse doGet (CloseableHttpClient closeableHttpClient, UrlBaseEntity urlBaseEntity) {
        return doGet(closeableHttpClient, null,urlBaseEntity);
    }


    public static CloseableHttpResponse doGet (CloseableHttpClient closeableHttpClient, HttpClientContext context, UrlBaseEntity urlBaseEntity) {
        return doGet(closeableHttpClient, context, urlBaseEntity, null);
    }

    /**
     * get method by params
     * @param httpClient
     * @param urlBaseEntity
     * @param list
     * @return
     */
    public static CloseableHttpResponse doGet (CloseableHttpClient httpClient, HttpClientContext context, UrlBaseEntity urlBaseEntity, List<NameValuePair> list) {
        int statusCode          = -1;
        CloseableHttpResponse response = null;
        try {
            URIBuilder uriBuilder = new URIBuilder(urlBaseEntity.getUrl());
            if (list != null) {
                uriBuilder.setParameters(list);
            }
            HttpGet httpGet = new HttpGet(uriBuilder.build());
            //httpGet.addHeader("Content-type","charset=utf-8");
            httpGet.addHeader("Referer", urlBaseEntity.getReferer());
            httpGet.addHeader("Host", urlBaseEntity.getHost());
            if (context != null) {
                response = httpClient.execute(httpGet, context);
            } else {
                response = httpClient.execute(httpGet);
            }
            statusCode = response.getStatusLine().getStatusCode();
        } catch (Exception e) {
            e.printStackTrace();
        }
        Log.debug("接口: " + urlBaseEntity.getUrl() + "结果-statusCode: "    + String.valueOf(statusCode));
        return response;
    }


    public static CloseableHttpResponse doPost (CloseableHttpClient closeableHttpClient, UrlBaseEntity urlBaseEntity) {
        return doPost(closeableHttpClient, null,urlBaseEntity);
    }


    public static CloseableHttpResponse doPost (CloseableHttpClient closeableHttpClient, HttpClientContext context, UrlBaseEntity urlBaseEntity) {
        return doPost(closeableHttpClient, context, urlBaseEntity, null);
    }

    public static CloseableHttpResponse doPost (CloseableHttpClient httpClient, HttpClientContext context, UrlBaseEntity urlBaseEntity, List<NameValuePair> list) {
        int statusCode          = -1;
        CloseableHttpResponse response = null;
        try {
            HttpPost httpPost = new HttpPost(urlBaseEntity.getUrl());
            if (list != null) {
                UrlEncodedFormEntity formEntity = new UrlEncodedFormEntity(list);
                httpPost.setEntity(formEntity);
            }
            httpPost.addHeader("Referer", urlBaseEntity.getReferer());
            httpPost.addHeader("Host", urlBaseEntity.getHost());
            if (context != null) {
                response = httpClient.execute(httpPost, context);
            } else {
                response = httpClient.execute(httpPost);
            }
            statusCode = response.getStatusLine().getStatusCode();
        } catch (Exception e) {
            e.printStackTrace();
        }
        Log.debug("接口: " + urlBaseEntity.getUrl() + "结果-statusCode: "    + String.valueOf(statusCode));
        return response;
    }

    public static String resultToString (CloseableHttpResponse loginResponse) {
        String result = null;
        try {
            result = EntityUtils.toString(loginResponse.getEntity(), "UTF-8");
        } catch (IOException e) {
            e.printStackTrace();
        }
        Log.debug("result: "        + result);
        return result;
    }

    /**
     * convert the result to map
     * @param loginResponse
     * @return
     */
    public static Map resultToMap (CloseableHttpResponse loginResponse) {
        String result = null;
        try {
            result = EntityUtils.toString(loginResponse.getEntity(), "UTF-8");
        } catch (IOException e) {
            e.printStackTrace();
        }
        Gson gson = new Gson();
        Map<String,String> map  = new HashMap<String,String>();
        map = gson.fromJson(result, map.getClass());
        Log.debug("result: "        + result);
        return map;
    }

    public static byte [] resultToByteArray (CloseableHttpResponse loginResponse) {
        byte[] result = null;
        try {
            result = EntityUtils.toByteArray(loginResponse.getEntity());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }
}
