package edu.auburn.eng.csse.comp3710.spring2018.nullterminator;

import android.os.Handler;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

/**
 * Created by Will on 2018-04-09.
 */

public class FragGame extends Fragment implements View.OnClickListener {

    MenuActivity menuActivity;
    ImageView imageBlue;
    ImageView imageRed;
    ImageView imageYellow;
    ImageView imageGreen;

    @Override
    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.frag_game,
                container, false);
        initViews(rootView);
        return rootView;
    }

    private void initViews(View view) {
        imageBlue = view.findViewById(R.id.button_blue);
        imageRed = view.findViewById(R.id.button_red);
        imageYellow = view.findViewById(R.id.button_yellow);
        imageGreen = view.findViewById(R.id.button_green);
        imageBlue.setOnClickListener(this);
        imageRed.setOnClickListener(this);
        imageYellow.setOnClickListener(this);
        imageGreen.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.button_blue:
                buttonGlow(imageBlue);
                break;
            case R.id.button_red:
                buttonGlow(imageRed);
                break;
            case R.id.button_yellow:
                buttonGlow(imageYellow);
                break;
            case R.id.button_green:
                buttonGlow(imageGreen);
                break;
        }
    }

    public void setMenuActivity(MenuActivity menuActivityIn) {
        menuActivity = menuActivityIn;
    }

    private void buttonGlow(final ImageView buttonIn) {
        buttonIn.setAlpha((float) 1.0);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                buttonIn.setAlpha((float) 0.3);
            }
        }, 1000);
    }
}
