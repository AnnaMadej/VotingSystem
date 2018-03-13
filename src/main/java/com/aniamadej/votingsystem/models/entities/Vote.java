package com.aniamadej.votingsystem.models.entities;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.UniqueElements;

import javax.persistence.*;
import javax.validation.Constraint;
import java.util.Date;

@Getter
@Setter
@Entity
@NoArgsConstructor
@Table (uniqueConstraints = {@UniqueConstraint(columnNames = {"projectId", "voterId"})})
public class Vote {



    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @ManyToOne
    @JoinColumn(name = "projectId")
    private Project project;

    @ManyToOne
    @JoinColumn(name = "voterId")
    private Voter voter;

    private Integer voteValue;

    private Date added = new Date();

    public Vote(Project project, Voter voter, Integer voteValue) {
        this.project = project;
        this.voter = voter;
        this.voteValue = voteValue;
    }


}
