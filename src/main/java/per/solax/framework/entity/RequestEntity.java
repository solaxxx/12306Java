package per.solax.framework.entity;

/**
 * @Author: solax
 * @Date: 2019/1/13
 */
public class RequestEntity {

    protected Account account;

    public SessionHttpRequest sessionHttpRequest = null;

    ResponseStore responseStore;

    public RequestEntity (SessionHttpRequest sessionHttpRequest, Account account) {
        this.account = account;
        this.sessionHttpRequest = sessionHttpRequest;
        this.init();
    }

    public RequestEntity (SessionHttpRequest sessionHttpRequest) {
        this.sessionHttpRequest = sessionHttpRequest;
        this.init();
    }

    public RequestEntity () {
        this.init();
    }

    private void init () {
        responseStore = new ResponseStore();
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    public SessionHttpRequest getSessionHttpRequest() {
        return sessionHttpRequest;
    }

    public void setSessionHttpRequest(SessionHttpRequest sessionHttpRequest) {
        this.sessionHttpRequest = sessionHttpRequest;
    }

    public ResponseStore getResponseStore() {
        return responseStore;
    }

    public void setResponseStore(ResponseStore responseStore) {
        this.responseStore = responseStore;
    }
}
