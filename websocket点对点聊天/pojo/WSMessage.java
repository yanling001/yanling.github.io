package com.bootexample.demo.pojo;

public class WSMessage {
    private String username;
    private Integer id;
    /**
     * 客户端标记
     */
    private String clientMark;

    /**
     * 内容
     */
    private String contents;

    /**
     * 消息类型，1.公告，2.点对点发消息，3.检查异地登录
     */
    private String type;
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getClientMark() {
        return clientMark;
    }

    public void setClientMark(String clientMark) {
        this.clientMark = clientMark;
    }

    public String getContents() {
        return contents;
    }

    public void setContents(String contents) {
        this.contents = contents;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }


}
