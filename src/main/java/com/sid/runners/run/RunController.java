package com.sid.runners.run;

import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

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
             throw new RunNotFoundException();
         }
         return run.get();
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("")
    void create(@Valid @RequestBody Run run){
        runrepo.save(run);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PutMapping("/{id}")
    void update(@Valid @RequestBody Run run,@PathVariable Integer id){
        runrepo.save(run);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{id}")
    void delete(@PathVariable Integer id)
    {
        runrepo.delete(runrepo.findById(id).get());
    }

    @GetMapping("/location/{location}")
    List<Run> findByLocation(@PathVariable String location){
        return runrepo.findAllByLocation(location);

    }



}

