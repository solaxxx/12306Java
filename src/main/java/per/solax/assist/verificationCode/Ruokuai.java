package per.solax.assist.verificationCode;

import per.solax.assist.base.Global;
import per.solax.assist.module.RuoKuaiBase;
import per.solax.assist.util.CommonUtil;
import per.solax.assist.util.Log;
import per.solax.assist.verificationCode.impl.AbstractVerificationCode;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @Author: solax
 * @Date: 2019/1/15
 */
public class Ruokuai extends AbstractVerificationCode {

    final static String SOFT_ID = "120010";

    final static String SOFT_KEY = "06dd886b595a489d96ab9228833ad9a1";

    final static String RUO_KUAI_URL = "http://api.ruokuai.com/create.json";

    final static String TYPE_ID = "6113";

    final static String TIME_OUT = "60";

    String result;

    public Ruokuai(byte[] byteArr) {
        super(byteArr);
    }


    @Override
    public void verification() {
        result = this.createByPost(this.byteArr);
    }

    @Override
    public String getAnswer() {
        Map map = CommonUtil.stringToMap(this.result);
        String reqResult = (String)map.get("Result");
        //String reqResult = "156";
        List list = this.getPos(reqResult);
        String result = this.toResult(list);
        return result;
    }


    private String createByPost(byte[] byteArr) {
        String result = "";
        try {
            String param = String
                    .format(
                            "username=%s&password=%s&typeid=%s&timeout=%s&softid=%s&softkey=%s",
                            Global.getInstance().getRuokuaiUserName(), Global.getInstance().getRuokuaiPassword(), TYPE_ID, TIME_OUT, SOFT_ID, SOFT_KEY);
            result = RuoKuaiBase.httpPostImage(RUO_KUAI_URL, param, byteArr);
        } catch (Exception e) {
            Log.debug("答题出错");
            e.printStackTrace();
        }
        Log.debug("若快获取到的验证码结果为：" + result);
        return result;
    }


    private List getPos (String result) {
        List posList = new ArrayList<>();
        char[] dst = result.toCharArray();
        for (char one : dst) {
            posList.add(this.getOnePos(one));
        }
        return posList;
    }

    private String toResult (List <String []> list) {
        List <String> tempList = new ArrayList<String>();
        for (String [] one : list) {
            tempList.add(String.join(",", one));
        }
        String result = String.join(",", tempList);
        return result;
    }
}
