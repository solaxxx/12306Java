package per.solax.framework.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @Author: solax
 * @Date: 2019/2/17
 * 需要查询的车票对象
 *
 */
public class TicketInput {

    // 哪天
    String date;

    // 始发站
    String fromStation;

    // 终点站
    String toStation;

    // 车次
    List<String> trainCode = new ArrayList<String>();

    // 座位类型
    List<String> seatType = new ArrayList<String>();

    // 需要几张
    int needNum = 1;

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getFromStation() {
        return fromStation;
    }

    public void setFromStation(String fromStation) {
        this.fromStation = fromStation;
    }

    public String getToStation() {
        return toStation;
    }

    public void setToStation(String toStation) {
        this.toStation = toStation;
    }

    public List<String> getTrainCode() {
        return trainCode;
    }

    public void setTrainCode(List<String> trainCode) {
        this.trainCode = trainCode;
    }

    public List<String> getSeatType() {
        return seatType;
    }

    public void setSeatType(List<String> seatType) {
        this.seatType = seatType;
    }

    /**
     * 是否是任意车次都行
     * 如果没有录入车次信息则是任意车次
     * @return
     */
    public Boolean isRandomTrainCode () {
        if (this.trainCode.size() == 0) return true;
        return false;
    }

    /**
     * 是否是任意坐席
     * @return
     */
    public Boolean isRandomSeatType () {
        if (this.seatType.size() == 0) return true;
        return false;
    }
}
