package com.admin.web.websocket;

import com.corundumstudio.socketio.SocketIOServer;
import com.corundumstudio.socketio.Transport;
import com.corundumstudio.socketio.annotation.SpringAnnotationScanner;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.annotation.Resource;

/**
 * NettySocketConfig
 *
 * @author zhaolei
 * @version 1.0
 * @since 2019/10/21
 */
@Configuration
public class NettySocketConfig {

    private static final Logger logger = LoggerFactory.getLogger(NettySocketConfig.class);

    @Resource
    private WesocketConfig wesocketConfig;

    @Bean
    public SocketIOServer socketIOServer() {
        /*
         * 创建Socket，并设置监听端口
         */
        com.corundumstudio.socketio.Configuration config = new com.corundumstudio.socketio.Configuration();
        //获取本机地址
        try {
            String address = wesocketConfig.getIp();
            //socket监听端口
            int port = Integer.valueOf(wesocketConfig.getPort());
            config.setHostname(address);
            config.setTransports(Transport.POLLING, Transport.WEBSOCKET);
            config.setOrigin(null);
//            config.setTransports(Transport.POLLING, Transport.WEBSOCKET);
            // 设置监听端口
            config.setPort(port);
            // 协议升级超时时间（毫秒），默认10000。HTTP握手升级为ws协议超时时间
//		config.setUpgradeTimeout(25000);
            // Ping消息间隔（毫秒），默认25
            // 000。客户端向服务器发送一条心跳消息间隔
//		config.setPingInterval(25000);
            // Ping消息超时时间（毫秒），默认60000，这个时间间隔内没有接收到心跳消息就会发送超时事件
//		config.setPingTimeout(60000);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return new SocketIOServer(config);
    }

    @Bean
    public SpringAnnotationScanner springAnnotationScanner(SocketIOServer socketServer) {
        return new SpringAnnotationScanner(socketServer);
    }
}
