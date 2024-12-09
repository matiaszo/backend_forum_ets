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
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.Token;
import com.example.demo.dto.FeedBack.FeedbackProfileDto;
import com.example.demo.dto.interaction.InteractionDataDto;
import com.example.demo.dto.interest.BodyInterestDto;
import com.example.demo.dto.interest.DeleteInterestDto;
import com.example.demo.dto.interest.InterestProfileDto;
import com.example.demo.dto.project.ProjectDataDto;
import com.example.demo.dto.skills.*;
import com.example.demo.dto.user.GiverDto;
import com.example.demo.dto.user.ProfileDto;
import com.example.demo.dto.user.UpdateDto;
import com.example.demo.interfaces.SkillsInterface;
import com.example.demo.interfaces.UserInterface;
import com.example.demo.interfaces.UserSkillInterface;
import com.example.demo.model.FeedbackModel;
import com.example.demo.model.InterestModel;
import com.example.demo.model.UserModel;
import com.example.demo.model.UserSkillModel;
import com.example.demo.repositories.FeedbackRepository;
import com.example.demo.repositories.InterestRepository;
import com.example.demo.repositories.ProjectRepository;
import com.example.demo.repositories.SkillsRepository;
import com.example.demo.repositories.UserRepository;
import com.example.demo.repositories.UserSkillRepository;
import com.example.demo.services.EncoderService;

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

    @Autowired
    EncoderService encoder;

    //! PARTE DAS SKILLS
    @PostMapping("/skill/{id}")
    public ResponseEntity<UserSkillDto> createSkill(@RequestAttribute Token token, @PathVariable Long id, @RequestBody Long skill) {

        if (token.userId() != id)
            return null;

        UserSkillModel userSkillModel = userSkillRepo.findByUserSkill(id, skill);

        if (userSkillModel != null) {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }

        userSkillService.Register(id, skill);
        
        return new ResponseEntity<>(new UserSkillDto(id, skill), HttpStatus.OK);
    }

    @DeleteMapping("/skill/{id}")
    public UserSkillDto deleteSkill(@RequestAttribute Token token, @PathVariable Long id, @RequestBody Long skill) {
        
        if (token.userId() != id)
            return null;

        UserSkillModel userSkillModel = userSkillRepo.findByUserSkill(id, skill);

        if (userSkillModel == null) {
            return null;
        }

        userSkillRepo.deleteById(userSkillModel.getId_user_skills());

        return new UserSkillDto(id, skill);
    }

    //! GET TODOS OS DADOS DO USUÁRIO
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

        List<InterestModel> interestsProfile = interRepo.returnInterest(model.getUserId());
        List<InterestProfileDto> InterestDto = new ArrayList<>();

        for (InterestModel inter : interestsProfile) {
            InterestDto.add(new InterestProfileDto(inter.getId_interest(), inter.getName()));
        }
        
        return new ResponseEntity<>(new ProfileDto(model.getUserId(), model.getBio(), model.getEdv(), model.getEmail(), model.getGitUsername(), model.getImage(), model.getName(), model.getInstructor(), skillsProfile, InterestDto), HttpStatus.ACCEPTED);
    }

    //! PARTE DOS INTERESSES
    @GetMapping("/interest/{id}")
    public List<InterestProfileDto> allInterest(@PathVariable Long id){

        List<InterestModel> modelSele = interRepo.returnInterest(id);

        List<InterestProfileDto> dto = new ArrayList<>();

        for (InterestModel model : modelSele) {
            dto.add(new InterestProfileDto(model.getId_interest(), model.getName()));
        }

        return dto;
    }

    @PostMapping("/interest/{id}")
    public ResponseEntity<InterestProfileDto> createInterest(@RequestAttribute Token token, @PathVariable Long id, @RequestBody BodyInterestDto body) {

        if (token.userId() != id)
            return new ResponseEntity<>(null, HttpStatus.FORBIDDEN);

        InterestModel interestModel = interRepo.findInterestUser(id, body.name().toUpperCase());

        if (interestModel != null) {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }

        Optional<UserModel> optionalUser = userRepo.findById(id);
        UserModel userModel = optionalUser.get();

        InterestModel model = new InterestModel();
        model.setName(body.name().toUpperCase());
        model.setUser(userModel);

        interRepo.save(model);

        return new ResponseEntity<>(new InterestProfileDto(model.getId_interest(), body.name()), HttpStatus.OK);
    }

    @DeleteMapping("/interest/{id}")
    public InterestProfileDto deleteInterest(@RequestAttribute Token token, @PathVariable Long id, @RequestBody DeleteInterestDto interest) {
        
        if (token.userId() != id)
            return null;

        Optional<InterestModel> optionalInter = interRepo.findById(interest.id());

        if(!optionalInter.isPresent()) {
            return null;
        }

        InterestModel model = optionalInter.get();

        interRepo.deleteById(interest.id());

        return new InterestProfileDto(interest.id(), model.getName());
    }

    //! FEEDBACK
    @GetMapping("/feedback/{id}")
    public List<FeedbackProfileDto> feedbacks(@RequestAttribute Token token, @PathVariable Long id) {

        List<FeedbackModel> feeds = feedRepo.findByUser(id);

        List<FeedbackProfileDto> feeddto = new ArrayList<>();

        for (FeedbackModel model : feeds) {

            ProjectDataDto project = new ProjectDataDto(model.getProject().getId_project(), model.getProject().getName());

            UserModel findUser = model.getInteraction().getUser();

            GiverDto user = new GiverDto(findUser.getUserId(), findUser.getEdv(), findUser.getName(), findUser.getInstructor(), findUser.getImage());

            InteractionDataDto inter = new InteractionDataDto(model.getInteraction().getId_interaction(), model.getInteraction().getType(), model.getInteraction().getDate());

            if (token.userId() != id && !model.getVisibility()) 
                continue;
            
            feeddto.add(new FeedbackProfileDto(model.getFeedbackId(), model.getFeedback(), model.getStars(), model.getVisibility(), inter, user, project));
        }

        return feeddto;
    }

    //! UPDATA
    @PatchMapping("/{id}")
    public ResponseEntity<String> updateUser(@RequestAttribute Token token, @PathVariable Long id, @RequestBody UpdateDto dtoUp){
        
        if (token.userId() != id)
            return new ResponseEntity<>(null, HttpStatus.FORBIDDEN);

        var found = userRepo.findById(id);

        if (found.isEmpty()) {
            return null;
        }

        if (dtoUp.bio() != null)
            found.get().setBio(dtoUp.bio());

        if (dtoUp.email() != null) {
            if (userService.validateEmail(dtoUp.email())) {
                found.get().setEmail(dtoUp.email());
            } else {
                return new ResponseEntity<>("Email inválido", HttpStatus.BAD_REQUEST);
            }
        }

        if (dtoUp.gitUserName() != null)
            found.get().setGitUsername(dtoUp.gitUserName());

        if (dtoUp.name() != null)
            found.get().setName(dtoUp.name());

        if (dtoUp.password() != null) {
            if (userService.validatePassword(dtoUp.password())) {
                found.get().setPassword(encoder.encode(dtoUp.password()));
            } else {
                return new ResponseEntity<>("Senha inválida", HttpStatus.BAD_REQUEST);
            }
        }

        userRepo.save(found.get());

        return new ResponseEntity<>("Atualizado com sucesso", HttpStatus.OK);
    }
}
