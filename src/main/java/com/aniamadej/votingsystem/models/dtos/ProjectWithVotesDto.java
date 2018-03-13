package com.aniamadej.votingsystem.models.dtos;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProjectWithVotesDto extends ProjectDto {
    private Integer positiveVotes = 0;
    private Integer negativeVotes = 0;

    public void incPositives() {
        this.setPositiveVotes(this.getPositiveVotes()+1);
    }
    public void incNegatives() {
        this.setNegativeVotes(this.getNegativeVotes()+1);
    }
}
