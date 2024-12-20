package com.example.demo.controller;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
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
import com.example.demo.dto.FeedBack.UpdateFeedback;
import com.example.demo.dto.FeedBack.FeedBackPostDto;
import com.example.demo.dto.interaction.InteractionExtra;
import com.example.demo.dto.interaction.InteractionFullDto;
import com.example.demo.dto.interest.BodyInterestDto;
import com.example.demo.dto.interest.DeleteInterestDto;
import com.example.demo.dto.interest.InterestProfileDto;
import com.example.demo.dto.skills.*;
import com.example.demo.dto.user.GiverDto;
import com.example.demo.dto.user.ProfileDto;
import com.example.demo.dto.user.UpdateDto;
import com.example.demo.interfaces.SkillsInterface;
import com.example.demo.interfaces.UserInterface;
import com.example.demo.interfaces.UserSkillInterface;
import com.example.demo.model.CommentModel;
import com.example.demo.model.FeedbackModel;
import com.example.demo.model.InteractionModel;
import com.example.demo.model.InterestModel;
import com.example.demo.model.LikeModel;
import com.example.demo.model.ProjectModel;
import com.example.demo.model.UserModel;
import com.example.demo.model.UserSkillModel;
import com.example.demo.repositories.CommentRepository;
import com.example.demo.repositories.FeedbackRepository;
import com.example.demo.repositories.InteractionRepository;
import com.example.demo.repositories.InterestRepository;
import com.example.demo.repositories.LikeRepository;
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

    @Autowired
    InteractionRepository InteractionRep;

    @Autowired
    CommentRepository CommentRep;

    @Autowired
    LikeRepository LikeRep;

    @Autowired
    ProjectRepository projRepo;

    //! PARTE DAS SKILLS
    @PostMapping("/skill/{id}")
    public ResponseEntity<UserSkillDto> createSkill(@RequestAttribute Token token, @PathVariable Long id, @RequestBody LongRecord skill) {

        if (token.userId() != id)
            return null;

        UserSkillModel userSkillModel = userSkillRepo.findByUserSkill(id, skill.skill());

        if (userSkillModel != null) {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }

        SkillDataDto data = userSkillService.Register(id, skill.skill());
        
        return new ResponseEntity<UserSkillDto>(new UserSkillDto(id,data.image(), data.name()), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@RequestAttribute Token token, @PathVariable Long id)
    {

        if (token.instructor() != true)
            return null;

        userRepo.deleteById(id);

        return new ResponseEntity<>("Usuario deletado com sucesso", HttpStatus.OK);
    }

    @DeleteMapping("/skill/{id}")
    public ResponseEntity<UserSkillDto> deleteSkill(@RequestAttribute Token token, @PathVariable Long id, @RequestBody LongRecord skill) {
        
        if (token.userId() != id)
            return null;

        UserSkillModel userSkillModel = userSkillRepo.findByUserSkill(id, skill.skill());

        if (userSkillModel == null) {
            return null;
        }

        userSkillRepo.deleteById(userSkillModel.getId_user_skills());

        return new ResponseEntity<>(new UserSkillDto(id, null, null), HttpStatus.OK);
    }

    //! GET TODOS OS DADOS DO USUÁRIO
    @GetMapping("/{id}")
    public ResponseEntity<ProfileDto> getAll(@RequestAttribute("token") Token token, @PathVariable Long id) {

        Optional<UserModel> optionalModel = userRepo.findById(id);
        if(optionalModel.isEmpty())
        {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
        UserModel model = optionalModel.get();

        List<Object[]> objectProfile = repo.returnSkillDto(model.getId_user());
        List<SkillProfileDto> skillsProfile = new ArrayList<>();

        if (objectProfile != null) {
            skillsProfile = objectProfile.stream()
                .map(profileData -> new SkillProfileDto((Long) profileData[0], (String) profileData[1], (String) profileData[2]))
                .collect(Collectors.toList());
        } else {
            skillsProfile = new ArrayList<>();
        }

        List<InterestModel> interestsProfile = interRepo.returnInterest(model.getId_user());
        List<InterestProfileDto> InterestDto = new ArrayList<>();

        for (InterestModel inter : interestsProfile) {
            InterestDto.add(new InterestProfileDto(inter.getId_interest(), inter.getName()));
        }

        var num = userRepo.findById(model.getId_user()).get().getInteractions().size();

        var isUser = token.userId() == id ? true : false;
        
        return new ResponseEntity<>(new ProfileDto(model.getId_user(), model.getBio(), model.getEdv(), model.getEmail(), model.getGitUsername(), model.getImage(), model.getName(), model.getInstructor(), isUser, num, skillsProfile, InterestDto), HttpStatus.OK);
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
    public ResponseEntity<InterestProfileDto> deleteInterest(@RequestAttribute Token token, @PathVariable Long id, @RequestBody DeleteInterestDto interest) {
        if (token.userId() != id)
            return null;

        Optional<InterestModel> optionalInter = interRepo.findById(interest.id());

        if(!optionalInter.isPresent()) {
            return null;
        }

        InterestModel model = optionalInter.get();

        interRepo.deleteById(interest.id());

        return new ResponseEntity<>(new InterestProfileDto(interest.id(), model.getName()), HttpStatus.OK);
    }

    //! FEEDBACK
    @GetMapping("/feedback/{id}")
    public ResponseEntity<List<FeedbackProfileDto>> feedbacks(@RequestAttribute Token token, @PathVariable Long id) {

        List<FeedbackModel> feeds = feedRepo.findByUser(id);

        List<FeedbackProfileDto> feeddto = new ArrayList<>();

        for (FeedbackModel model : feeds) {

            if (token.userId() != id && !model.getVisibility()) 
                continue;
            
            feeddto.add(new FeedbackProfileDto(model.getFeedbackId() ,model.getStars(), model.getFeedback(), model.getVisibility(), model.getProject().getName(), new GiverDto(model.getInteraction().getUser().getId_user(), model.getInteraction().getUser().getName(), model.getInteraction().getUser().getInstructor(), model.getInteraction().getUser().getImage())));
        }

        return new ResponseEntity<>(feeddto, HttpStatus.OK);
    }

    @PatchMapping("/feedback/{id}")
    public ResponseEntity<String> patchFeedback (@RequestAttribute Token token, @PathVariable Long id) {

        var found = feedRepo.findById(id);

        if (found.isEmpty())
            return new ResponseEntity<>("Feedback não encontrado", HttpStatus.NOT_FOUND);

        var esse = found.get();


        if (esse.getReceptor().getId_user() != token.userId())
            return new ResponseEntity<>("Somente o usuário pode alterar isso", HttpStatus.UNAUTHORIZED);


        esse.setVisibility(esse.getVisibility() ? false: true);
        feedRepo.save(esse);

        return new ResponseEntity<>( esse.getVisibility().toString() , HttpStatus.OK);
    }

    @PostMapping("/feedback")
    public ResponseEntity<String> createFeedback (@RequestAttribute Token token, @RequestBody FeedBackPostDto ids) {

        Optional<UserModel> receptor = userRepo.findById(ids.idUser());
        Optional<UserModel> sender = userRepo.findById(token.userId());
        Optional<ProjectModel> project = projectRepo.findById(ids.idProject());

        if(receptor.isPresent() && sender.isPresent() && project.isPresent())
        {
            InteractionModel inter = new InteractionModel();
            inter.setType("FEEDBACK");
            inter.setUser(sender.get());
            inter.setDate(new Timestamp(new Date().getTime()));

            inter = InteractionRep.save(inter);
    
            FeedbackModel model = new FeedbackModel();
            model.setFeedback(ids.text());
            model.setProject(project.get());
            model.setReceptor(receptor.get());
            model.setStars(ids.stars());
            model.setVisibility(true);
            model.setInteraction(inter);

            feedRepo.save(model);
            return new ResponseEntity<>("Inserido com sucesso", HttpStatus.OK);
        }
        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }

    //! UPDATA
    @PatchMapping("/{id}")
    public ResponseEntity<String> updateUser(@RequestAttribute Token token, @PathVariable Long id, @RequestBody UpdateDto dtoUp){
        System.out.println("CHAMOU O BACK");
        if (token.userId() != id)
            return new ResponseEntity<>(null, HttpStatus.FORBIDDEN);

        var found = userRepo.findById(id);

        if (found.isEmpty()) {
            return null;
        }

        if (dtoUp.bio() != null)
            found.get().setBio(dtoUp.bio());

        if (dtoUp.image() != null)
            found.get().setImage(dtoUp.image());

        if (dtoUp.email() != null) {
            if (userService.validateEmail(dtoUp.email(), id)) {
                found.get().setEmail(dtoUp.email());
            } else {
                return new ResponseEntity<>("Email inválido", HttpStatus.BAD_REQUEST);
            }
        }

        if (dtoUp.gitUsername() != null)
            found.get().setGitUsername(dtoUp.gitUsername());

        if (dtoUp.name() != null)
            found.get().setName(dtoUp.name());

        userRepo.save(found.get());

        return new ResponseEntity<>("Atualizado com sucesso", HttpStatus.OK);
    }

    @GetMapping("/interactions/{id}")
    public ResponseEntity<List<InteractionFullDto>> getUserInteractions(@RequestAttribute Token token, @PathVariable Long id)
    {
        List<InteractionFullDto> ret = new ArrayList<>();

        InteractionRep.findLatest(id).forEach((item) -> 
        {
            switch(item.getType())
            {
                case "COMMENT":
                {
                    Optional<CommentModel> Comment = CommentRep.findByInteraction(item.getId_interaction());
                    if(Comment.isPresent())
                    {
                        CommentModel c = Comment.get();
                        ret.add(new InteractionFullDto
                        (
                            item.getType(),
                            new Date(item.getDate().getTime()),
                            new InteractionExtra(c.getContent(), null, c.getTopic().getTitle())
                        ));
                    }
                }
                break;
                case "FEEDBACK":
                {
                    Optional<FeedbackModel> Feedback = feedRepo.findByInteraction(item.getId_interaction());
                    if(Feedback.isPresent())
                    {
                        FeedbackModel f = Feedback.get();
                        if(f.getVisibility())
                        {
                            ret.add(new InteractionFullDto
                            (
                                item.getType(),
                                new Date(item.getDate().getTime()),
                                new InteractionExtra(f.getFeedback(), f.getReceptor().getName(), null)
                            ));
                        }
                    }
                }
                break;
                case "LIKE":
                {
                    Optional<LikeModel> Like = LikeRep.findByInteraction(item.getId_interaction());
                    if(Like.isPresent())
                    {
                        LikeModel l = Like.get();
                        ret.add(new InteractionFullDto
                        (
                            item.getType(),
                            new Date(item.getDate().getTime()),
                            new InteractionExtra(null, l.getComment().getInteraction().getUser().getName(), null)
                        ));
                    }
                }
                break;

                default:
                break;
            }
        });
        return new ResponseEntity<>(ret, HttpStatus.OK);
    }
}
