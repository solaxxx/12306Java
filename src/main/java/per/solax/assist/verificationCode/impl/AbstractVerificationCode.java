package per.solax.assist.verificationCode.impl;

/**
 * @Author: solax
 * @Date: 2019/1/15
 */
abstract public class AbstractVerificationCode {

    protected byte[] byteArr;

    public AbstractVerificationCode (byte[] byteArr) {
        this.byteArr = byteArr;
        this.verification();
    }

    public abstract void verification ();

    public abstract String getAnswer ();


    protected String [] getOnePos (char one) {
        int oneInt = Integer.parseInt(String.valueOf(one));
        int offsetsX = 0;
        int offsetsY = 0;
        switch (oneInt) {
            case 1:
                offsetsY = 77;
                offsetsX = 40;
                break;
            case 2:
                offsetsY = 77;
                offsetsX = 112;
                break;
            case 3:
                offsetsY = 77;
                offsetsX = 184;
                break;
            case 4:
                offsetsY = 77;
                offsetsX = 256;
                break;
            case 5:
                offsetsY = 149;
                offsetsX = 40;
                break;
            case 6:
                offsetsY = 149;
                offsetsX = 112;
                break;
            case 7:
                offsetsY = 149;
                offsetsX = 184;
                break;
            case 8:
                offsetsY = 149;
                offsetsX = 256;
                break;
        }
        String [] result = {String.valueOf(offsetsX), String.valueOf(offsetsY)};
        return result;
    }
}
