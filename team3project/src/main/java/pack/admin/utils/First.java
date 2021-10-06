package pack.admin.utils;

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
