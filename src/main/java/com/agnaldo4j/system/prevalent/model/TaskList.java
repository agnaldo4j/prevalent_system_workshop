package com.agnaldo4j.system.prevalent.model;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.experimental.Accessors;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Accessors(fluent = true)
public class TaskList {

    private List<Task> tasks;

    @Getter(AccessLevel.PUBLIC)
    private String name;

    public TaskList(String name) {
        this.name = name;
        this.tasks = new ArrayList<>();
    }

    public List<Task> tasks() {
        return Collections.unmodifiableList(this.tasks);
    }

    public void addTask(Task task) {
        this.tasks.add(task);
    }

    public void executeTask(String taskName) {
        this.tasks.stream()
                .filter(task -> task.name().equals(taskName))
                .findFirst()
                .ifPresent(task -> task.doTask());
    }
}
