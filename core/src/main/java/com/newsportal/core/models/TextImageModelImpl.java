//package com.newsportal.core.models;
//
//import org.apache.sling.api.resource.Resource;
//import org.apache.sling.models.annotations.DefaultInjectionStrategy;
//import org.apache.sling.models.annotations.Model;
//import org.apache.sling.models.annotations.injectorspecific.ValueMapValue;
//
//@Model(adaptables = Resource.class,
//        adapters = TextImageModel.class,
//        defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL)
//public class TextImageModelImpl implements TextImageModel {
//
//    @ValueMapValue
//    private String title;
//
//    @ValueMapValue
//    private String description;
//
//    @Override
//    public String getTitle() {
//        return title;
//    }
//
//    @Override
//    public String getDescription() {
//        return description;
//    }
//}
