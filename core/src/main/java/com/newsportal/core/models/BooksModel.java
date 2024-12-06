package com.newsportal.core.models;

import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.injectorspecific.Self;
import org.apache.sling.models.annotations.injectorspecific.ValueMapValue;

@Model(adaptables=Resource.class, defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL)
public class BooksModel {


 @ValueMapValue
 private String text;

 @ValueMapValue
 private String desc;

 @Self
 SportsBooksModel SportsBooksModel;

 public String getText() {
  return text;
 }

 public String getDesc() {
  return desc;
 }
}
