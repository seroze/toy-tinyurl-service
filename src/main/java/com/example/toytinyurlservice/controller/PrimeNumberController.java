package com.example.toytinyurlservice.controller;

import okhttp3.Call;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;


@RestController
@RequestMapping("/tinyURL")
public class PrimeNumberController {



  @GetMapping("/isPrime/{num}")
  public String isPrime(@PathVariable Integer num) throws IOException {

//    OkHttpClient client = new OkHttpClient();
//    Request request = new Request.Builder().url("http://localhost:9090/helloWorld/v1/isPrime/"+num).build();
//    Call call = client.newCall(request);
//    Response response = call.execute();

    //generate random number
    int randomNumber = (int)(100 * Math.random());
    randomNumber%=2;
    if(randomNumber==0){
      OkHttpClient client = new OkHttpClient();
      Request request = new Request.Builder().url("http://localhost:9090/helloWorld/v1/isPrime/"+num).build();
      Call call = client.newCall(request);
      Response response = call.execute();
      return response.body().string();

    }else{
      OkHttpClient client = new OkHttpClient();
      Request request = new Request.Builder().url("http://localhost:9090/helloWorld/v1/isPrime/"+num).build();
      Call call = client.newCall(request);
      Response response = call.execute();
      return response.body().string();

    }

  }
}
