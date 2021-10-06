package pack.admin.utils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.stereotype.Component;

@Component
public class PointState {
    private final Map<Integer, Rank> pointStateMap = new HashMap<>();

    public PointState(List<Rank> ranks) {
        ranks.forEach(rank -> {
            pointStateMap.put(rank.getRankEnum(), rank);
        });
    }

    public Rank getPointStateMap(int rank) {
        return pointStateMap.get(rank);
    }

}
