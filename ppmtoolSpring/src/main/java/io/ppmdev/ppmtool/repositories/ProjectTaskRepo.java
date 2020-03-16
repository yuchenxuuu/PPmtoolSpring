package io.ppmdev.ppmtool.repositories;

import io.ppmdev.ppmtool.domain.Project;
import io.ppmdev.ppmtool.domain.ProjectTask;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProjectTaskRepo extends CrudRepository<ProjectTask, Long>{
}
