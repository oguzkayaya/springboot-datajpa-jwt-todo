package com.ok.todojwtjpademo.graphql;

import com.ok.todojwtjpademo.models.Todo;
import com.ok.todojwtjpademo.services.TodoService;
import graphql.schema.DataFetcher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
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