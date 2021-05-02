package com.game_manager_server.exceptions;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Collections;
import java.util.List;

@Data
@AllArgsConstructor
public class ErrorDto {
    private int errorCode;
    private String errorReason;
    private List<String> description;

    public ErrorDto(ErrorsDescription errorsDescription, String description) {
        this.errorCode = errorsDescription.getErrorCode();
        this.errorReason = errorsDescription.getErrorReason();
        this.description = Collections.singletonList(description);
    }

    public ErrorDto(ErrorsDescription errorsDescription, List<String> description) {
        this.errorCode = errorsDescription.getErrorCode();
        this.errorReason = errorsDescription.getErrorReason();
        this.description = description;
    }

}
