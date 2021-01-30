package com.milicaradovanovic.sap.dto;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.HashMap;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class ResponseDTO<T> {

    private boolean isError;
    private StatusEnum status;
    private T payload;
    private Object errors;

    public ResponseDTO(StatusEnum status, T payload) {
        this.isError = false;
        this.status = status;
        this.payload = payload;
    }

    public ResponseDTO(boolean isError, StatusEnum status, Object error) {
        this.isError = isError;
        this.status = status;
        this.payload = null;
        this.errors = error;
    }

    public boolean getIsError() {
        return isError;
    }

    public void setError(boolean error) {
        isError = error;
    }

    public T getPayload() {
        return payload;
    }

    public void setPayload(T payload) {
        this.payload = payload;
    }

    public Object getErrors() {
        return errors;
    }

    public void setErrors(Object errors) {
        this.errors = errors;
    }

    public void setStatus(StatusEnum status) {
        this.status = status;
    }

    public HashMap<String, Object> getStatus() {
        HashMap<String, Object> map = new HashMap<>();

        map.put("code", status.getCode());
        map.put("message", status.getMessage());

        return map;
    }
}
