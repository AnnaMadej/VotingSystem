package com.aniamadej.votingsystem.services;

import com.aniamadej.votingsystem.models.dtos.ProjectDto;
import com.aniamadej.votingsystem.models.dtos.ProjectWithVotesDto;
import com.aniamadej.votingsystem.models.entities.Project;
import com.aniamadej.votingsystem.models.entities.Vote;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

@Service
public class ProjectMapperService {

    private ModelMapper modelMapper;

    @Autowired
    public ProjectMapperService(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    public ProjectWithVotesDto mapProject(Project project){
        ProjectWithVotesDto projectWithVotesDto = modelMapper.map(project, ProjectWithVotesDto.class);
        for (Vote vote : project.getVotes()) {
            if(vote.getVoteValue()>0) projectWithVotesDto.incPositives();
            else projectWithVotesDto.incNegatives();
        }
      return projectWithVotesDto;
    }
}
