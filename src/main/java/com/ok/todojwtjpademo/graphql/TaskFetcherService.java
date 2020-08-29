package com.ok.todojwtjpademo.graphql;

import com.ok.todojwtjpademo.models.Task;
import com.ok.todojwtjpademo.models.Todo;
import com.ok.todojwtjpademo.repositories.TaskRepository;
import com.ok.todojwtjpademo.services.TaskService;
import graphql.schema.DataFetcher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.Map;

@Service
public class TaskFetcherService {

    @Autowired
    private TaskService taskService;

    public DataFetcher<Boolean> deleteTask() {
        return dataFetchingEnvironment -> {
            try {
                String user = dataFetchingEnvironment.getContext();
                int id = dataFetchingEnvironment.getArgument("id");
                taskService.DeleteTask(id, user);
                return true;
            } catch (Exception e) {
                e.printStackTrace();
                return false;
            }
        };
    }

    public DataFetcher<Boolean> addTask() {
        return dataFetchingEnvironment -> {
            try {
                String user = dataFetchingEnvironment.getContext();
                int todoId = dataFetchingEnvironment.getArgument("todoId");

                Map<String, Object> argument = dataFetchingEnvironment.getArgument("newTask");
                String taskName = (String) argument.get("taskName");
                String dueDateString = (String) argument.get("dueDate");
                Date dueDate = Date.valueOf(dueDateString);
                Boolean done = (Boolean) argument.get("done");
                Task newTask = new Task();
                newTask.setDone(done);
                newTask.setDueDate(dueDate);
                newTask.setTaskName(taskName);

                taskService.AddTaskToTodo(todoId, newTask, user);
                return true;
            } catch (Exception e) {
                e.printStackTrace();
                return false;
            }
        };
    }

    public DataFetcher<Boolean> updateTask() {
        return dataFetchingEnvironment -> {
            try {
                String user = dataFetchingEnvironment.getContext();
                int taskId = dataFetchingEnvironment.getArgument("id");

                Map<String, Object> argument = dataFetchingEnvironment.getArgument("newTask");
                String taskName = (String) argument.get("taskName");
                String dueDateString = (String) argument.get("dueDate");
                Date dueDate = Date.valueOf(dueDateString);
                Boolean done = (Boolean) argument.get("done");
                Task newTask = new Task();
                newTask.setDone(done);
                newTask.setDueDate(dueDate);
                newTask.setTaskName(taskName);

                taskService.UpdateTask(taskId, newTask, user);
                return true;
            } catch (Exception e) {
                e.printStackTrace();
                return false;
            }
        };
    }


}
