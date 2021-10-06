package pack.admin.utils;

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
