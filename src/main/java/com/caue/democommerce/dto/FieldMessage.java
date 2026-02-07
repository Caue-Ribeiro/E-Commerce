package com.caue.democommerce.dto;

public class FieldMessage {

    private final String fieldName;

    private final String message;

    public FieldMessage(String name, String message) {
        this.fieldName = name;
        this.message = message;
    }

    public String getFieldName() {
        return fieldName;
    }

    public String getMessage() {
        return message;
    }
}
