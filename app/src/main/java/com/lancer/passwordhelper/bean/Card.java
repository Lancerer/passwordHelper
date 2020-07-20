package com.lancer.passwordhelper.bean;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Generated;

/**
 * @author lancer
 * @des
 * @Date 2020/7/20 17:29
 */
@Entity
public  class Card {
    @Id(autoincrement = true)
    private Long id;
    private String name;
    private String account;
    private String password;
    private String webUrl;
    private int isCollect;
    @Generated(hash = 916241841)
    public Card(Long id, String name, String account, String password,
            String webUrl, int isCollect) {
        this.id = id;
        this.name = name;
        this.account = account;
        this.password = password;
        this.webUrl = webUrl;
        this.isCollect = isCollect;
    }
    @Generated(hash = 52700939)
    public Card() {
    }
    public Long getId() {
        return this.id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getName() {
        return this.name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getAccount() {
        return this.account;
    }
    public void setAccount(String account) {
        this.account = account;
    }
    public String getPassword() {
        return this.password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public String getWebUrl() {
        return this.webUrl;
    }
    public void setWebUrl(String webUrl) {
        this.webUrl = webUrl;
    }
    public int getIsCollect() {
        return this.isCollect;
    }
    public void setIsCollect(int isCollect) {
        this.isCollect = isCollect;
    }

}
