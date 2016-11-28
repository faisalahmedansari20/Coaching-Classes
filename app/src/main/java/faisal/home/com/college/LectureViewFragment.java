package faisal.home.com.college;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

/**
 * Created by Home on 11/24/2016.
 */
public class LectureViewFragment extends Fragment {
    ListView listView;
    View view;
    lectureAdapter adapter;
    String userName;
    public void setUserName(String userName)
    {
        this.userName=userName;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        view=inflater.inflate(R.layout.lectureslistview,null);

        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        listView= (ListView) view.findViewById(R.id.lectureListView);
        adapter=new lectureAdapter(getContext(),userName);
        listView.setAdapter(adapter);

    }
}
