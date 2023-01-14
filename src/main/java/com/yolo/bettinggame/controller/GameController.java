package com.yolo.bettinggame.controller;

import com.yolo.bettinggame.dto.BetResponse;
import com.yolo.bettinggame.service.GameServiceImpl;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
@Validated
@Slf4j
public class GameController {

    private GameServiceImpl gameService;

    @Autowired
    public GameController(GameServiceImpl gameService) {

        this.gameService = gameService;
    }

    @GetMapping("/bet/{value}")
    public ResponseEntity<BetResponse> getBetResult(@PathVariable @Min(1) @Max(100) Integer value) {

        BetResponse response = gameService.getBetResult(value);
        log.trace("Bet win : ", response.isWin());
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}