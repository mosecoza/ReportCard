package za.co.mosecoza.reportcard;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Bonginkosi Lukhele on 11-11-2016.
 */

public class ViewAllStudents extends ListActivity {

    StudentsRepo studentsRepo;
    ArrayList<String> data = new ArrayList<>();
    private ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.view_info);



//        TextView tv = (TextView) findViewById(R.id.tv_viewinfo);
        studentsRepo = new StudentsRepo();
        data = studentsRepo.getDataForListview();
//        tv.setText(tableLayout);
        if (data != null) {
            ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(getApplicationContext(),
                    android.R.layout.simple_list_item_1, data);
            listView = (ListView) findViewById(android.R.id.list);
            TextView tv = new TextView(this);
            tv.setText(R.string.listview_heading);
            listView.addHeaderView(tv);
            listView.setAdapter(arrayAdapter);
        }

    }

    @Override
    protected void onListItemClick(ListView listview, View view, int position,
                                   long lo) {
        super.onListItemClick(listview, view, position, lo);
        long ed = position;
        System.out.println("The other int is: "+position);
        System.out.println("The other long is: "+lo);
        System.out.println(studentsRepo.getStSurname(position));
//


//        Intent intent = new Intent(this, EditStudentInformation.class);
//        intent.putExtra("id", p);
//        startActivity(intent);
    }
}

