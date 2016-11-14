package za.co.mosecoza.reportcard;

import android.app.Dialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class RegisterStudents extends AppCompatActivity implements View.OnClickListener {
    Button btnUpdate;
    EditText sqlSurname, sqlName, sqlID_num, sqlAddress, sqlKin, sqlContact, sqlTest1,sqlTest2,sqlTest3;
    int getRowIdIfAddIsSuccess;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_students);
        initialise();

    }

    private void initialise() {
        sqlSurname = (EditText) findViewById(R.id.et_surname);
        sqlName = (EditText) findViewById(R.id.et_name);
        sqlID_num = (EditText) findViewById(R.id.et_id);
        sqlAddress = (EditText) findViewById(R.id.et_address);
        sqlKin = (EditText) findViewById(R.id.et_kin);
        sqlTest1 = (EditText) findViewById(R.id.et_test_1);
        sqlTest2 = (EditText) findViewById(R.id.et_test_2);
        sqlTest3 = (EditText) findViewById(R.id.et_test_3);
        sqlContact = (EditText) findViewById(R.id.et_contact);
        btnUpdate = (Button) findViewById(R.id.btn_add);

        btnUpdate.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
                Students students = new Students();
            switch (v.getId()){
                case R.id.btn_edit_update:
                boolean didItWork = true;
                try {
                    String surname = sqlSurname.getText().toString();
                    String name = sqlName.getText().toString();
                    String id = sqlID_num.getText().toString();
                    String address = sqlAddress.getText().toString();
                    String kin = sqlKin.getText().toString();
                    String contact = sqlContact.getText().toString();
                    String test1 = sqlTest1.getText().toString();
                    String test2 = sqlTest2.getText().toString();
                    String test3 = sqlTest3.getText().toString();


                    StudentsRepo studentsRepo = new StudentsRepo();

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
                        Dialog d = new Dialog(this);
                        System.out.println("The row_id is: " + getRowIdIfAddIsSuccess);
                        d.setTitle("Heck Yea!!");
                        TextView tv = new TextView(this);
                        tv.setText(R.string.diaolg_add_success);
                        d.setContentView(tv);
                        d.show();
                    }
                }break;
            }
        }

}
