package per.solax.framework.entity;

import per.solax.assist.util.HttpParams;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author: solax
 * @Date: 2019/3/10
 *
 * 获取Token时，其中的一个对象
 */
public class QueryLeftTicketRequestDTO {

    String trainNo;

    String stationTrainCode;

    String fromStation;

    String toStation;

    public Map toMap () {
        Map result = new HashMap<>();
        result.put("train_no", trainNo);
        result.put("station_train_code", stationTrainCode);
        result.put("from_station", fromStation);
        result.put("to_station", toStation);
        return result;
    }

}
