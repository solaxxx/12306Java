package per.solax.framework.entity;

import per.solax.framework.process.request.login.LeftTicketInit;

/**
 * @Author: solax
 * @Date: 2019/1/10
 */
public class Account {

    String username;

    String password;

    // 乘客管理器
    PassengerManager passengerManager;

    public Account (String username, String password) {
        this.username = username;
        this.password = password;
    }

    public Account () {}

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * entry
     */
    public void start () {
        // check user
        LeftTicketInit leftTicketInit = new LeftTicketInit();
    }

    public PassengerManager getPassengerManager() {
        return passengerManager;
    }

    public void setPassengerManager(PassengerManager passengerManager) {
        this.passengerManager = passengerManager;
    }
}
