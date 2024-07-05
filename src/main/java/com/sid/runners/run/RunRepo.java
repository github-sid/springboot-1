package com.sid.runners.run;

import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.ListCrudRepository;

import java.util.List;

public interface RunRepo extends ListCrudRepository<Run, Integer> {

  //Could place all the custom methods here
    @Query("select * from run where location = :location")
    List<Run> findAllByLocation(String location);
}