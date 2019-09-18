package com.agnaldo4j.system.prevalent.query;

import com.agnaldo4j.system.prevalent.Query;
import com.agnaldo4j.system.prevalent.model.Task;
import com.agnaldo4j.system.prevalent.model.TaskList;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class FindAllTasksOnTaskList implements Query<List<Task>, TaskList> {

    private String taskListName;

    public FindAllTasksOnTaskList(String taskListName) {
        this.taskListName = taskListName;
    }

    @Override
    public List<Task> execute(List<TaskList> state) {
        Optional<TaskList> queryResult = new FindTaskListByName(this.taskListName).execute(state);
        return queryResult.isPresent() ? queryResult.get().tasks() : new ArrayList<>();
    }
}
