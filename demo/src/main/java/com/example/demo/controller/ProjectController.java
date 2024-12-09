package com.example.demo.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.Token;
import com.example.demo.dto.project.ProjectListDto;
import com.example.demo.interfaces.ProjectInterface;
import com.example.demo.model.ProjectModel;

@RestController
@RequestMapping("/project")
public class ProjectController
{
    @Autowired
    ProjectInterface ProjectServ;

    @GetMapping
    public ResponseEntity<List<ProjectListDto>> GetUserProjects(@RequestAttribute Token token)
    {
        List<ProjectModel> results = ProjectServ.getUserProjects(token.userId());

        List<ProjectListDto> ret = new ArrayList<>();

        results.forEach((item) -> 
        {
            ret.add(new ProjectListDto(item.getId_project(), item.getName(), item.getDescription(), item.getImage()));
        });

        return new ResponseEntity<>(ret, HttpStatus.OK);
    }
}
