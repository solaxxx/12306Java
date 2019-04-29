package per.solax.framework.process.request.common;

import org.apache.http.client.methods.CloseableHttpResponse;
import per.solax.assist.base.SystemConstants;
import per.solax.assist.base.url.UrlBaseEntity;
import per.solax.assist.util.*;
import per.solax.assist.verificationCode.Ruokuai;
import per.solax.framework.process.AbstractRequestBase;

import java.util.Date;
import java.util.Map;

/**
 * @Author: solax
 * @Date: 2019/3/20
 */
public class ImageCode extends AbstractRequestBase {


    public ImageCode (Map map, UrlBaseEntity urlBaseEntity) {
        this.map = map;
        this.urlEntity = urlBaseEntity;
    }


    private String sendRequest () {
        String result = null;
        Boolean loop = true;
        int loopCount = 0;
        while (loop && loopCount < SystemConstants.checkCodeNum) {
            loopCount ++;
            Log.debug("第" + loopCount + "次尝试获取登录验证码");
            result = this.getVerificationCode();
            if (CommonUtil.notEmpty(result)) {
                loop = false;
            } else {
                try {
                    Thread.sleep(5000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
        if (!CommonUtil.notEmpty(result)) {
            Log.debug("获取验证码失败超过"+ SystemConstants.checkCodeNum +"次，请手动检查是否ip被封");
            return result;
        }
        return result;
    }


    public String getAnswer () {
        return sendRequest();
    }

    private String getVerificationCode () {
        String position = null;
        HttpParams httpParams = new HttpParams();
        httpParams.putAll(this.map);
        CloseableHttpResponse response = this.requestEntity.sessionHttpRequest.sendRequest(
                this.urlEntity,
                httpParams.build());
        byte [] array = HttpUtil.resultToByteArray(response);
        // 验证码获取判断
        if (this.validate(array)) {
            return position;
        }
        // 打码平台
        position = this.validateCode(array);
        return position;
    }

    private Boolean validate (byte [] array) {
        Boolean result = false;
        if (array == null) {
            Log.debug("获取验证码失败，未得到图片流数据");
            return result;
        }
        return result;
    }

    private String validateCode (byte [] arrays) {
        String pos = null;
        try {
            String path = "./" + this.requestEntity.getAccount().getUsername();
            String fileName = String.valueOf(new Date().getTime()) + "tkcode.png";
            FileUtil.saveLocal(path, fileName, arrays);
        } catch (Exception e) {
            e.printStackTrace();
            Log.debug("图片本地存储出现错误");
            return pos;
        }
        try {
            Ruokuai ruokuai = new Ruokuai(arrays);
            pos = ruokuai.getAnswer();
            if (pos == null) {
                Log.debug("获取若快验证码错误");
                return pos;
            }
        } catch (Exception e) {
            e.printStackTrace();
            Log.debug("若快打码时出错");
            return pos;
        }
        return pos;
    }
}
