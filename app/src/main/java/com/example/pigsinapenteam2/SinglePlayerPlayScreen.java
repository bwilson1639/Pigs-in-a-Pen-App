package com.example.pigsinapenteam2;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class SinglePlayerPlayScreen extends AppCompatActivity {
  //Instance Variables
  GameState gameState;
  public final int WIDTH = 3;
  public final int HEIGHT = 2;
  HumanPlayer player1;
  final int PLAYERONEINT = 1;
  BotPlayer player2;
  final int PLAYERTWOINT = 2;
  Button confirmButton;
  Button pauseButton;
  View pauseMenuLayout;
  View gameButtons;
  int cellX;
  int cellY;
  boolean playerHasMoved;
  boolean isHorizontal;
  Button currentButton;
  protected int totalScore;


  private Player currentPlayer;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_single_player_play_screen);
    setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);

    this.confirmButton = findViewById(R.id.confirmButtonPlayer1);
    //setting confirm button to invisible
    confirmButton.setVisibility(View.GONE);
    this.pauseMenuLayout = findViewById(R.id.pauseMenuLayout);
    this.pauseMenuLayout.setVisibility(View.GONE);
    this.gameButtons = findViewById(R.id.gameButtons);
    this.player1 = new HumanPlayer();
    this.player2 = new EasyBotPlayer(HEIGHT,WIDTH);
    this.currentPlayer = player1;
    this.playerHasMoved = false;
    this.currentButton = null;
    BoardState boardState = new BoardState(WIDTH,HEIGHT);
    this.gameState = new GameState(boardState, this.player1, this.player2,0);

    //Make the game full screen, hides any action bars on the phone.
    this.getWindow().getDecorView().setSystemUiVisibility(
            View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                    | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                    | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                    | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                    | View.SYSTEM_UI_FLAG_FULLSCREEN
                    | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY);

    this.totalScore = (WIDTH * HEIGHT) / 2;
  }

  /**
   * Highlights the fence button last clicked and displays the confirm button.
   * @param V the button that is pressed.
   */
  public void buttonClicked(View V){
    if(this.currentButton == null){
      int buttonId = V.getId();
      Button currentButton = findViewById(buttonId);
      this.currentButton = currentButton;
      this.confirmButton.setVisibility(View.VISIBLE);
      currentButton.setBackgroundColor(getResources().getColor(R.color.fences));
    } else{
      //if there already is a fence button chosen
      this.changeChoice(V);
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
    this.currentButton = buttonClicked;
  }

  public void onClickVertical1(View v){
    buttonClicked(v);
    cellX = 0;
    cellY = 0;
    isHorizontal = false;
  }

  public void onClickVertical2(View v) {
    this.buttonClicked(v);
    cellX = 0;
    cellY = 1;
    isHorizontal = false;
  }

  public void onClickVertical3(View v) {
    this.buttonClicked(v);
    cellX = 0;
    cellY = 2;
    isHorizontal = false;
  }

  public void onClickVertical4(View v) {
    this.buttonClicked(v);
    cellX = 0;
    cellY = 3;
    isHorizontal = false;
  }

  public void onClickVertical5(View v) {
    this.buttonClicked(v);
    cellX = 1;
    cellY = 0;
    isHorizontal = false;
  }

  public void onClickVertical6(View v) {
    this.buttonClicked(v);
    cellX = 1;
    cellY = 1;
    isHorizontal = false;
  }

  public void onClickVertical7(View v) {
    this.buttonClicked(v);
    cellX = 1;
    cellY = 2;
    isHorizontal = false;
  }

  public void onClickVertical8(View v) {
    this.buttonClicked(v);
    cellX = 1;
    cellY = 3;
    isHorizontal = false;
  }


  public void onClickHorizontal1(View v) {
    this.buttonClicked(v);
    cellX = 0;
    cellY = 0;
    isHorizontal = true;
  }
  public void onClickHorizontal2(View v) {
    this.buttonClicked(v);
    cellX = 0;
    cellY = 1;
    isHorizontal = true;
  }
  public void onClickHorizontal3(View v) {
    this.buttonClicked(v);
    cellX = 0;
    cellY = 2;
    isHorizontal = true;
  }
  public void onClickHorizontal4(View v) {
    this.buttonClicked(v);
    cellX = 1;
    cellY = 0;
    isHorizontal = true;
  }
  public void onClickHorizontal5(View v) {
    this.buttonClicked(v);
    cellX = 1;
    cellY = 1;
    isHorizontal = true;
  }
  public void onClickHorizontal6(View v) {
    this.buttonClicked(v);
    cellX = 1;
    cellY = 2;
    isHorizontal = true;
  }
  public void onClickHorizontal7(View v) {
    this.buttonClicked(v);
    cellX = 2;
    cellY = 0;
    isHorizontal = true;
  }
  public void onClickHorizontal8(View v) {
    this.buttonClicked(v);
    cellX = 2;
    cellY = 1;
    isHorizontal = true;
  }
  public void onClickHorizontal9(View v) {
    this.buttonClicked(v);
    cellX = 2;
    cellY = 2;
    isHorizontal = true;
  }
  public void onClickHorizontal10(View v) {
    this.buttonClicked(v);
    cellX = 1;
    cellY = 2;
    isHorizontal = true;
  }
  public void onClickHorizontal11(View v) {
    this.buttonClicked(v);
    cellX = 2;
    cellY = 2;
    isHorizontal = true;
  }


  public void onClickConfirmationButton(View v) {
    this.currentButton.setClickable(false);
    this.currentButton = null;
    this.playerHasMoved = true;
    this.confirmButton.setVisibility(View.GONE);
    this.confirmAction(this.cellX, this.cellY, this.isHorizontal);
    this.updateScore();
    if(gameState.player1Points + gameState.player2Points == totalScore){
      endGame();
    }
    System.out.println(this.gameState.currentBoardState);
  }

  public void confirmAction(int cellX, int cellY, boolean isHorizontal){

    int currentScore = this.gameState.player1Points;

    int lastPlayer1Score = gameState.player1Points;
    this.gameState = player1.doMove(this.gameState, cellX, cellY, isHorizontal);

    cellCheckAndUpdate(cellX, cellY, PLAYERONEINT);

    this.gameState.runBoardCheck();

    if(true){

      System.out.println("BOARD STATE BEFORE DO MOVE: ");
      System.out.println(this.gameState.currentBoardState);

     this.gameState = player2.doMove(this.gameState);

     String id = this.gameState.botLastMove.getButtonName();
     System.out.println(id);

     int resID = this.getResources().getIdentifier(id, "id", this.getPackageName());
     Button AIButton = findViewById(resID);
     AIButton.setBackgroundColor(getResources().getColor(R.color.fences));
     AIButton.setVisibility(View.VISIBLE);
     AIButton.setClickable(false);
    }

  }
  public void onClickPause(View v){
    pauseMenuLayout.setVisibility(View.VISIBLE);
    gameButtons.setVisibility(View.GONE);
    //Blur background -- TODO when mvp is done
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

   public void endGame(){
    Intent goToWinScreen = new Intent(getApplicationContext(), VictoryScreen.class);
    if(gameState.player2Points > gameState.player1Points){
      goToWinScreen.putExtra("playerWhoWon", 1);
     }
    else if(gameState.player1Points > gameState.player2Points){
      goToWinScreen.putExtra("playerWhoWon", 0);
    }
     startActivity(goToWinScreen);
   }



  public void updateScore() {
    TextView tv = findViewById(R.id.player1Score);
    tv.setText("" + this.gameState.player1Points);
  }


  private void cellCheckAndUpdate(int cellX,int cellY, int playerInt){

    System.out.println("hit cellCheckAndUpdate");

    int checkedCellX = cellXCheck(cellX);
    int checkedCellY = cellYCheck(cellY);

    if(this.gameState.currentBoardState.isComplete(checkedCellX, checkedCellY)){

      this.gameState.currentBoardState.setCellState(checkedCellX, checkedCellY, playerInt);

    }//if statement

    else if(checkedCellX > 0){

      if(this.gameState.currentBoardState.isComplete(checkedCellX -1, checkedCellY)){

        this.gameState.currentBoardState.setCellState(checkedCellX -1,checkedCellY, playerInt);

      }//if
    }//else if

    else if(checkedCellY  > 0){

      if(this.gameState.currentBoardState.isComplete(checkedCellX, checkedCellY - 1)){

        this.gameState.currentBoardState.setCellState(checkedCellX, checkedCellY -1, playerInt);

      }//if
    }//else if
    else{
      System.out.println("isComplete was false");
    }
  }//cellCheckAndUpdate

  private int cellXCheck(int cellX){

    if(cellX >= HEIGHT){

      System.out.println("cellX was greater or equal to Height");
      System.out.println(cellX);
      return cellX - 1;

    }//if statement

    else{

      return cellX;

    }//else statement
  }//cellXCheck

  private int cellYCheck(int cellY){

    if(cellY >= WIDTH){

      System.out.println("cellY was greater or equal to Width");
      System.out.println(cellY);
      return cellY - 1;

    }//if statement

    else{

      return cellY;

    }//else statement
  }//cellYCheck
}//singlePlayerPlayScreen