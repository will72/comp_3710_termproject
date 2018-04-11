package edu.auburn.eng.csse.comp3710.spring2018.nullterminator;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by Will on 2018-04-09.
 */

public class FragGame extends Fragment implements View.OnClickListener {

    MenuActivity menuActivity;
    ImageView imageBlue;
    ImageView imageRed;
    ImageView imageYellow;
    ImageView imageGreen;
    TextView startGame;
    TextView gameMessages;
    TextView gameCurrentScore;

    GameObject gameObject;
    boolean inGame = false;

    @Override
    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.frag_game,
                container, false);
        initViews(rootView);

        gameObject = new GameObject(this);

        return rootView;
    }

    private void initViews(View view) {
        imageBlue = view.findViewById(R.id.button_blue);
        imageRed = view.findViewById(R.id.button_red);
        imageYellow = view.findViewById(R.id.button_yellow);
        imageGreen = view.findViewById(R.id.button_green);
        startGame = view.findViewById(R.id.start_game);
        gameMessages = view.findViewById(R.id.game_messages);
        gameCurrentScore = view.findViewById(R.id.game_current_score);
        turnClickListenersOn();
    }

    public void turnClickListenersOn() {
        imageBlue.setOnClickListener(this);
        imageRed.setOnClickListener(this);
        imageYellow.setOnClickListener(this);
        imageGreen.setOnClickListener(this);
        startGame.setOnClickListener(this);
        gameMessages.setOnClickListener(this);
    }

    public void turnClickListenersOff() {
        imageBlue.setOnClickListener(null);
        imageRed.setOnClickListener(null);
        imageYellow.setOnClickListener(null);
        imageGreen.setOnClickListener(null);
        startGame.setOnClickListener(null);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.button_blue:
                gameObject.buttonPressed(imageBlue, gameObject.BUTTON_BLUE, gameObject.bluePlayer);
                break;
            case R.id.button_red:
                gameObject.buttonPressed(imageRed, gameObject.BUTTON_RED, gameObject.redPlayer);
                break;
            case R.id.button_yellow:
                gameObject.buttonPressed(imageYellow, gameObject.BUTTON_YELLOW, gameObject.yellowPlayer);
                break;
            case R.id.button_green:
                gameObject.buttonPressed(imageGreen, gameObject.BUTTON_GREEN, gameObject.greenPlayer);
                break;
            case R.id.start_game:
                gameObject.startGame();
                break;
        }
    }

    public void setMenuActivity(MenuActivity menuActivityIn) {
        menuActivity = menuActivityIn;
    }
}
