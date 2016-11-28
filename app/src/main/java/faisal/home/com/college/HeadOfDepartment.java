package faisal.home.com.college;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by Home on 11/23/2016.
 */
public class HeadOfDepartment implements Serializable {

    private String name,email,password;
    private long mobileNo;
    private int approved;

    HeadOfDepartment()
    {}

    public HeadOfDepartment(String email, String name,long mobileNo,String password, int approved) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.approved = approved;
        this.mobileNo = mobileNo;
    }

    public int isApproved() {
        return approved;
    }

    public void setApproved(int approved) {
        this.approved = approved;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
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
}
