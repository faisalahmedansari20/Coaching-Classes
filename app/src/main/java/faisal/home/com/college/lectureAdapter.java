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
public class lectureAdapter extends BaseAdapter {

    ArrayList list;
    Context context;
    TextView subject,time;
    Button button;
    String userName;

    lectureAdapter(Context context,String userName)
    {
        this.context=context;
        list=new MyCollegeDatabase(context).getLectures();
        this.userName=userName;
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
final int position=i;
        if(view==null)
        {
            LayoutInflater inflater= (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
          view= inflater.inflate(R.layout.singleviewlecturetime,null);
        }
        subject= (TextView) view.findViewById(R.id.SubjectNameListview);
        time= (TextView) view.findViewById(R.id.TimeNameListview);
        button= (Button) view.findViewById(R.id.CancelLectureButton);

        if(userName.equals("Student"))
        {
            button.setVisibility(View.GONE);
        }

        Lecture lecture= (Lecture) list.get(i);

        subject.setText(lecture.getSubject());
        time.setText(lecture.getTime());

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                list.remove(position);
                MyCollegeDatabase db=new MyCollegeDatabase(context);
                db.cancelLecture(time.getText().toString());
                notifyDataSetChanged();
            }
        });

        return view;
    }
}
