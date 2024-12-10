package com.newsportal.core.internal.v1;

import com.newsportal.core.models.ProductDetailModel;
import lombok.Getter;
import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.injectorspecific.ChildResource;
import org.apache.sling.models.annotations.injectorspecific.ValueMapValue;


@Model(adaptables = {Resource.class, SlingHttpServletRequest.class},
        adapters = ProductDetailModel.class,
        resourceType = {ProductDetailModelImpl.RESOURCE_TYPE},
      defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL
)
public class ProductDetailModelImpl implements ProductDetailModel {

    protected  static  final String RESOURCE_TYPE="newsportal/components/product-details";

    @ValueMapValue
    private String category;

    @ValueMapValue
    private String[] productTags;

    @ChildResource
    private Resource image;

    @ChildResource
    private Resource title;

    @ChildResource
    private Resource text;

    @ChildResource
    private Resource button;

    @Override
    public String getCategory() {
        return category;
    }

    @Override
    public String[] getProductTags() {
        return productTags;
    }

    @Override
    public String getImagePath() {
        return image != null ? image.getPath() : null;
    }

    @Override
    public String getTitle() {
        return title != null ? title.getValueMap().get("jcr:title", String.class) : null;
    }

    @Override
    public String getText() {
        return text != null ? text.getValueMap().get("text", String.class) : null;
    }

    @Override
    public String getButtonText() {
        return button != null ? button.getValueMap().get("text", String.class) : null;
    }

    @Override
    public String getButtonLink() {
        return button != null ? button.getValueMap().get("link", String.class) : null;
    }
}
