package com.ok.todojwtjpademo.graphql;

import com.ok.todojwtjpademo.models.Todo;
import com.ok.todojwtjpademo.services.TodoService;
import graphql.schema.DataFetcher;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class GraphQLTodoService {
    @Autowired
    private TodoService todoService;

    public DataFetcher<List<Todo>> getTodos() {
        return dataFetchingEnvironment -> {
            String user = dataFetchingEnvironment.getContext();
            return todoService.getAllTodos(user);
        };
    }
}
