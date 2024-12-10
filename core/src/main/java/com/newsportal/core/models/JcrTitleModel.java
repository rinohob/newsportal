package com.newsportal.core.models;

import lombok.Getter;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.injectorspecific.SlingObject;
import javax.annotation.PostConstruct;

@Model(adaptables = Resource.class, defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL)
public class JcrTitleModel {


   @SlingObject
    private Resource resource;

    @Getter
    private BlogPageModel blogPageModel;

    private static final String COMPONENT_RELATIVE_PATH = "/root/container/container/tagcomponent";

    @PostConstruct
    protected void init() {
        String resourcePath = resource.getPath();
        String componentPath = resourcePath + COMPONENT_RELATIVE_PATH;
        Resource componentResource = resource.getResourceResolver().getResource(componentPath);
        if (componentResource != null) {
            blogPageModel = componentResource.adaptTo(BlogPageModel.class);
        }
    }

}

