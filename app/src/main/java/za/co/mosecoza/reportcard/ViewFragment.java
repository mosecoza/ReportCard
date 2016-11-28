package za.co.mosecoza.reportcard;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Admin on 2016-11-25.
 */

public class ViewFragment extends Fragment{

    StudentsRepo studentsRepo;
    ArrayList<String> data = new ArrayList<>();
    private ListView listView;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.view_info, container, false);

        studentsRepo = new StudentsRepo();
        data = studentsRepo.getDataForListview();
        if (data != null) {
            ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this.getContext() ,
                    android.R.layout.simple_list_item_1, data);
            listView = (ListView) rootView.findViewById(android.R.id.list);
            TextView tv = new TextView(getContext());
            tv.setText(R.string.listview_heading);
            listView.addHeaderView(tv);
            listView.setAdapter(arrayAdapter);
        }

        return rootView;

    }


}
