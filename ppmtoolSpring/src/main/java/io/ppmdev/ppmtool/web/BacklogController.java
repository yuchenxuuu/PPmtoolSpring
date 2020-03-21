package io.ppmdev.ppmtool.web;


import io.ppmdev.ppmtool.domain.ProjectTask;
import io.ppmdev.ppmtool.exceptions.ProjectIdExceptions;
import io.ppmdev.ppmtool.service.ProjectTaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/backlog")
@CrossOrigin
public class BacklogController {

    @Autowired
    private ProjectTaskService ptservice;



    @PostMapping("/{backlog_id}")
    public ResponseEntity<?> addtasktobacklog(@Valid @RequestBody ProjectTask projecttask, BindingResult result, @PathVariable String backlog_id){
        ProjectTask projecttask1 = ptservice.addProjectTask(backlog_id, projecttask);
        if(projecttask1 == null){
            throw new ProjectIdExceptions("This is an error");
        }
        return new ResponseEntity<ProjectTask>(projecttask1, HttpStatus.CREATED);
    }

    @GetMapping("/{backlog_id}")
    public Iterable<ProjectTask> getProjectBacklog(@PathVariable String backlog_id){

        return ptservice.findBacklogById(backlog_id);

    }

    @GetMapping("/{backlog_id}/{pt_id}")
    public ResponseEntity<?> getProjectTask(@PathVariable String backlog_id, @PathVariable String pt_id){
        ProjectTask projectTask = ptservice.findPTByProjectSequence(backlog_id, pt_id);
        return new ResponseEntity<ProjectTask>( projectTask, HttpStatus.OK);
    }


    @PatchMapping("/{backlog_id}/{pt_id}")
    public ResponseEntity<?> updateProjectTask(@Valid @RequestBody ProjectTask projectTask, BindingResult result,
                                               @PathVariable String backlog_id, @PathVariable String pt_id ){


        ProjectTask updatedTask = ptservice.updateByProjectSequence(projectTask,backlog_id,pt_id);

        return new ResponseEntity<ProjectTask>(updatedTask,HttpStatus.OK);

    }


    @DeleteMapping("/{backlog_id}/{pt_id}")
    public ResponseEntity<?> deleteProjectTask(@PathVariable String backlog_id, @PathVariable String pt_id){
        ptservice.deletePTByProjectSequence(backlog_id, pt_id);

        return new ResponseEntity<String>("Project Task "+pt_id+" was deleted successfully", HttpStatus.OK);
    }




}
