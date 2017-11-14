package hu.bme.aut.personaltaskmanager.model;

import com.orm.SugarRecord;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Task extends SugarRecord {
    private String title;
    private String project;
    private Date date;
    private int durationTime;
    private boolean done;
    private boolean overdue;
    private String note;
    private List<Date> reminders;

    public Task(){ reminders = new ArrayList<>(); }

    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }

}
