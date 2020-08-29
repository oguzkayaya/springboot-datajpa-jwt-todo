package com.ok.todojwtjpademo.graphql;

import graphql.GraphQL;
import graphql.schema.DataFetcher;
import graphql.schema.GraphQLSchema;
import graphql.schema.idl.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class GraphQLConfiguration {

    @Autowired
    private TodoFetcherService todoFetcherService;
    @Autowired
    private TaskFetcherService taskFetcherService;

    @Bean
    public GraphQL graphQL() throws IOException {
        SchemaParser schemaParser = new SchemaParser();
        ClassPathResource schema = new ClassPathResource("schema.graphql");
        TypeDefinitionRegistry typeDefinitionRegistry = schemaParser.parse(schema.getInputStream());
        RuntimeWiring runtimeWiring = RuntimeWiring.newRuntimeWiring()
                .type(TypeRuntimeWiring.newTypeWiring("Query")
                        .dataFetcher("getTodos", todoFetcherService.getTodos())
                        .dataFetcher("getTodo", todoFetcherService.getTodo()))
                .type(TypeRuntimeWiring.newTypeWiring("Mutation")
                        .dataFetcher("deleteTodo", todoFetcherService.deleteTodo())
                        .dataFetcher("addTodo", todoFetcherService.addTodo())
                        .dataFetcher("updateTodo", todoFetcherService.updateTodo())
                        .dataFetcher("deleteTask", taskFetcherService.deleteTask())
                        .dataFetcher("addTask", taskFetcherService.addTask())
                        .dataFetcher("updateTask", taskFetcherService.updateTask()))
                .build();
        SchemaGenerator schemaGenerator = new SchemaGenerator();
        GraphQLSchema graphQLSchema = schemaGenerator.makeExecutableSchema(typeDefinitionRegistry, runtimeWiring);
        return GraphQL.newGraphQL(graphQLSchema).build();
    }

}
