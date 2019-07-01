package redis;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.*;

/**
 * @Author 胡说
 * @DATETIME 2019-07-01 19:19
 * TODO
 */
@Configuration
@EnableAspectJAutoProxy
@ComponentScan
public class AppConfig {
    public static void main(String[] args) {
        ApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);
        LimitService bean = context.getBean(LimitService.class);
        bean.execute();
    }

//    @Bean
//    public LimitService getService() {
//        return new LimitService();
//    }
//
//    @Bean
//    public LimitComponent getLimit() {
//        return new LimitComponent();
//    }
}
