package faisal.home.com.college;

import java.io.Serializable;

/**
 * Created by Home on 11/23/2016.
 */
public class Student implements Serializable {

    private String name,email,password,year,gender;
    private String issuedBook="dont have";
    int age;
    private long mobileNo;
    private int approved;

    Student()
    {

    }

    public Student(String email, String name, long mobileNo, String password, String gender, String year, int age, String issuedBook, int approved) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.year = year;
        this.gender = gender;
        this.issuedBook = issuedBook;
        this.age = age;
        this.mobileNo = mobileNo;
        this.approved = approved;
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

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
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

    public String getIssuedBook() {
        return issuedBook;
    }

    public void setIssuedBook(String issuedBook) {
        this.issuedBook = issuedBook;
    }
}
