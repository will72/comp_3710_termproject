package edu.auburn.eng.csse.comp3710.spring2018.nullterminator;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by Will on 2018-04-09.
 */

public class FragGame extends Fragment {

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
//        TextView LandownerButton = (TextView) view
//                .findViewById(R.id.landowner_assistance_button);
//        LandownerButton.setOnClickListener(this);
    }
}
