package za.co.mosecoza.reportcard;

/**
 * Created by Bonginkosi Lukhele on 11-11-2016.
 */

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class Home extends AppCompatActivity implements View.OnClickListener {
    Button view, edit, add, generate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home);

        view = (Button) findViewById(R.id.btn_view);
        edit = (Button) findViewById(R.id.btn_edit);
        add = (Button) findViewById(R.id.btn_add);
        view.setOnClickListener(this);
        edit.setOnClickListener(this);
        add.setOnClickListener(this);
    }

    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_view:
                Intent a = new Intent("android.intent.action.INFO");
                startActivity(a);
                break;

            case R.id.btn_edit:
                Intent b = new Intent(this, EditStudentInformation.class);
                startActivity(b);
                break;

            case R.id.btn_add:
                Intent c = new Intent("android.intent.action.Register");
                startActivity(c);
                break;
        }

    }

}

