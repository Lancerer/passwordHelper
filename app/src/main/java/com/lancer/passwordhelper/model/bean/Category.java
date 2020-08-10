package com.lancer.passwordhelper.model.bean;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.NotNull;
import org.greenrobot.greendao.annotation.Property;

import java.io.Serializable;

/**
 * @author lancer
 * @des
 * @Date 2020/7/20 16:30
 */
@Entity
public class Category implements Serializable {
    private static final long serialVersionUID = -414628076430975324L;
    @Id(autoincrement = true)
    private Long id;
    @NotNull
    @Property(nameInDb = "CATEGORY_NAME")
    private String categoryName;

    @Generated(hash = 984232919)
    public Category(Long id, @NotNull String categoryName) {
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

    @Override
    public String toString() {
        return "Category{" +
                "id=" + id +
                ", categoryName='" + categoryName + '\'' +
                '}';
    }
}
