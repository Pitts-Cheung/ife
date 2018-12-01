package com.example.pitts.ife.Bean;

import java.util.List;

public class Task {

    private String taskTitle;

    private String taskContent;

    private int UserId;

    private String taskTime;

    private int taskGift;

    private List<Integer> taskDDLDate;

    private List<Integer> taskDDLTime;

    private String taskType;

    public String getTaskTitle() {
        return taskTitle;
    }

    public void setTaskTitle(String taskTitle) {
        this.taskTitle = taskTitle;
    }

    public String getTaskContent() {
        return taskContent;
    }

    public void setTaskContent(String taskContent) {
        this.taskContent = taskContent;
    }

    public int getUserId() {
        return UserId;
    }

    public void setUserId(int userId) {
        UserId = userId;
    }

    public String getTaskTime() {
        return taskTime;
    }

    public void setTaskTime(String taskTime) {
        this.taskTime = taskTime;
    }

    public int getTaskGift() {
        return taskGift;
    }

    public void setTaskGift(int taskGift) {
        this.taskGift = taskGift;
    }

    public List<Integer> getTaskDDLDate() {
        return taskDDLDate;
    }

    public void setTaskDDLDate(List<Integer> taskDDLDate) {
        this.taskDDLDate = taskDDLDate;
    }

    public List<Integer> getTaskDDLTime() {
        return taskDDLTime;
    }

    public void setTaskDDLTime(List<Integer> taskDDLTime) {
        this.taskDDLTime = taskDDLTime;
    }

    public String getTaskType() {
        return taskType;
    }

    public void setTaskType(String taskType) {
        this.taskType = taskType;
    }
}
