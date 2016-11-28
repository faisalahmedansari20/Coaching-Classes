package faisal.home.com.college;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Home on 11/24/2016.
 */
public class HodFragment extends Fragment implements AdapterView.OnItemSelectedListener{

    Spinner spinner;
    ListView listView;
    HodAdapter adapter;
    View view;
    TextView textView;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
       view= inflater.inflate(R.layout.hodpanelview,container,false);

        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        spinner=(Spinner) view.findViewById(R.id.HODSpinner);
        spinner.setOnItemSelectedListener(this);
        listView= (ListView) view.findViewById(R.id.hodListView);
        adapter=new HodAdapter(getContext(),"Teacher");
        listView.setAdapter(adapter);
    }


    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        switch ((String)spinner.getSelectedItem())
        {
            case "Teacher":
                adapter.setTeacher("Teacher");
                adapter.notifyDataSetChanged();
                break;
            case "Student":
                adapter.setStudent("Student");
                adapter.notifyDataSetChanged();
                break;
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }



}
