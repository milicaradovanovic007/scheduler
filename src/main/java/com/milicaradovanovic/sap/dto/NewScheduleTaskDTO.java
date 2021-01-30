package com.milicaradovanovic.sap.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class NewScheduleTaskDTO {
    @NotNull
    @NotBlank
    private String name;

    @NotNull
    @NotBlank
    private String recurrency;

    @NotNull
    @NotBlank
    private String code;

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
