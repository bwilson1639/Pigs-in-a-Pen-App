/**
 * Jared Boonstra + Osccar Jaimes
 *
 * MultiplayerSetupScreen
 *
 * Does the stuff that sets the settings for the multiplayer game to run off of.
 */
package com.example.pigsinapenteam2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Spinner;

public class MultiplayerSetupScreen extends AppCompatActivity {

  Spinner gameBoardSizeSpinner;
  Spinner mapLayoutSpinner;
  public static int boardSize;
  public static int boardType;
  protected String sizeText;
  protected String typeText;
  public static String player1Animal;
  public static String player2Animal;

  ImageView pig;
  ImageView cat;
  ImageView cow;
  ImageView dog;

  ImageView rooster;
  ImageView horse;
  ImageView sheep;
  ImageView pig2;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_multiplayer_setup_screen);
    setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
    ScreenLogic.fullScreen(this);

    gameBoardSizeSpinner = findViewById(R.id.board_size_drop_down);
    mapLayoutSpinner = findViewById(R.id.board_size_drop_down_difficulty);


    player2Animal = "";
    player1Animal = "";

    cat = findViewById(R.id.cat);
    cow = findViewById(R.id.cow);
    dog = findViewById(R.id.dog);
    pig = findViewById(R.id.pig);


    rooster = findViewById(R.id.rooster);
    horse = findViewById(R.id.horse);
    sheep = findViewById(R.id.player2);
    pig2 = findViewById(R.id.pig2);

  }

  /**
   * Starts the game with the respective choices made in the setup screen.
   * @param V the button to press to start the game.
   */
  public void playGame(View V){
    sizeText = gameBoardSizeSpinner.getSelectedItem().toString();
    if(sizeText.equals("Small")){
      boardSize = 0;
    }
    else if(sizeText.equals("Medium")){
      boardSize = 1;
    }
    else if(sizeText.equals("Large")){
      boardSize = 2;
    }

    typeText = mapLayoutSpinner.getSelectedItem().toString();
    if(typeText.equals("Normal")){
      boardType = 1;
    }
    else if(typeText.equals("Garden")){
      boardType = 2;
    }
    else if(typeText.equals("Hill")){
      boardType = 3;
    }

    Intent goToPlayScreen = new Intent(getApplicationContext(), MultiplayerPlayScreen.class);
    startActivity(goToPlayScreen);
  }

  public void onClickPig(View v){
    pig.setBackgroundColor(getResources().getColor(R.color.transBlue));
    dog.setBackgroundColor(getResources().getColor(R.color.transparent));
    cow.setBackgroundColor(getResources().getColor(R.color.transparent));
    cat.setBackgroundColor(getResources().getColor(R.color.transparent));
    MultiplayerSetupScreen.player1Animal = "pig";
  }

  public void onClickDog(View v){
    dog.setBackgroundColor(getResources().getColor(R.color.transBlue));
    pig.setBackgroundColor(getResources().getColor(R.color.transparent));
    cow.setBackgroundColor(getResources().getColor(R.color.transparent));
    cat.setBackgroundColor(getResources().getColor(R.color.transparent));
    MultiplayerSetupScreen.player1Animal = "dog";
  }

  public void onClickCow(View v){
    cow.setBackgroundColor(getResources().getColor(R.color.transBlue));
    pig.setBackgroundColor(getResources().getColor(R.color.transparent));
    dog.setBackgroundColor(getResources().getColor(R.color.transparent));
    cat.setBackgroundColor(getResources().getColor(R.color.transparent));
    MultiplayerSetupScreen.player1Animal = "cow";
  }

  public void onClickCat(View v){
    cat.setBackgroundColor(getResources().getColor(R.color.transBlue));
    pig.setBackgroundColor(getResources().getColor(R.color.transparent));
    dog.setBackgroundColor(getResources().getColor(R.color.transparent));
    cow.setBackgroundColor(getResources().getColor(R.color.transparent));
    MultiplayerSetupScreen.player1Animal = "cat";
  }

  public void onClickRooser(View v){
    rooster.setBackgroundColor(getResources().getColor(R.color.transRed));
    horse.setBackgroundColor(getResources().getColor(R.color.transparent));
    sheep.setBackgroundColor(getResources().getColor(R.color.transparent));
    pig2.setBackgroundColor(getResources().getColor(R.color.transparent));
    MultiplayerSetupScreen.player2Animal = "rooster";
  }

  public void onClickHorse(View v){
    horse.setBackgroundColor(getResources().getColor(R.color.transRed));
    rooster.setBackgroundColor(getResources().getColor(R.color.transparent));
    sheep.setBackgroundColor(getResources().getColor(R.color.transparent));
    pig2.setBackgroundColor(getResources().getColor(R.color.transparent));
    MultiplayerSetupScreen.player2Animal = "horse";
  }

  public void onClickSheep(View v){
    sheep.setBackgroundColor(getResources().getColor(R.color.transRed));
    rooster.setBackgroundColor(getResources().getColor(R.color.transparent));
    horse.setBackgroundColor(getResources().getColor(R.color.transparent));
    pig2.setBackgroundColor(getResources().getColor(R.color.transparent));
    MultiplayerSetupScreen.player2Animal = "sheep";
  }

  public void onClickPig2(View v){
    pig2.setBackgroundColor(getResources().getColor(R.color.transRed));
    rooster.setBackgroundColor(getResources().getColor(R.color.transparent));
    horse.setBackgroundColor(getResources().getColor(R.color.transparent));
    sheep.setBackgroundColor(getResources().getColor(R.color.transparent));
    MultiplayerSetupScreen.player2Animal = "pig";
  }

  protected void onResume(){
    super.onResume();
    ScreenLogic.fullScreen(this);
  }
}
