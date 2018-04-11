package edu.auburn.eng.csse.comp3710.spring2018.nullterminator;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * Created by Will on 2018-04-09.
 */

public class FragInfo extends Fragment {

    MenuActivity menuActivity;
    TextView infoLastScore;
    TextView infoHighScore;

    @Override
    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.frag_info,
                container, false);
        initViews(rootView);
        updateScoreInfo(menuActivity.scoreObject);
        return rootView;
    }

    private void initViews(View view) {
        infoLastScore = view.findViewById(R.id.info_last_score);
        infoHighScore = view.findViewById(R.id.info_high_score);
    }

    public void setMenuActivity(MenuActivity menuActivityIn) {
        menuActivity = menuActivityIn;
    }

    public void updateScoreInfo(ScoreObject scoreObjectIn) {
        infoLastScore.setText(Integer.toString(scoreObjectIn.lastScore));
        infoHighScore.setText(Integer.toString(scoreObjectIn.highScore));
    }
}
