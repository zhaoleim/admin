package com.admin.web.websocket;

import com.corundumstudio.socketio.SocketIOClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class RequestDataStore {

    @Autowired
    Environment env;

    private static Map<String, MonitorItemVO> onemap = new ConcurrentHashMap<>();

    private static Map<String, MonitorItemVO> twomap = new ConcurrentHashMap<>();

    private static Map<String, MonitorItemVO> threemap = new ConcurrentHashMap<>();

    private static Map<String, MonitorItemVO> fourmap = new ConcurrentHashMap<>();

    private static Map<String, MonitorItemVO> fivemap = new ConcurrentHashMap<>();

    private static Map<String, SocketIOClient> pingmap = new ConcurrentHashMap<>();

    private static Map<String, SocketIOClient> msgClientMap = new ConcurrentHashMap<>();


    private static Map<String, Map<String, MonitorItemVO>> datamap = new ConcurrentHashMap<>();

    public void inintMap() {
        datamap.put("15", onemap);//3
        datamap.put("60", twomap);//12
        datamap.put("1440", threemap);//288
        datamap.put("10080", fourmap);//2016
        datamap.put("43200", fivemap);//8640

    }

    public Map<String, SocketIOClient> getMsgClientMap(){
        return msgClientMap;
    }

    public Map<String, SocketIOClient> getPingMap() {
        return pingmap;
    }

    //返回瞬时监控数据地址
    public String getRequestPath() {
        return env.getProperty("monitor.query.request");

    }

    //返回时间范围监控数据地址
    public String getRequestRangePath() {
        return env.getProperty("monitor.query_range.request");

    }

    // 根据时间返回对应的map
    public Map<String, MonitorItemVO> getSocketMsg(String num) {
        inintMap();
        return datamap.get(num);
    }

    // 根据时间返回对应的map
    public List<Map<String, MonitorItemVO>> getSocketList() {
        List<Map<String, MonitorItemVO>> list = new ArrayList<>();
        list.add(onemap);
        list.add(twomap);
        list.add(threemap);
        list.add(fourmap);
        list.add(fivemap);
        return list;
    }
}
