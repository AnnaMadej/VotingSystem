package com.aniamadej.votingsystem.models.repositories;

import com.aniamadej.votingsystem.models.entities.Project;
import com.aniamadej.votingsystem.models.entities.Vote;
import com.aniamadej.votingsystem.models.entities.Voter;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VoteRepository extends JpaRepository<Vote, Long> {
    boolean existsByVoterAndProject(Voter voter, Project project);
}
