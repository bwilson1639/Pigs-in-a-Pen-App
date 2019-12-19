package com.example.pigsinapenteam2;

import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.*;

public class PlayScreenTest {


  @Test
  public void totalScoreCalculator() {
    PlayScreen tester = new PlayScreen();

    if(tester.boardSize == 1){
      tester.width = 4;
      tester.height = 3;
    }
    System.out.println(tester.width * tester.height);
    System.out.println(tester.totalScoreCalculator());
    assertEquals(8, tester.totalScoreCalculator());

    }
}