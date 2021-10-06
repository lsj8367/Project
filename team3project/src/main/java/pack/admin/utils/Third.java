package pack.admin.utils;

public class Third implements Rank {

    @Override
    public int getRankEnum() {
        return RankEnum.THIRD.getRank();
    }

    @Override
    public int giveUserPoint() {
        return 1000;
    }

}
