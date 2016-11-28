package faisal.home.com.college;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by Home on 11/24/2016.
 */
public class FragmentBookLectureSlot extends Fragment implements View.OnClickListener {
    View view;
    Spinner spinner;
    EditText editText;
    Button button;
    String time, subject;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.teachpanelview, null);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        spinner = (Spinner) view.findViewById(R.id.LectureTimeSpinner);
        editText = (EditText) view.findViewById(R.id.lectureSubjectEditText);
        button = (Button) view.findViewById(R.id.TeacherBookLectureButton);
        button.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.TeacherBookLectureButton:
                time = spinner.getSelectedItem().toString();
                subject = editText.getText().toString();
                bookLecture(time, subject);

                break;
        }
    }

    public void bookLecture(String time, String subject) {
        //Calendar today = Calendar.getInstance();
        Date today=new Date();
        String currentDate, currentMonth;

        SimpleDateFormat formator = new SimpleDateFormat("dd/MM/yy HH:mm:ss");
        String dateAndTime = formator.format(today);
        currentDate = dateAndTime.substring(0, 2);
        currentMonth = dateAndTime.substring(3, 5);

        MyCollegeDatabase db = new MyCollegeDatabase(getContext());
        db.bookLecture(currentDate, subject, time, currentMonth);

    }
}
