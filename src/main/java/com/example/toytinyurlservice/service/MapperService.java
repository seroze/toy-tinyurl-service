package com.example.toytinyurlservice.service;

import com.example.toytinyurlservice.dao.MappingDAO;
import com.example.toytinyurlservice.model.UrlMapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionStage;

@Component
public class MapperService {

  private final MappingDAO mappingDAO;
  private final int SIZE = 6;

  @Autowired
  public MapperService(MappingDAO mappingDAO){
    this.mappingDAO = mappingDAO;
  }

  public CompletionStage<String> getLongURL(String shortURL){
    return mappingDAO.getLongURL(shortURL).thenApply(UrlMapping::getLongURL);
  }

  public CompletionStage<String> insertLongURL(String longURL){
    return getRandomString(SIZE)
        .thenCompose( shortURL -> mappingDAO.insertMapping( UrlMapping.builder().shortURL("www."+shortURL+".com").longURL(longURL).build()));
  }

  private CompletionStage<String> getRandomString(int n){

    String sampleSpace = "ABCDEFGHIJKLMNOPQRSTUVWXYZ"
        + "0123456789"
        + "abcdefghijklmnopqrstuvxyz";

    StringBuilder sb = new StringBuilder();
    for(int i=0;i<n;++i){
      int idx = (int) (sampleSpace.length() * Math.random());
      sb.append(sampleSpace.charAt(idx));
    }
    return CompletableFuture.completedFuture(sb.toString());

  }
}
