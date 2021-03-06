package com.ok.todojwtjpademo.graphql;

import com.ok.todojwtjpademo.models.Todo;
import com.ok.todojwtjpademo.services.TodoService;
import graphql.schema.DataFetcher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class TodoFetcherService {
    @Autowired
    private TodoService todoService;

    public DataFetcher<List<Todo>> getTodos() {
        return dataFetchingEnvironment -> {
            String user = dataFetchingEnvironment.getContext();
            return todoService.getAllTodos(user);
        };
    }

    public DataFetcher<Todo> getTodo() {
        return dataFetchingEnvironment -> {
            String user = dataFetchingEnvironment.getContext();
            int id = dataFetchingEnvironment.getArgument("id");
            return todoService.getOneTodo(id, user);
        };
    }

    public DataFetcher<Todo> deleteTodo() {
        return dataFetchingEnvironment -> {
            String user = dataFetchingEnvironment.getContext();
            int id = dataFetchingEnvironment.getArgument("id");
            return todoService.deleteTodo(id, user);
        };
    }


    public DataFetcher<Todo> addTodo() {
        return dataFetchingEnvironment -> {
            String user = dataFetchingEnvironment.getContext();
            Map<String, Object> argument = dataFetchingEnvironment.getArgument("newTodo");
            String desc = (String) argument.get("description");
            Todo newTodo = new Todo();
            newTodo.setDescription(desc);
            return todoService.addTodo(newTodo, user);
        };
    }

    public DataFetcher<Todo> updateTodo() {
        return dataFetchingEnvironment -> {
            String user = dataFetchingEnvironment.getContext();
            Map<String, Object> argument = dataFetchingEnvironment.getArgument("newTodo");
            String desc = (String) argument.get("description");
            int todoId = dataFetchingEnvironment.getArgument("id");
            Todo newTodo = new Todo();
            newTodo.setDescription(desc);
            return todoService.updateTodo(todoId, newTodo, user);
        };
    }
}
