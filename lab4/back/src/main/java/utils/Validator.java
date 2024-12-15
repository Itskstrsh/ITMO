package utils;

import jakarta.ws.rs.WebApplicationException;
import jakarta.ws.rs.core.Response;
import request.ResultRequest;
import request.UserRequest;

public class Validator {
    public void validateUserRequest(UserRequest userRequest) {
        if (userRequest.getUsername() == null || userRequest.getUsername().isEmpty()) {
            throw new WebApplicationException("Username is required", Response.Status.BAD_REQUEST);
        }
        if (userRequest.getPassword() == null || userRequest.getPassword().isEmpty()) {
            throw new WebApplicationException("Password is required", Response.Status.BAD_REQUEST);
        }
    }

    public void validateResultRequest(ResultRequest resultRequest) {
        if (resultRequest.getUserId() == null) {
            throw new WebApplicationException("User ID is required", Response.Status.BAD_REQUEST);
        }
        if (resultRequest.getR() <= 0) {
            throw new WebApplicationException("Radius must be greater than 0", Response.Status.BAD_REQUEST);
        }
        if (resultRequest.getY() < -3 || resultRequest.getY() > 3) {
            throw new WebApplicationException("Y must be (-3;3)", Response.Status.BAD_REQUEST);
        }
        if (resultRequest.getX() < -2 || resultRequest.getX() > 2) {
            throw new WebApplicationException("X must be (-2;2)", Response.Status.BAD_REQUEST);
        }
    }

}
