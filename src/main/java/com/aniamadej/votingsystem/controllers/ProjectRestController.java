package com.aniamadej.votingsystem.controllers;

import com.aniamadej.votingsystem.models.dtos.ProjectDto;
import com.aniamadej.votingsystem.models.dtos.ProjectWithVotesDto;
import com.aniamadej.votingsystem.models.dtos.VoteDto;
import com.aniamadej.votingsystem.services.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ProjectRestController {


    private ProjectService projectService;

    @Autowired
    public ProjectRestController(ProjectService projectService) {
        this.projectService = projectService;
    }

    @GetMapping("/api/projects")
    public ResponseEntity<List<ProjectDto>> getProjects(){
        return ResponseEntity.ok().body( projectService.getProjects());
    }

    @PostMapping("/api/project/{projectId}/vote")
    public ResponseEntity<Boolean> vote(@PathVariable ("projectId") Long projectId, @RequestParam ("voterId") Long voterId, @RequestParam("voteValue") Integer voteValue){

        Boolean result = projectService.vote(projectId, voterId, voteValue);
        return ResponseEntity.ok().body(result);
    }

    @PutMapping ("/api/project/{projectId}/deactivate")
    public ResponseEntity<Boolean> deactivate(@PathVariable ("projectId") Long projectId){
        Boolean result = projectService.deactivateProject(projectId);
        if (!result) {
            return ResponseEntity.badRequest().body(result);
        }
        return ResponseEntity.ok().body(result);
    }

    @GetMapping ("/api/project/{projectId}")
    public ResponseEntity<ProjectWithVotesDto> getProject(@PathVariable("projectId") Long projectId) {
        ProjectWithVotesDto git diffprojectWithVotesDto = projectService.getProject(projectId);

        if (projectWithVotesDto.getProjectName() != null) {
            return ResponseEntity.ok().body(projectWithVotesDto);
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(projectWithVotesDto);
    }

}
