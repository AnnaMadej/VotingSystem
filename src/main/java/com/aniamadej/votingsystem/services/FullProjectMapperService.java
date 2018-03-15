package com.aniamadej.votingsystem.services;

import com.aniamadej.votingsystem.models.dtos.ProjectWithVotesDto;
import com.aniamadej.votingsystem.models.entities.Project;
import com.aniamadej.votingsystem.models.entities.Vote;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FullProjectMapperService implements ProjectMapper {

    private ModelMapper modelMapper;

    @Autowired
    public FullProjectMapperService(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    @Override
    public ProjectWithVotesDto map(Project project){
        ProjectWithVotesDto projectWithVotesDto = modelMapper.map(project, ProjectWithVotesDto.class);
        int positiveVotes = 0;
        int negativeVotes = 0;
        for (Vote vote : project.getVotes()) {
            if(vote.getVoteValue()>0) positiveVotes++;
            else negativeVotes++;
        }
        projectWithVotesDto.setPositiveVotes(positiveVotes);
        projectWithVotesDto.setNegativeVotes(negativeVotes);
      return projectWithVotesDto;
    }
}
