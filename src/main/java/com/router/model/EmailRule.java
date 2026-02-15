package com.router.model;

public class EmailRule {

    private int id;
    private String senderPattern;
    private String subjectPattern;
    private String routeTo;
    private int priority;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getSenderPattern() {
        return senderPattern;
    }

    public void setSenderPattern(String senderPattern) {
        this.senderPattern = senderPattern;
    }

    public String getSubjectPattern() {
        return subjectPattern;
    }

    public void setSubjectPattern(String subjectPattern) {
        this.subjectPattern = subjectPattern;
    }

    public String getRouteTo() {
        return routeTo;
    }

    public void setRouteTo(String routeTo) {
        this.routeTo = routeTo;
    }

    public int getPriority() {
        return priority;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }
}
