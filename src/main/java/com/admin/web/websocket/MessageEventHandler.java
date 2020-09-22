package com.admin.web.websocket;

import com.corundumstudio.socketio.AckRequest;
import com.corundumstudio.socketio.SocketIOClient;
import com.corundumstudio.socketio.SocketIOServer;
import com.corundumstudio.socketio.annotation.OnConnect;
import com.corundumstudio.socketio.annotation.OnDisconnect;
import com.corundumstudio.socketio.annotation.OnEvent;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * 消息事件处理器
 *
 * @author zhaolei
 * @version 1.0
 * @since 2019/10/21
 */
@Component
public class MessageEventHandler {

    private final SocketIOServer server;

    @Autowired
    private RequestDataStore requestDataStore;

    public final static SimpleDateFormat sdfTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    private static final Logger logger = LoggerFactory.getLogger(MessageEventHandler.class);

    @Autowired
    public MessageEventHandler(SocketIOServer server) {
        this.server = server;
    }

    /**
     * 添加connect事件，当客户端发起连接时调用
     *
     * @param client
     */
    @OnConnect
    public void onConnect(SocketIOClient client) {
        Map<String, SocketIOClient> dataMap = null;
        if (client != null) {
            logger.info("websocket请求已连接成功, sessionId=" + client.getSessionId());
        } else {
            logger.error("客户端为空");
        }
    }


    /**
     * 添加@OnDisconnect事件，客户端断开连接时调用，刷新客户端信息
     *
     * @param client
     */
    @OnDisconnect
    public void onDisconnect(SocketIOClient client) {
        logger.info("客户端断开连接, sessionId=" + client.getSessionId().toString());
        List<Map<String, MonitorItemVO>> list = requestDataStore.getSocketList();
        Map<String, SocketIOClient> pingmap = requestDataStore.getPingMap();// 获取任务存储map
        Map<String, SocketIOClient> msgClientMap = requestDataStore.getMsgClientMap();
        for (Map<String, MonitorItemVO> map : list) {
//            System.out.println("=================================map:" + map.size());
            map.remove(client.getSessionId() + "cpu");
            map.remove(client.getSessionId() + "mem");
            map.remove(client.getSessionId() + "disk");
            map.remove(client.getSessionId() + "net");
//            System.out.println("=================================map:" + map.size());
        }
        //关闭连接则将消息推送接收人剔除
        for(Map.Entry<String, SocketIOClient> entry : msgClientMap.entrySet()){
            String userId = entry.getKey();
            SocketIOClient entryClient = entry.getValue();
            if(client.getSessionId().equals(entryClient.getSessionId())){
                msgClientMap.remove(userId);
                break;
            }
        }
        if (pingmap != null && pingmap.size() > 0) {
            pingmap.remove("ping");
        }
        client.sendEvent("disconnect", "The client is disconnect!");
        client.disconnect();
    }


    // 消息接收入口
    @OnEvent(value = "itemEvent")
    public void initEvent(SocketIOClient client, AckRequest ackRequest, MonitorItemVO param)
            throws InterruptedException {
        Map<String, MonitorItemVO> dataMap = null;
        Map<String, MonitorItemVO> newDataMap = null;
        logger.info(">>>>>触发任务新建事件itemEvent<<<<<" + param);

        if (client.isChannelOpen()) {
            param.setClient(client);
            logger.info("websocket请求参数:", "************param" + param);
            if (!param.getLastTime().equals(param.getTime())) {
                dataMap = requestDataStore.getSocketMsg(param.getLastTime());// 获取原始任务容器
                MonitorItemVO monitoritemTask = dataMap.get(client.getSessionId() + param.getRequestType());// 获取监控请求参数
                newDataMap = requestDataStore.getSocketMsg(param.getTime());// 获取新的任务集合
                newDataMap.put(client.getSessionId() + param.getRequestType(), monitoritemTask);// 添加新任务
                dataMap.remove(client.getSessionId() + param.getRequestType());// 移除旧任务

            } else {

                // 组装请求参数
                dataMap = requestDataStore.getSocketMsg(param.getTime());
                System.out.println("===========" + dataMap);
                dataMap.put(client.getSessionId() + param.getRequestType(), param);
            }

        } else {
            client.sendEvent("itemEvent", "客户端已断开连接!!!");
        }
    }
    @OnEvent(value = "msgEvent")
    public void msgEvent(SocketIOClient client, AckRequest ackRequest, UserId userId){

        Map<String, SocketIOClient> msgClientMap = null;
        logger.info(">>>>>触发消息事件msgEvent<<<<<" + userId);
        if (client.isChannelOpen()) {
            msgClientMap = requestDataStore.getMsgClientMap();
            if(StringUtils.isEmpty(userId.getUserId())){
                return;
            }
            msgClientMap.put(userId.getUserId(), client);
        }
    }

    @OnEvent(value = "disMsgEvent")
    public void disMsgEvent(SocketIOClient client, AckRequest ackRequest, UserId userId){

        //关闭连接则将消息推送接收人剔除
        logger.info(">>>>>触发断开消息事件disMsgEvent<<<<<" + userId);
        Map<String, SocketIOClient> msgClientMap = requestDataStore.getMsgClientMap();
        msgClientMap.remove(userId.getUserId());

    }


    // 集群消息入口
    @OnEvent(value = "clusterEvent")
    public void clusterEvent(SocketIOClient client, AckRequest ackRequest, MonitorItemVO param)
            throws InterruptedException {
//        System.out.println("**********************" + param);
        Map<String, MonitorItemVO> dataMap = null;
        Map<String, MonitorItemVO> newDataMap = null;
        logger.info(">>>>>触发任务新建事件clusterEvent<<<<<" + param);

        if (client.isChannelOpen()) {
            param.setClient(client);
//            logger.info("websocket请求参数:", "************param" + param);
            if (StringUtils.isNotEmpty(param.getLastTime())) {
                if (!param.getLastTime().equals(param.getTime())) {
                    dataMap = requestDataStore.getSocketMsg(param.getLastTime());// 获取原始任务容器
                    MonitorItemVO monitoritemTask = dataMap.get(client.getSessionId() + param.getRequestType());// 获取监控请求参数
                    newDataMap = requestDataStore.getSocketMsg(param.getTime());// 获取新的任务集合
                    newDataMap.put(client.getSessionId() + param.getRequestType(), monitoritemTask);// 添加新任务
                    dataMap.remove(client.getSessionId() + param.getRequestType());// 移除旧任务
                }
            } else {
                // 组装请求参数
                dataMap = requestDataStore.getSocketMsg(param.getTime());
//                System.out.println("===========" + dataMap);
                dataMap.put(client.getSessionId() + param.getRequestType(), param);
            }

        } else {
            client.sendEvent("clusterEvent", "客户端已断开连接!!!");
        }
    }

    // 消息销毁入口
    @OnEvent(value = "disEvent")
    public void disEvent(SocketIOClient client, AckRequest ackRequest, ChatMessage chat) throws InterruptedException {
        Map<String, MonitorItemVO> dataMap = null;
        logger.info(">>>>>触发任务销毁事件disEvent<<<<<");
        if (client.isChannelOpen()) {
            List<Map<String, MonitorItemVO>> list = requestDataStore.getSocketList();
            for (Map<String, MonitorItemVO> map : list) {
//                System.out.println("=================================map:" + map.size());
                map.remove(client.getSessionId() + "cpu");
                map.remove(client.getSessionId() + "mem");
                map.remove(client.getSessionId() + "disk");
                map.remove(client.getSessionId() + "net");
//                System.out.println("=================================map:" + map.size());
            }
            client.sendEvent("disEvent", "容器数据清理完成!!!!");
            logger.info(">>>>>disEvent容器数据清理完成!!!<<<<<");
        } else {
            client.sendEvent("disEvent", "客户端已断开连接!!!");
        }
    }

    // 消息接收入口
    @OnEvent(value = "ding")
    public void heartEvent(SocketIOClient client, AckRequest ackRequest, MonitorItemVO param)
            throws InterruptedException {
        logger.info(">>>>>触发任务心跳事件heartEvent<<<<<");
        if (client.isChannelOpen()) {
//            System.out.println("=====================" + param);
            if (param.getDing().equals("ding")) {
                client.sendEvent("dong", "dong");
            }
        }
    }

    public static String getTime() {
        return sdfTime.format(new Date());
    }

//    // 登录接口
//    @OnEvent(value = "login")
//    public void onLogin(SocketIOClient client, AckRequest ackRequest, LoginRequest message) {
//        logger.info("接收到客户端登录消息");
//        if (ackRequest.isAckRequested()) {
//            ackRequest.sendAckData("服务器回答login", message.getCode(), message.getBody());
//        }
//    }
}
