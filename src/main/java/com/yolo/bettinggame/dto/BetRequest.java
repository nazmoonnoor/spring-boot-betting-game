package com.yolo.bettinggame.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class BetRequest {

    @NotNull(message = "Bet number is mandatory")
    private Number number;
}
