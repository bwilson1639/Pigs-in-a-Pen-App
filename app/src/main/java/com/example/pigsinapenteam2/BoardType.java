package com.example.pigsinapenteam2;

import java.util.Random;

public class BoardType {

  private BoardState inputtedState;
  private int width;
  private int height;

  //=======================================================================================

  public BoardType(BoardState inputtedState, int boardID, int boardSize){

    this.inputtedState = inputtedState;
    this.width = inputtedState.getWidth() - 1;
    this.height = inputtedState.getHeight() - 1;


    switch (boardID){


      case 2:

        gardenMapSetup(boardSize);
        break;

      case 3:

        hillMapSetup(boardSize);
        break;

      default:

        break;
    }//switch
  }//constructor



  private void gardenMapSetup(int boardSize){

    int voidedCell = 3;

    //sets corners as void
    inputtedState.setCellState(0, width, voidedCell);
    inputtedState.setTopWallState(0, width);
    inputtedState.setBottomWallState(0, width);
    inputtedState.setRightWallState(0, width);
    inputtedState.setLeftWallState(0,width);
    inputtedState.setCellState(height, 0, voidedCell);
    inputtedState.setTopWallState(height, 0);
    inputtedState.setBottomWallState(height, 0);
//    inputtedState.setRightWallState();
    inputtedState.setLeftWallState(height, 0);



    //if boardsize is not Small
    if(boardSize > 0){

      inputtedState.setCellState(1, width, voidedCell);
      inputtedState.setTopWallState(1, width);
      inputtedState.setRightWallState(1, width);
      inputtedState.setCellState(height - 1, 0, voidedCell);
      inputtedState.setBottomWallState(height - 1, 0);
      inputtedState.setLeftWallState(height - 1, 0);

    }//if statement

    if(boardSize > 1){

      inputtedState.setCellState(0, width -1, voidedCell);
      inputtedState.setTopWallState(0, width -1);
      inputtedState.setRightWallState(0, width - 1);
      inputtedState.setCellState(height, 1, voidedCell);
      inputtedState.setBottomWallState(height, 1);
      inputtedState.setLeftWallState(height, 1);

    }//if statement
  }//gardenMapSetup

  private void hillMapSetup(int boardSize){

    switch (boardSize){

      case 0:
        smallHillMapSetup();
        break;

      case 1:
        mediumHillMapSetup();
        break;

      case 2:
        largeHillMapSetup();
        break;

    }//switch
  }//hillMapSetup

  private void smallHillMapSetup(){

    int voidedCell = 3;

    inputtedState.setCellState(0, 1, voidedCell);
    inputtedState.setTopWallState(0,1);
    inputtedState.setBottomWallState(0,1);
    inputtedState.setBottomWallState(1,1);
    inputtedState.setCellState(1, 1, voidedCell);

  }//smallHillMapSetup

  private void mediumHillMapSetup(){

    int voidedCell = 3;

    inputtedState.setCellState(1,1, voidedCell);
    inputtedState.setRightWallState(1,1);
    inputtedState.setCellState(1,2, voidedCell);

  }//mediumHillMapSetup

  private void largeHillMapSetup(){

    int voidedCell = 3;

    inputtedState.setCellState(1, 2, voidedCell);
    inputtedState.setBottomWallState(1,2);
    inputtedState.setCellState(2,2, voidedCell);

  }//largeHillMapSetup


}
