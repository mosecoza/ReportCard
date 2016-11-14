package za.co.mosecoza.reportcard;

import android.app.Dialog;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TableLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class EditStudentInformation extends AppCompatActivity {

    //initialising variables
    EditText et_searchId, vSurname, vStudName, vSid, vSadres, vSkin, vScontact, vStest1, vStest2, vstest3;

    Button btn_update;
    String sSnam, sNam, sId, sAdres, sKin, sCont, theId, sSub1, sSub2, sSub3;

    TableLayout tableLayout;

    TextView tv;
    StudentsRepo databaseReportCard;
    Dialog dialog;
    long long_id;
    Bundle bundle;
    int getRowIdIfUpdateSuccess;
    StudentsRepo studentsRepo;
    Students students;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.edit_info);

        et_searchId = (EditText) findViewById(R.id.et_editFind_id);
        bundle = getIntent().getExtras();
        int bunny;
        if (bundle==null) {
            return;
        } else {
            bunny = bundle.getInt("id");
            System.out.println("the bunny is: " + bunny);
            searchFromBundle(bunny);

        }


        btn_update = (Button) findViewById(R.id.btn_edit_update);
        tableLayout = (TableLayout) findViewById(R.id.tl_data);


    }

    //method to search for the ID
    public void search(View v) {

        initialise();

        theId = et_searchId.getText().toString();

        long_id = Long.parseLong(theId);
        databaseReportCard = new StudentsRepo();

        if (databaseReportCard.doesStudentExist(theId)) {
            getStudentDataFromDatabase();
            setEditextContent();
            Toast.makeText(this, " this student exists", Toast.LENGTH_LONG).show();

        } else {
            Toast.makeText(this, " this student does not exists", Toast.LENGTH_LONG).show();
            return;
        }
//        if (getStudentDataFromDatabase()) {

//        }
    }

    //      method to send the update
    public void sendUpdate(View view) {
        boolean didItWork = true;
        if (readEditText()) {

            long_id = Long.parseLong(theId);

            try {

                StudentsRepo studentsRepo = new StudentsRepo();
                Students students = new Students();
                students.setRow_id(theId);
                students.setSurname(sSnam);
                students.setName(sNam);
                students.setId(sId);
                students.setNext_of_kin(sKin);
                students.setContact(sCont);
                students.setSubject_1(sSub1);
                students.setSubject_2(sSub2);
                students.setSubject_3(sSub3);
                getRowIdIfUpdateSuccess = studentsRepo.updateStudentData(students);
                DatabaseManager.getInstance().closeDatabase();
            } catch (Exception e) {
                didItWork = false;
                String error = e.toString();
            } finally {
                if (didItWork) {

                    dialog = new Dialog(this);
                    dialog.setTitle(" Did it work ");
                    TextView tv = new TextView(this);
                    tv.setText(R.string.update_succsess);
                    dialog.setContentView(tv);
                    dialog.show();
                }
            }
        }
    }

    //a method to read  information of a learner from the editText so to send queries to database
    public boolean readEditText() {
        sSnam = vSurname.getText().toString();
        sNam = vStudName.getText().toString();
        sKin = vSkin.getText().toString();
        sId = vSid.getText().toString();
        sAdres = vSadres.getText().toString();
        sCont = vScontact.getText().toString();
        sSub1 = vStest1.getText().toString();
        sSub2 = vStest2.getText().toString();
        sSub3 = vstest3.getText().toString();

        if (sSnam.isEmpty() || sNam.isEmpty() || sId.isEmpty()
                || sAdres.isEmpty() || sCont.isEmpty() || sSub1.isEmpty()
                || sSub2.isEmpty() || sSub3.isEmpty()) {

            dialog = new Dialog(getApplicationContext());
            dialog.setTitle(" error message  ");
            tv.setText(R.string.edit_dialog_msg);
            dialog.setContentView(tv);
            dialog.show();
            return false;
        }
        return true;
    }

    //a method to retrieve information about of a learner from database
    public Boolean getStudentDataFromDatabase() {
        System.out.println("the index is: " + long_id);
        ArrayList<String> list = new ArrayList();

        StudentsRepo studentsRepo = new StudentsRepo();


        if (studentsRepo.getStudentToEditInfo(theId)!=null){
            list = studentsRepo.getStudentToEditInfo(theId);
        sSnam = list.get(1);
        sNam = list.get(2);
        sId = list.get(3);
        sAdres = list.get(4);
        sKin = list.get(5);
        sCont = list.get(6);
        sSub1 = list.get(7);
        sSub2 = list.get(8);
        sSub3 = list.get(9);
            return true;
        }
        else{
            return false;
        }

    }

    //a method to populate the current information of a learner on the editext
    public void setEditextContent() {
        vSurname.setText(sSnam);
        vStudName.setText(sNam);
        vSid.setText(sId);
        vSkin.setText(sKin);
        vSadres.setText(sAdres);
        vScontact.setText(sCont);
        vStest1.setText(sSub1);
        vStest2.setText(sSub2);
        vstest3.setText(sSub3);
    }

    //to initialise variables and views in the data form
    public void initialise() {
        vSadres = (EditText) findViewById(R.id.et_edit_address);
        vScontact = (EditText) findViewById(R.id.et_edit_contact);
        vSid = (EditText) findViewById(R.id.et_edit_id);
        vSkin = (EditText) findViewById(R.id.et_edit_kin);
        vStudName = (EditText) findViewById(R.id.et_edit_name);
        vSurname = (EditText) findViewById(R.id.et_edit_surname);
        vStest1 = (EditText) findViewById(R.id.et_edit_test1);
        vStest2 = (EditText) findViewById(R.id.et_edit_test2);
        vstest3 = (EditText) findViewById(R.id.et_edit_test3);
    }

    //a method to search a student if coming from view_info class
    public void searchFromBundle(int bunny) {
        System.out.println("the bunny is: " + bunny);

        et_searchId.setText(String.format(String.valueOf(bunny)));
        search(et_searchId);
    }
}
