package io.ppmdev.ppmtool.repositories;

import io.ppmdev.ppmtool.domain.Backlog;
import io.ppmdev.ppmtool.domain.Project;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BacklogRepo extends CrudRepository<Backlog, Long> {

    Backlog findByProjectIdentifier(String Identifier);
}
