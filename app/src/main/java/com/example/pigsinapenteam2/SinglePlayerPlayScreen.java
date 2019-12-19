/**
 * Jared Boonstra - 1572694
 *
 * SinglePlayerPlayScreen.java
 *
 * Sets up the board and calls functions from PlayScreen and this class to play the game.
 */
package com.example.pigsinapenteam2;


import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class SinglePlayerPlayScreen extends PlayScreen {


  //Players
  public BotPlayer player2;
  private Player currentPlayer;
  ImageView playerOnePicture;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_single_player_play_screen);
    setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
    isMultiplayer = false;
    boardSize = SinglePlayerSetupScreen.boardSize;
    boardType = SinglePlayerSetupScreen.boardType;
    playerOnePicture = findViewById(R.id.playerOne);

   boardSizeSetter();
   difficultySetter();
   scoreBoardDefaulter();
   layoutSetter();
   animalSetter();
   startGameButtonsGone();

    //===== Game Buttons Layout ======
    gameButtons.setVisibility(View.VISIBLE);
    //===== Players in game =======
    //player2 = new EasyBotPlayer();
    currentPlayer = player1;
    playerHasMoved = false;
    //===== Current Chosen 'Fence' Button =======
    currentButton = null;
    //===== Board State & Game State ======
    boardState = new BoardState(width,height);
    BoardType boardLayout = new BoardType(boardState,boardType, boardSize);
    boardState = boardLayout.getInputtedState();
    gameState = new GameState(boardState, player1, player2,0);
    //===== Full Screen =====
    ScreenLogic.fullScreen(this);


    totalScore = totalScoreCalculator();
  }


  /**
   * Confirms the current chosen fence button and processes the move in the backend.
   * @param v
   */
  public void onClickConfirmationButton(View v) {
    currentButton.setClickable(false);
    currentButton = null;
    playerHasMoved = true;
    confirmButton.setVisibility(View.GONE);
    confirmAction(cellX, cellY, isHorizontal);
  }

  public void confirmAction(int cellX, int cellY, boolean isHorizontal){
    while(true) {

      int tempScore = gameState.player1Points;
      gameState = player1.doMove(gameState, cellX, cellY, PLAYERONEINT, isHorizontal);
      updateClaimedCells(gameState.currentBoardState,boardSize);
      gameState.currentBoardCheck.scoreCheck();
      gameState.runBoardCheck();
      updateScore();


    if (gameState.player1Points + gameState.player2Points == totalScore) {
      endGame();
      return;
    }

      this.updateScore();
      if (gameState.player1Points + gameState.player2Points == totalScore) {
        endGame();
        return;
      }//if

      if(tempScore == gameState.player1Points){

        break;

      }//if

      else{

        return;

      }
    }//while loop

    while (true) {
      int tempScorePlayer2 = gameState.player2Points;
      if (true) {
        gameState = player2.doMove(gameState);
        System.out.println(gameState.currentBoardState);
        updateClaimedCells(gameState.currentBoardState,boardSize);
        gameState.currentBoardCheck.scoreCheck();
        String id = gameState.botLastMove.getButtonName(SinglePlayerSetupScreen.boardSize);
        gameState.runBoardCheck();
        updateScore();
        gameState.runBoardCheck();
        int resID = this.getResources().getIdentifier(id, "id", this.getPackageName());
        Button AIButton = findViewById(resID);
        AIButton.setBackgroundColor(getResources().getColor(R.color.fences));
        AIButton.setVisibility(View.VISIBLE);
        AIButton.setClickable(false);
      }//if statement

      if (gameState.player1Points + gameState.player2Points == totalScore) {
        endGame();
        return;
      }//if

      if(tempScorePlayer2 == gameState.player2Points){
        break;
      }//if statement
    }//while loop

  }

  /**
   * boardSizeSetter
   *
   * Sets the correct board size on the screen to play
   */
  public void boardSizeSetter() {
    if (SinglePlayerSetupScreen.boardSize == 0) {
      width = 3;
      height = 2;
      this.gameButtons = findViewById(R.id.smallGameButtons); //smallButtons
    } else if (SinglePlayerSetupScreen.boardSize == 1) {
      width = 4;
      height = 3;
      this.gameButtons = findViewById(R.id.mediumGameButtons); //mediumButtons
    } else if (SinglePlayerSetupScreen.boardSize == 2) {
      width = 5;
      height = 4;
      this.gameButtons = findViewById(R.id.largeGameButtons); //largeButtons
    }
  }

  public void difficultySetter(){
    if(SinglePlayerSetupScreen.gameDifficulty == 0){
      this.player2 = new EasyBotPlayer();
    } else if(SinglePlayerSetupScreen.gameDifficulty == 1){
      this.player2 = new MediumBotPlayer();
    } else{
      this.player2 = new HardBotPlayer();
    }
  }


  public void animalSetter(){
    playerOnePicture.setImageResource(R.drawable.pig);
    if(!SinglePlayerSetupScreen.animal.isEmpty()) {
      if (SinglePlayerSetupScreen.animal.equals("dog")) {
        playerOnePicture.setImageResource(R.drawable.dog);
      } else if (SinglePlayerSetupScreen.animal.equals("cat")) {
        playerOnePicture.setImageResource(R.drawable.cat);
      } else if (SinglePlayerSetupScreen.animal.equals("cow")) {
        playerOnePicture.setImageResource(R.drawable.cow);
      } else if (SinglePlayerSetupScreen.animal.equals("pig")) {
        playerOnePicture.setImageResource(R.drawable.pig);
      }
    }
  }


  /**
   * LayoutSetter
   *
   * sets the map for the game from garden, as well as other maps
   */
  public void layoutSetter(){
    if(SinglePlayerSetupScreen.boardSize == 0){
      if(SinglePlayerSetupScreen.boardType == 1){
        return;
      } else if(SinglePlayerSetupScreen.boardType == 2){
        ImageView garden1 = findViewById(R.id.garden1);
        garden1.setVisibility(View.VISIBLE);
        ImageView garden2 = findViewById(R.id.garden2);
        garden2.setVisibility(View.VISIBLE);

        Button garden1Top = findViewById(R.id.h2);
        garden1Top.setClickable(false);
        garden1Top.setBackgroundColor(getResources().getColor(R.color.fences));

        Button garden1Right = findViewById(R.id.v3);
        garden1Right.setClickable(false);
        garden1Right.setBackgroundColor(getResources().getColor(R.color.fences));

        Button garden2Top = findViewById(R.id.h6);
        garden2Top.setClickable(false);
        garden2Top.setBackgroundColor(getResources().getColor(R.color.fences));

        Button garden2Right = findViewById(R.id.v4);
        garden2Right.setClickable(false);
        garden2Right.setBackgroundColor(getResources().getColor(R.color.fences));

        Button garden3Top = findViewById(R.id.v2);
        garden3Top.setClickable(false);
        garden3Top.setBackgroundColor(getResources().getColor(R.color.fences));

        Button garden3right = findViewById(R.id.v5);
        garden3right.setClickable(false);
        garden3right.setBackgroundColor(getResources().getColor(R.color.fences));

        Button h5 = findViewById(R.id.h5);
        h5.setClickable(false);
        h5.setBackgroundColor(getResources().getColor(R.color.fences));

        Button h3 = findViewById(R.id.h3);
        h3.setClickable(false);
        h3.setBackgroundColor(getResources().getColor(R.color.fences));


      }//small gardens
      else if(SinglePlayerSetupScreen.boardType == 3){
        ImageView hill1 = findViewById(R.id.hill1);
        hill1.setVisibility(View.VISIBLE);
        ImageView hill2 = findViewById(R.id.hill2);
        hill2.setVisibility(View.VISIBLE);

        Button hill1Top = findViewById(R.id.h1);
        hill1Top.setClickable(false);
        hill1Top.setBackgroundColor(getResources().getColor(R.color.fences));

        Button hill2Top = findViewById(R.id.h4);
        hill2Top.setClickable(false);
        hill2Top.setBackgroundColor(getResources().getColor(R.color.fences));

        Button hill3Top = findViewById(R.id.h7);
        hill3Top.setClickable(false);
        hill3Top.setBackgroundColor(getResources().getColor(R.color.fences));
      }//hill map small
    }//map size small
    else if(SinglePlayerSetupScreen.boardSize == 1){
      if(boardType == 1){
        return;
      }//if statement

      else if(boardType == 2) {
        ImageView garden3 = findViewById(R.id.garden3);
        garden3.setVisibility(View.VISIBLE);
        ImageView garden4 = findViewById(R.id.garden4);
        garden4.setVisibility(View.VISIBLE);
        ImageView garden5 = findViewById(R.id.garden5);
        garden5.setVisibility(View.VISIBLE);
        ImageView garden6 = findViewById(R.id.garden6);
        garden6.setVisibility(View.VISIBLE);

        Button garden1Top = findViewById(R.id.h12);
        garden1Top.setClickable(false);
        garden1Top.setBackgroundColor(getResources().getColor(R.color.fences));

        Button garden1Right = findViewById(R.id.v12);
        garden1Right.setClickable(false);
        garden1Right.setBackgroundColor(getResources().getColor(R.color.fences));

        Button garden2Top = findViewById(R.id.h16);
        garden2Top.setClickable(false);
        garden2Top.setBackgroundColor(getResources().getColor(R.color.fences));

        Button garden2Right = findViewById(R.id.v13);
        garden2Right.setClickable(false);
        garden2Right.setBackgroundColor(getResources().getColor(R.color.fences));

        Button garden3Top = findViewById(R.id.h17);
        garden3Top.setClickable(false);
        garden3Top.setBackgroundColor(getResources().getColor(R.color.fences));

        Button garden3right = findViewById(R.id.v17);
        garden3right.setClickable(false);
        garden3right.setBackgroundColor(getResources().getColor(R.color.fences));

        Button garden4Top = findViewById(R.id.h21);
        garden4Top.setClickable(false);
        garden4Top.setBackgroundColor(getResources().getColor(R.color.fences));

        Button v18 = findViewById(R.id.v18);
        v18.setClickable(false);
        v18.setBackgroundColor(getResources().getColor(R.color.fences));

        Button v11 = findViewById(R.id.v11);
        v11.setClickable(false);
        v11.setBackgroundColor(getResources().getColor(R.color.fences));

        Button v14 = findViewById(R.id.v14);
        v14.setClickable(false);
        v14.setBackgroundColor(getResources().getColor(R.color.fences));

        Button v16 = findViewById(R.id.v16);
        v16.setClickable(false);
        v16.setBackgroundColor(getResources().getColor(R.color.fences));

        Button v19 = findViewById(R.id.v19);
        v19.setClickable(false);
        v19.setBackgroundColor(getResources().getColor(R.color.fences));

        Button h13 = findViewById(R.id.h13);
        h13.setClickable(false);
        h13.setBackgroundColor(getResources().getColor(R.color.fences));

        Button h20 = findViewById(R.id.h20);
        h20.setClickable(false);
        h20.setBackgroundColor(getResources().getColor(R.color.fences));

      }//medium garden map


      else if(SinglePlayerSetupScreen.boardType == 3) {
        ImageView hill3 = findViewById(R.id.hill3);
        hill3.setVisibility(View.VISIBLE);
        ImageView hill4 = findViewById(R.id.hill4);
        hill4.setVisibility(View.VISIBLE);

        Button hill3Top = findViewById(R.id.v15);
        hill3Top.setClickable(false);
        hill3Top.setBackgroundColor(getResources().getColor(R.color.fences));


      }//medium hill map
    }//else if medium
    else{
      if(boardType == 1){
        return;
      }//if statement

      else if(SinglePlayerSetupScreen.boardType == 2) {
        ImageView garden7 = findViewById(R.id.garden7);
        garden7.setVisibility(View.VISIBLE);
        ImageView garden8 = findViewById(R.id.garden8);
        garden8.setVisibility(View.VISIBLE);
        ImageView garden9 = findViewById(R.id.garden9);
        garden9.setVisibility(View.VISIBLE);
        ImageView garden10 = findViewById(R.id.garden10);
        garden10.setVisibility(View.VISIBLE);
        ImageView garden11 = findViewById(R.id.garden11);
        garden11.setVisibility(View.VISIBLE);
        ImageView garden12 = findViewById(R.id.garden12);
        garden12.setVisibility(View.VISIBLE);




        Button garden1Top = findViewById(R.id.h28);
        garden1Top.setClickable(false);
        garden1Top.setBackgroundColor(getResources().getColor(R.color.fences));
        Button garden2Top = findViewById(R.id.h29);
        garden2Top.setClickable(false);
        garden2Top.setBackgroundColor(getResources().getColor(R.color.fences));
        Button garden3Top = findViewById(R.id.h34);
        garden3Top.setClickable(false);
        garden3Top.setBackgroundColor(getResources().getColor(R.color.fences));
        Button garden4Top = findViewById(R.id.h40);
        garden4Top.setClickable(false);
        garden4Top.setBackgroundColor(getResources().getColor(R.color.fences));
        Button garden5Top = findViewById(R.id.h45);
        garden5Top.setClickable(false);
        garden5Top.setBackgroundColor(getResources().getColor(R.color.fences));
        Button garden6Top = findViewById(R.id.h46);
        garden6Top.setClickable(false);
        garden6Top.setBackgroundColor(getResources().getColor(R.color.fences));

        Button garden1Right = findViewById(R.id.v27);
        garden1Right.setClickable(false);
        garden1Right.setBackgroundColor(getResources().getColor(R.color.fences));
        Button garden2Right = findViewById(R.id.v28);
        garden2Right.setClickable(false);
        garden2Right.setBackgroundColor(getResources().getColor(R.color.fences));
        Button garden3Right = findViewById(R.id.v34);
        garden3Right.setClickable(false);
        garden3Right.setBackgroundColor(getResources().getColor(R.color.fences));
        Button garden4Right = findViewById(R.id.v35);
        garden4Right.setClickable(false);
        garden4Right.setBackgroundColor(getResources().getColor(R.color.fences));
        Button garden5Right = findViewById(R.id.v41);
        garden5Right.setClickable(false);
        garden5Right.setBackgroundColor(getResources().getColor(R.color.fences));
        Button garden6Right = findViewById(R.id.v42);
        garden6Right.setClickable(false);
        garden6Right.setBackgroundColor(getResources().getColor(R.color.fences));

        Button v26 = findViewById(R.id.v26);
        v26.setClickable(false);
        v26.setBackgroundColor(getResources().getColor(R.color.fences));
        Button v33 = findViewById(R.id.v33);
        v33.setClickable(false);
        v33.setBackgroundColor(getResources().getColor(R.color.fences));
        Button v36 = findViewById(R.id.v36);
        v36.setClickable(false);
        v36.setBackgroundColor(getResources().getColor(R.color.fences));
        Button v43 = findViewById(R.id.v43);
        v43.setClickable(false);
        v43.setBackgroundColor(getResources().getColor(R.color.fences));

        Button h33 = findViewById(R.id.h33);
        h33.setClickable(false);
        h33.setBackgroundColor(getResources().getColor(R.color.fences));
        Button h35 = findViewById(R.id.h35);
        h35.setClickable(false);
        h35.setBackgroundColor(getResources().getColor(R.color.fences));
        Button h39 = findViewById(R.id.h39);
        h39.setClickable(false);
        h39.setBackgroundColor(getResources().getColor(R.color.fences));
        Button h41 = findViewById(R.id.h41);
        h41.setClickable(false);
        h41.setBackgroundColor(getResources().getColor(R.color.fences));


      }//garden large board
      else if(SinglePlayerSetupScreen.boardType == 3) {
        ImageView hill5 = findViewById(R.id.hill5);
        hill5.setVisibility(View.VISIBLE);
        ImageView hill6 = findViewById(R.id.hill6);
        hill6.setVisibility(View.VISIBLE);

        Button hill3Top = findViewById(R.id.h37);
        hill3Top.setClickable(false);
        hill3Top.setBackgroundColor(getResources().getColor(R.color.fences));


      }//medium hill map
    }//large map
  }//layoutSetter

}//singlePlayerPlayScreen