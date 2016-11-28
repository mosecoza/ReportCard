package za.co.mosecoza.reportcard;

/**
 * Created by Bonginkosi Lukhele on 11-11-2016.
 */

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

public class Home extends AppCompatActivity  {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home);
        ViewPager viewPager = (ViewPager) findViewById(R.id.vp_pager);
        viewPager.setAdapter(new AppFragmentPagerAdapter(getSupportFragmentManager(), Home.this));
        TabLayout tabLayout = (TabLayout)findViewById(R.id.tab);
        tabLayout.setupWithViewPager(viewPager);

    }

}

