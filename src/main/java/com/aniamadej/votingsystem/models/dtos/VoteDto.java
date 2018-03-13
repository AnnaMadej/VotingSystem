package com.aniamadej.votingsystem.models.dtos;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class VoteDto {

    private Long id;
    private Long projectId;
    private Long voterId;
}
