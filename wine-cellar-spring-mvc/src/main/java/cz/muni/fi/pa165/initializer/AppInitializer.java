package cz.muni.fi.pa165.initializer;

import cz.muni.fi.pa165.config.WebMvcConfig;
import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

import javax.servlet.Filter;

/**
 * @author MarekScholtz
 * @version 2016.12.10
 */
public class AppInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {

    @Override
    protected Class<?>[] getRootConfigClasses() {
        return new Class<?>[]{WebMvcConfig.class};
    }

    @Override
    protected String[] getServletMappings() {
        return new String[]{"/"};
    }

    @Override
    protected Filter[] getServletFilters() {
        CharacterEncodingFilter encodingFilter = new CharacterEncodingFilter();
        encodingFilter.setEncoding("utf-8");
        return new Filter[]{encodingFilter};
    }

    @Override
    protected Class<?>[] getServletConfigClasses() {
        return null;
    }

}
