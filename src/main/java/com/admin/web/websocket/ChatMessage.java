package com.admin.web.websocket;

/**
 * ChatMessage
 *
 * @author zhaolei
 * @version 1.0
 * @since 2019/10/21
 */
public class ChatMessage {
    private String userName;
    private String message;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
