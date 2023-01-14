package com.yolo.bettinggame.service;

import com.yolo.bettinggame.dto.BetResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GameServiceImpl implements GameService {

    private RandomService randomService;

    @Autowired
    public GameServiceImpl(RandomService randomService){

        this.randomService = randomService;
    }

    @Override
    public BetResponse getBetResult(int userValue) {

        // Obtain a random number
        double betRandom = randomService.getBetRandomNumber();

        if (userValue > betRandom)
            return calculateRTP(userValue, betRandom);

        return BetResponse.builder()
                .result(0)
                .isWin(false)
                .build();
    }

    private BetResponse calculateRTP(int userValue, double betRandom) {

        double result = betRandom * (99.0 / (100 - userValue));
        return BetResponse.builder()
                .result(result)
                .isWin(true)
                .build();
    }
}
