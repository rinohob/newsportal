// package com.newsportal.core.services;

// import com.fasterxml.jackson.databind.JsonNode;
// import com.fasterxml.jackson.databind.ObjectMapper;
// import com.newsportal.core.configs.WeatherUpdatesConfig;
// import org.apache.http.impl.client.CloseableHttpClient;
// import org.apache.http.impl.client.HttpClients;
// import org.osgi.service.component.annotations.Activate;
// import org.osgi.service.component.annotations.Component;
// import org.osgi.service.component.annotations.Modified;
// import org.osgi.service.metatype.annotations.Designate;
// import org.slf4j.Logger;
// import org.slf4j.LoggerFactory;

// @Component(service=weatherService.class)
// @Designate(ocd= WeatherUpdatesConfig.class)
// public class weatherService {
//     private static final Logger LOG = LoggerFactory.getLogger(weatherService.class);
//     private String update;
//     @Activate
//     @Modified
//     private void test(WeatherUpdatesConfig config){
//         update=config.morningUpdate();
//     }

//     public String getUpdate() {
//         return update;
//     }
//    private final CloseableHttpClient httpClient= HttpClients.createDefault();
//    private final  ObjectMapper objectMapper=new ObjectMapper();

//    public JsonNode fetchPosts() throws Exception{
//    }

   