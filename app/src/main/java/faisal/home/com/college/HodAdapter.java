package faisal.home.com.college;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Home on 11/24/2016.
 */
public class HodAdapter extends BaseAdapter {

    ArrayList list;
    Context context;
    String panelName;

    HodAdapter(Context context, String panelName) {
        this.context = context;
        list = new MyCollegeDatabase(context).getTeacher();
        this.panelName = panelName;
    }

    public void setStudent(String panelName) {
        this.panelName = panelName;
        list = new MyCollegeDatabase(context).getStudents();
    }

    public void setTeacher(String panelName) {
        this.panelName = panelName;
        list = new MyCollegeDatabase(context).getTeacher();
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int i) {
        return list.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        final int position = i;
        if (view == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view=inflater.inflate(R.layout.singlelistview, null);
        }
        final TextView nameText = (TextView) view.findViewById(R.id.ListNameTextView);
        final Button approveButton = (Button) view.findViewById(R.id.ApproveButton);
        final Button rejectButton = (Button) view.findViewById(R.id.RejectButton);

        if (panelName.equals("Teacher")) {
            final Teacher teacher = (Teacher) list.get(i);

            nameText.setText(teacher.getEmail());

            if (teacher.isApproved() == 1) {
                approveButton.setText("Accepted");
            } else {
                approveButton.setText("Approve");
            }

            approveButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    approveButton.setText("Accepted");
                    MyCollegeDatabase db = new MyCollegeDatabase(context);
                    db.approveTeacher(teacher.getEmail());
                }
            });


            rejectButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    MyCollegeDatabase db = new MyCollegeDatabase(context);
                    db.removeTeacher(nameText.getText().toString());
                    list.remove(position);
                    notifyDataSetChanged();
                }
            });
        } else if (panelName.equals("Student")) {
            final Student student = (Student) list.get(i);

            nameText.setText(student.getEmail());

            if (student.isApproved() == 1) {
                approveButton.setText("Accepted");
            } else {
                approveButton.setText("Approve");
            }

            approveButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    approveButton.setText("Accepted");
                    MyCollegeDatabase db = new MyCollegeDatabase(context);
                    db.approveStudent(student.getEmail());
                }
            });


            rejectButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    MyCollegeDatabase db = new MyCollegeDatabase(context);
                    db.removeStudent(nameText.getText().toString());
                    list.remove(position);
                    notifyDataSetChanged();
                }
            });
        }

        return view;


    }
}
