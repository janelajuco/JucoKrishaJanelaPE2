package krishajanela.juco.com.jucokrishajanelape2;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class MainActivity extends AppCompatActivity {

    EditText eFname, eGender, eAge;
    TextView tMsg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        eFname = findViewById(R.id.editText_fname);
        eGender = findViewById(R.id.editText_gender);
        eAge = findViewById(R.id.editText_age);
        tMsg = findViewById(R.id.tvmsg);
    }

    public void saveInternal (View v){
        FileOutputStream fos = null;
        try {
            fos = openFileOutput("data2.txt", Context.MODE_PRIVATE);
            String fname = eFname.getText().toString() + "\n";
            String age = eAge.getText().toString() + "\n";
            String gender = eGender.getText().toString() + "\n";
            fos.write(fname.getBytes());
            fos.write(age.getBytes());
            fos.write(gender.getBytes());
            Toast.makeText(this, "Data saved...", Toast.LENGTH_LONG).show();
        } catch (Exception e) {
            Toast.makeText(this, "Error writing data...", Toast.LENGTH_LONG).show();
        }finally {
            try {
                fos.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void displayMsg(View v) {
        SharedPreferences sp = getSharedPreferences("data1", Context.MODE_PRIVATE);
        String name = sp.getString("name", null);
        String section = sp.getString("sec", null);
        String message = "Good afternoon " + name + "!\n\nYour section is " + section;
        //String message2 = "Your section is " + section;
        tMsg.setText(message);
        //tMsg.setText(message2);
    }

    public void displayInternal(View v) {
        try {
            FileInputStream fin = openFileInput("data2.txt" );
            int c;
            StringBuffer buffer = new StringBuffer();
            while ((c = fin.read())!= -1) {
                buffer.append((char) c);
            }
            String message = "" + buffer;
            tMsg.setText(message);
        } catch (Exception e) {
            Toast.makeText(this, "Error reading...", Toast.LENGTH_LONG).show();
        }
    }
}
