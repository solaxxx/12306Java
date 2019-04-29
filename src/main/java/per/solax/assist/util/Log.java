package per.solax.assist.util;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * @Author: solax
 * @Date: 2019/1/11
 */
public class Log {

    static Logger logger = LogManager.getLogger(LogManager.ROOT_LOGGER_NAME);

    public static void info (String message) {
        logger.info(message);
    }

    public static void info (String ...message) {
        StringBuilder stringBuilder = new StringBuilder();
        for (String one : message) {
            stringBuilder.append(one);
        }
        logger.info(stringBuilder.toString());
    }

    public static void debug (String message) {
        logger.info(message);
    }
}
