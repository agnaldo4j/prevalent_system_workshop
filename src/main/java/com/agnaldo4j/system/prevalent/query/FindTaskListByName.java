package com.agnaldo4j.system.prevalent.query;

import com.agnaldo4j.system.prevalent.Query;
import com.agnaldo4j.system.prevalent.model.TaskList;

import java.util.List;
import java.util.Optional;

public class FindTaskListByName implements Query<Optional<TaskList>, TaskList> {

    private String name;

    public FindTaskListByName(String name) {
        this.name = name;
    }

    @Override
    public Optional<TaskList> execute(List<TaskList> state) {
        return state.stream().filter(taskList -> taskList.name().equals(name)).findFirst();
    }
}
