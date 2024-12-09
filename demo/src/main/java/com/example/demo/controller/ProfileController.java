package com.example.demo.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.FeedBack.FeedbackProfileDto;
import com.example.demo.dto.interaction.InteractionDataDto;
import com.example.demo.dto.interest.InterestProfileDto;
import com.example.demo.dto.project.ProjectDataDto;
import com.example.demo.dto.skills.*;
import com.example.demo.dto.user.GiverDto;
import com.example.demo.dto.user.ProfileDto;
import com.example.demo.dto.user.UserDataDto;
import com.example.demo.interfaces.SkillsInterface;
import com.example.demo.interfaces.UserInterface;
import com.example.demo.interfaces.UserSkillInterface;
import com.example.demo.model.FeedbackModel;
import com.example.demo.model.InterestModel;
import com.example.demo.model.SkillsModel;
import com.example.demo.model.UserModel;
import com.example.demo.model.UserSkillModel;
import com.example.demo.repositories.FeedbackRepository;
import com.example.demo.repositories.InterestRepository;
import com.example.demo.repositories.ProjectRepository;
import com.example.demo.repositories.SkillsRepository;
import com.example.demo.repositories.UserRepository;
import com.example.demo.repositories.UserSkillRepository;

@RestController
@RequestMapping("/profile")
public class ProfileController {
    
    @Autowired
    SkillsInterface service;

    @Autowired
    UserSkillInterface userSkillService;

    @Autowired
    UserInterface userService;

    @Autowired
    SkillsRepository repo;

    @Autowired
    UserSkillRepository userSkillRepo;

    @Autowired
    InterestRepository interRepo;

    @Autowired
    UserRepository userRepo;

    @Autowired
    FeedbackRepository feedRepo;

    @Autowired
    ProjectRepository projectRepo;

    //! PARTE DAS SKILLS
    @PostMapping("/skill/{id}")
    public ResponseEntity<UserSkillDto> createSkill(@PathVariable Long id, @RequestBody Long skill) {

        UserSkillModel userSkillModel = userSkillRepo.findByUserSkill(id, skill);

        if (userSkillModel != null) {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }

        userSkillService.Register(id, skill);
        
        return new ResponseEntity<>(new UserSkillDto(id, skill), HttpStatus.OK);
    }

    @DeleteMapping("/skill/{id}")
    public UserSkillDto deleteSkill(@PathVariable Long id, @RequestBody Long skill) {
        
        UserSkillModel userSkillModel = userSkillRepo.findByUserSkill(id, skill);

        if (userSkillModel == null) {
            return null;
        }

        userSkillRepo.deleteById(userSkillModel.getId_user_skills());

        return new UserSkillDto(id, skill);
    }

    //! GET TODO O USUÁRIO
    @GetMapping("/{id}")
    public ResponseEntity<ProfileDto> getAll(@PathVariable Long id) {

        Optional<UserModel> optionalModel = userRepo.findById(id);
        UserModel model = optionalModel.get();

        List<Object[]> objectProfile = repo.returnSkillDto(model.getUserId());
        List<SkillProfileDto> skillsProfile = new ArrayList<>();

        if (objectProfile != null) {
            skillsProfile = objectProfile.stream()
                .map(profileData -> new SkillProfileDto((Long) profileData[0], (String) profileData[1], (String) profileData[2]))
                .collect(Collectors.toList());
        } else {
            skillsProfile = new ArrayList<>();
        }

        List<InterestProfileDto> interestsProfile = interRepo.returnInterest(model.getUserId());
        
        if (interestsProfile == null) {
            interestsProfile = new ArrayList<>();
        }
        
        return new ResponseEntity<>(new ProfileDto(model.getUserId(), model.getBio(), model.getEdv(), model.getEmail(), model.getGitUsername(), model.getImage(), model.getName(), model.getInstructor(), skillsProfile, interestsProfile), HttpStatus.ACCEPTED);
    }

    //! PARTE DOS INTERESSES
    @PostMapping("/interest/{id}")
    public ResponseEntity<InterestProfileDto> createInterest(@PathVariable Long id, @RequestBody String name) {

        InterestModel interestModel = interRepo.findInterestUser(id, name.toUpperCase());

        if (interestModel != null) {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }

        Optional<UserModel> optionalUser = userRepo.findById(id);
        UserModel userModel = optionalUser.get();

        InterestModel model = new InterestModel();
        model.setName(name.toUpperCase());
        model.setUser(userModel);

        interRepo.save(model);

        return new ResponseEntity<>(new InterestProfileDto(model.getId_interest(), name), HttpStatus.OK);
    }

    @DeleteMapping("/interest/{id}")
    public InterestProfileDto deleteInterest(@PathVariable Long id, @RequestBody Long interest) {
        
        Optional<InterestModel> optionalInter = interRepo.findById(interest);
        if(!optionalInter.isPresent()) {
            return null;
        }

        InterestModel model = optionalInter.get();


        interRepo.deleteById(interest);

        return new InterestProfileDto(interest, model.getName());
    }

    //! FEEDBACK
    @GetMapping("/feedback/{id}")
    public List<FeedbackProfileDto> feedbacks(@PathVariable Long id) {

        List<FeedbackModel> feeds = feedRepo.findByUser(id);

        List<FeedbackProfileDto> feeddto = new ArrayList<>();

        for (FeedbackModel model : feeds) {

            ProjectDataDto project = new ProjectDataDto(model.getProject().getId_project(), model.getProject().getTitle());

            UserModel findUser = model.getInteraction().getUser();

            GiverDto user = new GiverDto(findUser.getUserId(), findUser.getEdv(), findUser.getName(), findUser.getInstructor(), findUser.getImage());

            InteractionDataDto inter = new InteractionDataDto(model.getInteraction().getId_interaction(), model.getInteraction().getType(), model.getInteraction().getDate());

            feeddto.add(new FeedbackProfileDto(model.getFeedbackId(), model.getFeedback(), model.getStars(), model.getVisibility(), inter, user, project));
        }

        return feeddto;
    }
}
