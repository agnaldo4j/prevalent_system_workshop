package com.agnaldo4j.system.prevalent.model;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.experimental.Accessors;

import java.io.Serializable;

@Accessors(fluent = true)
public class Task implements Serializable {

    @Getter(AccessLevel.PUBLIC)
    private boolean done;

    @Getter(AccessLevel.PUBLIC)
    private String name;

    public Task(String name) {
        this.name = name;
    }

    public void doTask() {
        this.done = true;
    }
}
