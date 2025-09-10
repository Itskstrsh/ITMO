package services;

import data.AreaChecker;
import entity.ResultEntity;
import services.ResultRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import utils.Truncate;

import java.util.List;

@ApplicationScoped
public class ResultService {

    @Inject
    private ResultRepository resultRepository;

    public List<ResultEntity> getAllResults() {
        return resultRepository.findAll();
    }

    public boolean processResult(float x, float y, float r) {
        x = Truncate.truncate(x, 2);
        y = Truncate.truncate(y, 2);
        boolean isInside = AreaChecker.isInArea(x, y, r);

        ResultEntity entity = ResultEntity.builder()
                .x(x)
                .y(y)
                .r(r)
                .result(isInside)
                .build();

        resultRepository.save(entity);
        return isInside;
    }
}
