package pack.admin.utils;

import static org.assertj.core.api.Assertions.assertThat;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import pack.config.RankConfig;

@SpringBootTest(classes = RankConfig.class)
class PointStateTest {

    @Autowired
    PointState pointState;

    @Test
    void test() {
        Rank rank = pointState.getPointStateMap(RankEnum.FIRST.getRank());

        assertThat(rank.getRankEnum()).isEqualTo(RankEnum.FIRST.getRank());
        assertThat(rank.giveUserPoint()).isEqualTo(3000);
    }

}