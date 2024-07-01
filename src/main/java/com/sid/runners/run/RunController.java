package com.sid.runners.run;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.Iterator;
import java.util.List;
import java.util.Optional;


//Expect Response body - JSON
@RestController
@RequestMapping("/api/runs")
public class RunController {


    private final RunRepo runrepo;

    public RunController(RunRepo runrepo){
        this.runrepo =  runrepo;
    }


    @GetMapping("")
    List<Run> findAll(){
        return runrepo.findAll();

    }


//Intercepting the dynamic ids
    //Sending to the method
    //@PathVariable is used to send whatever is in the path to respective method
    @GetMapping("/{id}")
    Run findById(@PathVariable Integer id){
         Optional<Run> run = runrepo.findById(id);
         if(run.isEmpty()){
             throw new ResponseStatusException(HttpStatus.NOT_FOUND,"Run not found!!");
         }
         return run.get();
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("")
    void create(@RequestBody Run run){
        runrepo.create(run);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PutMapping("/{id}")
    void update(@RequestBody Run run,@PathVariable Integer id){
        runrepo.update(run,id);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{id}")
    void delete(@PathVariable Integer id)
    {
        runrepo.delete(id);
    }
}
