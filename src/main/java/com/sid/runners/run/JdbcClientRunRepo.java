package com.sid.runners.run;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.simple.JdbcClient;
import org.springframework.stereotype.Repository;
import org.springframework.util.Assert;

import java.util.List;
import java.util.Optional;

// It is used to indicate that the class is a Data Access Object (DAO) or a repository.
@Repository
public class JdbcClientRunRepo {

    private static final Logger log = LoggerFactory.getLogger(JdbcClientRunRepo.class);

    private final JdbcClient jdbcClient;

    //DI -> JDBC Client Instance
    public JdbcClientRunRepo(JdbcClient jdbcClient){
        this.jdbcClient = jdbcClient;
    }

    public List<Run> findAll(){
        return jdbcClient.sql("select * from run")
                .query(Run.class)
                .list();
    }


    public Optional<Run> findById(Integer id){
        return jdbcClient.sql("select id," +
                        " title," +
                        "started_on," +
                        "completed_on," +
                        "miles," +
                        "location " +
                        "from Run WHERE id = :id")
                .param("id", id)
                .query(Run.class)
                .optional();
    }

    //To create, update and delete -> use update
    public void create(Run run){
        var updated =  jdbcClient.sql("INSERT INTO run(id,title,started_on,completed_on,miles,location)" +
                        "values(?,?,?,?,?,?)")
                .params(List.of(
                        run.id(),
                        run.title(),
                        run.startedOn(),
                        run.completedOn(),
                        run.miles(),
                        run.location().toString()))
                .update();
        //Update returns how many rows were affected

        Assert.state(updated==1,"Failed to update run"+ run.title());
    }

    public int count(){
        return jdbcClient.sql("select * from run").query().listOfRows().size();
    }

    public void saveAll(List<Run> runs){
        runs.stream().forEach(this::create);
    }


}
