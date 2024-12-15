package controller;


import entity.UserEntity;
import jakarta.inject.Inject;
import jakarta.ws.rs.core.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import request.UserRequest;
import service.UserService;
import utils.Validator;


public class LoginController {
    private static final Logger logger = LoggerFactory.getLogger(LoginController.class);
    @Inject
    private Validator validator;
    @Inject
    private UserService userService;


    public Response login(UserRequest userRequest) {
        try {
            validator.validateUserRequest(userRequest);
            UserEntity user = userService.authenticateUser(userRequest.getUsername(), userRequest.getPassword());
            if (user == null) {
                logger.warn("Authentication failed for user: {}", userRequest.getUsername());
                return Response.status(Response.Status.UNAUTHORIZED)
                        .entity("Invalid username or password")
                        .build();
            }
            logger.info("User authenticated: {}", userRequest.getUsername());
            return Response.ok(user).build();
        } catch (Exception e) {
            logger.error("Error during login", e);
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("An unexpected error occurred")
                    .build();
        }
    }

}
