package com.aniamadej.votingsystem.models.dtos;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class ProjectDto {

    private Long id;
    private String projectName;
    private String projectDescription;
    private boolean active;

}
