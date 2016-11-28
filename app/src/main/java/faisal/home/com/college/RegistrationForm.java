package faisal.home.com.college;

/**
 * Created by Home on 11/23/2016.
 */
public class RegistrationForm {

    private String name,email,password,confirm,gender,subject,year;
    private long mobile;
    private int age ;


    public RegistrationForm(String name, String email, String password, String confirm, String gender, String subject, String year, long mobile, int age) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.confirm = confirm;
        this.gender = gender;
        this.subject = subject;
        this.year = year;
        this.mobile = mobile;
        this.age = age;
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

    public String getConfirm() {
        return confirm;
    }

    public void setConfirm(String confirm) {
        this.confirm = confirm;
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

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public long getMobile() {
        return mobile;
    }

    public void setMobile(long mobile) {
        this.mobile = mobile;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}
