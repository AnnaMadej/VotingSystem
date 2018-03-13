package com.aniamadej.votingsystem.models.repositories;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.aniamadej.votingsystem.models.entities.Voter;

@Repository
public interface VotersRepository extends JpaRepository<Voter, Long>{

}
