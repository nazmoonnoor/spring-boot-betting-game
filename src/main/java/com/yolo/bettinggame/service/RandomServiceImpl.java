package com.yolo.bettinggame.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class RandomServiceImpl implements RandomService {

    @Override
    public double getBetRandomNumber() throws RuntimeException {

        try {
            return (Math.random() * 100 + 1);
        } catch (NumberFormatException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public int getWholeRandomNumber() throws RuntimeException {

        try {
            return (int) (Math.random() * 100 + 1);
        } catch (NumberFormatException e) {
            throw new RuntimeException(e);
        }
    }
}
