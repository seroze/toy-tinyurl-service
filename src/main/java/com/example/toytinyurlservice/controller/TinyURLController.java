package com.example.toytinyurlservice.controller;

import com.example.toytinyurlservice.service.MapperService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.CompletionStage;

@RestController
@RequestMapping("/tinyURL")
public class TinyURLController {
  private final MapperService mapperService;

  @Autowired
  public TinyURLController(MapperService mapperService){
    this.mapperService = mapperService;
  }

  @GetMapping("/fetchLongURL")
  public CompletionStage<ResponseEntity<String>> getLongURL(@RequestBody String shortURL){//read request
    //fetch in database
    System.out.println( " request received for shortURL : "+shortURL );
    return mapperService.getLongURL(shortURL).thenApply(  longURL -> {
      return new ResponseEntity<>(longURL, HttpStatus.FOUND);
    });
  }

  @PostMapping("/shorten")
  public CompletionStage<String> createShortURL(@RequestBody  String longURL){//write request
    //insert into database
    System.out.println( " request received for shortURL : "+longURL );

    return mapperService.insertLongURL(longURL);
  }

}
