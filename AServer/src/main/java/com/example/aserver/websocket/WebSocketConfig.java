package com.example.aserver.websocket;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.server.standard.ServerEndpointExporter;

/**
 * @author chen jia hao
 */
@Configuration
public class WebSocketConfig {
    /**
     * 支持websocket
     * @return
     */
    @Bean
    public ServerEndpointExporter createServerEndExporter(){
        return new ServerEndpointExporter();
    }
}