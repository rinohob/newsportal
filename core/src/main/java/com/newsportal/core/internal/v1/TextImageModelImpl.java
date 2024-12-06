package com.newsportal.core.internal.v1;


import com.newsportal.core.models.TextImageModel;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.injectorspecific.ValueMapValue;

@Model(adaptables = Resource.class,
        adapters = TextImageModel.class,
        resourceType = { TextImageModelImpl.RESOURCE_TYPE },
        defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL)
public class TextImageModelImpl implements TextImageModel {

    protected static final String RESOURCE_TYPE = "newsportal/components/text-and-image";

    @ValueMapValue
    private String title;

    @ValueMapValue
    private String description;

    @Override
    public String getTitle() {
        return title;
    }

    @Override
    public String getDescription() {
        return description;
    }
}
