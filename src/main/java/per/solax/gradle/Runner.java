package per.solax.gradle;

import per.solax.assist.base.SystemConstants;
import per.solax.framework.entity.RequestEntity;
import per.solax.framework.entity.SessionHttpRequest;
import per.solax.framework.top.AbstractFactory;
import per.solax.framework.process.AbstractRequestProcess;
import per.solax.framework.process.request.login.*;
import per.solax.framework.process.request.query.QueryPassenger;
import per.solax.framework.entity.Account;
import per.solax.assist.util.Log;

/**
 * @Author: solax
 * @Date: 2019/1/10
 */
public class Runner extends AbstractFactory {

    @Override
    public void run() {
        super.run();
        this.onlyQuery();
        //this.oneThread();
    }

    public void onlyQuery () {
        int i = 10;
        int b = 0;
        while (b < i) {
            this.query(null);
            try {
                Thread.sleep(10000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            b ++;
        }

        Log.debug("一个线程结束");
    }

    public void oneThread () {
        // login
        RequestEntity requestEntity = this.login();
        if (requestEntity == null) return;
        // query
        this.query(requestEntity);
        // order
        Log.debug("一个线程结束");
    }

    private RequestEntity login () {
        int count = 0;
        Boolean login = false;
        RequestEntity requestEntity = null;
        while (count < SystemConstants.loginNum) {
            count ++;
            requestEntity = this.loginOne();
            if (requestEntity != null) {
                login = true;
                count += SystemConstants.loginNum;
            } else {
                try {
                    Thread.sleep(5000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
        return requestEntity;
    }

    private RequestEntity loginOne () {
        // create a request domain
        RequestEntity requestEntity = new RequestEntity(
                new SessionHttpRequest(),
                new Account("kamiao00701", "binghe64")
        );
        // imageCode
        LoginImageCode imageCode = new LoginImageCode(requestEntity);
        if (!this.check(imageCode)) return null;
        // auth
        Auth auth = new Auth(requestEntity);

        // 看验证码是否正确
        CodeCheck codeCheck = new CodeCheck(requestEntity);
        if (!this.check(codeCheck)) return null;

        // login
        Login login = new Login(requestEntity);
        if (!this.check(login)) return null;

        auth = new Auth(requestEntity);

        // 获取用户基本信息
        UamAuthClient uamAuthClient = new UamAuthClient(requestEntity);
        if (!this.check(uamAuthClient)) return null;
        return requestEntity;
    }

    private void query (RequestEntity requestEntity) {
        if (requestEntity == null) {
            requestEntity = new RequestEntity(new SessionHttpRequest());
        }
      //  QueryTicket queryPassenger = new QueryTicket(requestEntity);
    }

    private void  passenger (RequestEntity requestEntity) {
        if (requestEntity == null) {
            requestEntity = new RequestEntity(new SessionHttpRequest());
        }
        QueryPassenger queryPassenger = new QueryPassenger(requestEntity);
    }

    private Boolean check (AbstractRequestProcess process) {
        if (process.isError) return false;
        return true;
    }
}
