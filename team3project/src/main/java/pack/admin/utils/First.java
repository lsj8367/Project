package pack.admin.utils;

import org.springframework.stereotype.Component;

@Component
public class First implements Rank {

    @Override
    public int getRankEnum() {
        return RankEnum.FIRST.getRank();
    }

    @Override
    public int giveUserPoint() {
        return 3000;
    }

}
