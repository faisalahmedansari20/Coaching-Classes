package faisal.home.com.college;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.zip.Inflater;

/**
 * Created by Home on 11/24/2016.
 */
public class LibraryAdabter extends BaseAdapter {

String[] books;
    String[] streams;
    ArrayList listFromLibrary,studentbookList;
    Context context;
    String email;
MyCollegeDatabase db;
    LibraryAdabter(Context context,String email)
    {
        this.context=context;
        this.email=email;
        books=context.getResources().getStringArray(R.array.Library);
        streams=context.getResources().getStringArray(R.array.LibraryBookStream);
         db=new MyCollegeDatabase(context);
        listFromLibrary=db.getBooksFromLibrary();
        studentbookList=db.getStudentBooks(email);

    }


    @Override
    public int getCount() {
        return listFromLibrary.size();
    }

    @Override
    public Object getItem(int i) {
        return listFromLibrary.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {

        if(view==null)
        {
            LayoutInflater inflater= (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
           view= inflater.inflate(R.layout.singleviewlecturetime,null);
        }

        final TextView textName= (TextView) view.findViewById(R.id.SubjectNameListview);
        final TextView textStream= (TextView) view.findViewById(R.id.TimeNameListview);
        final Button button= (Button) view.findViewById(R.id.CancelLectureButton);

        textName.setText(books[i]);
        textStream.setText(streams[i]);

       Book book=(Book) listFromLibrary.get(i);

        if(studentbookList.contains(books[i]))
        {
            button.setText("Return");
        }
        else if(book.available==0)
        {
            button.setText("Unavailabe");
        }
        else
        {
            button.setText("Issue");
        }

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(button.getText().toString().equals("Return"))
                {
                    db.returnBook(email,textName.getText().toString());
                    studentbookList.remove(textName.getText().toString());
                    notifyDataSetChanged();
                }
                else if(button.getText().toString().equals("Issue"))
                {
                    db.issueBookFromLibrary(email,textName.getText().toString());
                    studentbookList.add(textName.getText().toString());
                    notifyDataSetChanged();
                }
            }
        });

        return view;
    }
}
