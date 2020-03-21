package io.ppmdev.ppmtool.service;


import io.ppmdev.ppmtool.domain.Backlog;
import io.ppmdev.ppmtool.domain.Project;
import io.ppmdev.ppmtool.domain.ProjectTask;
import io.ppmdev.ppmtool.exceptions.ProjectIdExceptions;
import io.ppmdev.ppmtool.repositories.BacklogRepo;
import io.ppmdev.ppmtool.repositories.ProjectTaskRepo;
import io.ppmdev.ppmtool.repositories.Repo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProjectTaskService {

    @Autowired
    private BacklogRepo backlogrepo;
    @Autowired
    private ProjectTaskRepo projecttaskrepo;
    @Autowired
    private Repo repo;

    public ProjectTask addProjectTask(String projectIdentifier, ProjectTask projectTask){
        Backlog backlog = backlogrepo.findByProjectIdentifier(projectIdentifier);

        projectTask.setBacklog(backlog);

        Integer BacklogSequence = backlog.getPTSequence();

        BacklogSequence++;
        backlog.setPTSequence(BacklogSequence);
        //add sequence to a project task
        projectTask.setProjectSequence(projectIdentifier+"-"+BacklogSequence);
        projectTask.setProjectIdentifier(projectIdentifier);
        //Set initial status
        if(projectTask.getStatus()==""|| projectTask.getStatus()==null){
            projectTask.setStatus("TO_DO");
        }
        if(projectTask.getPriority() == 0 || projectTask.getStatus() == null){
            projectTask.setPriority(3);
        }
        return projecttaskrepo.save(projectTask);
    }

    public Iterable<ProjectTask>findBacklogById(String id){
        Project project = repo.findByProjectIdentifier(id);

        if(project==null){
            throw new ProjectIdExceptions("Project with ID: '"+id+"' does not exist");
        }
        return projecttaskrepo.findByProjectIdentifierOrderByPriority(id);
    }

    public ProjectTask findPTByProjectSequence(String backlog_id, String pt_id){

        //make sure we are searching on an existing backlog
        Backlog backlog = backlogrepo.findByProjectIdentifier(backlog_id);
        if(backlog==null){
            throw new ProjectIdExceptions("Project with ID: '"+backlog_id+"' does not exist");
        }

        //make sure that our task exists
        ProjectTask projectTask = projecttaskrepo.findByProjectSequence(pt_id);

        if(projectTask == null){
            throw new ProjectIdExceptions("Project Task '"+pt_id+"' not found");
        }

        //make sure that the backlog/project id in the path corresponds to the right project
        if(!projectTask.getProjectIdentifier().equals(backlog_id)){
            throw new ProjectIdExceptions("Project Task '"+pt_id+"' does not exist in project: '"+backlog_id);
        }


        return projectTask;
    }

    public ProjectTask updateByProjectSequence(ProjectTask updatedTask, String backlog_id, String pt_id){
        ProjectTask projectTask = findPTByProjectSequence(backlog_id, pt_id);
        projectTask = updatedTask;

        return projecttaskrepo.save(projectTask);
    }


    public void deletePTByProjectSequence(String backlog_id, String pt_id){
        ProjectTask projectTask = findPTByProjectSequence(backlog_id, pt_id);

        projecttaskrepo.delete(projectTask);
//        ProjectTask projectTask2 = findPTByProjectSequence(backlog_id, pt_id);
//        System.out.println(projectTask2.getProjectSequence());
//        System.out.println(projecttaskrepo.findAll().toString());
//        System.out.println("1111111");
//        System.out.println(repo.findAll().getClass());
    }

}
