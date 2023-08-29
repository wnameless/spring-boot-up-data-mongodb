package cascade.all;

import org.springframework.context.annotation.Configuration;
import com.github.wnameless.spring.boot.up.EnableSpringBootUp;
import com.github.wnameless.spring.boot.up.data.mongodb.config.EnableSpringBootUpMongo;

@EnableSpringBootUpMongo
@EnableSpringBootUp
@Configuration
public class AppConfig {}
