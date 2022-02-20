package com.example.toytinyurlservice.model;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
public class UrlMapping {

  private String shortURL;
  private String longURL;
  private LocalDateTime createdAt;
}
