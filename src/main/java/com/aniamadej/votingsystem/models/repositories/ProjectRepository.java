package com.aniamadej.votingsystem.models.repositories;
import com.aniamadej.votingsystem.models.dtos.ProjectWithVotesDto;
import com.aniamadej.votingsystem.models.entities.Project;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ProjectRepository extends JpaRepository<Project, Long> {
    List<Project> findAllByOrderByProjectName();

    @Query("SELECT new com.aniamadej.votingsystem.models.dtos.ProjectWithVotesDto(p.projectName, p.projectDescription, p.active, sum(case when v.voteValue>0 then 1 else 0 end), sum(case when v.voteValue<=0 then 1 else 0 end)) from Project p left join Vote v on p.id=v.project where p.id=:projectId")
    ProjectWithVotesDto getProjectWithVotesDto(@Param("projectId") Long projectId);

}
