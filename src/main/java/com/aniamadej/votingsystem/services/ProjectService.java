package com.aniamadej.votingsystem.services;

import com.aniamadej.votingsystem.models.dtos.ProjectDto;
import com.aniamadej.votingsystem.models.dtos.ProjectWithVotesDto;
import com.aniamadej.votingsystem.models.entities.Project;
import com.aniamadej.votingsystem.models.entities.Vote;
import com.aniamadej.votingsystem.models.entities.Voter;
import com.aniamadej.votingsystem.models.repositories.ProjectRepository;
import com.aniamadej.votingsystem.models.repositories.VoteRepository;
import com.aniamadej.votingsystem.models.repositories.VotersRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class ProjectService {

    private ProjectRepository projectRepository;
    private VotersRepository votersRepository;
    private VoteRepository voteRepository;
    private ProjectMapper<ProjectWithVotesDto> fullProjectMapperService;
    private ModelMapper modelMapper;

    @Autowired
    public ProjectService(ProjectRepository projectRepository, VotersRepository votersRepository, VoteRepository voteRepository, FullProjectMapperService fullProjectMapperService, ModelMapper modelMapper) {
        this.projectRepository = projectRepository;
        this.votersRepository = votersRepository;
        this.voteRepository = voteRepository;
        this.fullProjectMapperService = fullProjectMapperService;
        this.modelMapper = modelMapper;
    }




    public List<ProjectDto> getProjects(){
        List<Project> projects = projectRepository.findAllByOrderByProjectName();
        List<ProjectDto> projectDtos = new ArrayList<>();
        for (Project project : projects
             ) {
            projectDtos.add(modelMapper.map(project, ProjectDto.class));
        }

      /*  Collections.sort(projectDtos, new Comparator<ProjectDto>() {
            @Override
            public int compare(ProjectDto o1, ProjectDto o2) {
                return o1.getProjectName().compareToIgnoreCase(o2.getProjectName());
            }
        }); */
        return projectDtos;
    }

    public Boolean vote(Long projectId, Long voterId, Integer voteValue){
        Vote vote = new Vote();

        Optional<Voter> optionalVoter = votersRepository.findById(voterId);
        Optional<Project> optionalProject = projectRepository.findById(projectId);

        if (!optionalProject.isPresent()) return false;
            Project project = optionalProject.get();
            project.addVote(vote);

        if(!optionalProject.get().isActive()) return false;

        if (!optionalVoter.isPresent())  return  false;
            vote.setVoter(optionalVoter.get());

        if (voteRepository.existsByVoterAndProject(vote.getVoter(), vote.getProject())) return false;

        vote.setVoteValue(voteValue);
        voteRepository.save(vote);
        return true;
    }

    public Boolean deactivateProject(Long projectId) {
        Optional<Project> optionalProject = projectRepository.findById(projectId);

        if (!optionalProject.isPresent()) {
            return false;
        }
        Project project = optionalProject.get();
        project.setActive(false);
        projectRepository.save(project);
        return true;

    }

    public ProjectWithVotesDto getProject(Long projectId) {
        ProjectWithVotesDto projectWithVotesDto = new ProjectWithVotesDto();
        if (projectRepository.existsById(projectId)){
            projectWithVotesDto = projectRepository.getProjectWithVotesDto(projectId);
        }
        return projectWithVotesDto;
    }
}
