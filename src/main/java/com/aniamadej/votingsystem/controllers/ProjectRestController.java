package com.aniamadej.votingsystem.controllers;

import com.aniamadej.votingsystem.models.dtos.ProjectDto;
import com.aniamadej.votingsystem.models.dtos.VoteDto;
import com.aniamadej.votingsystem.services.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ProjectRestController {

    @Autowired
    public ProjectService projectService;

    @GetMapping("/api/projects")
    public ResponseEntity<List<ProjectDto>> getProjects(){
        return ResponseEntity.ok().body( projectService.getProjects());
    }

    @PostMapping("/api/vote")
    public ResponseEntity<String> vote(@RequestParam ("projectId") Long projectId, @RequestParam ("voterId") Long voterId, @RequestParam("voteValue") Integer voteValue){

        String message = projectService.vote(projectId, voterId, voteValue);
        if (message.equals("OK")) return ResponseEntity.ok().body(message);
        return ResponseEntity.badRequest().body(message);
    }

    @PutMapping ("/api/deactivateProject/{projectId}")
    public ResponseEntity<String> deactivate(@PathVariable ("projectId") Long projectId){
        String message = projectService.deactivateProject(projectId);
        if (message.equals("OK")) return ResponseEntity.ok().body(message);
        return ResponseEntity.badRequest().body(message);
    }

    @GetMapping ("/api/project/{projectId}")
    public ResponseEntity<ProjectDto> getProjject(@PathVariable("projectId") Long projectId) {
        ProjectDto projectDto = projectService.getProject(projectId);

        if(projectDto.getId()==null) return ResponseEntity.badRequest().body(projectDto);
        return ResponseEntity.ok().body(projectDto);
    }
}
