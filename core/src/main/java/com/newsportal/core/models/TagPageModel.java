package com.newsportal.core.models;

import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.Default;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.injectorspecific.ValueMapValue;

@Model(adaptables = Resource.class,
        defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL)
public class TagPageModel {

    @ValueMapValue
    @Default(values = "Yes Title")
    private String title;

    @ValueMapValue
    @Default(values = " yes Description")
    private String description;

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

}
