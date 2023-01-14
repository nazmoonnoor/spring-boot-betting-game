package com.yolo.bettinggame;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.yolo.bettinggame.dto.BetResponse;
import com.yolo.bettinggame.service.RandomService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.Date;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class GameControllerTests {

    @Autowired
    RandomService randomService;
    @Autowired
    private MockMvc mvc;

    @Test
    public void oneMillionRoundRTPCalculation() throws RuntimeException {

        try {
            long roundOfBet = 1_000_000;
            int wholeRandomNumber = randomService.getWholeRandomNumber();

            ExecutorService es = Executors.newFixedThreadPool(24);
            AtomicInteger totalCount = new AtomicInteger(0);
            for (int i = 0; i < roundOfBet; i++) {
                Future<Integer> val = es.submit(new BetApiTask(mvc, wholeRandomNumber));
                totalCount.addAndGet(val.get());
            }
            es.awaitTermination(1, TimeUnit.SECONDS);

            int totalWin = totalCount.get();
            System.out.println("Total win: " + totalWin);
            System.out.println("Calculated RTP: " + getRTP(roundOfBet, totalWin));
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } catch (ExecutionException e) {
            throw new RuntimeException(e);
        }
    }

    private static String getRTP(long roundOfBet, int totalWin) {
        double rtp = (double) (totalWin * 100) / roundOfBet;
        return rtp+"%";
    }
}

class BetApiTask implements Callable<Integer> {

    private MockMvc mvc;
    private int wholeRandomNumber;

    public BetApiTask(MockMvc mvc, int wholeRandomNumber) {

        this.mvc = mvc;
        this.wholeRandomNumber = wholeRandomNumber;
    }

    @Override
    public Integer call() throws RuntimeException {
        try {
            MvcResult response = mvc.perform(MockMvcRequestBuilders
                            .get("/api/bet/"+wholeRandomNumber)
                            .accept(MediaType.APPLICATION_JSON))
                            .andDo(print())
                            .andExpect(status().isOk()).andReturn();
            String json = response.getResponse().getContentAsString();
            BetResponse betResponse = new ObjectMapper().readValue(json, BetResponse.class);
            return betResponse.isWin() == true ? 1 : 0;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
