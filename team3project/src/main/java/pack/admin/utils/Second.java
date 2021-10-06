package pack.admin.utils;

import org.springframework.stereotype.Component;

@Component
public class Second implements Rank {

    @Override
    public int getRankEnum() {
        return RankEnum.SECOND.getRank();
    }

    @Override
    public int giveUserPoint() {
        return 2000;
    }

}
