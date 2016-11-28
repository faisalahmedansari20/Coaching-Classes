package faisal.home.com.college;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

/**
 * Created by Home on 11/23/2016.
 */
class MyCollegeDatabase {

    Context context;
    SQLiteDatabase db;
    CollegeDatabase collegeDatabase;

    MyCollegeDatabase(Context context) {
        this.context = context;
        collegeDatabase = new CollegeDatabase(context);
        db = new CollegeDatabase(context).getReadableDatabase();
    }

    public void insertRegistrationForm(RegistrationForm form, String panelName) {
        switch (panelName) {
            case "CollegeAdmin":
                insertAdminForm(form);
                break;

            case "HOD":
                insertHODForm(form);
                break;

            case "Teacher":
                insertTeacherForm(form);
                break;

            case "Student":
                insertStudentRegistrationForm(form);
                break;
        }

    }

    public void insertAdminForm(RegistrationForm form) {
        ContentValues values = new ContentValues();
        values.put(CollegeDatabase.ADMIN_NAME, form.getName());
        values.put(CollegeDatabase.ADMIN_EMAIL_ID, form.getEmail());
        values.put(CollegeDatabase.ADMIN_MOBILE_NUMBER, form.getMobile());
        values.put(CollegeDatabase.ADMIN_PASSWORD, form.getPassword());
        long effectedRow = db.insert(CollegeDatabase.ADMIN_TABLENAME, null, values);

        registrationMsg(effectedRow, "Registration Successful", "Registration Failed");
    }

    public void insertHODForm(RegistrationForm form) {

        ContentValues values = new ContentValues();
        values.put(CollegeDatabase.HOD_NAME, form.getName());
        values.put(CollegeDatabase.HOD_EMAIL_ID, form.getEmail());
        values.put(CollegeDatabase.HOD_MOBILE_NUMBER, form.getMobile());
        values.put(CollegeDatabase.HOD_PASSWORD, form.getPassword());
        long effectedRow = db.insert(CollegeDatabase.HOD_TABLENAME, null, values);

        registrationMsg(effectedRow, "Registration Successful", "Registration Failed");
    }

    public void insertTeacherForm(RegistrationForm form) {
        ContentValues values = new ContentValues();
        values.put(CollegeDatabase.TEACHER_NAME, form.getName());
        values.put(CollegeDatabase.TEACHER_EMAIL_ID, form.getEmail());
        values.put(CollegeDatabase.TEACHER_MOBILE_NUMBER, form.getMobile());
        values.put(CollegeDatabase.TEACHER_PASSWORD, form.getPassword());
        values.put(CollegeDatabase.TEACHER_AGE, form.getAge());
        values.put(CollegeDatabase.TEACHER_GENDER, form.getGender());
        values.put(CollegeDatabase.TEACHER_SUBJECT, form.getSubject());

        long effectedRow = db.insert(CollegeDatabase.TEACHER_TABLENAME, null, values);

        registrationMsg(effectedRow, "Registration Successful", "Registration Failed");
    }

    public void insertStudentRegistrationForm(RegistrationForm form) {
        ContentValues values = new ContentValues();
        values.put(CollegeDatabase.STUDENTS_NAME, form.getName());
        values.put(CollegeDatabase.STUDENTS_EMAIL_ID, form.getEmail());
        values.put(CollegeDatabase.STUDENTS_MOBILE_NUMBER, form.getMobile());
        values.put(CollegeDatabase.STUDENTS_PASSWORD, form.getPassword());
        values.put(CollegeDatabase.STUDENTS_AGE, form.getAge());
        values.put(CollegeDatabase.STUDENTS_GENDER, form.getGender());
        values.put(CollegeDatabase.STUDENTS_YEAR, form.getYear());

        long effectedRow = db.insert(CollegeDatabase.STUDENTS_TABLENAME, null, values);

        registrationMsg(effectedRow, "Registration Successful", "Registration Failed");
    }

    public HashMap checkEmailIdAndPassword(String email, String password) {
        Admin admin = null;
        HeadOfDepartment hod = null;
        Teacher teacher = null;
        Student student = null;
        Cursor cursor = null;
        String panelName = null;
        HashMap map = new HashMap();

//        String[] adminColumns = {CollegeDatabase.ADMIN_EMAIL_ID, CollegeDatabase.ADMIN_PASSWORD};
//        String[] hodColumns = {CollegeDatabase.HOD_EMAIL_ID, CollegeDatabase.HOD_PASSWORD};
//        String[] teacherColumns = {CollegeDatabase.TEACHER_EMAIL_ID, CollegeDatabase.TEACHER_PASSWORD};
//        String[] studentColumns = {CollegeDatabase.STUDENTS_EMAIL_ID, CollegeDatabase.STUDENTS_PASSWORD};

        String selectionArgs[] = {email};
        cursor = db.query(CollegeDatabase.ADMIN_TABLENAME, null, CollegeDatabase.ADMIN_EMAIL_ID + "=?", selectionArgs, null, null, null);


        while (cursor.moveToNext()) {
            admin = new Admin();
            panelName = "CollegeAdmin";
            admin.setEmail(cursor.getString(0));
            admin.setName(cursor.getString(1));
            admin.setMobileNo(Long.parseLong(cursor.getString(2)));
            admin.setPassword(cursor.getString(3));

        }
        cursor.close();

        cursor = db.query(CollegeDatabase.HOD_TABLENAME, null, CollegeDatabase.HOD_EMAIL_ID + "=?", selectionArgs, null, null, null);

//        if (cursor != null) {
//            hod = new HeadOfDepartment();
//            panelName="HOD";
//        }

        while (cursor.moveToNext()) {
            hod = new HeadOfDepartment();
            panelName = "HOD";
            hod.setEmail(cursor.getString(0));
            hod.setName(cursor.getString(1));
            hod.setMobileNo(Long.parseLong(cursor.getString(2)));
            hod.setPassword(cursor.getString(3));
            hod.setApproved(cursor.getInt(4));
        }
        cursor.close();

        cursor = db.query(CollegeDatabase.TEACHER_TABLENAME, null, CollegeDatabase.TEACHER_EMAIL_ID + "=?", selectionArgs, null, null, null);

//        if (cursor != null) {
//            teacher = new Teacher();
//            panelName="Teacher";
//        }

        while (cursor.moveToNext()) {
            teacher = new Teacher();
            panelName = "Teacher";
            teacher.setEmail(cursor.getString(0));
            teacher.setName(cursor.getString(1));
            teacher.setMobileNo(Long.parseLong(cursor.getString(2)));
            teacher.setPassword(cursor.getString(3));
            teacher.setGender(cursor.getString(4));
            teacher.setSubject(cursor.getString(5));
            teacher.setAge(Integer.parseInt(cursor.getString(6)));
            teacher.setApproved(cursor.getInt(7));
        }
        cursor.close();

        cursor = db.query(CollegeDatabase.STUDENTS_TABLENAME, null, CollegeDatabase.STUDENTS_EMAIL_ID + "=?", selectionArgs, null, null, null);

//        if (cursor != null) {
//            student = new Student();
//            panelName="Student";
//        }

        while (cursor.moveToNext()) {
            student = new Student();
            panelName = "Student";
            student.setEmail(cursor.getString(0));
            student.setName(cursor.getString(1));
            student.setMobileNo(Long.parseLong(cursor.getString(2)));
            student.setPassword(cursor.getString(3));
            student.setGender(cursor.getString(4));
            student.setYear(cursor.getString(5));
            student.setAge(Integer.parseInt(cursor.getString(6)));
            student.setIssuedBook(cursor.getString(7));
            student.setApproved(cursor.getInt(8));
        }
        cursor.close();

        if (admin != null && admin.getEmail().equals(email) && admin.getPassword().equals(password)) {
            map.clear();
            map.put("panel", admin);
            map.put("panelName", panelName);
            map.put("isUser", true);
            // Log.d("msg","admin");
            return map;
        } else if (hod != null && hod.getEmail().equals(email) && hod.getPassword().equals(password)) {
            map.clear();
            map.put("panel", hod);
            map.put("panelName", panelName);
            map.put("isUser", true);
            // Log.d("msg","hod");

            return map;
        } else if (teacher != null && teacher.getEmail().equals(email) && teacher.getPassword().equals(password)) {
            map.clear();
            map.put("panel", teacher);
            map.put("panelName", panelName);
            map.put("isUser", true);
            // Log.d("msg","teacher");

            return map;
        } else if (student != null && student.getEmail().equals(email) && student.getPassword().equals(password)) {
            map.clear();
            map.put("panel", student);
            map.put("panelName", panelName);
            map.put("isUser", true);
            //  Log.d("msg","student");

            return map;
        } else {

            map.put("isUser", false);
            //   Log.d("msg","noo");

            return map;
        }
    }

    public ArrayList getHodFromDatabase() {
        ArrayList list = new ArrayList();
        Cursor cursor = db.query(CollegeDatabase.HOD_TABLENAME, null, null, null, null, null, null);

        while (cursor.moveToNext()) {
            list.add(new HeadOfDepartment(cursor.getString(0), cursor.getString(1), cursor.getLong(2), cursor.getString(3), cursor.getInt(4)));
        }
        return list;
    }

    public void approveHod(String email) {
        ContentValues values = new ContentValues();

        values.put(CollegeDatabase.HOD_APPROVAL, 1);
        int row = db.update(CollegeDatabase.HOD_TABLENAME, values, CollegeDatabase.HOD_EMAIL_ID + "=?", new String[]{email});

        registrationMsg(row, "Approved", "Not Approved");
    }

    public void removeHod(String email) {
        String arg[] = {email};
        int row = db.delete(CollegeDatabase.HOD_TABLENAME, CollegeDatabase.HOD_EMAIL_ID + "=?", arg);

        registrationMsg(row, "Removed", "Not Removed");
    }

    public ArrayList getTeacher() {
        Cursor cursor = db.query(CollegeDatabase.TEACHER_TABLENAME, null, null, null, null, null, null);
        ArrayList list = new ArrayList();
        while (cursor.moveToNext()) {
            list.add(new Teacher(cursor.getString(0), cursor.getString(1), cursor.getLong(2), cursor.getString(3), cursor.getString(4),
                    cursor.getString(5), cursor.getInt(6), cursor.getInt(7)));
        }
        return list;
    }

    public ArrayList getStudents() {
        Cursor cursor = db.query(CollegeDatabase.STUDENTS_TABLENAME, null, null, null, null, null, null);
        ArrayList list = new ArrayList();
        while (cursor.moveToNext()) {
            list.add(new Student(cursor.getString(0), cursor.getString(1), cursor.getLong(2), cursor.getString(3), cursor.getString(4),
                    cursor.getString(5), cursor.getInt(6), cursor.getString(7), cursor.getInt(8)));
        }
        return list;
    }

    public void registrationMsg(long effectedRow, String msg, String fail) {
        if (effectedRow > 0) {
            Toast.makeText(context, msg, Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(context, fail, Toast.LENGTH_SHORT).show();

        }
    }

    public void bookLecture(String date, String subject, String time, String month) {
        clearPreviousDateLectures(date, month);

        ContentValues values = new ContentValues();
        values.put(CollegeDatabase.LECTURE_DATE, date);
        values.put(CollegeDatabase.LECTURE_SUBJECT, subject);
        values.put(CollegeDatabase.LECTURE_TIME, time);
        values.put(CollegeDatabase.LECTURE_MONTH, month);

        long row = db.insert(CollegeDatabase.LECTURE_TABLENAME, null, values);
        registrationMsg(row, "Lecture placed", " this slot is already booked");

    }

    public void clearPreviousDateLectures(String date, String month) {
        db.delete(CollegeDatabase.LECTURE_TABLENAME, CollegeDatabase.LECTURE_MONTH + "<?", new String[]{month});
        db.delete(CollegeDatabase.LECTURE_TABLENAME, CollegeDatabase.LECTURE_DATE + "<?", new String[]{date});
    }

    public ArrayList getLectures() {
        ArrayList list = new ArrayList();
        Cursor cursor = db.query(CollegeDatabase.LECTURE_TABLENAME, null, null, null, null, null, null);
        while (cursor.moveToNext()) {
            list.add(new Lecture(cursor.getString(0), cursor.getString(1), cursor.getString(2), cursor.getString(3)));
        }

        return list;
    }

    public void cancelLecture(String time) {
        int row = db.delete(CollegeDatabase.LECTURE_TABLENAME, CollegeDatabase.LECTURE_TIME + "=?", new String[]{time});
        registrationMsg(row, "Lecture Cancelled", "no such lecture");
    }

    public void approveTeacher(String email) {
        ContentValues values = new ContentValues();

        values.put(CollegeDatabase.TEACHER_APPROVAL, 1);
        int row = db.update(CollegeDatabase.TEACHER_TABLENAME, values, CollegeDatabase.TEACHER_EMAIL_ID + "=?", new String[]{email});

        registrationMsg(row, "Approved", "Not Approved");
    }

    public void removeTeacher(String email) {
        String arg[] = {email};
        int row = db.delete(CollegeDatabase.TEACHER_TABLENAME, CollegeDatabase.TEACHER_EMAIL_ID + "=?", arg);

        registrationMsg(row, "Removed", "Not Removed");
    }

    public void approveStudent(String email) {
        ContentValues values = new ContentValues();

        values.put(CollegeDatabase.STUDENTS_APPROVAL, 1);
        int row = db.update(CollegeDatabase.STUDENTS_TABLENAME, values, CollegeDatabase.STUDENTS_EMAIL_ID + "=?", new String[]{email});

        registrationMsg(row, "Approved", "Not Approved");
    }

    public void removeStudent(String email) {
        String arg[] = {email};
        int row = db.delete(CollegeDatabase.STUDENTS_TABLENAME, CollegeDatabase.STUDENTS_EMAIL_ID + "=?", arg);

        registrationMsg(row, "Removed", "Not Removed");
    }


    public ArrayList getBooksFromLibrary() {
        ArrayList list = new ArrayList();

        Cursor cursor = db.query(CollegeDatabase.LIBRARIES_TABLENAME, null, null, null, null, null, null);
        while (cursor.moveToNext()) {
            list.add(new Book(cursor.getString(0), cursor.getString(1), cursor.getInt(2)));
        }
        return list;
    }

    public void returnBook(String email,String bookName) {
        ContentValues values = new ContentValues();
        values.put(CollegeDatabase.LIBRARIES_AVAILABLE, 1);

        int row = db.update(CollegeDatabase.LIBRARIES_TABLENAME, values, CollegeDatabase.LIBRARIES_BOOKNAME + "=?", new String[]{bookName});

      Cursor cursor=  db.query(CollegeDatabase.STUDENTS_TABLENAME,new String[]{CollegeDatabase.STUDENTS_BOOKS},CollegeDatabase.STUDENTS_EMAIL_ID+"=?",new String[]{email},null,null,null);
        String book=null;
        while(cursor.moveToNext())
        {
        book=    cursor.getString(0);
        }

        ArrayList list=new ArrayList();
        String[] books=book.split(",");
        list.addAll(Arrays.asList(books));

        list.remove("bookName");
        String[] remainingBook=new String[list.size()];
        if(list.size()==0)
        {
            remainingBook[0]="";
        }else {
            list.toArray(remainingBook);
        }
        String bookss="";

        for(int i=0;i<remainingBook.length;i++)
        {
            bookss+=remainingBook[i];
        }

        ContentValues values1=new ContentValues();
        values1.put(CollegeDatabase.STUDENTS_BOOKS,bookss);
       int returnRow= db.update(CollegeDatabase.STUDENTS_TABLENAME,values1,CollegeDatabase.STUDENTS_EMAIL_ID+"=?",new String[]{email});
        registrationMsg(returnRow, "Booked has been returned", "oppss something went wrong");
    }

    public void issueBookFromLibrary(String email,String bookName)
    {
        ContentValues values = new ContentValues();
        values.put(CollegeDatabase.LIBRARIES_AVAILABLE, 0);

         db.update(CollegeDatabase.LIBRARIES_TABLENAME, values, CollegeDatabase.LIBRARIES_BOOKNAME + "=?", new String[]{bookName});

        ContentValues studentValues=new ContentValues();
        studentValues.put(CollegeDatabase.STUDENTS_BOOKS,bookName+",");
       int rows= db.update(CollegeDatabase.STUDENTS_TABLENAME,studentValues,CollegeDatabase.STUDENTS_EMAIL_ID+"=?",new String[]{email});

        registrationMsg(rows, "Booked has been issued", "oppss something went wrong");    }

    public ArrayList getStudentBooks(String email)
    {
        ArrayList list=new ArrayList();
      Cursor cursor=  db.query(CollegeDatabase.STUDENTS_TABLENAME,new String[]{CollegeDatabase.STUDENTS_BOOKS},CollegeDatabase.STUDENTS_EMAIL_ID+"=?",new String[]{email},null,null,null);

        if(cursor.getColumnCount()>0) {
            String book = null;
            while (cursor.moveToNext()) {
                book = cursor.getString(0);
            }
if(book!=null) {
    String[] books = book.split(",");
    list.addAll(Arrays.asList(books));
}
        }

        return list;
    }

    public void createLibrary(Context context,SQLiteDatabase db) {
        String bookName[] = context.getResources().getStringArray(R.array.Library);
        String subjectName[] = context.getResources().getStringArray(R.array.LibraryBookStream);

        for (int i = 0; i < bookName.length; i++) {
            ContentValues values = new ContentValues();
            values.put(CollegeDatabase.LIBRARIES_BOOKNAME, bookName[i]);
            values.put(CollegeDatabase.LIBRARIES_STREAM, subjectName[i]);
            values.put(CollegeDatabase.LIBRARIES_AVAILABLE, 1);

            db.insert(CollegeDatabase.LIBRARIES_TABLENAME, null, values);
        }
    }

    class CollegeDatabase extends SQLiteOpenHelper {

        Context context;

        static final String DATABASE_NAME = "COLLEGE";
        static final int DATABASE_VERSION = 1;

        // ADMIN TABLE
        static final String ADMIN_TABLENAME = "ADMIN";
        static final String ADMIN_EMAIL_ID = "EMAIL_ID";
        static final String ADMIN_NAME = "NAME";
        static final String ADMIN_MOBILE_NUMBER = "MOBILE_NUMBER";
        static final String ADMIN_PASSWORD = "PASSWORD";

        static final String CREATE_ADMINTABLE = "CREATE TABLE " + ADMIN_TABLENAME + "(" + ADMIN_EMAIL_ID + " VARCHAR(255) PRIMARY KEY ," +
                "" + ADMIN_NAME + " VARCHAR(255)," + ADMIN_MOBILE_NUMBER + " VARCHAR(255)," + ADMIN_PASSWORD + " VARCHAR(255));";


        //HOD TABLE
        static final String HOD_TABLENAME = "HOD";
        static final String HOD_EMAIL_ID = "EMAIL_ID";
        static final String HOD_NAME = "NAME";
        static final String HOD_MOBILE_NUMBER = "MOBILE_NUMBER";
        static final String HOD_PASSWORD = "PASSWORD";
        static final String HOD_APPROVAL = "APPROVAL";

        static final String CREATE_HODTABLE = "CREATE TABLE " + HOD_TABLENAME + "(" + HOD_EMAIL_ID + " VARCHAR(255) PRIMARY KEY ," +
                "" + HOD_NAME + " VARCHAR(255)," + HOD_MOBILE_NUMBER + " VARCHAR(255)," + HOD_PASSWORD + " VARCHAR(255)," + HOD_APPROVAL + " INTEGER);";

        //TEACHER TABLE
        static final String TEACHER_TABLENAME = "TEACHERS";
        static final String TEACHER_EMAIL_ID = "EMAIL_ID";
        static final String TEACHER_NAME = "NAME";
        static final String TEACHER_MOBILE_NUMBER = "MOBILE_NUMBER";
        static final String TEACHER_PASSWORD = "PASSWORD";
        static final String TEACHER_GENDER = "GENDER";
        static final String TEACHER_SUBJECT = "SUBJECT";
        static final String TEACHER_AGE = "AGE";
        static final String TEACHER_APPROVAL = "APPROVAL";

        static final String CREATE_TEACHERS_TABLE = "CREATE TABLE " + TEACHER_TABLENAME + "(" + TEACHER_EMAIL_ID + " VARCHAR(255) PRIMARY KEY ," +
                "" + TEACHER_NAME + " VARCHAR(255)," + TEACHER_MOBILE_NUMBER + " VARCHAR(255)," + TEACHER_PASSWORD + " VARCHAR(255)," + TEACHER_GENDER + " VARCHAR(255)," +
                "" + TEACHER_SUBJECT + " VARCHAR(255)," + TEACHER_AGE + " VARCHAR(255)," + TEACHER_APPROVAL + " INTEGER);";

        //STUDENT TABLE
        static final String STUDENTS_TABLENAME = "STUDENTS";
        static final String STUDENTS_EMAIL_ID = "EMAIL_ID";
        static final String STUDENTS_NAME = "NAME";
        static final String STUDENTS_MOBILE_NUMBER = "MOBILE_NUMBER";
        static final String STUDENTS_PASSWORD = "PASSWORD";
        static final String STUDENTS_GENDER = "GENDER";
        static final String STUDENTS_YEAR = "YEAR";
        static final String STUDENTS_AGE = "AGE";
        static final String STUDENTS_BOOKS = "BOOKS";
        static final String STUDENTS_APPROVAL = "APPROVAL";

        static final String CREATE_STUDENTS_TABLE = "CREATE TABLE " + STUDENTS_TABLENAME + "(" + STUDENTS_EMAIL_ID + " VARCHAR(255) PRIMARY KEY ," +
                "" + STUDENTS_NAME + " VARCHAR(255)," + STUDENTS_MOBILE_NUMBER + " VARCHAR(255)," + STUDENTS_PASSWORD + " VARCHAR(255)," + STUDENTS_GENDER + " VARCHAR(255)," +
                "" + STUDENTS_YEAR + " VARCHAR(255)," + STUDENTS_AGE + " VARCHAR(255)," + STUDENTS_BOOKS + " VARCHAR(255)," + STUDENTS_APPROVAL + " INTEGER);";

        // LIBRARIES
        static final String LIBRARIES_TABLENAME = "LIBRARY";
        static final String LIBRARIES_BOOKNAME = "BOOK_NAME";
        static final String LIBRARIES_AVAILABLE = "AVAILABLE";
        static final String LIBRARIES_STREAM = "STEAM";

        private static final String CREATE_LIBRARIESTABLE = "CREATE TABLE " + LIBRARIES_TABLENAME + "(" + LIBRARIES_BOOKNAME + " VARCHAR(255), " + LIBRARIES_STREAM + " VARCHAR(255)," +
                "" + LIBRARIES_AVAILABLE + " INTEGER); ";




        //LECTURES
        static final String LECTURE_TABLENAME = "LECTURE";
        static final String LECTURE_DATE = "DATE";
        static final String LECTURE_MONTH = "MONTH";
        static final String LECTURE_SUBJECT = "SUBJECT";
        static final String LECTURE_TIME = "TIME";

        static final String CREATE_LECTURE = "CREATE TABLE " + LECTURE_TABLENAME + "(" + LECTURE_DATE + " VARCHAR(255)," + LECTURE_MONTH + " VARCHAR(255), " + LECTURE_SUBJECT + " VARCHAR(255), " + LECTURE_TIME + " VARCHAR(255) PRIMARY KEY);";


        CollegeDatabase(Context context) {

            super(context, DATABASE_NAME, null, DATABASE_VERSION);
            this.context=context;
        }

        @Override
        public void onCreate(SQLiteDatabase db) {
            db.execSQL(CREATE_ADMINTABLE);
            db.execSQL(CREATE_HODTABLE);
            db.execSQL(CREATE_TEACHERS_TABLE);
            db.execSQL(CREATE_STUDENTS_TABLE);
            db.execSQL(CREATE_LIBRARIESTABLE);
            db.execSQL(CREATE_LECTURE);
            createLibrary(context,db);
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int i, int i1) {

            db.execSQL("DROP TABLE IF EXISTS" + ADMIN_TABLENAME);
            db.execSQL("DROP TABLE IF EXISTS" + HOD_TABLENAME);
            db.execSQL("DROP TABLE IF EXISTS" + TEACHER_TABLENAME);
            db.execSQL("DROP TABLE IF EXISTS" + STUDENTS_TABLENAME);
            db.execSQL("DROP TABLE IF EXISTS" + LIBRARIES_TABLENAME);
            db.execSQL("DROP TABLE IF EXISTS" + LECTURE_TABLENAME);

            onCreate(db);

        }
    }
}