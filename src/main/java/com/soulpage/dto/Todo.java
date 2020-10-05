/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.soulpage.dto;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

/**
 *
 * @author Charan Teja
 */
@Entity
@Table(name = "todo")
public class Todo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(columnDefinition = "integer default 0")
    private Integer parent_id;
    @Column
    private String name;
    @Column
    private String description;
    @Column(columnDefinition = "boolean default false")
    private Boolean isFavourite = false;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "timestamp", nullable = false,
            columnDefinition = "TIMESTAMP default CURRENT_TIMESTAMP on update CURRENT_TIMESTAMP")
    private Date timestamp = new Date();

    @Transient
    private List<Todo> subTodoList = new ArrayList<>();

    public List<Todo> getSubTodoList() {
        return subTodoList;
    }

    public void setSubTodoList(List<Todo> subTodoList) {
        this.subTodoList = subTodoList;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getParent_id() {
        return parent_id;
    }

    public void setParent_id(int parent_id) {
        this.parent_id = parent_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isIsFavourite() {
        return isFavourite;
    }

    public void setIsFavourite(boolean isFavourite) {
        this.isFavourite = isFavourite;
    }

    public Date getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }

    @Override
    public String toString() {
        return "Todo{" + "id=" + id + ", parent_id=" + parent_id + ", name=" + name + ", description=" + description + ", isFavourite=" + isFavourite + ", timestamp=" + timestamp + ", subTodoList=" + subTodoList + '}';
    }

}
