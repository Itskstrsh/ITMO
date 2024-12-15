package api;

import controller.LoginController;
import controller.RegistrationController;
import controller.ResultController;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import request.ResultRequest;
import request.UserRequest;



@Path("/controller")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class ApiController {

    @Inject
    private ResultController resultController;
    @Inject
    private LoginController loginController;
    @Inject
    private RegistrationController registration;


    @POST
    @Path("/register")
    public Response register(UserRequest userRequest) {
        return registration.register(userRequest);
    }

    @POST
    @Path("/login")
    public Response login(UserRequest userRequest) {
        return loginController.login(userRequest);
    }

    @POST
    @Path("/results")
    public Response results(ResultRequest resultRequest) {
        return resultController.saveResult(resultRequest);
    }

    @GET
    @Path("/results/{userId}")
    public Response getResults(@PathParam("userId") Long userId) {
        return resultController.getResultsByUserId(userId);
    }
}