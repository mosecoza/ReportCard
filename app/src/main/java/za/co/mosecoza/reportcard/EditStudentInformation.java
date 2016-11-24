package za.co.mosecoza.reportcard;

import android.app.Dialog;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TableLayout;
import android.widget.TextView;

import java.util.ArrayList;

public class EditStudentInformation extends AppCompatActivity {

    //initialising variables
    EditText et_searchId, vSurname, vStudName, vSid, vSadres, vSkin, vScontact, vStest1, vStest2, vstest3;

    Button btn_update;
    String sSnam, sNam, sId, sAdres, sKin, sCont, theId, sSub1, sSub2, sSub3, error;

    TableLayout tableLayout;

    TextView tv;
    StudentsRepo databaseReportCard;
    Dialog dialog;
    long long_id;
    Bundle bundle;
    int getRowIdIfUpdateSuccess;
    boolean didItWork;
    StudentsRepo studentsRepo;
    Students students;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.edit_info);

        btn_update = (Button) findViewById(R.id.btn_edit_update);
        tableLayout = (TableLayout) findViewById(R.id.tl_data);
        tableLayout.setVisibility(View.GONE);
        btn_update.setVisibility(View.GONE);

        et_searchId = (EditText) findViewById(R.id.et_editFind_id);
        try {
            bundle = getIntent().getExtras();
            int bunny;

            bunny = bundle.getInt("id");
            searchFromBundle(bunny);
        }catch (Exception e) {
            didItWork = false;
            error = e.toString();
            showMessage("Check if Bundle is null","Oh sorry, press back");

        } finally {
            if (didItWork) {
                showMessage("Check if Bundle is null","Student was successfully added");
            }
        }
    }

    //method to search for the ID
    public void search(View v) {
        tableLayout.setVisibility(View.VISIBLE);
        btn_update.setVisibility(View.VISIBLE);
        initialise();

        theId = et_searchId.getText().toString();

        databaseReportCard = new StudentsRepo();

        try{
            getStudentDataFromDatabase();
        }catch (Exception e){
            error = e.toString();
            didItWork = false;
        }finally {
            didItWork=true;
            if(didItWork){
                showMessage("Editext information status","editext successfully filled");}
            else
                showMessage("Editext information status"," no data was found for: "+theId);
        }
        setEditextContent();
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
                error = e.toString();
            } finally {didItWork = true;
                if (didItWork) {

                    showMessage("---------student update----------","student updated");
                    tableLayout.setVisibility(View.GONE);
                    btn_update.setVisibility(View.GONE);
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

        if (sSnam.isEmpty() && sNam.isEmpty() && sId.isEmpty()
                && sAdres.isEmpty() && sCont.isEmpty() && sSub1.isEmpty()
                && sSub2.isEmpty() && sSub3.isEmpty()) {

           showMessage("    Get information from editext     ","no all data is inserted");
            return false;
        }
        showMessage("    Get information from editext     ","successfully read from editext");
        return true;
    }

    //a method to retrieve information about of a learner from database
    public Boolean getStudentDataFromDatabase() {
        ArrayList<String> list = new ArrayList();

        StudentsRepo studentsRepo = new StudentsRepo();


        if (studentsRepo.getStudentToEditInfo(theId)!=null){
            try{
                studentsRepo.doesStudentExist(theId);
                list=studentsRepo.getStudentToEditInfo(theId);
            }catch (Exception e){
                didItWork = false;
                String error = e.toString();

            }finally {
                didItWork=true;
                if(didItWork){

                    showMessage("    check if student exist    ","This student exist");}
                else
                    showMessage("    check if student exist    ","student does not exist");
            }
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
            finish();
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

        et_searchId.setText(String.format(String.valueOf(bunny)));
        search(et_searchId);
    }
    public void DeleteStudent(View view){
        StudentsRepo studentsRepo = new StudentsRepo();


        if (studentsRepo.getStudentToEditInfo(theId)!=null){
            int s =studentsRepo.deleteStudent(theId);
            if (s>0){
                showMessage("     Student Delete      ", "The student was deleted");
            initialise();}
            else {showMessage("     Student Delete      ","The student was not deleted");}
    }
    }

    public void showMessage(String title, String message){
        AlertDialog.Builder builder = new AlertDialog.Builder(this,R.style.alertDialogTheme);
        builder.setMessage(message);
        builder.setTitle(title);
        builder.show();
    }

}
