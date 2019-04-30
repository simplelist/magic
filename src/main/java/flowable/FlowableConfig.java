package flowable;

import org.flowable.spring.SpringProcessEngineConfiguration;
import org.flowable.spring.boot.EngineConfigurationConfigurer;
import org.flowable.spring.boot.ProcessEngineAutoConfiguration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Collections;

/**
 * @Author kun
 * @DATETIME 2019-03-02 09:58
 */

@AutoConfigureAfter(ProcessEngineAutoConfiguration.class)
@Configuration
public class FlowableConfig {
    @Autowired
    private CustomProcessDiagramGeneratorI customProcessDiagramGeneratorI;

    @Bean
    @ConditionalOnClass(SpringProcessEngineConfiguration.class)
    public EngineConfigurationConfigurer<SpringProcessEngineConfiguration> customizeSpringProcessEngineConfiguration() {
        return processEngineConfiguration -> {
            processEngineConfiguration.setEventListeners(Collections.singletonList(new ProjectEventListener()));
            processEngineConfiguration.setProcessDiagramGenerator(customProcessDiagramGeneratorI);
        };
    }
}