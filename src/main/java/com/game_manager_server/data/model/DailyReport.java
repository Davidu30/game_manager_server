package com.game_manager_server.data.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({"dayNum", "startTime", "endTime", "workDurationMin"})
public class DailyReport {
    //timeClock

    @JsonProperty("dayNum")
    private final int dayNum;

    @JsonProperty("startTime")
    private final long startTime;

    @JsonProperty("endTime")
    private final long endTime;

    @JsonProperty("workDurationMin")
    private final long workDurationMin;

    public DailyReport(int dayNum, long startTime, long endTime) {
        this.dayNum = dayNum;
        this.startTime = startTime;
        this.endTime = endTime;
        workDurationMin = (endTime - startTime) / 60000;
    }

}
