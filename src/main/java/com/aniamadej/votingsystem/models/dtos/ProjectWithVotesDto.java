package com.aniamadej.votingsystem.models.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProjectWithVotesDto {
    private String projectName;
    private String projectDescription;
    private boolean active;
    private int positiveVotes = 0;
    private int negativeVotes = 0;

    public ProjectWithVotesDto(String projectName, String projectDescription, boolean active, long positiveVotes, long negativeVotes) {
        this.projectName = projectName;
        this.projectDescription = projectDescription;
        this.active = active;
        this.positiveVotes = (int)positiveVotes;
        this.negativeVotes = (int)negativeVotes;
    }
}
