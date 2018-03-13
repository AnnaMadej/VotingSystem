package com.aniamadej.votingsystem;

import com.aniamadej.votingsystem.models.entities.Project;
import com.aniamadej.votingsystem.models.entities.Voter;
import com.aniamadej.votingsystem.models.repositories.ProjectRepository;
import com.aniamadej.votingsystem.models.repositories.VotersRepository;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertNotNull;

@RunWith(SpringRunner.class)
@SpringBootTest
public class VotingsystemApplicationTests {

	@Autowired
	private VotersRepository votersRepository;

	@Autowired
	private ProjectRepository projectRepository;


	@Test
	public void contextLoads() {
		assertNotNull(votersRepository);
	}


	@Test
	public void contextLoads2() {
		assertNotNull(projectRepository);
	}

	@Test
    @Ignore
    public void createVoters(){
	    for (int i=1; i<5; i++){
	        votersRepository.save(new Voter("voter " + i));
        }
    }

    @Test
    @Ignore
    public void createProjects(){
        for (int i=5; i>=1; i--){
            projectRepository.save(new Project("Project " + i, "Description: " + i, true));
        }
    }



}
