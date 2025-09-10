package controller;

import jakarta.inject.Inject;
import jakarta.ws.rs.WebApplicationException;
import jakarta.ws.rs.core.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import request.UserRequest;
import service.ResultService;
import service.UserService;
import entity.UserEntity;
import utils.Validator;

public class RegistrationController {
    private static final Logger logger = LoggerFactory.getLogger(RegistrationController.class);

    @Inject
    private Validator validator;

    @Inject
    private UserService userService;

    @Inject
    private ResultService resultService;

    public Response register(UserRequest userRequest) {
        try {
            validator.validateUserRequest(userRequest);
            UserEntity newUser = userService.registerUser(userRequest.getUsername(), userRequest.getPassword());
            logger.info("User registered: {}", newUser.getUsername());
            return Response.ok(newUser).build();
        } catch (WebApplicationException e) {
            logger.warn("Validation failed for user registration: {}", e.getMessage());
            return Response.status(e.getResponse().getStatus())
                    .entity(e.getMessage())
                    .build();
        } catch (Exception e) {
            logger.error("Error during registration", e);
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("An unexpected error occurred")
                    .build();
        }
    }
}
