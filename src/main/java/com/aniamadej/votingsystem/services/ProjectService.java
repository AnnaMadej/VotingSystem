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
    private ModelMapper modelMapper;
    private ProjectMapperService projectMapperService;

    @Autowired
    public ProjectService(ProjectRepository projectRepository, VotersRepository votersRepository, VoteRepository voteRepository, ModelMapper modelMapper, ProjectMapperService projectMapperService) {
        this.projectRepository = projectRepository;
        this.votersRepository = votersRepository;
        this.voteRepository = voteRepository;
        this.modelMapper = modelMapper;
        this.projectMapperService = projectMapperService;
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

    public String vote(Long projectId, Long voterId, Integer voteValue){
        Vote vote = new Vote();

        Optional<Voter> optionalVoter = votersRepository.findById(voterId);
        Optional<Project> optionalProject = projectRepository.findById(projectId);

        if (!optionalProject.isPresent()) return "invalid project";
            Project project = optionalProject.get();
            project.addVote(vote);

        if(!optionalProject.get().isActive()) return "this project is not active!";

        if (!optionalVoter.isPresent())  return  "invalid voter";
            vote.setVoter(optionalVoter.get());

        if (voteRepository.existsByVoterAndProject(vote.getVoter(), vote.getProject())) return "multiple vote!";

        vote.setVoteValue(voteValue);
        voteRepository.save(vote);
        return "OK";
    }

    public String deactivateProject(Long projectId) {
        Optional<Project> optionalProject = projectRepository.findById(projectId);


        if (!optionalProject.isPresent()) {
            return "invalid projectId";
        }
        Project project = optionalProject.get();
        project.setActive(false);
        projectRepository.save(project);
        return "OK";

    }

    public ProjectWithVotesDto getProject(Long projectId) {
        ProjectWithVotesDto projectWithVotesDto = new ProjectWithVotesDto();
        Optional<Project> optionalProject = projectRepository.findById(projectId);
        if (optionalProject.isPresent()){
            projectWithVotesDto = projectMapperService.mapProject(optionalProject.get());
        }
        return projectWithVotesDto;
    }
}
