package faisal.home.com.college;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class Panel extends AppCompatActivity {

    TextView panelName, approvalMsg;
    EditText nameEditText, mobileNumberEditText, passwordEditText, emailEditText, subjectEditText, yearEditText, issuedBookEditText;
    LinearLayout panelYearLinear, panelSubjectLinear, panelIssuedBookLinear, teacherLinear, studentLinear;

    Student student;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.panelview);

        prepareView();
    }

    public void onSave(View view) {

        switch (view.getId()) {
            case R.id.ButtonOnSave:

                switch (panelName.getText().toString()) {
                    case "CollegeAdmin":
                        adminSaveChanges();
                        break;
                    case "HOD":
                        hodSaveChanges();
                        break;

                    case "Teacher":
                        teacherSaveChanges();
                        break;
                    case "Student":
                        studentSaveChanges();
                        break;
                }
                break;
        }

    }

    public void prepareView() {
        panelYearLinear = (LinearLayout) findViewById(R.id.panelYearLinear);
        panelSubjectLinear = (LinearLayout) findViewById(R.id.panelYearLinear);
        panelIssuedBookLinear = (LinearLayout) findViewById(R.id.panelYearLinear);
        teacherLinear = (LinearLayout) findViewById(R.id.PanelTeacherLinear);
        studentLinear = (LinearLayout) findViewById(R.id.PanelStudentLinear);

        panelName = (TextView) findViewById(R.id.PanelTitle);
        approvalMsg = (TextView) findViewById(R.id.TextViewNotApproved);

        nameEditText = (EditText) findViewById(R.id.NamePanelEditText);
        mobileNumberEditText = (EditText) findViewById(R.id.MobilePanelEditText);
        passwordEditText = (EditText) findViewById(R.id.passwordPanelEditText);
        emailEditText = (EditText) findViewById(R.id.EmailPanelEditText);
        yearEditText = (EditText) findViewById(R.id.yearPanelEditText);
        subjectEditText = (EditText) findViewById(R.id.subjectpanelEditTextView);
        issuedBookEditText = (EditText) findViewById(R.id.issueBookPanelEditText);

        prepareBasedOnUser();
    }

    public void prepareBasedOnUser() {
        String panelTitle = getIntent().getStringExtra("panelName");
        panelName.setText(panelTitle);

        switch (panelTitle) {

            case "CollegeAdmin":
                hideUnWantedText();
                prepareForAdmin();
                break;

            case "HOD":
                hideUnWantedText();
                prepareForHOD();

                break;

            case "Teacher":
                Teacher teacher = (Teacher) getIntent().getSerializableExtra("object");
                if (teacher.isApproved() == 1) {
                    teacherLinear.setVisibility(View.VISIBLE);
                }
                subjectEditText.setVisibility(View.VISIBLE);


                panelYearLinear.setVisibility(View.GONE);
                studentLinear.setVisibility(View.GONE);
                panelIssuedBookLinear.setVisibility(View.GONE);
                yearEditText.setVisibility(View.GONE);
                issuedBookEditText.setVisibility(View.GONE);

                prepareForTeachers();

                break;

            case "Student":
                panelYearLinear.setVisibility(View.VISIBLE);
                studentLinear.setVisibility(View.VISIBLE);
                panelIssuedBookLinear.setVisibility(View.VISIBLE);
                yearEditText.setVisibility(View.VISIBLE);
                issuedBookEditText.setVisibility(View.VISIBLE);

                teacherLinear.setVisibility(View.GONE);
                subjectEditText.setVisibility(View.GONE);

                prepareForStudents();
                break;

        }
    }

    public void hideUnWantedText() {
        panelYearLinear.setVisibility(View.GONE);
        studentLinear.setVisibility(View.GONE);
        panelIssuedBookLinear.setVisibility(View.GONE);
        yearEditText.setVisibility(View.GONE);
        issuedBookEditText.setVisibility(View.GONE);

        teacherLinear.setVisibility(View.GONE);
        studentLinear.setVisibility(View.GONE);
        subjectEditText.setVisibility(View.GONE);
    }

    public void prepareForAdmin() {

        Admin admin = (Admin) getIntent().getSerializableExtra("object");
        nameEditText.setText(admin.getName());
        emailEditText.setText(admin.getEmail());
        mobileNumberEditText.setText("" + admin.getMobileNo());
        passwordEditText.setText(admin.getPassword());

        AdminPanelFragment fragment = (AdminPanelFragment) getSupportFragmentManager().findFragmentByTag("AdminFrag");
        if (fragment == null) {
            fragment = new AdminPanelFragment();
            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
            transaction.add(R.id.linearFarg, fragment, "AdminFrag");
            transaction.commit();
        }
    }

    public void prepareForHOD() {
        HeadOfDepartment hod = (HeadOfDepartment) getIntent().getSerializableExtra("object");
        nameEditText.setText(hod.getName());
        emailEditText.setText(hod.getEmail());
        mobileNumberEditText.setText("" + hod.getMobileNo());
        passwordEditText.setText(hod.getPassword());

        if (hod.isApproved() == 0) {
            approvalMsg.setVisibility(View.VISIBLE);
        } else if (hod.isApproved() == 1) {
            approvalMsg.setVisibility(View.GONE);
            HodFragment fragment = (HodFragment) getSupportFragmentManager().findFragmentByTag("HodFrag");
            if (fragment == null) {
                fragment = new HodFragment();
                FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
                transaction.add(R.id.linearFarg, fragment, "HodFrag");
                transaction.commit();
            }
        }
    }

    public void prepareForTeachers() {
        Teacher teacher = (Teacher) getIntent().getSerializableExtra("object");
        nameEditText.setText(teacher.getName());
        emailEditText.setText(teacher.getEmail());
        passwordEditText.setText(teacher.getPassword());
        mobileNumberEditText.setText("" + teacher.getMobileNo());
        subjectEditText.setText(teacher.getSubject());

        if (teacher.isApproved() == 0) {
            approvalMsg.setVisibility(View.VISIBLE);
        } else {
            approvalMsg.setVisibility(View.GONE);

            FragmentBookLectureSlot slot = (FragmentBookLectureSlot) getSupportFragmentManager().findFragmentByTag("lecture");
            if (slot == null) {
                slot = new FragmentBookLectureSlot();
                FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
                transaction.add(R.id.linearFarg, slot, "lecture");
                transaction.commit();
            }
        }
    }

    public void prepareForStudents() {
         student = (Student) getIntent().getSerializableExtra("object");
        nameEditText.setText(student.getName());
        emailEditText.setText(student.getEmail());
        passwordEditText.setText(student.getPassword());
        mobileNumberEditText.setText("" + student.getMobileNo());
        yearEditText.setText(student.getYear());
//        issuedBookEditText.setText(student.getIssuedBook());

        if (student.isApproved() == 0) {
            approvalMsg.setVisibility(View.VISIBLE);
        }
        else
        {
            approvalMsg.setVisibility(View.GONE);

            LectureViewFragment slot = (LectureViewFragment) getSupportFragmentManager().findFragmentByTag("lecture");
            if (slot == null) {
                slot = new LectureViewFragment();
                slot.setUserName("Student");
                FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
                transaction.add(R.id.linearFarg, slot, "lecture");
                transaction.commit();
            }
        }
    }

    public void adminSaveChanges() {
        Admin admin = (Admin) getIntent().getSerializableExtra("object");
        admin.setName(nameEditText.getText().toString());
        admin.setMobileNo(Long.parseLong(mobileNumberEditText.getText().toString()));
        admin.setPassword(passwordEditText.getText().toString());
        admin.setEmail(emailEditText.getText().toString());

        MyCollegeDatabase db = new MyCollegeDatabase(this);
        RegistrationForm form = new RegistrationForm(admin.getName(), admin.getEmail(), admin.getPassword(), admin.getPassword(), null, null, null, admin.getMobileNo(), 0);
        db.insertAdminForm(form);

        Toast.makeText(this, "Saved...", Toast.LENGTH_SHORT).show();
    }

    public void hodSaveChanges() {
        HeadOfDepartment hod = (HeadOfDepartment) getIntent().getSerializableExtra("object");
        hod.setName(nameEditText.getText().toString());
        hod.setMobileNo(Long.parseLong(mobileNumberEditText.getText().toString()));
        hod.setPassword(passwordEditText.getText().toString());
        hod.setEmail(emailEditText.getText().toString());

        MyCollegeDatabase db = new MyCollegeDatabase(this);
        RegistrationForm form = new RegistrationForm(hod.getName(), hod.getEmail(), hod.getPassword(), hod.getPassword(), null, null, null, hod.getMobileNo(), 0);
        db.insertHODForm(form);

        Toast.makeText(this, "Saved...", Toast.LENGTH_SHORT).show();
    }

    public void teacherSaveChanges() {
        Teacher teacher = (Teacher) getIntent().getSerializableExtra("object");
        teacher.setName(nameEditText.getText().toString());
        teacher.setMobileNo(Long.parseLong(mobileNumberEditText.getText().toString()));
        teacher.setPassword(passwordEditText.getText().toString());
        teacher.setEmail(emailEditText.getText().toString());
        teacher.setSubject(subjectEditText.getText().toString());

        MyCollegeDatabase db = new MyCollegeDatabase(this);
        RegistrationForm form = new RegistrationForm(teacher.getName(), teacher.getEmail(), teacher.getPassword(), teacher.getPassword(), teacher.getGender(), teacher.getSubject(), null, teacher.getMobileNo(), teacher.getAge());
        db.insertTeacherForm(form);

        Toast.makeText(this, "Saved...", Toast.LENGTH_SHORT).show();
    }

    public void studentSaveChanges() {
        Student student = (Student) getIntent().getSerializableExtra("object");
        student.setName(nameEditText.getText().toString());
        student.setMobileNo(Long.parseLong(mobileNumberEditText.getText().toString()));
        student.setPassword(passwordEditText.getText().toString());
        student.setEmail(emailEditText.getText().toString());


        MyCollegeDatabase db = new MyCollegeDatabase(this);
        RegistrationForm form = new RegistrationForm(student.getName(), student.getEmail(), student.getPassword(), student.getPassword(), student.getGender(), null, student.getYear(), student.getMobileNo(), student.getAge());
        db.insertTeacherForm(form);

        Toast.makeText(this, "Saved...", Toast.LENGTH_SHORT).show();
    }

    FragmentBookLectureSlot slot = null;
    LectureViewFragment list = null;
    FragmentTransaction transaction;
    FragmentLibraryView libraryFragment=null;

    public void onButtonPressed(View view) {


        switch (view.getId()) {
            case R.id.BookLectureButton:

                slot = (FragmentBookLectureSlot) getSupportFragmentManager().findFragmentByTag("lecture");
                transaction=getSupportFragmentManager().beginTransaction();

                if (slot == null) {
                    slot = new FragmentBookLectureSlot();
//                    transaction = getSupportFragmentManager().beginTransaction();
                    transaction.replace(R.id.linearFarg, slot, "lecture");
                    transaction.commit();
                }
                else
                {
                    transaction.replace(R.id.linearFarg, slot,"lecture");
                    transaction.commit();
                }
                break;
            case R.id.ViewLectureButton:

                list = (LectureViewFragment) getSupportFragmentManager().findFragmentByTag("lecturelist");
                transaction=getSupportFragmentManager().beginTransaction();
                if (list == null) {

                    list = new LectureViewFragment();
                    transaction.replace(R.id.linearFarg, list, "lecturelist");
                    transaction.commit();
                    list.setUserName("Teacher");
                }
                else
                {
                    transaction.replace(R.id.linearFarg,list,"lecturelist");
                    transaction.commit();
                }
                break;

            case R.id.ViewLectureStutentButton:
                if(student.isApproved()==1) {
                    list = (LectureViewFragment) getSupportFragmentManager().findFragmentByTag("lecturelist");
                    transaction = getSupportFragmentManager().beginTransaction();
                    if (list == null) {

                        list = new LectureViewFragment();
                        transaction.replace(R.id.linearFarg, list, "lecturelist");
                        transaction.commit();
                        list.setUserName("Student");
                    } else {
                        transaction.replace(R.id.linearFarg, list, "lecturelist");
                        transaction.commit();
                    }
                    break;
                }

            case R.id.LibraryButton:
                if(student.isApproved()==1) {
                    libraryFragment = (FragmentLibraryView) getSupportFragmentManager().findFragmentByTag("library");
                    transaction = getSupportFragmentManager().beginTransaction();

                    if (libraryFragment == null) {
                        libraryFragment = new FragmentLibraryView();
                        transaction.replace(R.id.linearFarg, libraryFragment, "library");
                        transaction.commit();
                        libraryFragment.setStudentName(student.getEmail());

                    } else {
                        transaction.replace(R.id.linearFarg, libraryFragment, "library");
                        transaction.commit();
                    }
                    break;
                }
        }
    }
}
