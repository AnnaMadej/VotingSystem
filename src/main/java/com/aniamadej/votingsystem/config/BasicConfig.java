package com.aniamadej.votingsystem.config;

import com.aniamadej.votingsystem.models.dtos.ProjectDto;
import com.aniamadej.votingsystem.models.entities.Project;
import org.modelmapper.Condition;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BasicConfig {

    @Bean
    public ModelMapper modelMapper(){
        return new ModelMapper();
    }

}
