package com.example.toytinyurlservice.dao;

import com.example.toytinyurlservice.model.UrlMapping;
import org.apache.commons.dbcp2.BasicDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionStage;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


@Component
public class PostgresDAO {
  private final JdbcTemplate jdbcTemplate;
  private final ExecutorService executorService ;

  @Autowired
  public PostgresDAO(BasicDataSource basicDataSource, @Value("${postgres.jdbc.pool.size}") int poolSize){
    jdbcTemplate = new JdbcTemplate(basicDataSource);
    executorService =  Executors.newFixedThreadPool(poolSize);
  }

  public <T> CompletableFuture<T> queryForObject(String sqlQuery , RowMapper<T> rm, Object... args){
    return CompletableFuture.supplyAsync( () -> this.jdbcTemplate.queryForObject(sqlQuery, rm, args)
    , this.executorService);
  }

  public CompletionStage<Integer> update(String sqlQuery, Object... args){
    return CompletableFuture.supplyAsync(() -> this.jdbcTemplate.update(sqlQuery, args), this.executorService);
  }

  public CompletionStage<int[]> batchUpdate(String sqlQuery){
    return CompletableFuture.supplyAsync(() -> this.jdbcTemplate.batchUpdate(sqlQuery), this.executorService);
  }





}
