package service;

import dao.ResultDAO;
import entity.ResultEntity;
import entity.UserEntity;
import jakarta.ejb.Stateless;
import jakarta.inject.Inject;

import java.util.List;

@Stateless
public class ResultService {

    @Inject
    private ResultDAO resultDAO;

    public void saveResult(UserEntity user, double x, double y, double r, boolean hit) {
        ResultEntity result = new ResultEntity();
        result.setUser(user);
        result.setX(x);
        result.setY(y);
        result.setR(r);
        result.setHit(hit);
        resultDAO.save(result);
    }
    public List<ResultEntity> getResultsByUserId(Long userId) {
        return resultDAO.findByUserId(userId);
    }
}
