package per.solax.framework.entity;

import per.solax.assist.util.Log;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author: solax
 * @Date: 2019/2/17
 */
public class PassengerManager {
    // 一个账号下的所有联系人
    List<Passenger> accountPassenger = new ArrayList<>();

    // 需要购票的联系人
    List<Passenger> orderPassenger = new ArrayList<>();


    public String toTicketString (int seatType) {
        if (orderPassenger.size() == 0) {
            Log.info("没有选择联系人");
            return null;
        }
        List <String> list = new ArrayList<>();
        if (list.size() == 1) {
            list.add(orderPassenger.get(0).toOrderString());
        } else {
            for (Passenger passenger : orderPassenger) {
                list.add(passenger.toOrderString() + "_" + seatType);
            }
        }
        return seatType + "," + String.join(",", list);
    }

    public String toTicketStringOld () {
        if (orderPassenger.size() == 0) {
            Log.info("没有选择联系人");
            return null;
        }
        List <String> list = new ArrayList<>();
        if (list.size() == 1) {
            list.add(orderPassenger.get(0).toOrderStringOld());
        } else {
            for (Passenger passenger : orderPassenger) {
                list.add(passenger.toOrderString());
            }
        }
        return String.join(",", list);
    }
}
