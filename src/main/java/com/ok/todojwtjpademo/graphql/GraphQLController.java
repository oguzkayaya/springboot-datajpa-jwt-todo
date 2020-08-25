package com.ok.todojwtjpademo.graphql;

import com.ok.todojwtjpademo.util.JwtUtil;
import graphql.ExecutionInput;
import graphql.ExecutionResult;
import graphql.GraphQL;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
public class GraphQLController {

    @Autowired
    private GraphQL graphQL;
    @Autowired
    private JwtUtil jwtUtil;

    @PostMapping(value = "/graphql")
    public ResponseEntity<?> executeQuery(@RequestBody GraphQLRequestBody body, HttpServletRequest request) {
        String user = jwtUtil.extractUsernameFromRequest(request);
        ExecutionResult executionResult = graphQL.execute(ExecutionInput.newExecutionInput()
                .query(body.getQuery())
                .operationName(body.getOperationName())
                .variables(body.getVariables())
                .context(user)
                .build());
        return new ResponseEntity<>(executionResult, HttpStatus.OK);
    }

}
