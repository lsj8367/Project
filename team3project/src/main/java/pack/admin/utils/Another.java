package pack.admin.utils;

import org.springframework.stereotype.Component;

@Component
public class Another implements Rank {

    @Override
    public int getRankEnum() {
        return RankEnum.ANOTHER.getRank();
    }

    @Override
    public int giveUserPoint() {
        return 0;
    }

}
