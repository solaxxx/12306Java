package per.solax.assist.util;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @Author: solax
 * @Date: 2019/1/22
 */
public class DateUtil {

    public static final String FORMAT_DAY = "yyyy-MM-dd";

    public static String dateToString (Date date) {
        SimpleDateFormat sdf = new SimpleDateFormat(FORMAT_DAY);
        return sdf.format(date);
    }
}
