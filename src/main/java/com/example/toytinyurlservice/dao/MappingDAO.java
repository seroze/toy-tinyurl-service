package com.example.toytinyurlservice.dao;

import com.example.toytinyurlservice.model.UrlMapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletionStage;

@Component
public class MappingDAO {
  private final PostgresDAO postgresDAO;

  private final String insertQuery = "insert into mappings(shorturl, longurl, createdat) values( ? , ? , now() ) ";
  private final String selectQuery = "select * from mappings where shorturl = ? ";

  @Autowired
  public MappingDAO(PostgresDAO postgresDAO){
    this.postgresDAO = postgresDAO;

  }

  public CompletionStage<String> insertMapping(@NonNull UrlMapping urlMapping){
//    List<Object> args = new ArrayList<>();
//    args.add(urlMapping.getShortURL());
//    args.add(urlMapping.getLongURL());
    String insertQuery = "insert into mappings(shorturl, longurl, createdat) values(  '"+ urlMapping.getShortURL() + "' , '" + urlMapping.getLongURL() +"' , "+ "now() ) ";

    //return postgresDAO.update(insertQuery, args).thenApply( var -> urlMapping.getShortURL());
    System.out.println(insertQuery+" insertQuery");
    return postgresDAO.update(insertQuery).thenApply( var -> urlMapping.getShortURL());

  }

  public CompletionStage<UrlMapping> getLongURL(@NonNull String shortURL){
//    List<Object> args = new ArrayList<>();
//    args.add(shortURL);
    String selectQuery = "select * from mappings where shorturl =  '"+shortURL+"'";
    System.out.println(selectQuery+" selectQuery");
    return postgresDAO.queryForObject(selectQuery, this::extractLongURL );
  }

  private UrlMapping extractLongURL(ResultSet rs, int i) throws SQLException {
    return UrlMapping.builder()
        .shortURL(rs.getString("shorturl"))
        .longURL(rs.getString("longurl"))
        .createdAt(rs.getTimestamp("createdat").toLocalDateTime())
        .build();
  }

}
