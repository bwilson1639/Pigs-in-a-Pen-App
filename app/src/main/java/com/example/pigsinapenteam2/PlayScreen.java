/**
 * Jared Boonstra - 1572694
 * PlayScreen.java
 *
 * Holds functions + info that both the multiplayer and singleplayer screen have. Passes them on to both of them.
 */
package com.example.pigsinapenteam2;

import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class PlayScreen extends AppCompatActivity {

  //Game State
  public GameState gameState;
  public BoardState boardState;

  //players
  public HumanPlayer player1 = new HumanPlayer();


  //Buttons
  public Button confirmButton;
  public Button currentButton;

  //Views
  public View pauseMenuLayout;
  public View gameButtons;
  TextView player1ScoreBoard;
  TextView player2ScoreBoard;
  public View leftIndicator;
  public View rightIndicator;

  //Ints
  public int cellX;
  public int cellY;
  public int totalScore;
  public int width;
  public int height;
  public final int PLAYERONEINT = 1;
  public final int PLAYERTWOINT = 2;
  public static int boardType;
  public static int boardSize;


  //Bools
  public boolean playerHasMoved;
  public boolean isHorizontal;
  public static boolean isMultiplayer;

  /**
   * ScoreBoardDefaulter
   *
   * Sets the scoreBoard to the correct view and sets them to 0.
   */
  public void scoreBoardDefaulter(){
    player1ScoreBoard = findViewById(R.id.player1Score);
    player2ScoreBoard = findViewById(R.id.player2Score);
    player1ScoreBoard.setText("0");
    player2ScoreBoard.setText("0");
  }

  /**
   * startGameButtonsGone
   *
   * Finds the views of the confirm button for player one (Which both single and multiplayer share), and the view of the pause menu layout. Makes them GONE for now
   */
  public void startGameButtonsGone(){
    //===== Confirm Button ===========
    confirmButton = findViewById(R.id.confirmButtonPlayer1);
    confirmButton.setVisibility(View.GONE);
    //===== Pause Button ========
    pauseMenuLayout = findViewById(R.id.pauseMenuLayout);
    pauseMenuLayout.setVisibility(View.GONE);
    //====== Turn Indicators ===== //
    leftIndicator = findViewById(R.id.LeftIndicator);
    rightIndicator = findViewById(R.id.RightIndicator);
    rightIndicator.setVisibility(View.GONE);
  }

  public void onClickPause(View v){
    pauseMenuLayout.setVisibility(View.VISIBLE);
    gameButtons.setVisibility(View.GONE);
  }

  public void resumeButton(View v){
    gameButtons.setVisibility(View.VISIBLE);
    pauseMenuLayout.setVisibility(View.GONE);
  }

  public void pauseToMainMenu(View v){
    Intent goToMainMenu = new Intent(getApplicationContext(), MainScreen.class);
    startActivity(goToMainMenu);
  }
  public void restartButton(View v){
    this.recreate();
  }

  /**
   * Highlights the fence button last clicked and displays the confirm button.
   * @param V the button that is pressed.
   */
  public void buttonClicked(View V){
    if(currentButton == null){
      int buttonId = V.getId();
      Button currentButton = findViewById(buttonId);
      this.currentButton = currentButton;
      confirmButton.setVisibility(View.VISIBLE);
      currentButton.setBackgroundColor(getResources().getColor(R.color.fences));
    } else{
      //if there already is a fence button chosen
      changeChoice(V);
    }


  }

  /**
   * Changes the fence selection from currentButton to button V.
   * @param V the button that is pressed.
   */
  public void changeChoice(View V){
    int buttonId = V.getId();
    Button buttonClicked = findViewById(buttonId);
    this.currentButton.setBackgroundColor(getResources().getColor(R.color.transparent));
    buttonClicked.setBackgroundColor(getResources().getColor(R.color.fences));
    currentButton = buttonClicked;
  }

  public void updateScore() {
    player1ScoreBoard.setText("" + gameState.player1Points);
    player2ScoreBoard.setText("" + gameState.player2Points);
  }

  /**
   * endGame
   *
   * Compares scores of two players then sends an int corresponding to that player to the winner screen
   */
  public void endGame(){
    Intent goToWinScreen = new Intent(getApplicationContext(), VictoryScreen.class);
    if(gameState.player2Points > gameState.player1Points){
      goToWinScreen.putExtra("playerWhoWon", 1);
    }
    else if(gameState.player1Points > gameState.player2Points){
      goToWinScreen.putExtra("playerWhoWon", 0);
    }
    else if(gameState.player1Points == gameState.player2Points){
      goToWinScreen.putExtra("playerWhoWon", 2);
    }
    startActivity(goToWinScreen);
  }

  // Individual Button functions - There's a lot
  public void onClickVertical1(View v){
    buttonClicked(v);
    cellX = 0;
    cellY = 0;
    isHorizontal = false;
  }

  /**
   * Highlights the vertical fence button at position 0,1
   * and updates instance variables needed for backend logic
   * @param v
   */
  public void onClickVertical2(View v) {
    buttonClicked(v);
    cellX = 0;
    cellY = 1;
    isHorizontal = false;
  }

  /**
   * Highlights the vertical fence button at position 0,2
   * and updates instance variables needed for backend logic
   * @param v
   */
  public void onClickVertical3(View v) {
    buttonClicked(v);
    cellX = 0;
    cellY = 2;
    isHorizontal = false;
  }

  /**
   * Highlights the vertical fence button at position 0,3
   * and updates instance variables needed for backend logic
   * @param v
   */
  public void onClickVertical4(View v) {
    buttonClicked(v);
    cellX = 0;
    cellY = 3;
    isHorizontal = false;
  }

  /**
   * Highlights the vertical fence button at position 1,0
   * and updates instance variables needed for backend logic
   * @param v
   */
  public void onClickVertical5(View v) {
    buttonClicked(v);
    cellX = 1;
    cellY = 0;
    isHorizontal = false;
  }

  /**
   * Highlights the vertical fence button at position 1,1
   * and updates instance variables needed for backend logic
   * @param v
   */
  public void onClickVertical6(View v) {
    buttonClicked(v);
    cellX = 1;
    cellY = 1;
    isHorizontal = false;
  }

  /**
   * Highlights the vertical fence button at position 1,2
   * and updates instance variables needed for backend logic
   * @param v
   */
  public void onClickVertical7(View v) {
    buttonClicked(v);
    cellX = 1;
    cellY = 2;
    isHorizontal = false;
  }

  /**
   * Highlights the vertical fence button at position 1,3
   * and updates instance variables needed for backend logic
   * @param v
   */
  public void onClickVertical8(View v) {
    buttonClicked(v);
    cellX = 1;
    cellY = 3;
    isHorizontal = false;
  }


  public void onClickVertical9(View v) {
    buttonClicked(v);
    cellX = 0;
    cellY = 0;
    isHorizontal = false;
  }


  public void onClickVertical10(View v) {
    buttonClicked(v);
    cellX = 0;
    cellY = 1;
    isHorizontal = false;
  }

  public void onClickVertical11(View v) {
    buttonClicked(v);
    cellX = 0;
    cellY = 2;
    isHorizontal = false;
  }

  public void onClickVertical12(View v) {
    buttonClicked(v);
    cellX = 0;
    cellY = 3;
    isHorizontal = false;
  }

  public void onClickVertical13(View v) {
    buttonClicked(v);
    cellX = 0;
    cellY = 4;
    isHorizontal = false;
  }

  public void onClickVertical14(View v) {
    buttonClicked(v);
    cellX = 1;
    cellY = 0;
    isHorizontal = false;
  }

  public void onClickVertical15(View v) {
    buttonClicked(v);
    cellX = 1;
    cellY = 1;
    isHorizontal = false;
  }

  public void onClickVertical16(View v) {
    buttonClicked(v);
    cellX = 1;
    cellY = 2;
    isHorizontal = false;
  }

  public void onClickVertical17(View v) {
    buttonClicked(v);
    cellX = 1;
    cellY = 3;
    isHorizontal = false;
  }

  public void onClickVertical18(View v) {
    buttonClicked(v);
    cellX = 1;
    cellY = 4;
    isHorizontal = false;
  }


  public void onClickVertical19(View v) {
    buttonClicked(v);
    cellX = 2;
    cellY = 0;
    isHorizontal = false;
  }

  public void onClickVertical20(View v) {
    buttonClicked(v);
    cellX = 2;
    cellY = 1;
    isHorizontal = false;
  }

  public void onClickVertical21(View v) {
    buttonClicked(v);
    cellX = 2;
    cellY = 2;
    isHorizontal = false;
  }

  public void onClickVertical22(View v) {
    buttonClicked(v);
    cellX = 2;
    cellY = 3;
    isHorizontal = false;
  }

  public void onClickVertical23(View v) {
    buttonClicked(v);
    cellX = 2;
    cellY = 4;
    isHorizontal = false;
  }

  public void onClickVertical24(View v){
    buttonClicked(v);
    cellX = 0;
    cellY = 0;
    isHorizontal = false;
  }

  public void onClickVertical25(View v){
    buttonClicked(v);
    cellX = 0;
    cellY = 1;
    isHorizontal = false;
  }

  public void onClickVertical26(View v){
    buttonClicked(v);
    cellX = 0;
    cellY = 2;
    isHorizontal = false;
  }

  public void onClickVertical27(View v){
    buttonClicked(v);
    cellX = 0;
    cellY = 3;
    isHorizontal = false;
  }

  public void onClickVertical28(View v){
    buttonClicked(v);
    cellX = 0;
    cellY = 4;
    isHorizontal = false;
  }

  public void onClickVertical29(View v){
    buttonClicked(v);
    cellX = 0;
    cellY = 5;
    isHorizontal = false;
  }

  public void onClickVertical30(View v){
    buttonClicked(v);
    cellX = 1;
    cellY = 0;
    isHorizontal = false;
  }

  public void onClickVertical31(View v){
    buttonClicked(v);
    cellX = 1;
    cellY = 1;
    isHorizontal = false;
  }

  public void onClickVertical32(View v){
    buttonClicked(v);
    cellX = 1;
    cellY = 2;
    isHorizontal = false;
  }

  public void onClickVertical33(View v){
    buttonClicked(v);
    cellX = 1;
    cellY = 3;
    isHorizontal = false;
  }

  public void onClickVertical34(View v){
    buttonClicked(v);
    cellX = 1;
    cellY = 4;
    isHorizontal = false;
  }

  public void onClickVertical35(View v){
    buttonClicked(v);
    cellX = 1;
    cellY = 5;
    isHorizontal = false;
  }

  public void onClickVertical36(View v){
    buttonClicked(v);
    cellX = 2;
    cellY = 0;
    isHorizontal = false;
  }

  public void onClickVertical37(View v){
    buttonClicked(v);
    cellX = 2;
    cellY = 1;
    isHorizontal = false;
  }

  public void onClickVertical38(View v){
    buttonClicked(v);
    cellX = 2;
    cellY = 2;
    isHorizontal = false;
  }

  public void onClickVertical39(View v){
    buttonClicked(v);
    cellX = 2;
    cellY = 3;
    isHorizontal = false;
  }

  public void onClickVertical40(View v){
    buttonClicked(v);
    cellX = 2;
    cellY = 4;
    isHorizontal = false;
  }

  public void onClickVertical41(View v){
    buttonClicked(v);
    cellX = 2;
    cellY = 5;
    isHorizontal = false;
  }

  public void onClickVertical42(View v){
    buttonClicked(v);
    cellX = 3;
    cellY = 0;
    isHorizontal = false;
  }

  public void onClickVertical43(View v){
    buttonClicked(v);
    cellX = 3;
    cellY = 1;
    isHorizontal = false;
  }

  public void onClickVertical44(View v){
    buttonClicked(v);
    cellX = 3;
    cellY = 2;
    isHorizontal = false;
  }

  public void onClickVertical45(View v){
    buttonClicked(v);
    cellX = 3;
    cellY = 3;
    isHorizontal = false;
  }

  public void onClickVertical46(View v){
    buttonClicked(v);
    cellX = 3;
    cellY = 4;
    isHorizontal = false;
  }

  public void onClickVertical47(View v){
    buttonClicked(v);
    cellX = 3;
    cellY = 5;
    isHorizontal = false;
  }








  /**
   * Highlights the horizontal fence button at position 0,0
   * and updates instance variables needed for backend logic
   * @param v
   */
  public void onClickHorizontal1(View v) {
    buttonClicked(v);
    cellX = 0;
    cellY = 0;
    isHorizontal = true;
  }

  /**
   * Highlights the horizontal fence button at position 0,1
   * and updates instance variables needed for backend logic
   * @param v
   */
  public void onClickHorizontal2(View v) {
    buttonClicked(v);
    cellX = 0;
    cellY = 1;
    isHorizontal = true;
  }

  /**
   * Highlights the horizontal fence button at position 0,2
   * and updates instance variables needed for backend logic
   * @param v
   */
  public void onClickHorizontal3(View v) {
    buttonClicked(v);
    cellX = 0;
    cellY = 2;
    isHorizontal = true;
  }

  /**
   * Highlights the horizontal fence button at position 1,0
   * and updates instance variables needed for backend logic
   * @param v
   */
  public void onClickHorizontal4(View v) {
    buttonClicked(v);
    cellX = 1;
    cellY = 0;
    isHorizontal = true;
  }

  /**
   * Highlights the horizontal fence button at position 1,1
   * and updates instance variables needed for backend logic
   * @param v
   */
  public void onClickHorizontal5(View v) {
    buttonClicked(v);
    cellX = 1;
    cellY = 1;
    isHorizontal = true;
  }

  /**
   * Highlights the horizontal fence button at position 1,2
   * and updates instance variables needed for backend logic
   * @param v
   */
  public void onClickHorizontal6(View v) {
    buttonClicked(v);
    cellX = 1;
    cellY = 2;
    isHorizontal = true;
  }

  /**
   * Highlights the horizontal fence button at position 2,0
   * and updates instance variables needed for backend logic
   * @param v
   */
  public void onClickHorizontal7(View v) {
    buttonClicked(v);
    cellX = 2;
    cellY = 0;
    isHorizontal = true;
  }

  /**
   * Highlights the horizontal fence button at position 2,1
   * and updates instance variables needed for backend logic
   * @param v
   */
  public void onClickHorizontal8(View v) {
    buttonClicked(v);
    cellX = 2;
    cellY = 1;
    isHorizontal = true;
  }

  /**
   * Highlights the horizontal fence button at position 2,2
   * and updates instance variables needed for backend logic
   * @param v
   */
  public void onClickHorizontal9(View v) {
    buttonClicked(v);
    cellX = 2;
    cellY = 2;
    isHorizontal = true;
  }


  public void onClickHorizontal10(View v) {
    buttonClicked(v);
    cellX = 0;
    cellY = 0;
    isHorizontal = true;
  }

  public void onClickHorizontal11(View v) {
    buttonClicked(v);
    cellX = 0;
    cellY = 1;
    isHorizontal = true;
  }

  public void onClickHorizontal12(View v) {
    buttonClicked(v);
    cellX = 0;
    cellY = 2;
    isHorizontal = true;
  }


  public void onClickHorizontal13(View v) {
    buttonClicked(v);
    cellX = 0;
    cellY = 3;
    isHorizontal = true;
  }


  public void onClickHorizontal14(View v) {
    buttonClicked(v);
    cellX = 1;
    cellY = 0;
    isHorizontal = true;
  }

  public void onClickHorizontal15(View v) {
    buttonClicked(v);
    cellX = 1;
    cellY = 1;
    isHorizontal = true;
  }

  public void onClickHorizontal16(View v) {
    buttonClicked(v);
    cellX = 1;
    cellY = 2;
    isHorizontal = true;
  }

  public void onClickHorizontal17(View v) {
    buttonClicked(v);
    cellX = 1;
    cellY = 3;
    isHorizontal = true;
  }

  public void onClickHorizontal18(View v) {
    buttonClicked(v);
    cellX = 2;
    cellY = 0;
    isHorizontal = true;
  }

  public void onClickHorizontal19(View v) {
    buttonClicked(v);
    cellX = 2;
    cellY = 1;
    isHorizontal = true;
  }


  public void onClickHorizontal20(View v) {
    buttonClicked(v);
    cellX = 2;
    cellY = 2;
    isHorizontal = true;
  }

  public void onClickHorizontal21(View v) {
    buttonClicked(v);
    cellX = 2;
    cellY = 3;
    isHorizontal = true;
  }

  public void onClickHorizontal22(View v) {
    buttonClicked(v);
    cellX = 3;
    cellY = 0;
    isHorizontal = true;
  }


  public void onClickHorizontal23(View v) {
    buttonClicked(v);
    cellX = 3;
    cellY = 1;
    isHorizontal = true;
  }

  public void onClickHorizontal24(View v) {
    buttonClicked(v);
    cellX = 3;
    cellY = 2;
    isHorizontal = true;
  }

  public void onClickHorizontal25(View v) {
    buttonClicked(v);
    cellX = 3;
    cellY = 3;
    isHorizontal = true;
  }

  public void onClickHorizontal26(View v){
    buttonClicked(v);
    cellX = 0;
    cellY = 0;
    isHorizontal = true;
  }

  public void onClickHorizontal27(View v){
    buttonClicked(v);
    cellX = 0;
    cellY = 1;
    isHorizontal = true;
  }

  public void onClickHorizontal28(View v){
    buttonClicked(v);
    cellX = 0;
    cellY = 2;
    isHorizontal = true;
  }

  public void onClickHorizontal29(View v){
    buttonClicked(v);
    cellX = 0;
    cellY = 3;
    isHorizontal = true;
  }

  public void onClickHorizontal30(View v){
    buttonClicked(v);
    cellX = 0;
    cellY = 4;
    isHorizontal = true;
  }

  public void onClickHorizontal31(View v){
    buttonClicked(v);
    cellX = 1;
    cellY = 0;
    isHorizontal = true;
  }

  public void onClickHorizontal32(View v){
    buttonClicked(v);
    cellX = 1;
    cellY = 1;
    isHorizontal = true;
  }

  public void onClickHorizontal33(View v){
    buttonClicked(v);
    cellX = 1;
    cellY = 2;
    isHorizontal = true;
  }

  public void onClickHorizontal34(View v){
    buttonClicked(v);
    cellX = 1;
    cellY = 3;
    isHorizontal = true;
  }

  public void onClickHorizontal35(View v){
    buttonClicked(v);
    cellX = 1;
    cellY = 4;
    isHorizontal = true;
  }

  public void onClickHorizontal36(View v){
    buttonClicked(v);
    cellX = 2;
    cellY = 0;
    isHorizontal = true;
  }

  public void onClickHorizontal37(View v){
    buttonClicked(v);
    cellX = 2;
    cellY = 1;
    isHorizontal = true;
  }

  public void onClickHorizontal38(View v){
    buttonClicked(v);
    cellX = 2;
    cellY = 2;
    isHorizontal = true;
  }

  public void onClickHorizontal39(View v){
    buttonClicked(v);
    cellX = 2;
    cellY = 3;
    isHorizontal = true;
  }

  public void onClickHorizontal40(View v){
    buttonClicked(v);
    cellX = 2;
    cellY = 4;
    isHorizontal = true;
  }

  public void onClickHorizontal41(View v){
    buttonClicked(v);
    cellX = 3;
    cellY = 0;
    isHorizontal = true;
  }

  public void onClickHorizontal42(View v){
    buttonClicked(v);
    cellX = 3;
    cellY = 1;
    isHorizontal = true;
  }

  public void onClickHorizontal43(View v){
    buttonClicked(v);
    cellX = 3;
    cellY = 2;
    isHorizontal = true;
  }

  public void onClickHorizontal44(View v){
    buttonClicked(v);
    cellX = 3;
    cellY = 3;
    isHorizontal = true;
  }

  public void onClickHorizontal45(View v){
    buttonClicked(v);
    cellX = 3;
    cellY = 4;
    isHorizontal = true;
  }

  public void onClickHorizontal46(View v){
    buttonClicked(v);
    cellX = 4;
    cellY = 0;
    isHorizontal = true;
  }

  public void onClickHorizontal47(View v){
    buttonClicked(v);
    cellX = 4;
    cellY = 1;
    isHorizontal = true;
  }

  public void onClickHorizontal48(View v){
    buttonClicked(v);
    cellX = 4;
    cellY = 2;
    isHorizontal = true;
  }

  public void onClickHorizontal49(View v){
    buttonClicked(v);
    cellX = 4;
    cellY = 3;
    isHorizontal = true;
  }

  public void onClickHorizontal50(View v){
    buttonClicked(v);
    cellX = 4;
    cellY = 4;
    isHorizontal = true;
  }







  public void onBackPressed(){//so they can't use the back button during a game. CHEATERS
    return;
  }

  protected void onResume(){
    super.onResume();
    ScreenLogic.fullScreen(this);
  }

  /**
   * updateClaimedCells(BoardState, int)
   *    updates the color of a cell if it's claimed.
   * @param state: current board state
   * @param boardSize: 0,1, or 2 if small, medium, or large
   */
  protected void updateClaimedCells(BoardState state, int boardSize) {
    int cellState;
    String cellName = "CellClaimed";
    int cellNum;
    int width = state.getWidth();
    int height = state.getHeight();

    switch (boardSize) {
      case 0:
        cellName = "small" + cellName;
        break;
      case 1:
        cellName = "medium" + cellName;
        break;
      case 2:
        cellName = "large" + cellName;
        break;
      default:
        assert (false): "INVALID BOARD SIZE.";
        break;
    }
    String currentCellName;
    ImageView cellImage;
    for (int y = 0; y < height; y++) {
      for (int x = 0; x < width; x++) {
        cellState = state.getCellState(y,x);
        cellNum = y * width + x;
        currentCellName = cellName + cellNum;
        int resID = this.getResources().getIdentifier(currentCellName,
            "id", this.getPackageName());
        cellImage = findViewById(resID);


        switch (cellState) {
          case 1:
            cellImage.setBackgroundColor(getResources().getColor(R.color.Player1Color));
            break;
          case 2:
            cellImage.setBackgroundColor(getResources().getColor(R.color.Player2Color));
            break;
          default:
            assert (false) : "invalid cell state";
            break;
        }
      }
    }
  }
  public int totalScoreCalculator(){
    if(boardType == 1){
      return width * height;
    }
    else if(boardType == 2){
      if(boardSize == 0){
        return width * height - 2;
      }
      else if(boardSize == 1){
        return width * height - 4;
      }
      else if(boardSize == 2){
        return width * height - 6;
      }
    }
    else if(boardType == 3){
      return width * height - 2;
    }
    return width * height;
  }
}
