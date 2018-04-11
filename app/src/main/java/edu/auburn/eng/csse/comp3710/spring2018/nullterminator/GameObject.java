package edu.auburn.eng.csse.comp3710.spring2018.nullterminator;

import java.util.ArrayList;
import java.util.Random;

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
    }

    public void buttonPressed(final ImageView buttonIn, int buttonId) {
        buttonIn.setAlpha((float) 1.0);
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                buttonIn.setAlpha(ALPHA);
            }
        }, DELAY_SHORT);
        if(fragGame.inGame) {
            if (buttonId != currentSequence.get(currentSequenceSpot)){
                fragGame.turnClickListenersOff();
                fragGame.gameMessages.setText("Incorrect :(");
                fragGame.gameMessages.setBackgroundColor(
                        fragGame.menuActivity.getResources()
                                .getColor(R.color.red));
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
                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            fragGame.gameMessages.setBackgroundColor(
                                    fragGame.menuActivity.getResources()
                                            .getColor(R.color.bar_color));
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
        int buttonType = arrayIn.get(0);
        switch (buttonType) {
            case BUTTON_RED:
                currentImage = fragGame.imageRed;
                break;
            case BUTTON_BLUE:
                currentImage = fragGame.imageBlue;
                break;
            case BUTTON_GREEN:
                currentImage = fragGame.imageGreen;
                break;
            case BUTTON_YELLOW:
                currentImage = fragGame.imageYellow;
                break;
            default:
                currentImage = null;
                break;
        }
        currentImage.setAlpha((float) 1.0);
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                currentImage.setAlpha(ALPHA);
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
