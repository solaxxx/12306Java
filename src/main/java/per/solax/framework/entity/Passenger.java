package per.solax.framework.entity;

/**
 * @Author: solax
 * @Date: 2019/2/17
 */
public class Passenger {
/*
            if self.is_more_ticket_num is 1:
            passengerTicketStrList.append(
            '0,' + user_info[0]['passenger_type'] + "," + user_info[0][
            "passenger_name"] + "," +
    user_info[0]['passenger_id_type_code'] + "," + user_info[0]['passenger_id_no'] + "," +
    user_info[0]['mobile_no'] + ',N')
            oldPassengerStr.append(
    user_info[0]['passenger_name'] + "," + user_info[0]['passenger_id_type_code'] + "," +
    user_info[0]['passenger_id_no'] + "," + user_info[0]['passenger_type'] + '_')
            else:*/
    // 姓名
    String passenger_name;

    // 类型
    String passenger_type;

    // what`s a fuck
    String passenger_id_type_code;

    // id
    String passenger_id_no;

    // 电话号码
    String mobile_no;

    public String toOrderString () {
        return "0," +
                passenger_type + "," +
                passenger_name + "," +
                passenger_id_type_code + "," +
                passenger_id_no + "," +
                mobile_no + ",N";
    }

    public String toOrderStringOld () {
        return  passenger_name + "," +
                passenger_id_type_code + "," +
                passenger_id_no + "," +
                passenger_type + "_";
    }

}
