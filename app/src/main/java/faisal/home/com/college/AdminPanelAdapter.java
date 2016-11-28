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
public class AdminPanelAdapter extends BaseAdapter {

    ArrayList list;
    Context context;

    AdminPanelAdapter(Context context) {
        this.context = context;
            list = new MyCollegeDatabase(context).getHodFromDatabase();
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
            view = inflater.inflate(R.layout.singlelistview, null);
        }
        final TextView nameText = (TextView) view.findViewById(R.id.ListNameTextView);
        final Button approveButton = (Button) view.findViewById(R.id.ApproveButton);
        final Button rejectButton = (Button) view.findViewById(R.id.RejectButton);

        final HeadOfDepartment hod = (HeadOfDepartment) list.get(i);

        nameText.setText(hod.getEmail());

        if (hod.isApproved() == 1) {
            approveButton.setText("Accepted");
        } else {
            approveButton.setText("Approve");
        }

        approveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                approveButton.setText("Accepted");
                MyCollegeDatabase db = new MyCollegeDatabase(context);
                db.approveHod(hod.getEmail());
            }
        });


        rejectButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MyCollegeDatabase db = new MyCollegeDatabase(context);
                db.removeHod(nameText.getText().toString());
                list.remove(position);
                notifyDataSetChanged();
            }
        });

        AdminPanelFragment.NotifyOnListViewSizeZero(list);
        return view;
    }

}
