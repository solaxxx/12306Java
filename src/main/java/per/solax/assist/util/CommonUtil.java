package per.solax.assist.util;

import com.google.gson.Gson;

import java.util.AbstractList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author: solax
 * @Date: 2019/1/16
 */
public class CommonUtil {

    public static Map stringToMap (String str) {
        Gson gson = new Gson();
        Map<String,String> map  = new HashMap<String,String>();
        map = gson.fromJson(str, map.getClass());
        return map;
    }

    public static Boolean requestSuccess (Map resultMap,  int value) {
        if (resultMap.get("result_code") instanceof Double) {
            Double d = (Double) resultMap.get("result_code");
            int code =  d.intValue();
            if (code == value) return true;
        } else if (resultMap.get("result_code") instanceof String) {
            String code = (String) resultMap.get("result_code");
            if (code.equals(String.valueOf(value)) ) return true;
        } else {
            Log.debug("错误的返回值类型");
            return false;
        }
        return false;
    }

    public static <T> Boolean notEmpty (T one) {
        if (one == null) return false;
        if (one instanceof List) {
            if (((List) one).size() ==0) {
                return false;
            }
        } else if (one instanceof String) {
            if (one.equals("")) {
                return false;
            }
        } else if (one instanceof Map) {
            if (((Map) one).size() == 0) {
                return false;
            }
        }
        return true;
    }

    public static <T> Boolean equals (T one,  T two) {
        if (one == null) return false;
        if (one instanceof String) {
            return one.equals(two);
        }
        return true;
    }

    public static Boolean is(Boolean one) {
        if (one == null) return false;
        return one;
    }
}
