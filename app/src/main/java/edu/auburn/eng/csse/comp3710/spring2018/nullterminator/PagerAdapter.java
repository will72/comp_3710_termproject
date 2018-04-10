package edu.auburn.eng.csse.comp3710.spring2018.nullterminator;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

/**
 * Created by wharwell on 10/10/16.
 */

public class PagerAdapter extends FragmentStatePagerAdapter {
    int mNumOfTabs;
    MenuActivity menuActivity;
    FragGame tabFragGame;
    FragInfo tabFragInfo;

    public PagerAdapter(FragmentManager fm, int NumOfTabs,
                        MenuActivity activityIn) {
        super(fm);
        this.mNumOfTabs = NumOfTabs;
        this.menuActivity = activityIn;
        tabFragGame = new FragGame();
        tabFragGame.setMenuActivity(menuActivity);
        menuActivity.setFragGame(tabFragGame);
        tabFragInfo = new FragInfo();
        tabFragInfo.setMenuActivity(menuActivity);
        menuActivity.setFragInfo(tabFragInfo);
    }

    @Override
    public Fragment getItem(int position) {

        switch (position) {
            case 0:
                return tabFragGame;
            case 1:
                return tabFragInfo;
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return mNumOfTabs;
    }
}