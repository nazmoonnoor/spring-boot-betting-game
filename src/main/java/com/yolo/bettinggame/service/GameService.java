package com.yolo.bettinggame.service;

import com.yolo.bettinggame.dto.BetResponse;

public interface GameService {

    BetResponse getBetResult(int userValue);
}
