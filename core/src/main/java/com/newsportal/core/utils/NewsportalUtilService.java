package com.newsportal.core.utils;

import  org.apache.sling.api.resource.LoginException;
import org.apache.sling.api.resource.ResourceResolver;
import org.apache.sling.api.resource.ResourceResolverFactory;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.HashMap;
import java.util.Map;


@Component(service = NewsportalUtilService.class)
public class NewsportalUtilService {

    private static final Logger LOG = LoggerFactory.getLogger(NewsportalUtilService.class);

    @Reference
    ResourceResolverFactory factory;

    public ResourceResolver getResourceResolver(String subService) {
        final Map<String, Object> map = new HashMap<>();
        map.put(ResourceResolverFactory.SUBSERVICE, subService);
        try {
            return factory.getServiceResourceResolver(map);
        } catch (LoginException e) {
            LOG.error("error while getting resource resolver ",e);
        }
        return null;
    }
}
