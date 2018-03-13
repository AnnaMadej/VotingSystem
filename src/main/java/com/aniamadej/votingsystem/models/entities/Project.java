package com.aniamadej.votingsystem.models.entities;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class Project {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String projectName;
    private String projectDescription;
    private boolean active;

    @OneToMany(mappedBy = "project", cascade = {CascadeType.MERGE, CascadeType.PERSIST} )
    private Set<Vote> votes;

    public Project(String projectName, String projectDescription, boolean active) {
        this.projectName = projectName;
        this.projectDescription = projectDescription;
        this.active = active;
    }

    public void addVote(Vote vote){
        this.getVotes().add(vote);
        vote.setProject(this);
    }
}
