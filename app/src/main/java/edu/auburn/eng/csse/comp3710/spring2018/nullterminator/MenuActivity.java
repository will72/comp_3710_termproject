package edu.auburn.eng.csse.comp3710.spring2018.nullterminator;

import android.support.design.widget.TabLayout;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;


public class MenuActivity extends AppCompatActivity {

    FragGame fragGame;
    FragInfo fragInfo;
    ScoreObject scoreObject;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.menu);

        scoreObject = new ScoreObject(this);

        initTabs();
    }

    private void initTabs() {
        TabLayout tabLayout = (TabLayout) findViewById(R.id.tab_layout);
        tabLayout.addTab(tabLayout.newTab().setText("Game"));
        tabLayout.addTab(tabLayout.newTab().setText("Info"));
        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);
        final int tabBlue = ContextCompat.getColor(getApplicationContext(),
                R.color.tab_overlay_blue);
        final int tabGrey = ContextCompat.getColor(getApplicationContext(),
                R.color.tab_overlay_dark_grey);
        TabLayout.Tab tabGame = tabLayout.getTabAt(0);
        TabLayout.Tab tabInfo = tabLayout.getTabAt(1);
        tabLayout.setTabTextColors(tabGrey, tabBlue);
        final ViewPager viewPager = (ViewPager) findViewById(R.id.pager);
        final PagerAdapter adapter = new PagerAdapter
                (getSupportFragmentManager(), tabLayout.getTabCount(),
                        this);
        viewPager.setAdapter(adapter);
        viewPager.addOnPageChangeListener(new
                TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        tabLayout.setOnTabSelectedListener(
                new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }

    public void setFragGame(FragGame fragGameIn) {
        fragGame = fragGameIn;
    }
    public void setFragInfo(FragInfo fragInfoIn) {
        fragInfo = fragInfoIn;
    }
    public FragGame getFragGame() {
        return fragGame;
    }
    public FragInfo getFragInro() {
        return fragInfo;
    }
}
