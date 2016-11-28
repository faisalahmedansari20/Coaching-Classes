package faisal.home.com.college;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.HashMap;

public class Registration_Form extends Activity implements AdapterView.OnItemSelectedListener {

    //  TextView name,email,password,confirm,age,gender,subject,year,mobile;
    LinearLayout nameLinear, emailLinear, passwordLinear, confirmLinear, ageLinear, genderLinear, subjectLinear, yearLinear, mobileLinear;
    EditText nameEdit, emailEdit, passwordEdit, confirmEdit, ageEdit, genderEdit, subjectEdit, yearEdit, mobileEdit;
    Spinner spinner;
    String panelName;
    Button registrationButton;
    static  HashMap map;
    boolean isOldUser;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration__form);

        spinner = (Spinner) findViewById(R.id.Spinner);
//        String panelNames[] = getResources().getStringArray(R.array.panelName);
//        spinner.setAdapter(new ArrayAdapter(this, R.layout.support_simple_spinner_dropdown_item, panelNames));

        spinner.setOnItemSelectedListener(this);

        prepareAllView();

    }


    public void displayRegistrationFormDependingOnPanel(String panelName) {

        nameLinear.setVisibility(View.VISIBLE);
        emailLinear.setVisibility(View.VISIBLE);
        passwordLinear.setVisibility(View.VISIBLE);
        confirmLinear.setVisibility(View.VISIBLE);
        mobileLinear.setVisibility(View.VISIBLE);

        registrationButton.setVisibility(View.VISIBLE);


        if (panelName.equals("Teacher")) {
            ageLinear.setVisibility(View.VISIBLE);
            subjectLinear.setVisibility(View.VISIBLE);
            genderLinear.setVisibility(View.VISIBLE);

            yearLinear.setVisibility(View.GONE);
        } else if (panelName.equals("Student")) {

            ageLinear.setVisibility(View.VISIBLE);
            yearLinear.setVisibility(View.VISIBLE);
            genderLinear.setVisibility(View.VISIBLE);

            subjectLinear.setVisibility(View.GONE);
        } else {
            ageLinear.setVisibility(View.GONE);
            subjectLinear.setVisibility(View.GONE);
            genderLinear.setVisibility(View.GONE);

            yearLinear.setVisibility(View.GONE);
        }


    }

    public void prepareAllView() {
        nameLinear = (LinearLayout) findViewById(R.id.NameLinear);
        emailLinear = (LinearLayout) findViewById(R.id.EmailLinear);
        passwordLinear = (LinearLayout) findViewById(R.id.PasswordLinear);
        confirmLinear = (LinearLayout) findViewById(R.id.confirmLinear);
        ageLinear = (LinearLayout) findViewById(R.id.AgeLinear);
        yearLinear = (LinearLayout) findViewById(R.id.YearLinear);
        subjectLinear = (LinearLayout) findViewById(R.id.SubjectLinear);
        mobileLinear = (LinearLayout) findViewById(R.id.MobilenoLinear);
        genderLinear = (LinearLayout) findViewById(R.id.GenderLinear);

        nameEdit = (EditText) findViewById(R.id.NameEditText);
        emailEdit = (EditText) findViewById(R.id.EmailEditText);
        passwordEdit = (EditText) findViewById(R.id.passwordEditText);
        confirmEdit = (EditText) findViewById(R.id.ConfirmEditText);
        ageEdit = (EditText) findViewById(R.id.AgeEditText);
        yearEdit = (EditText) findViewById(R.id.YearEditText);
        subjectEdit = (EditText) findViewById(R.id.SubjectEditText);
        mobileEdit = (EditText) findViewById(R.id.MobileEditText);
        genderEdit = (EditText) findViewById(R.id.GenderEditText);

        registrationButton = (Button) findViewById(R.id.RegisterButton);
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

        panelName = spinner.getItemAtPosition(i).toString();
        displayRegistrationFormDependingOnPanel(panelName);
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        outState.putString("name", nameEdit.getText().toString());
        outState.putString("email", emailEdit.getText().toString());
        outState.putString("password", passwordEdit.getText().toString());
        outState.putString("confirm", confirmEdit.getText().toString());
        outState.putString("age", ageEdit.getText().toString());
        outState.putString("year", yearEdit.getText().toString());
        outState.putString("subject", subjectEdit.getText().toString());
        outState.putString("mobile", mobileEdit.getText().toString());
        outState.putString("gender", genderEdit.getText().toString());
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);

        nameEdit.setText(savedInstanceState.get("name").toString());
        emailEdit.setText(savedInstanceState.get("email").toString());
        passwordEdit.setText(savedInstanceState.get("password").toString());
        confirmEdit.setText(savedInstanceState.get("confirm").toString());
        ageEdit.setText(savedInstanceState.get("age").toString());
        yearEdit.setText(savedInstanceState.get("year").toString());
        subjectEdit.setText(savedInstanceState.get("subject").toString());
        mobileEdit.setText(savedInstanceState.get("mobile").toString());
        genderEdit.setText(savedInstanceState.get("gender").toString());
    }

    public void Send(View view) {
        panelName = spinner.getSelectedItem().toString();
        int age;
        long mobile;

        if (ageEdit.getText().toString().length() == 0) {
            age = 0;
        } else {
            age = Integer.parseInt(ageEdit.getText().toString());
        }
        if (mobileEdit.getText().toString().length() == 0) {
            mobile = Long.parseLong("0");
        } else {
            mobile = Long.parseLong(mobileEdit.getText().toString());
        }

        RegistrationForm form = new RegistrationForm(nameEdit.getText().toString(), emailEdit.getText().toString(), passwordEdit.getText().toString()
                , confirmEdit.getText().toString(), genderEdit.getText().toString(), subjectEdit.getText().toString(), yearEdit.getText().toString(),
                mobile, age);


        FormValidator validator = new FormValidator(this, panelName, form);

        if (validator.validate()) {
            //  Toast.makeText(this, "validated", Toast.LENGTH_SHORT).show();

            String[] params={emailEdit.getText().toString(),passwordEdit.getText().toString()};

            EmailCheck check=new EmailCheck(this,map,form);
            check.execute(params);



        } else {
            Toast.makeText(this, "Please fill All Details properly", Toast.LENGTH_SHORT).show();
        }
    }

    public void prepareIntent() {
        panelName = spinner.getSelectedItem().toString();
        Intent intent = new Intent(this, Panel.class);
        intent.putExtra("panelName", panelName);

        switch (panelName) {
            case "CollegeAdmin":
                Admin admin = new Admin();
                admin.setName(nameEdit.getText().toString());
                admin.setEmail(emailEdit.getText().toString());
                admin.setPassword(passwordEdit.getText().toString());
                admin.setMobileNo(Long.parseLong(mobileEdit.getText().toString()));
                intent.putExtra("object", admin);
                break;
            case "HOD":
                HeadOfDepartment hod = new HeadOfDepartment();
                hod.setName(nameEdit.getText().toString());
                hod.setEmail(emailEdit.getText().toString());
                hod.setMobileNo(Long.parseLong(mobileEdit.getText().toString()));
                hod.setPassword(passwordEdit.getText().toString());

                intent.putExtra("object", hod);
                break;
            case "Teacher":
                Teacher teacher = new Teacher();
                teacher.setName(nameEdit.getText().toString());
                teacher.setEmail(emailEdit.getText().toString());
                teacher.setPassword(passwordEdit.getText().toString());
                teacher.setGender(genderEdit.getText().toString());
                teacher.setSubject(subjectEdit.getText().toString());
                teacher.setAge(Integer.parseInt(ageEdit.getText().toString()));

                intent.putExtra("object", teacher);
                break;
            case "Student":
                Student student = new Student();
                student.setYear(yearEdit.getText().toString());
                student.setAge(Integer.parseInt(ageEdit.getText().toString()));
                student.setName(nameEdit.getText().toString());
                student.setEmail(emailEdit.getText().toString());
                student.setPassword(passwordEdit.getText().toString());
                student.setMobileNo(Long.parseLong(mobileEdit.getText().toString()));
                student.setGender(genderEdit.getText().toString());

                intent.putExtra("object",student);
                break;
        }

        startActivity(intent);

    }

    public class EmailCheck extends AsyncTask<String, Void, HashMap> {
        HashMap map;
        Context context;
        RegistrationForm form;


        EmailCheck(Context context, HashMap map,RegistrationForm form) {
            this.context = context;
            this.map = map;
            this.form=form;
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
            Registration_Form.map=map;
           isOldUser=(Boolean)map.get("isUser");
            if(!isOldUser) {
                MyCollegeDatabase db = new MyCollegeDatabase(context);
                db.insertRegistrationForm(form, panelName);
                prepareIntent();
            }
            else {
                Toast.makeText(context,"You already have account",Toast.LENGTH_SHORT).show();
            }
        }
    }
}
