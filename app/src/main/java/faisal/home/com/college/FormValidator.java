package faisal.home.com.college;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

/**
 * Created by Home on 11/23/2016.
 */
public class FormValidator {

    Context context;
    String panelName;
    RegistrationForm form;

    FormValidator(Context context, String panelName, RegistrationForm form) {
        this.context = context;
        this.panelName = panelName;
        this.form = form;
    }

    public boolean validate() {


        boolean isNamePasswordValid = false;
        boolean isEmailValid = false;
        boolean isMobileNumberValide = false;

        String mobile = "" + form.getMobile();

        if (form.getName().length()>0 && form.getPassword().equals(form.getConfirm())) {
            isNamePasswordValid = true;
            Log.d("msg","isnamepass"+isNamePasswordValid);
        }
        if (form.getEmail().length() > 0) {
            isEmailValid = isEmailValid(form.getEmail());
            Log.d("msg","email"+isEmailValid);
        }

        if (mobile.matches("[0-9]+") && mobile.length()>=10) {
            isMobileNumberValide = true;
            Log.d("msg","mobile"+isMobileNumberValide);
        }

        switch (panelName) {
            case "CollegeAdmin":

                return isNamePasswordValid && isEmailValid && isMobileNumberValide;

            case "HOD":

                return isNamePasswordValid && isEmailValid && isMobileNumberValide;

            case "Teacher":

                return (form.getAge()!=0 && form.getSubject().length()!=0 && form.getGender().length()!=0
                        && isNamePasswordValid && isEmailValid && isMobileNumberValide);

            case "Student":

                return (isNamePasswordValid && isEmailValid && isMobileNumberValide &&
                form.getAge()!=0 && form.getGender().length()!=0 && form.getYear().length()!=0 );

            default:
                Toast.makeText(context,"default",Toast.LENGTH_SHORT).show();
                return false;
        }


    }

    public boolean isEmailValid(String email) {
        String emailPattern = "^[a-zA-Z0-9-]+(\\.[a-zA-Z0-9]+)*@[a-zA-Z0-9]+(\\.[a-zA-Z]{2,}$)";

        return (email.matches(emailPattern)) ? true : false;

    }
}
