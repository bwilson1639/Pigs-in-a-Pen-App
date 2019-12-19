package com.example.pigsinapenteam2;

        import org.junit.Test;

        import static org.junit.Assert.*;

public class SinglePlayerPlayScreenTest {

  @Test
  /**
   * A test to check that the bot created would be easy bot player if game difficulty is set to 0.
   */
  public void difficultySetter() {
    SinglePlayerSetupScreen.gameDifficulty = 0;
    SinglePlayerPlayScreen sp = new SinglePlayerPlayScreen();
    sp.difficultySetter();
    assertTrue(sp.player2 instanceof EasyBotPlayer);
  }
}