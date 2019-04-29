package per.solax.assist.util;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * @Author: solax
 * @Date: 2019/1/13
 */
public class HttpParams {
    List<NameValuePair> list = new LinkedList<>();

    public void add(String key, String value) {
        BasicNameValuePair param1 = new BasicNameValuePair(key, value);
        list.add(param1);
    }

    public List<NameValuePair> build () {
        return list;
    }

    public void putAll (Map<String,String> map) {
        for (String key: map.keySet() ) {
            String value = map.get(key);
            this.add(key, value);
        }
    }
}
