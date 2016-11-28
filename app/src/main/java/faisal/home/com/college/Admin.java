package faisal.home.com.college;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by Home on 11/23/2016.
 */
public class Admin implements Serializable {

    private String name,email,password;
    private ArrayList hodArray;
    private long mobileNo;

    public ArrayList getHodArray() {
        return hodArray;
    }

    public void setHodArray(ArrayList hodArray) {
        this.hodArray = hodArray;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }



    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }



    public long getMobileNo() {
        return mobileNo;
    }

    public void setMobileNo(long mobileNo) {
        this.mobileNo = mobileNo;
    }




    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
