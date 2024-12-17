package com.example.demo.controller;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.Token;
import com.example.demo.dto.project.NewProjectDto;
import com.example.demo.dto.project.NewProjectMessageDto;
import com.example.demo.dto.project.ProjectDataDto;
import com.example.demo.dto.project.ProjectFullDto;
import com.example.demo.dto.project.ProjectListDto;
import com.example.demo.dto.project.ProjectMessageDto;
import com.example.demo.dto.user.UserCommentDto;
import com.example.demo.interfaces.ProjectInterface;
import com.example.demo.model.InteractionModel;
import com.example.demo.model.ProjectGoalModel;
import com.example.demo.model.ProjectMemberModel;
import com.example.demo.model.ProjectMessageModel;
import com.example.demo.model.ProjectModel;
import com.example.demo.model.UserModel;
import com.example.demo.repositories.InteractionRepository;
import com.example.demo.repositories.ProjectGoalRepository;
import com.example.demo.repositories.ProjectMemberRepository;
import com.example.demo.repositories.ProjectMessageRepository;
import com.example.demo.repositories.ProjectRepository;
import com.example.demo.repositories.UserRepository;

@RestController
@RequestMapping("/project")
public class ProjectController
{
    @Autowired
    ProjectInterface ProjectServ;

    @Autowired
    ProjectRepository ProjectRep;

    @Autowired
    ProjectMemberRepository ProjectMemberRep;

    @Autowired
    ProjectGoalRepository ProjectGoalRep;

    @Autowired
    UserRepository UserRep;

    @Autowired
    ProjectMessageRepository ProjectMessageRep;

    @Autowired
    InteractionRepository InteractionRep;

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

    @GetMapping("/{id}")
    public ResponseEntity<ProjectFullDto> GetProject(@RequestAttribute Token token, @PathVariable Long id)
    {
        Optional<ProjectModel> Project = ProjectRep.findById(id);
        if(!Project.isPresent())
        {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }

        ProjectModel P = Project.get();

        List<ProjectMemberModel> Members = P.getMembers();
        boolean Access = false;

        for(int i = 0; i < Members.size(); ++i)
        {
            if(Members.get(i).getUser().getId_user() == token.userId())
            {
                Access = true;
                break;
            }
        }

        if(!Access)
        {
            return new ResponseEntity<>(null, HttpStatus.FORBIDDEN);
        }

        List<String> Goals = new ArrayList<>();
        List<UserCommentDto> Users = new ArrayList<>();
        List<ProjectMessageDto> Messages = new ArrayList<>();

        P.getGoals().forEach((item) ->
        {
            Goals.add(item.getDescription());
        });

        P.getMembers().forEach((item) ->
        {
            Users.add(new UserCommentDto
            (
                item.getUser().getId_user(),
                item.getUser().getName(),
                item.getUser().getInstructor(),
                item.getUser().getImage()
            ));
        });

        P.getMessages().forEach((item) ->
        {
            Messages.add(new ProjectMessageDto
            (
                item.getId_project_message(),
                item.getText(),
                item.getInteraction().getUser().getId_user()
            ));
        });

        ProjectFullDto ret = new ProjectFullDto
        (
            P.getId_project(),
            P.getName(),
            P.getDescription(),
            Goals,
            Users,
            Messages
        );

        return new ResponseEntity<>(ret, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<ProjectDataDto> CreateProject(@RequestAttribute Token token, @RequestBody NewProjectDto data)
    {
        ProjectModel Project = new ProjectModel();
        Project.setName(data.name());
        Project.setDescription(data.description());
        Project.setImage(data.image());

        Project = ProjectRep.save(Project);

        List<String> Goals = data.goals();
        for(int i = 0; i < Goals.size(); ++i)
        {
            ProjectGoalModel Goal = new ProjectGoalModel();
            Goal.setProject(Project);
            Goal.setDescription(Goals.get(i));

            ProjectGoalRep.save(Goal);
        }

        List<Long> Users = data.users();
        for(int i = 0; i < Users.size(); ++i)
        {
            Optional<UserModel> User = UserRep.findById(Users.get(i));
            if(User.isPresent())
            {
                ProjectMemberModel Member = new ProjectMemberModel();
                Member.setProject(Project);
                Member.setUser(User.get());
                ProjectMemberRep.save(Member);
            }else
            {
                return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
            }
        }
        return new ResponseEntity<>(new ProjectDataDto(Project.getId_project(), Project.getName()), HttpStatus.OK);
    }

    @PostMapping("/{id}")
    public ResponseEntity<String> NewMessage(@RequestAttribute Token token, @PathVariable Long id, @RequestBody NewProjectMessageDto Data)
    {
        Optional<ProjectModel> Project = ProjectRep.findById(id);
        if(!Project.isPresent())
        {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }

        Optional<UserModel> User = UserRep.findById(token.userId());
        if(!User.isPresent())
        {
            return new ResponseEntity<>(null, HttpStatus.UNAUTHORIZED);
        }

        ProjectModel P = Project.get();

        List<ProjectMemberModel> Members = P.getMembers();
        boolean Access = false;

        for(int i = 0; i < Members.size(); ++i)
        {
            if(Members.get(i).getUser().getId_user() == token.userId())
            {
                Access = true;
                break;
            }
        }

        if(!Access)
        {
            return new ResponseEntity<>("Acesso negado", HttpStatus.FORBIDDEN);
        }

        InteractionModel Interaction = new InteractionModel();
        Interaction.setType(null);
        Interaction.setDate(new Timestamp(new Date().getTime()));
        Interaction.setUser(User.get());
        Interaction = InteractionRep.save(Interaction);

        ProjectMessageModel Message = new ProjectMessageModel();
        Message.setProject(P);
        Message.setText(Data.text());
        Message.setInteraction(Interaction);
        ProjectMessageRep.save(Message);

        return new ResponseEntity<>("Mensagem criada", HttpStatus.OK);
    }
}
