package faisal.home.com.college;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.webkit.HttpAuthHandler;
import android.widget.EditText;
import android.widget.TextView;

import java.util.HashMap;

public class Login_Form extends AppCompatActivity {

    EditText email, password;
    TextView errorTextView;
    String[] params = null;
    static HashMap map;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login__form);
        email = (EditText) findViewById(R.id.EmailID);
        password = (EditText) findViewById(R.id.Password);
        errorTextView = (TextView) findViewById(R.id.WarningTextView);
        params = new String[2];


    }



    public void Send(View view) {
        switch (view.getId()) {
            case R.id.loginButton:

                params[0] = email.getText().toString();
                params[1] = password.getText().toString();
                Worker worker = new Worker(this, map);
                worker.execute(params);

                break;

            case R.id.RegisterButton:
                errorTextView.setVisibility(View.GONE);
                startActivity(new Intent(this, Registration_Form.class));
                break;
        }
    }

    public void loginProcess(HashMap map) {

        boolean valid = (boolean) map.get("isUser");
        if (!valid) {
            errorTextView.setText("Invalidate Email and Password");
            errorTextView.setVisibility(View.VISIBLE);
        } else {
            errorTextView.setVisibility(View.GONE);
            Intent intent = new Intent(this, Panel.class);
            switch ((String) map.get("panelName")) {
                case "CollegeAdmin":
                    Admin admin = (Admin) map.get("panel");
                    intent.putExtra("object", admin);
                    break;
                case "HOD":
                    HeadOfDepartment hod = (HeadOfDepartment) map.get("panel");
                    intent.putExtra("object", hod);
                    break;
                case "Teacher":
                    Teacher teacher = (Teacher) map.get("panel");
                    intent.putExtra("object", teacher);
                    break;
                case "Student":
                    Student student = (Student) map.get("panel");
                    intent.putExtra("object", student);
                    break;
            }

            intent.putExtra("panelName", (String) map.get("panelName"));
            startActivity(intent);
        }
    }


    public class Worker extends AsyncTask<String, Void, HashMap> {
        HashMap map;
        Context context;


        Worker(Context context, HashMap map) {
            this.context = context;
            this.map = map;
        }

        @Override
        protected HashMap doInBackground(String... strings) {

            MyCollegeDatabase db = new MyCollegeDatabase(context);
            map = db.checkEmailIdAndPassword(strings[0], strings[1]);

            return map;
        }

        @Override
        protected void onPostExecute(HashMap map) {
            super.onPostExecute(map);
            loginProcess(map);
            Login_Form.map=map;
        }
    }
}
