package com.example.todolistapp;

public class Task {
    private int id;
    private String name;
    private boolean isCompleted;

    public Task(int id, String name, boolean isCompleted) {
        this.id = id;
        this.name = name;
        this.isCompleted = isCompleted;
    }

    public Task(String name) {
        this.name = name;
        this.isCompleted = false;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public boolean isCompleted() {
        return isCompleted;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setCompleted(boolean completed) {
        isCompleted = completed;
    }
}
