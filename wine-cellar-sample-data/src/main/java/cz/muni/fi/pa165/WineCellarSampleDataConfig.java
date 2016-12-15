package cz.muni.fi.pa165;

import cz.muni.fi.pa165.SampleDataLoadingFacade;
import cz.muni.fi.pa165.SampleDataLoadingFacadeImpl;
import cz.muni.fi.pa165.config.ServiceConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import java.io.IOException;

/**
 * @author MarekScholtz
 * @version 2016.12.15
 */

@Configuration
@Import(ServiceConfiguration.class)
@ComponentScan(basePackageClasses = SampleDataLoadingFacadeImpl.class)
public class WineCellarSampleDataConfig {

    @Inject
    private SampleDataLoadingFacade sampleDataLoadingFacade;

    @PostConstruct
    public void dataLoad() throws IOException {
        sampleDataLoadingFacade.loadSampleData();
    }

}
