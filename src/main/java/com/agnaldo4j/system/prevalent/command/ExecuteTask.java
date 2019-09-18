package com.agnaldo4j.system.prevalent.command;

import com.agnaldo4j.system.prevalent.Command;
import com.agnaldo4j.system.prevalent.model.TaskList;
import com.agnaldo4j.system.prevalent.query.FindTaskListByName;

import java.util.List;

public class ExecuteTask implements Command<TaskList> {
    private String taskListName;
    private String taskName;

    public ExecuteTask(String taskListName, String taskName) {
        this.taskListName = taskListName;
        this.taskName = taskName;
    }

    @Override
    public void execute(List<TaskList> state) {
        new FindTaskListByName(this.taskListName)
                .execute(state)
                .ifPresent(taskList -> taskList.executeTask(taskName));
    }
}
