package controller;

import entity.ResultEntity;
import entity.UserEntity;
import jakarta.inject.Inject;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.WebApplicationException;
import jakarta.ws.rs.core.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import request.ResultRequest;
import response.ResultResponse;
import service.ResultService;
import service.UserService;
import utils.AreaChecker;
import utils.Validator;

import java.util.List;
import java.util.stream.Collectors;

public class ResultController {
    private static final Logger logger = LoggerFactory.getLogger(ResultController.class);

    @Inject
    private Validator validator;
    @Inject
    private AreaChecker areaChecker;
    @Inject
    private UserService userService;
    @Inject
    private ResultService resultService;

    public Response saveResult(ResultRequest resultRequest) {
        try {
            validator.validateResultRequest(resultRequest);
            UserEntity user = userService.findUserById(resultRequest.getUserId());
            if (user == null) {
                logger.warn("User with ID {} not found", resultRequest.getUserId());
                return Response.status(Response.Status.NOT_FOUND)
                        .entity("User not found")
                        .build();
            }
            boolean hit = areaChecker.checkHit(resultRequest.getX(), resultRequest.getY(), resultRequest.getR());
            resultService.saveResult(user, resultRequest.getX(), resultRequest.getY(), resultRequest.getR(), hit);
            logger.info("Result saved for user ID: {}, Hit: {}", resultRequest.getUserId(), hit);
            return Response.ok(new ResultResponse(resultRequest.getX(), resultRequest.getY(), resultRequest.getR(), hit))
                    .build();
        } catch (WebApplicationException e) {
            logger.warn("Validation failed for result saving: {}", e.getMessage());
            return Response.status(e.getResponse().getStatus())
                    .entity(e.getMessage())
                    .build();
        } catch (Exception e) {
            logger.error("Error during saving result", e);
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("An unexpected error occurred")
                    .build();
        }
    }

    public Response getResultsByUserId(@PathParam("userId") Long userId) {
        try {
            List<ResultEntity> results = resultService.getResultsByUserId(userId);
            if (results.isEmpty()) {
                logger.info("No results found for user ID: {}", userId);
                return Response.status(Response.Status.NOT_FOUND)
                        .entity("No results found for this user")
                        .build();
            }
            List<ResultResponse> responseList = results.stream()
                    .map(result -> new ResultResponse(result.getX(), result.getY(), result.getR(), result.getHit().equals("yes")))
                    .collect(Collectors.toList());
            logger.info("Results retrieved for user ID: {}", userId);
            return Response.ok(responseList).build();
        } catch (Exception e) {
            logger.error("Error retrieving results for user ID: {}", userId, e);
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("An unexpected error occurred while retrieving results")
                    .build();
        }
    }
}
