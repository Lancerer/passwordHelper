package com.lancer.passwordhelper.bean;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Generated;

/**
 * @author lancer
 * @des
 * @Date 2020/7/20 16:30
 */
@Entity
public class Category {
    @Id(autoincrement = true)
    private Long id;
    private String categoryName;
    @Generated(hash = 686479975)
    public Category(Long id, String categoryName) {
        this.id = id;
        this.categoryName = categoryName;
    }
    @Generated(hash = 1150634039)
    public Category() {
    }

    public Category(String categoryName) {
        this.categoryName = categoryName;
    }

    public Long getId() {
        return this.id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getCategoryName() {
        return this.categoryName;
    }
    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }
   
}
