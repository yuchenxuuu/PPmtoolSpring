package io.ppmdev.ppmtool.service;

import io.ppmdev.ppmtool.domain.Backlog;
import io.ppmdev.ppmtool.domain.Project;
import io.ppmdev.ppmtool.domain.User;
import io.ppmdev.ppmtool.exceptions.ProjectIdExceptions;
import io.ppmdev.ppmtool.exceptions.ProjectNameExceptions;
import io.ppmdev.ppmtool.repositories.BacklogRepo;
import io.ppmdev.ppmtool.repositories.Repo;
import io.ppmdev.ppmtool.repositories.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProjectService {
    @Autowired
    private Repo repo;

    @Autowired
    private BacklogRepo backlogrepo;

    @Autowired
    private UserRepo userrepo;

    public Project saveOrUpdateProject(Project project, String username) {
        try{
            User user = userrepo.findByUsername(username);
            project.setUser(user);
            project.setProjectLeader(user.getUsername());
            project.setProjectIdentifier(project.getProjectIdentifier().toUpperCase());
            if(project.getId() == null){
                Backlog backlog = new Backlog();
                project.setBacklog(backlog);
                backlog.setProject(project);
                backlog.setProjectIdentifier(project.getProjectIdentifier().toUpperCase());
            }

            if(project.getId() != null){
                project.setBacklog(backlogrepo.findByProjectIdentifier(project.getProjectIdentifier().toUpperCase()));
            }
            return repo.save(project);

        }catch(Exception e){
            throw new ProjectIdExceptions("Error when create the project");
        }

    }



    public Project findProjectByProjectId(String projectId){
        Project project = repo.findByProjectIdentifier(projectId.toUpperCase());
        if(project == null){
            throw new ProjectIdExceptions("Cannot find project that doesn't exist.");
        }
        return project;
    }


    public Iterable<Project> findAllProjects() {
        return repo.findAll();
    }


    public void deleteProjectByIdentifier(String projectId){
        Project project = repo.findByProjectIdentifier(projectId);
        if(project == null){
            throw new ProjectNameExceptions("Cannot delete project that doesn't exist.");
        }
        repo.delete(project);
    }




}
