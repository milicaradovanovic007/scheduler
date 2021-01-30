package com.milicaradovanovic.sap.dto;

public class ScheduleTaskDTO {
    private Integer id;
    private String name;
    private String recurrency;
    private String code;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRecurrency() {
        return recurrency;
    }

    public void setRecurrency(String recurrency) {
        this.recurrency = recurrency;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
