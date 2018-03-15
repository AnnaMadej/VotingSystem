package com.aniamadej.votingsystem.services;

import com.aniamadej.votingsystem.models.entities.Project;

public interface ProjectMapper<T> {
    T map(Project project);
}
