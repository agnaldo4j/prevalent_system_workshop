package com.agnaldo4j.system.prevalent.command;

import com.agnaldo4j.system.prevalent.Command;
import com.agnaldo4j.system.prevalent.model.Task;
import com.agnaldo4j.system.prevalent.model.TaskList;
import com.agnaldo4j.system.prevalent.query.FindTaskListByName;

import java.util.List;

public class TaskAdder implements Command<TaskList> {

    private String taskListName;
    private String taskName;

    public TaskAdder(String taskListName, String taskName) {
        this.taskListName = taskListName;
        this.taskName = taskName;
    }

    @Override
    public void execute(List<TaskList> state) {
        new FindTaskListByName(this.taskListName)
                .execute(state)
                .ifPresent(taskList -> taskList.addTask(new Task(taskName)));
    }
}
