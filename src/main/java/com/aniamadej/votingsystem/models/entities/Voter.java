package com.aniamadej.votingsystem.models.entities;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;

@Setter
@Getter
@NoArgsConstructor
@Entity
public class Voter {
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Long id;
    private String voterName;

  /*  @OneToMany (mappedBy = "voter", cascade = {CascadeType.MERGE, CascadeType.PERSIST} )
    private Set<Vote> votes; */

    public Voter(String voterName) {
        this.voterName = voterName;
    }
}
