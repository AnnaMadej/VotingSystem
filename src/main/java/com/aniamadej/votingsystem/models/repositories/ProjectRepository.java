package com.aniamadej.votingsystem.models.repositories;

import com.aniamadej.votingsystem.models.dtos.ProjectWithVotesDto;
import com.aniamadej.votingsystem.models.entities.Project;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ProjectRepository extends JpaRepository<Project, Long> {
    List<Project> findAllByOrderByProjectName();
    @Query("SELECT p.id, p.projectName, p.projectDescription, p.active, sum(case when v.voteValue>0 then 1 else 0 end) as positiveVotes, sum(case when v.voteValue<=0 then 1 else 0 end) as negativeVotes from Project p left join Vote v on p.id=v.project where p.id=1")
    ProjectWithVotesDto getProjectWithVotesDto();
}
