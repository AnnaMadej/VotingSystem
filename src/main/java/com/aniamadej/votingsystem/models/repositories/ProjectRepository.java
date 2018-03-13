package com.aniamadej.votingsystem.models.repositories;

import com.aniamadej.votingsystem.models.entities.Project;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProjectRepository extends JpaRepository<Project, Long> {

    List<Project> findAllByOrderByProjectName();
}
