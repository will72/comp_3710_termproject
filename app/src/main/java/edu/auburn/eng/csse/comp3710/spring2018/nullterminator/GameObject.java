package edu.auburn.eng.csse.comp3710.spring2018.nullterminator;

import java.util.ArrayList;
import java.util.Random;

import android.media.MediaPlayer;
import android.os.Handler;
import android.widget.ImageView;
import android.widget.Toast;

/**
 * Created by Will on 2018-04-10.
 */

public class GameObject {

    public FragGame fragGame;

    Handler handler;
    Random rand;
    ArrayList<Integer> currentSequence;
    boolean newHighScore = false;

    MediaPlayer bluePlayer;
    MediaPlayer redPlayer;
    MediaPlayer yellowPlayer;
    MediaPlayer greenPlayer;
    MediaPlayer correctPlayer;
    MediaPlayer incorrectPlayer;

    final long DELAY = 1000;
    final long DELAY_SHORT = 250;
    final long DELAY_LONG = 3000;
    final float ALPHA = (float) 0.3;
    final int BUTTON_RED = 0;
    final int BUTTON_BLUE = 1;
    final int BUTTON_GREEN = 2;
    final int BUTTON_YELLOW = 3;

    int currentSequenceSpot = 0;
    int currentScore = 0;

    public GameObject(FragGame fragGameIn) {
        fragGame = fragGameIn;

        handler = new android.os.Handler();
        rand = new Random();

        currentSequence = new ArrayList<>();

        bluePlayer = MediaPlayer.create(fragGame.getContext(), R.raw.button1);
        redPlayer = MediaPlayer.create(fragGame.getContext(), R.raw.button2);
        yellowPlayer = MediaPlayer.create(fragGame.getContext(), R.raw.button3);
        greenPlayer = MediaPlayer.create(fragGame.getContext(), R.raw.button4);
        correctPlayer = MediaPlayer.create(fragGame.getContext(), R.raw.correct);
        incorrectPlayer = MediaPlayer.create(fragGame.getContext(), R.raw.incorrect);
        correctPlayer.setVolume(0.01f, 0.01f);
        incorrectPlayer.setVolume(0.01f, 0.01f);
    }

    public void buttonPressed(final ImageView buttonIn, int buttonId, final MediaPlayer playerIn) {
        playerIn.start();
        buttonIn.setAlpha((float) 1.0);
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                buttonIn.setAlpha(ALPHA);
                playerIn.pause();
                playerIn.seekTo(0);
            }
        }, DELAY_SHORT);
        if(fragGame.inGame) {
            if (buttonId != currentSequence.get(currentSequenceSpot)){
                fragGame.turnClickListenersOff();
                fragGame.gameMessages.setText("Incorrect :(");
                fragGame.gameMessages.setBackgroundColor(
                        fragGame.menuActivity.getResources()
                                .getColor(R.color.red));
                incorrectPlayer.start();
                if(newHighScore) {
                    newHighScore = false;
                    Toast.makeText(fragGame.menuActivity
                                    .getApplicationContext(),
                            ("New high score of "
                                    + Integer.toString(currentScore)
                                    + "!!!"),
                            Toast.LENGTH_LONG).show();
                }
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        currentScore = 0;
                        fragGame.gameCurrentScore.setText(
                                Integer.toString(currentScore));
                        fragGame.gameMessages.setText("Hit " +
                                "\"START GAME\" to start!");
                        fragGame.gameMessages.setBackgroundColor(
                                fragGame.menuActivity.getResources()
                                        .getColor(R.color.bar_color));
                        currentSequenceSpot = 0;
                        currentSequence = new ArrayList<>();
                        fragGame.startGame.setText("START GAME");
                        fragGame.turnClickListenersOn();
                        fragGame.inGame = false;
//                        incorrectPlayer.pause();
                        incorrectPlayer.seekTo(0);
                    }
                }, DELAY_LONG);
            } else  {
                currentSequenceSpot++;
                if(currentSequenceSpot >= currentSequence.size()) {
                    currentScore++;
                    fragGame.gameCurrentScore.setText(
                            Integer.toString(currentScore));
                    fragGame.turnClickListenersOff();
                    currentSequenceSpot = 0;
                    fragGame.gameMessages.setText("Good Job! Moving on...");
                    fragGame.gameMessages.setBackgroundColor(
                            fragGame.menuActivity.getResources()
                                    .getColor(R.color.green));
                    correctPlayer.start();
                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            fragGame.gameMessages.setBackgroundColor(
                                    fragGame.menuActivity.getResources()
                                            .getColor(R.color.bar_color));
                            correctPlayer.pause();
                            correctPlayer.seekTo(0);
                            dispSequence();
                        }
                    }, DELAY_LONG);
                }
            }
        }
        if(fragGame.inGame)
            updateScore();
    }

    private void updateScore() {
        fragGame.menuActivity.scoreObject
                .writeLastScore(Integer
                        .toString(currentScore));
        if(currentScore > fragGame.menuActivity
                .scoreObject.highScore) {
            fragGame.menuActivity
                    .scoreObject.writeHighScore(Integer
                    .toString(currentScore));
            newHighScore = true;
        }
        fragGame.menuActivity.fragInfo.updateScoreInfo(
                fragGame.menuActivity
                        .scoreObject);
    }

    public void startGame() {
        fragGame.inGame = true;
        fragGame.turnClickListenersOff();
        fragGame.startGame.setText("GAME IN PROGRESS");
        dispSequence();
    }

    private void dispSequence() {
        fragGame.gameMessages.setText("Pay attention to the sequence.");
        currentSequence.add(rand.nextInt(4));
        ArrayList<Integer> sequenceCopy = new ArrayList<>(currentSequence);
        dispSequenceHelper(sequenceCopy);
        fragGame.turnClickListenersOff();
    }
    private void dispSequenceHelper(final ArrayList<Integer> arrayIn) {
        if(arrayIn.size() == 0) {
            fragGame.turnClickListenersOn();
            fragGame.gameMessages.setText("Now enter the sequence!");
            return;
        }
        final ImageView currentImage;
        final MediaPlayer currentPlayer;
        int buttonType = arrayIn.get(0);
        switch (buttonType) {
            case BUTTON_RED:
                currentImage = fragGame.imageRed;
                currentPlayer = redPlayer;
                break;
            case BUTTON_BLUE:
                currentImage = fragGame.imageBlue;
                currentPlayer = bluePlayer;
                break;
            case BUTTON_GREEN:
                currentImage = fragGame.imageGreen;
                currentPlayer = greenPlayer;
                break;
            case BUTTON_YELLOW:
                currentImage = fragGame.imageYellow;
                currentPlayer = yellowPlayer;
                break;
            default:
                currentImage = null;
                currentPlayer = null;
                break;
        }
        currentImage.setAlpha((float) 1.0);
        currentPlayer.start();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                currentImage.setAlpha(ALPHA);
                currentPlayer.pause();
                currentPlayer.seekTo(0);
                arrayIn.remove(0);
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        dispSequenceHelper(arrayIn);
                    }
                }, DELAY_SHORT);
            }
        }, DELAY);
    }

}
