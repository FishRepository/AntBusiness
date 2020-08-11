package com.api.customer.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * 推送到客户端消息记录
 */
public class PushLog implements Serializable {
    private static final long serialVersionUID = 7413093335239777280L;

    private Integer id;
    private Integer account_id;//用户id
    private String customers;//一次推送的客户id集合 英文半角逗号","分隔
    private Integer type;//提醒类型  1例假2生日
    private String message;//推送消息文本
    private Date create_time;//推送时间
    private Integer consume_state;//消费状态 0未消费 1已消费

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getAccount_id() {
        return account_id;
    }

    public void setAccount_id(Integer account_id) {
        this.account_id = account_id;
    }

    public String getCustomers() {
        return customers;
    }

    public void setCustomers(String customers) {
        this.customers = customers;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Date getCreate_time() {
        return create_time;
    }

    public void setCreate_time(Date create_time) {
        this.create_time = create_time;
    }

    public Integer getConsume_state() {
        return consume_state;
    }

    public void setConsume_state(Integer consume_state) {
        this.consume_state = consume_state;
    }
}
