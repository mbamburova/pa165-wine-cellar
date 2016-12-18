package cz.muni.fi.pa165.config;

import cz.muni.fi.pa165.dto.*;
import cz.muni.fi.pa165.entity.*;
import org.dozer.DozerBeanMapper;
import org.dozer.Mapper;
import org.dozer.loader.api.BeanMappingBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

/**
 * @author Silvia Borzová
 *         13/11/2016
 */
@Configuration
@Import({PersistenceApplicationContext.class})
@ComponentScan(basePackages = {"cz.muni.fi.pa165.service", "cz.muni.fi.pa165.facade"})
public class ServiceConfiguration {

    @Bean
    public Mapper dozer(){
        DozerBeanMapper dozer = new DozerBeanMapper();
        dozer.addMapping(new DozerCustomConfig());
        return dozer;
    }

    public class DozerCustomConfig extends BeanMappingBuilder {
        @Override
        protected void configure() {
            mapping(MarketingEvent.class, MarketingEventDto.class);
            mapping(Packing.class, PackingDto.class);
            mapping(Packing.class, PackingCreateDto.class);
            mapping(Price.class, PriceCreateDto.class);
            mapping(Price.class, PriceDto.class);
            mapping(WineList.class, WineListDto.class);
            mapping(Wine.class, WineDto.class);
        }
    }
}
