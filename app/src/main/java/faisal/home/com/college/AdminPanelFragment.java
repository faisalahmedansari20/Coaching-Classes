package faisal.home.com.college;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Home on 11/24/2016.
 */
public class AdminPanelFragment extends Fragment {
    ListView listView;
    AdminPanelAdapter adapter;
    View view;
    static TextView textView;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.adminpanelview, container, false);

        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        listView = (ListView) view.findViewById(R.id.AdminListView);
        textView = (TextView) view.findViewById(R.id.AdminPanelListText);
        adapter = new AdminPanelAdapter(getContext());
        listView.setAdapter(adapter);

        if (listView.getChildCount() == 0) {
            textView.setText("Department list is empty");
        }
    }


    public static void NotifyOnListViewSizeZero(ArrayList list) {
        if (list.size() == 0) {
            textView.setText("Department list is empty");
        } else {
            textView.setText("List of Head of Department");
        }
    }


}
