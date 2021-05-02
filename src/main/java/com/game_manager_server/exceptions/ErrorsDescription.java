package com.game_manager_server.exceptions;

public enum ErrorsDescription {
    GENERAL_SERVER_ERROR(1000, "General Server error"),
    INVALID_REQUEST_PARAMETER(1001, "Invalid request parameter"),
    BAD_REQUEST(1003, "Invalid request"),
    PERSISTENCY_OPERATION_ERROR(1002, "Persistence operation error");

    ErrorsDescription(int errorCode, String errorReason) {
        this.errorCode = errorCode;
        this.errorReason = errorReason;
    }

    private int errorCode;
    private String errorReason;

    public int getErrorCode() {
        return errorCode;
    }

    public String getErrorReason() {
        return errorReason;
    }
}
