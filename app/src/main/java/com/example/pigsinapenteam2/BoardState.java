package com.example.pigsinapenteam2;

/**
 * stores the state of the board and all inputted data.
 * The board is a 3D array with a format as follows:
 * [x coordinate][y coordinate][wall data and cell data]
 * for wall data and cell data, 0-3 are for the four walls of the cell, and
 * 4 is for the cell data itself
 */
public class BoardState {

  private int[][][] boardData;
  private int width;
  private int height;

  /**
   * default constructor for BoardState. Sets the board as a 10x10 empty board
   *
   */
  public BoardState() {

    boardData = new int[10][10][5];
    width = 10;
    height = 10;
  }//default constructor

  /**
   * contructor for BoardState. creates a board of inputted size and assigns class
   * variables.
   * @param inputtedWidth
   * @param inputtedHeight
   */
  public BoardState(int inputtedWidth, int inputtedHeight){

    boardData = new int[inputtedWidth][inputtedHeight][5];
    width = inputtedWidth;
    height = inputtedHeight;
  }//constructor

  public int getWidth(){

    return width;

  }//getWidth

  public int getHeight(){

    return height;

  }//getHeight

  /**
   * gets data from the saved cell data at the specified coordinates
   * @param xCoord
   * @param yCoord
   * @return    the int value at [xCoord][yCoord][4]
   */
  public int getCellState(int xCoord, int yCoord){

    return boardData[xCoord][yCoord][4];

  }//getCellState

  /**
   * sets data for the cell state at the specified coordinates
   * @param xCoord
   * @param yCoord
   * @param cellInput the new int value at [xCoord][yCoord][4]
   */
  public void setCellState(int xCoord, int yCoord, int cellInput){

    if(xCoord > width){
      return;

    }//if statement

    if(yCoord > height){
      return;
    }//if statement

    boardData[xCoord][yCoord][4] = cellInput;

  }//setCellState

  /**
   * gets the top wall at specified coordinates             *----------*   <== retrieves this
   * @param xCoord                                          |          |
   * @param yCoord                                          |          |
   * @return the data of the top wall                       *----------*
   */
  public int getTopWallState(int xCoord, int yCoord){

    if(xCoord > width){
      return -1;
    }//if statement

    if(yCoord > height){
      return -1;
    }//if statement

    return boardData[xCoord][yCoord][0];

  }//getTopWallState

  /**
   * sets the top wall at specified coordinates             *----------*   <== sets this
   * @param xCoord                                          |          |
   * @param yCoord                                          |          |
   * @param wallInput                                       *----------*
   */
  public void setTopWallState(int xCoord, int yCoord, int wallInput){

    if(xCoord > width) {
      return;
    }//if statement

    if(yCoord > height){
      return;
    }// if statement

    boardData[xCoord][yCoord][0] = wallInput;

    if(yCoord > 0){
      boardData[xCoord][yCoord - 1][2] = wallInput;

    }//if statement

  }//setTopWallState

  /**
   * gets the right wall at specified coordinates           *----------*
   * @param xCoord                                          |          | <== retrieves this
   * @param yCoord                                          |          |
   * @return the data of the right wall                     *----------*
   */
  public int getRightWallState(int xCoord, int yCoord){

    if(xCoord > width){
      return -1;
    }//if statement

    if(yCoord > height){
      return -1;
    }//if statement

    return boardData[xCoord][yCoord][1];

  }//getRightWallState


  /**
   * sets the right wall at specified coordinates           *----------*
   * @param xCoord                                          |          |  <== sets this
   * @param yCoord                                          |          |
   * @param input                                           *----------*
   */
  public void setRightWallState(int xCoord, int yCoord, int input){

    if(xCoord > width){
      return;
    }//if statement

    if(yCoord > height){
      return;
    }//if statement

    boardData[xCoord][yCoord][1] = input;

    if(xCoord < width){

      boardData[xCoord + 1][yCoord][3] = input;

    }//if statement
  }//setRightWallState

  public int getBottomWallState(int xCoord, int yCoord){

    if(xCoord > width){
      return -1;
    }//if statement

    if(yCoord > height){
      return -1;
    }// if statement

    return boardData[xCoord][yCoord][2];

  }//getBottomWallState

  public void setBottomWallState(int xCoord, int yCoord, int input){

    if(xCoord > width){
      return;
    }//if statement

    if(yCoord > height){
      return;
    }//if statement

    boardData[xCoord][yCoord][2] = input;

    if(yCoord > 0){

      boardData[xCoord][yCoord + 1][0] = input;

    }//if statement
  }//setBottomWallState

  public int getLeftWallState(int xCoord, int yCoord){

    if(xCoord > width){
      return -1;
    }//if statement

    if(yCoord > height){
      return -1;
    }//if statement

    return boardData[xCoord][yCoord][3];

  }//getLeftWallState

  public void setLeftWallState(int xCoord, int yCoord, int input){

    if(xCoord > width){
      return;
    }//if statement

    if(yCoord > height){
      return;
    }//if statement

    boardData[xCoord][yCoord][3] = input;

    if(xCoord > 0){

      boardData[xCoord - 1][yCoord][1] = input;

    }//if statement

  }//setLeftWallState

  public int getWallState( int xCoord, int yCoord) {

    int stateAdd = 0;

    if(xCoord > width){
      return -1;
    }//if statement

    if(yCoord > height){
      return -1;
    }//if statement

    //adds all four wallStates together to check if cell is done
    for( int n = 0; n <= 4; n++){

      stateAdd = stateAdd +boardData[xCoord][yCoord][n];

    }//for loop

    return stateAdd;

  }//getWallState

}//BoardState
