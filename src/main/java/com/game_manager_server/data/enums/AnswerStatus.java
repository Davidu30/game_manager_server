package com.game_manager_server.data.enums;

import java.util.stream.Stream;

public enum AnswerStatus {

    WRONG(0), RIGHT(1);

    private int value;

    AnswerStatus(int value) {
        this.value = value;
    }

    AnswerStatus getAnswerStatus(String status) {
        return Stream.of(AnswerStatus.values()).filter(a -> a.name().equalsIgnoreCase(status)).findFirst()
                .orElseThrow(() -> new IllegalStateException(String.format("Unsupported answer status %s.", status)));
    }
    public int getValue() {
        return value;
    }
}
