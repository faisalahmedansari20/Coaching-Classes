package faisal.home.com.college;

/**
 * Created by Home on 11/24/2016.
 */
public class Lecture {
    String date,subject,time,month;

    Lecture(String date,String month,String subject,String time)
    {
        this.date=date;
        this.subject=subject;
        this.time=time;
        this.month=month;
    }

    public String getMonth() {
        return month;
    }

    public void setMonth(String month) {
        this.month = month;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
