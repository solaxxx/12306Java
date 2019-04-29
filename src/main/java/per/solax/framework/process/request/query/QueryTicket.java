package per.solax.framework.process.request.query;

import org.apache.http.client.methods.CloseableHttpResponse;
import per.solax.assist.base.Global;
import per.solax.assist.base.url.Url;
import per.solax.framework.entity.Order;
import per.solax.framework.entity.RequestEntity;
import per.solax.framework.entity.TicketInput;
import per.solax.framework.entity.TicketQuery;
import per.solax.framework.process.AbstractRequestProcess;
import per.solax.assist.util.DateUtil;
import per.solax.assist.util.HttpParams;
import per.solax.assist.util.HttpUtil;
import per.solax.assist.util.Log;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @Author: solax
 * @Date: 2019/1/18
 */
public class QueryTicket  extends AbstractRequestProcess {

    // query ticket info
    TicketInput ticketInput;

    Order order;

    public QueryTicket(RequestEntity requestEntity, TicketInput ticketInput) {
        super(requestEntity);
        this.ticketInput = ticketInput;
    }

    @Override
    protected void before() {

    }

    @Override
    protected void send() {
        HttpParams httpParams = new HttpParams();
        httpParams.add("leftTicketDTO.train_date",      ticketInput.getDate());
        httpParams.add("leftTicketDTO.from_station",    ticketInput.getFromStation());
        httpParams.add("leftTicketDTO.to_station",      ticketInput.getToStation());
        httpParams.add("purpose_codes", "ADULT");
        CloseableHttpResponse closeableHttpResponse = this.requestEntity.sessionHttpRequest.sendRequest(
                Url.queryTicket,
                httpParams.build()
        );
        this.requestEntity.getResponseStore().setResultMap(HttpUtil.resultToMap(closeableHttpResponse));
    }

    @Override
    protected void after() {
       // Log.debug("查询订票信息:" + this.getResultMap() );
        try {
            Map result = this.getResultMap();
            Map data = (Map)result.get("data");
            List<String> resultList =  (List)data.get("result");
            if (resultList ==null  || resultList.size() == 0) {
                Log.debug("未找到一条车次信息，请确认查询条件是否正确");
                return;
            } else {
                List <TicketQuery> allTicket = this.getTicketQueryList(resultList);
                List <TicketQuery> filerTicket = this.getMathTicket(allTicket);
                TicketQuery ticketQuery = this.maxScoreOne(filerTicket);
                order = this.generateOrder(ticketQuery);
            }
        } catch (Exception e) {
            Log.debug("解析查询结果错误");
            e.printStackTrace();
        }
    }


    /**
     * 转换为对象
     * @param resultList
     */
    protected List <TicketQuery> getTicketQueryList (List<String> resultList) {
        List <TicketQuery> ticketQueries = new ArrayList<>();
        for (String one : resultList) {
            ticketQueries.add(new TicketQuery(one));
        }
        return ticketQueries;
    }


    /**
     * 过滤出可以订票的车次
     * @param ticketQueries
     */
    private List <TicketQuery> getMathTicket (List <TicketQuery> ticketQueries) {
        List <TicketQuery> ticketQueriesResult = new ArrayList<>();
        if (ticketQueries == null  || ticketQueries.size() == 0) return ticketQueries;
        for (TicketQuery ticketQuery: ticketQueries) {
            if (ticketQuery.match(ticketInput)) {
                ticketQueriesResult.add(ticketQuery);
            }
        }
        return ticketQueriesResult;
    }

    /**
     * 最高权重的票
     * @param ticketQueries
     * @return
     */
    private TicketQuery maxScoreOne (List <TicketQuery> ticketQueries) {
        if (ticketQueries == null  || ticketQueries.size() == 0) return null;
        return ticketQueries.get(0);
    }


    private Order generateOrder (TicketQuery ticketQuery) {
        if (ticketQuery == null) return null;
        Order order = new Order(ticketQuery);
        return order;
    }
}
