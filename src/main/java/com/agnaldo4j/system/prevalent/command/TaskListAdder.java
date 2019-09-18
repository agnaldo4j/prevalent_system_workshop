package com.agnaldo4j.system.prevalent.command;

import com.agnaldo4j.system.prevalent.Command;
import com.agnaldo4j.system.prevalent.model.TaskList;

import java.util.List;

public class TaskListAdder implements Command<TaskList> {

    private String name;

    public TaskListAdder(String name) {
        this.name = name;
    }

    @Override
    public void execute(List<TaskList> state) {
        state.add(new TaskList(name));
    }
}
