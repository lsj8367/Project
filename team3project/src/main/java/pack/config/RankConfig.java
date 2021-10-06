package pack.config;

import java.util.Arrays;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import pack.admin.utils.Another;
import pack.admin.utils.First;
import pack.admin.utils.PointState;
import pack.admin.utils.Second;
import pack.admin.utils.Third;

@Configuration
public class RankConfig {

    @Bean
    public PointState pointState() {
        return new PointState(Arrays.asList(new First(), new Second(), new Third(), new Another()));
    }

}
