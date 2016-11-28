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
public class FragmentLibraryView extends Fragment {

    View view;
    ListView listView;
    LibraryAdabter adabter;
    String studentName;
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
        adabter=new LibraryAdabter(getContext(),studentName);
        listView.setAdapter(adabter);

    }

    public void setStudentName(String studentName)
    {
        this.studentName=studentName;
    }
}
