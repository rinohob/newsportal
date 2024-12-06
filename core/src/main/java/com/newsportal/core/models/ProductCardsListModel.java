package com.newsportal.core.models;
import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.injectorspecific.ValueMapValue;

@Model(
        adaptables = {Resource.class, SlingHttpServletRequest.class},
        defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL
)
public class ProductCardsListModel {

    @ValueMapValue
    private static String productExpiry;

    @ValueMapValue
    private String productPrice ;

    @ValueMapValue
    private String productImage;


    @ValueMapValue
    private String productColour ;

    @ValueMapValue
    private String[] productTags;

    public static String getProductExpiry() {
        return productExpiry;
    }

    public String getProductPrice() {
        return productPrice;
    }

    public String getProductImage() {
        return productImage;
    }

    public String getProductColour() {
        return productColour;
    }

    public String[] getProductTags() {
        return productTags;
     }
}