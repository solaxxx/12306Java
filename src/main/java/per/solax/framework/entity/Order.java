package per.solax.framework.entity;

import per.solax.assist.util.DateUtil;

import java.lang.reflect.Array;
import java.security.PublicKey;
import java.util.*;

/**
 * @Author: solax
 * @Date: 2019/2/17
 *
 *
 *
 */
public class Order {

    String secretStr = "";

    String trainDate;

    String backTrainDate;

    String tourFlag = "dc";

    String purposeCodes = "ADULT";

    String queryFromStationName;

    String queryToStationName;

    public Token token;
    // 匹配的坐席
    public int matchSeat = -1;

    public PassengerManager passengerManager;

    public Boolean needCheckCode = false;

    public Order (TicketQuery ticketQuery) {
        this.secretStr              = ticketQuery.secretStr;
        this.trainDate              = ticketQuery.date;
        this.matchSeat              = ticketQuery.matchSeat;
        this.backTrainDate          = DateUtil.dateToString(new Date());
        this.queryFromStationName   = ticketQuery.fromStation;
        this.queryToStationName     = ticketQuery.toStation;
    }

    public Map toNormalSubmitMap () {
        Map result = new HashMap<>();
        result.put("secretStr", this.secretStr);
        result.put("train_date", this.trainDate);
        result.put("back_train_date", this.backTrainDate);
        result.put("tour_flag", this.tourFlag);
        result.put("purpose_codes", this.purposeCodes);
        result.put("query_from_station_name", this.queryFromStationName);
        result.put("query_to_station_name", this.queryToStationName);
        return result;
    }

    public  Map toCheckOrderInfoMap () {
        Map result = new HashMap<>();
        result.put("passengerTicketStr", this.passengerManager.toTicketString(this.matchSeat));
        result.put("oldPassengerStr", this.passengerManager.toTicketStringOld());
        result.put("REPEAT_SUBMIT_TOKEN", this.token.token);
        result.put("randCode", "");
        result.put("cancel_flag", "2");
        result.put("bed_level_order_num", "000000000000000000000000000000");
        result.put("tour_flag", "dc");
        result.put("_json_att", "");
        return result;
    }

    public Map toConfirmSingleForQueueMap () {
        Map result = new HashMap<>();
        result.put("passengerTicketStr", this.passengerManager.toTicketString(this.matchSeat));
        result.put("oldPassengerStr", this.passengerManager.toTicketStringOld());
        result.put("purpose_codes", this.token.ticketInfoForPassengerForm.purposeCodes);
        result.put("key_check_isChange", this.token.ticketInfoForPassengerForm.keyCheckIsChange);
        result.put("leftTicketStr", this.token.ticketInfoForPassengerForm.leftTicketStr);
        result.put("train_location", this.token.ticketInfoForPassengerForm.trainLocation);
        result.put("seatDetailType", "");
        result.put("roomType", "00");
        result.put("dwAll", "N");
        result.put("whatsSelect", 1);
        result.put("_json_at", "");
        result.put("randCode", "");
        result.put(choose_seats, "");
        result.put("REPEAT_SUBMIT_TOKEN", this.token.token);
        return result;
    }
/*    data['train_no'] = self.ticketInfoForPassengerForm['queryLeftTicketRequestDTO']['train_no'],
    data['stationTrainCode'] = self.ticketInfoForPassengerForm['queryLeftTicketRequestDTO'][
            'station_train_code'],
    data['seatType'] = self.set_type,
    data['fromStationTelecode'] = self.ticketInfoForPassengerForm['queryLeftTicketRequestDTO'][
            'from_station'],
    data['toStationTelecode'] = self.ticketInfoForPassengerForm['queryLeftTicketRequestDTO']['to_station'],
    data['leftTicket'] = self.ticketInfoForPassengerForm['leftTicketStr'],
    data['purpose_codes'] = self.ticketInfoForPassengerForm['purpose_codes'],
    data['train_location'] = self.ticketInfoForPassengerForm['train_location'],
    data['REPEAT_SUBMIT_TOKEN'] = self.token,*/
    public Map toQueueMap () {
        Map result = new HashMap<>();
        result.put("train_date", this.trainDate + "00:00:00 GMT+0800 (中国标准时间)");
        result.put("stationTrainCode", token.ticketInfoForPassengerForm.queryLeftTicketRequestDTO.stationTrainCode);
        result.put("seatType", this.matchSeat);
        result.put("fromStationTelecode", token.ticketInfoForPassengerForm.queryLeftTicketRequestDTO.fromStation);
        result.put("leftTicket", token.ticketInfoForPassengerForm.leftTicketStr);
        result.put("purpose_codes", token.ticketInfoForPassengerForm.purposeCodes);
        result.put("train_location", token.ticketInfoForPassengerForm.trainLocation);
        result.put("REPEAT_SUBMIT_TOKEN", token.token);
        return result;
    }

    public static void main (String [] args ) {
        String [] dd = {};
        System.out.println(Collections.nCopies(59, "solax").toString());
        String [] argsViews = new String [] {"1","2"};
        List<String> views = Arrays.asList(argsViews);
        views.set(1, "c");
        views.remove(0);
        System.out.println(argsViews[1]);
        System.out.println(views.subList(0,1));
        List<String> ddc = new ArrayList<String>();

        Collections.addAll(views, "dd");
       // List<String> ddw = new ArrayList<String>();
      //  ddw.removeAll(String::);

        //Collections.re
/*
        Map<String, String> map = new HashMap<>();
        map.forEach((k, v) -> {
            System.out.println(k);
        });

        Vector vector = new Vector();
        vector.add("1a");
        System.out.println(vector.toString());
        List <String> b = new LinkedList<>();
        System.out.println(b instanceof RandomAccess);
        List <String> d = new ArrayList<>();
        d.add("a");
        d.add("b");
        d.add("c");
        d.add("d");
        d.add("e");
        System.out.println(d instanceof RandomAccess);
        Iterator iterator = d.iterator();
        while (iterator.hasNext()){

            System.out.println(iterator.next());
        }

        Iterator iterator2 = d.iterator();
        iterator2.forEachRemaining(
                element ->
                        System.out.println(element)
        );
        SortedMap sortedMap = new TreeMap();*/
    }

}
