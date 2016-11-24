package za.co.mosecoza.reportcard;

import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TableLayout;

public class RegisterStudents extends AppCompatActivity implements View.OnClickListener {
    Button btnAddStudent;
    EditText sqlSurname, sqlName, sqlID_num, sqlAddress, sqlKin, sqlContact, sqlTest1, sqlTest2, sqlTest3;
    int getRowIdIfAddIsSuccess;
    TableLayout registerTable;
    String surname, name, id,address, kin,contact,test1,test2,test3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_students);
        initialise();
    }

    private void initialise() {
        registerTable = (TableLayout) findViewById(R.id.tl_register_students);
        sqlSurname = (EditText) findViewById(R.id.et_surname);
        sqlName = (EditText) findViewById(R.id.et_name);
        sqlID_num = (EditText) findViewById(R.id.et_id);
        sqlAddress = (EditText) findViewById(R.id.et_address);
        sqlKin = (EditText) findViewById(R.id.et_kin);
        sqlTest1 = (EditText) findViewById(R.id.et_test_1);
        sqlTest2 = (EditText) findViewById(R.id.et_test_2);
        sqlTest3 = (EditText) findViewById(R.id.et_test_3);
        sqlContact = (EditText) findViewById(R.id.et_contact);
        btnAddStudent = (Button) findViewById(R.id.btn_add);

        btnAddStudent.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        Students students = new Students();

        boolean didItWork = true;
        try {

            StudentsRepo studentsRepo = new StudentsRepo();
            readEditText();

            students.setSurname(surname);
            students.setName(name);
            students.setId(id);
            students.setAddress(address);
            students.setNext_of_kin(kin);
            students.setContact(contact);
            students.setSubject_1(test1);
            students.setSubject_2(test2);
            students.setSubject_3(test3);
            getRowIdIfAddIsSuccess = studentsRepo.insert(students);

        } catch (Exception e) {
            didItWork = false;
            String error = e.toString();


        } finally {
            if (didItWork) {
                showMessage("Student Register","Student was successfully added");
                clearEditText();
            }

        }
    }
    public void showMessage(String title, String message){
        AlertDialog.Builder builder = new AlertDialog.Builder(this,R.style.alertDialogTheme);
        builder.setMessage(message);
        builder.setTitle(title);
        builder.show();
    }
    public void clearEditText(){
        sqlSurname.setText(null);
        sqlName.setText(null);
        sqlID_num.setText(null);
        sqlAddress.setText(null);
        sqlKin.setText(null);
        sqlTest1.setText(null);
        sqlTest2.setText(null);
        sqlTest3.setText(null);
        sqlContact.setText(null);
        btnAddStudent.setVisibility(View.GONE);
    }
    //a method to read  information of a learner from the editText so to send queries to database
    public boolean readEditText() {
        surname = sqlSurname.getText().toString();
        name = sqlName.getText().toString();
        id = sqlID_num.getText().toString();
        address = sqlAddress.getText().toString();
        kin = sqlKin.getText().toString();
        contact = sqlContact.getText().toString();
        test1 = sqlTest1.getText().toString();
        test2 = sqlTest2.getText().toString();
        test3 = sqlTest3.getText().toString();

        if (surname.isEmpty() && name.isEmpty() && id.isEmpty()
                && address.isEmpty() && contact.isEmpty() && test1.isEmpty()
                && test2.isEmpty() && test3.isEmpty()) {
            return true;
        }
        return false;
    }

}
