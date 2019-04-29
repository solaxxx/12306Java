package per.solax.framework.entity;

import java.util.List;

/**
 * @Author: solax
 * @Date: 2019/2/17
 * 查询出来的每一条票务信息
 */
public class TicketQuery {

    String trainCode;

    Boolean hasStandSeat = false;

    Boolean hasSeated = false;

    Boolean hasHardSleeper = false;

    Boolean hastSortSleeper = false;

    Boolean hasHighSortSleeper = false;

    int standSeatNum = 20;                  // 1

    int seatedNum = 20;                     // 2

    int hardSleeperNum = 20;                // 3

    int sortSleeper = 20;                   // 4

    int highSortSleeperNum = 20;            // 5

    String secretStr = "";

    String date;

    String fromStation;

    String toStation;

/*                '硬座': 1,
                        '无座': 1,
                        '软座': 2,
                        '软卧': 4,
                        '硬卧': 3,*/
    // 匹配的坐席
    public int matchSeat = -1;

    public TicketQuery (String ticketColumn) {
        String [] args = ticketColumn.split("|");
        this.date               = args[13];
        this.secretStr          = args[0];
        this.trainCode          = args[3];
        this.fromStation        = args[6];
        this.toStation          = args[7];
        this.hasStandSeat       = this.getHasNum(args[26]);
        this.hasSeated          = this.getHasNum(args[29]);
        this.hasHardSleeper     = this.getHasNum(args[28]);
        this.hastSortSleeper    = this.getHasNum(args[23]);
        this.hasHighSortSleeper = this.getHasNum(args[21]);
        this.standSeatNum       = this.getNum(args[26]);
        this.seatedNum          = this.getNum(args[29]);
        this.hardSleeperNum     = this.getNum(args[28]);
        this.sortSleeper        = this.getNum(args[23]);
        this.highSortSleeperNum = this.getNum(args[21]);
    }

    private Boolean getHasNum (String one) {
        if (one.equals("无")) return false;
        return true;
    }

    private int getNum (String one) {
        if (one.equals("无")) return 0;
        int num = 20;
        try {
            num = Integer.parseInt(one);
        }catch (Exception e) {
            num = 20;
        }
        return num;
    }

    public Boolean match (TicketInput ticketInput) {
        List<String> trainCodeList = ticketInput.getTrainCode();
        Boolean trainCodeMatch = false;
        // 匹配的车次
        for (String trainCode  : trainCodeList) {
            if (this.trainCode.equals(trainCode)) {
                trainCodeMatch = true;
            }
        }
        if (!trainCodeMatch) return false;
        Boolean seatTypeMath = false;
        // 匹配的坐席
        List<String> seatTypeList = ticketInput.getSeatType();
        for (String seatType  : seatTypeList) {
            if ("高级软卧".equals(seatType)) {
                seatTypeMath = this.hasSeat(highSortSleeperNum, ticketInput.needNum);
                matchSeat = 5;
            } else if ("软卧".equals(seatType)) {
                seatTypeMath = this.hasSeat(sortSleeper, ticketInput.needNum);
                matchSeat = 4;
            } else if ("硬卧".equals(seatType)) {
                seatTypeMath = this.hasSeat(hardSleeperNum, ticketInput.needNum);
                matchSeat = 3;
            } else if ("硬座".equals(seatType)) {
                seatTypeMath = this.hasSeat(seatedNum, ticketInput.needNum);
                matchSeat = 1;
            } else if ("无座".equals(seatType)) {
                seatTypeMath = this.hasSeat(standSeatNum, ticketInput.needNum);
                matchSeat = 1;
            }
        }
        return seatTypeMath;
    }

    private Boolean hasSeat (int seatNum, int neadNum) {
        return seatNum >= neadNum ? true : false;
    }
}
