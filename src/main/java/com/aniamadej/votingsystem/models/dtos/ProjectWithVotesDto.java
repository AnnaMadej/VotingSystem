package com.aniamadej.votingsystem.models.dtos;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProjectWithVotesDto extends ProjectDto {
    private int positiveVotes = 0;
    private int negativeVotes = 0;
}
