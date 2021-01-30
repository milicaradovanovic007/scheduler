package com.milicaradovanovic.sap.entity;

import javax.persistence.*;

@Entity
@Table(name = "schedule_task", schema = "mysql", catalog = "")
public class ScheduleTaskEntity {
    private Long id;
    private String name;
    private String recurrency;
    private String code;

    public void setId(Long id) {
        this.id = id;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    public Long getId() {
        return id;
    }

    @Basic
    @Column(name = "name", nullable = false, length = 250)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Basic
    @Column(name = "recurrency", nullable = false, length = 30)
    public String getRecurrency() {
        return recurrency;
    }

    public void setRecurrency(String recurrency) {
        this.recurrency = recurrency;
    }

    @Basic
    @Column(name = "code", nullable = false)
    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
