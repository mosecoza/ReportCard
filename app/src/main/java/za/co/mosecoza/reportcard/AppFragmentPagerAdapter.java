package za.co.mosecoza.reportcard;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

/**
 * Created by Admin on 2016-11-25.
 */

public class AppFragmentPagerAdapter extends FragmentPagerAdapter{
    final int PAGE_COUNT = 3;

    private String tabTitles[] = new String[] { "Register", "View All", "Edit information"};
    private Context mContext;

    public AppFragmentPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    public AppFragmentPagerAdapter(FragmentManager fm, Context context) {
        super(fm);
        mContext = context;
    }

    @Override
    public Fragment getItem(int position) {
        if (position == 0) {
            return new RegisterFragment();
        } else if (position == 1) {
            return new ViewFragment();
        } else {
            return new EditStudentInformation();
        }
    }

    @Override
    public int getCount() {
        return PAGE_COUNT;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        if (position == 0) {
            return mContext.getResources().getString(R.string.register_new_student);
        } else if (position == 1) {
            return mContext.getResources().getString(R.string.view_all_students);
        } else {
            return mContext.getResources().getString(R.string.edit_student_data);
        }
    }
}
