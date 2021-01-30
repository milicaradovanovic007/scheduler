package com.milicaradovanovic.sap.dto;

public enum StatusEnum {
    OK(2000, "Success"),
    RESOURCE_UPDATED(1000, "Resource updated successfully"),
    RESOURCE_FOUND(1001, "Resource found"),
    RESOURCE_CREATED(1002, "Resource created successfully"),
    RESOURCE_DELETED(1003, "Resource deleted successfully"),

    RESOURCE_NOT_FOUND(4000, "Resource not found"),
    FORBIDDEN_ACCESS(4001, "Access is forbidden, You need to be an administrator"),
    METHOD_UNSUPPORTED(4002, "Method not supported"),
    BAD_CREDENTIALS(4003, "Bad credentials"),
    BODY_VALIDATION_ERROR(4004, "Invalid parameters in request body");

    private int code;
    private String message;

    StatusEnum(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public int getCode() {
        return this.code;
    }

    public String getMessage() {
        return this.message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
