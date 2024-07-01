package com.sid.runners.run;

import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

// It is used to indicate that the class is a Data Access Object (DAO) or a repository.
@Repository
public class RunRepo {


    private List<Run> runs = new ArrayList<>();


    List<Run> findAll(){
        return runs;
    }

    //Optional -> container object which may or may not contain null
    //if the value is present => isPresent() return true
    //Way to deal with objects
    Optional<Run> findById(Integer id){
        return runs.stream()
                .filter(run -> run.id() == id)
                .findFirst();
    }

    void create(Run run){
        runs.add(run);
    }


    void update(Run run, Integer id) {
        Optional<Run> existingRun = findById(id);
        if (existingRun.isPresent()) {
            runs.set(runs.indexOf(existingRun.get()), run);
        }
    }

    void delete(Integer id){
        runs.removeIf(run -> run.id().equals(id));
    }

    //To mark a method that should be executed after the DI uis done
    //@PostConstruct will run once all the bean properties have been set.
    @PostConstruct
    private void init(){
        runs.add(new Run(1,"Monday Run",
                LocalDateTime.now(),LocalDateTime.now().plus(30,
                ChronoUnit.MINUTES),
                3,
                Location.OUTDOOR));

        runs.add(new Run(2,"Tues Run",
                LocalDateTime.now(),LocalDateTime.now().plus(60,
                ChronoUnit.MINUTES),
                3,
                Location.INDOOR));

        runs.add(new Run(3,"Wed Run",
                LocalDateTime.now(),LocalDateTime.now().plus(40,
                ChronoUnit.MINUTES),
                3,
                Location.INDOOR));


        runs.add(new Run(4,"Thursday Run",
                LocalDateTime.now(),LocalDateTime.now().plus(20,
                ChronoUnit.MINUTES),
                3,
                Location.OUTDOOR));

        runs.add(new Run(5,"Friday Run",
                LocalDateTime.now(),LocalDateTime.now().plus(70,
                ChronoUnit.MINUTES),
                3,
                Location.OUTDOOR));

        runs.add(new Run(6,"Sat Run",
                LocalDateTime.now(),LocalDateTime.now().plus(90,
                ChronoUnit.MINUTES),
                3,
                Location.OUTDOOR));


        runs.add(new Run(7,"Sunday Run",
                LocalDateTime.now(),LocalDateTime.now().plus(44,
                ChronoUnit.MINUTES),
                3,
                Location.INDOOR));


        runs.add(new Run(8,"Fun Run",
                LocalDateTime.now(),LocalDateTime.now().plus(78,
                ChronoUnit.MINUTES),
                3,
                Location.INDOOR));


        runs.add(new Run(9,"Rainy Run",
                LocalDateTime.now(),LocalDateTime.now().plus(39,
                ChronoUnit.MINUTES),
                3,
                Location.INDOOR));

        runs.add(new Run(10,"Windy Run",
                LocalDateTime.now(),LocalDateTime.now().plus(77,
                ChronoUnit.MINUTES),
                3,
                Location.OUTDOOR));

    }


}
