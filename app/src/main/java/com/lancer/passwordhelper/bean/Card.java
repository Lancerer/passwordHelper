package com.lancer.passwordhelper.bean;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.NotNull;
import org.greenrobot.greendao.annotation.Property;

import java.io.Serializable;

/**
 * @author lancer
 * @des
 * @Date 2020/7/20 17:29
 */
@Entity
public class Card implements Serializable {

    private static final long serialVersionUID = -8773931339633931708L;
    @Id(autoincrement = true)
    @Property(nameInDb = "id")
    private Long id;

    @Property(nameInDb = "NAME")
    private String name;

    @NotNull
    @Property(nameInDb = "ACCOUNT")
    private String account;

    @NotNull
    @Property(nameInDb = "PASSWORD")
    private String password;

    @Property(nameInDb = "WEB_URL")
    private String webUrl;

    @Property(nameInDb = "FOLDER")
    private String folder;

    @Property(nameInDb = "REMARK")
    private String remark;

    private int isCollect;

    @Generated(hash = 1262416488)
    public Card(Long id, String name, @NotNull String account, @NotNull String password,
            String webUrl, String folder, String remark, int isCollect) {
        this.id = id;
        this.name = name;
        this.account = account;
        this.password = password;
        this.webUrl = webUrl;
        this.folder = folder;
        this.remark = remark;
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

    public String getFolder() {
        return this.folder;
    }

    public void setFolder(String folder) {
        this.folder = folder;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    @Override
    public String toString() {
        return "Card{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", account='" + account + '\'' +
                ", password='" + password + '\'' +
                ", webUrl='" + webUrl + '\'' +
                ", folder='" + folder + '\'' +
                ", remark='" + remark + '\'' +
                ", isCollect=" + isCollect +
                '}';
    }
}
