package com.aniamadej.votingsystem.models.dtos;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProjectWithVotesDto{
    private String projectName;
    private String projectDescription;
    private boolean active;
    private int positiveVotes = 0;
    private int negativeVotes = 0;
}
