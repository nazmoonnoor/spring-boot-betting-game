package com.yolo.bettinggame;

import com.yolo.bettinggame.dto.BetResponse;
import com.yolo.bettinggame.service.GameService;
import com.yolo.bettinggame.service.RandomService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@SpringBootTest
@AutoConfigureMockMvc
public class GameServiceTests {

    @MockBean
    RandomService randomService;

    @Autowired
    GameService gameService;

    @Test
    public void withUserValueCalculateRTP(){
        int userValue = 50;

        when(randomService.getBetRandomNumber()).thenReturn(40.5);

        BetResponse response = gameService.getBetResult(userValue);

        assertEquals(80.19, response.getResult());
    }
}
