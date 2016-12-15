package cz.muni.fi.pa165.controller;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import cz.muni.fi.pa165.rest.ApiUris;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author MarekScholtz
 * @version 2016.12.14
 */
@RestController
public class MainController {

    @RequestMapping(value = {"", "/"}, method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public final Map<String, String> getResources() {

        Map<String,String> resourcesMap = new HashMap<>();

        resourcesMap.put("albums_uri", ApiUris.ROOT_URI_WINELISTS);

        return Collections.unmodifiableMap(resourcesMap);
    }

}
