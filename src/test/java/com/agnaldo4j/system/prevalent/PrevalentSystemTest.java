package com.agnaldo4j.system.prevalent;

import com.agnaldo4j.system.prevalent.command.TaskAdder;
import com.agnaldo4j.system.prevalent.command.TaskListAdder;
import com.agnaldo4j.system.prevalent.model.Task;
import com.agnaldo4j.system.prevalent.model.TaskList;
import com.agnaldo4j.system.prevalent.query.FindAllTasksOnTaskList;
import com.agnaldo4j.system.prevalent.query.FindTaskListByName;
import org.junit.After;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class PrevalentSystemTest {

    PrevalentSystemWorkshop<TaskList> prevalentSystem = new PrevalentSystemWorkshop<>();

    @After
    public void cleanState() throws Exception {
        prevalentSystem.destroyState();
    }

    @Test
    public void addTaskListCarroToPrevalentSystem() throws Exception {
        prevalentSystem.load(new ArrayList<TaskList>());
        prevalentSystem.execute(new TaskListAdder("carro"));

        Optional<TaskList> queryResult = prevalentSystem.execute(new FindTaskListByName("carro"));
        assertTrue("someLibraryMethod should return 'true'", queryResult.isPresent());
    }

    @Test
    public void addTaskOnTaskListCarro() throws Exception {
        prevalentSystem.load(new ArrayList<TaskList>());
        prevalentSystem.execute(new TaskListAdder("carro"));
        prevalentSystem.execute(new TaskAdder("carro", "abastecer"));

        List<Task> queryResult = prevalentSystem.execute(new FindAllTasksOnTaskList("carro"));
        assertEquals(1, queryResult.size());
        assertEquals("abastecer", queryResult.get(0).name());
    }
}
