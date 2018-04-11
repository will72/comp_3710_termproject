package edu.auburn.eng.csse.comp3710.spring2018.nullterminator;

import java.util.ArrayList;
import java.util.Random;

import android.os.Handler;
import android.widget.ImageView;

/**
 * Created by Will on 2018-04-10.
 */

public class GameObject {

    public MenuActivity menuActivity;
    public FragGame fragGame;

    Handler handler;
    Random rand;

    final long DELAY = 1000;
    final long DELAY_SHORT = 250;
    final float ALPHA = (float) 0.3;
    ArrayList<Integer> currentSequence;
    final int BUTTON_RED = 0;
    final int BUTTON_BLUE = 1;
    final int BUTTON_GREEN = 2;
    final int BUTTON_YELLOW = 3;

    public GameObject(FragGame fragGameIn) {
        fragGame = fragGameIn;

        handler = new android.os.Handler();
        rand = new Random();

        currentSequence = new ArrayList<>();
    }

    public void buttonGlow(final ImageView buttonIn) {
        buttonIn.setAlpha((float) 1.0);
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                buttonIn.setAlpha(ALPHA);
            }
        }, DELAY_SHORT);
    }

    public void startGame() {
        fragGame.turnClickListenersOff();
        fragGame.startGame.setText("GAME IN PROGRESS");
        fragGame.gameMessages.setText("Pay attention to the sequence.");
        dispSequence();
    }

    private void dispSequence() {
        currentSequence.add(rand.nextInt(4));
        ArrayList<Integer> sequenceCopy = new ArrayList<>(currentSequence);
        dispSequenceHelper(sequenceCopy);
    }
    private void dispSequenceHelper(final ArrayList<Integer> arrayIn) {
        if(arrayIn.size() == 0) {
            fragGame.turnClickListenersOn();
            fragGame.startGame.setText("START GAME");
            fragGame.gameMessages.setText("Hit \"START GAME\" to start!");
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
