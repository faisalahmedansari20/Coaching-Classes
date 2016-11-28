package faisal.home.com.college;

import java.io.Serializable;

/**
 * Created by Home on 11/23/2016.
 */
public class Teacher implements Serializable {

    private String name,email,password,gender,subject;
    private int age;
    private long mobileNo;

    Teacher()
    {}

    public Teacher(String email, String name, long mobileNo, String password, String gender,
                   String subject, int age,int approved) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.gender = gender;
        this.subject = subject;
        this.age = age;
        this.mobileNo = mobileNo;
        this.approved = approved;
    }

    private int approved;

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

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public long getMobileNo() {
        return mobileNo;
    }

    public void setMobileNo(long mobileNo) {
        this.mobileNo = mobileNo;
    }
}
