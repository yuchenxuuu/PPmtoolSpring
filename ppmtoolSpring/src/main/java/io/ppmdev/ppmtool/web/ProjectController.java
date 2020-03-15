package io.ppmdev.ppmtool.web;

import io.ppmdev.ppmtool.domain.Project;
import io.ppmdev.ppmtool.exceptions.ProjectIdExceptions;
import io.ppmdev.ppmtool.service.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/project")
@CrossOrigin
public class ProjectController {

    @Autowired
    private ProjectService projectService;

    @PostMapping("")
    public ResponseEntity<?> createNewProject(@Valid @RequestBody Project project, BindingResult result){
        if(result.hasErrors()){
            Map<String, String> errorMap = new HashMap<>();
            for(FieldError err:result.getFieldErrors()){
                errorMap.put(err.getField(),err.getDefaultMessage());
            }
            return new ResponseEntity<Map<String, String>>(errorMap, HttpStatus.BAD_REQUEST);
        }
        Project projectUpdated = projectService.saveOrUpdateProject(project);
        return new ResponseEntity<Project>(project, HttpStatus.CREATED);
    }

    @GetMapping("/{projectName}")
    public ResponseEntity<?> getProjectByName(@PathVariable String projectName){
        Project project = projectService.findProjectByProjectName(projectName);
        if(project == null) throw new ProjectIdExceptions("Project name doesn't exist.");
        return new ResponseEntity<Project>(project, HttpStatus.OK);
    }

    @GetMapping("/{projectId}")
    public ResponseEntity<?> getProjectById(@PathVariable String projectId){
        Project project = projectService.findProjectByProjectName(projectId);
        if(project == null) throw new ProjectIdExceptions("Project name doesn't exist.");
        return new ResponseEntity<Project>(project, HttpStatus.OK);
    }

    @GetMapping("/all")
    public Iterable<Project> getAllProjects(){
        return projectService.findAllProjects();
    }
    @DeleteMapping("/{projectName}")
    public ResponseEntity<?> deleteProject(@PathVariable String projectName) {
        projectService.deleteProjectByName(projectName);
        return new ResponseEntity<String>("Project"+projectName +" was deleted!", HttpStatus.OK);
    }

}
