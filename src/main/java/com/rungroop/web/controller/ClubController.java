package com.rungroop.web.controller;

import com.rungroop.web.dto.ClubDto;
import com.rungroop.web.models.Club;
import com.rungroop.web.service.ClubService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@Controller
public class ClubController {
    private ClubService clubService;

    @Autowired
    public ClubController(ClubService clubService) {
        this.clubService = clubService;
    }
    @GetMapping("/clubs")
    public String listClubs(Model model) {
        List<ClubDto> clubs = clubService.findAllClubs();
        model.addAttribute("clubs", clubs);
        return "clubs-list";

    }
    // Details
    @GetMapping("/clubs/{clubId}")
    public String clubDetails(@PathVariable("clubId") Long clubId, Model model) {
        ClubDto clubDto = clubService.findClubById(clubId);
        model.addAttribute("club", clubDto);
        return "clubs-details";
    }

    // search by title
    @GetMapping("/clubs/search")
    public String searchClubs(@RequestParam(value ="query") String query,  Model model) {
     List<ClubDto> clubs = clubService.searchClubs(query);
     model.addAttribute("clubs", clubs);
     return "clubs-list";
    }
    @GetMapping("/clubs/new")
    public String createClubForm(Model model) {
        Club club = new Club();
        model.addAttribute("club", club);
        return "clubs-create";
    }
    // Create new clubs
    @PostMapping("/clubs/new")
    public String saveClub(@ModelAttribute("club") Club club){
        clubService.saveClub(club);
        return "redirect:/clubs";
    }
    @GetMapping("/clubs/{clubId}/edit")
    public String editClubForm(@PathVariable("clubId") long clubId, Model model) {
        ClubDto clubDto = clubService.findClubById(clubId);
          model.addAttribute("club", clubDto);
    return "clubs-edit";
    }
    // Update
    @PostMapping ("/clubs/{clubId}/edit")
    public String updateClub(@PathVariable("clubId") Long clubId, @Validated
    @ModelAttribute("club") ClubDto club,
                             BindingResult result , Model model) {
    if (result.hasErrors()) {
        model.addAttribute("club", club);
        return "clubs-edit";
    }
     club.setId(clubId);
     clubService.updateClub(club);
     return "redirect:/clubs";
    }
    // delete
    @GetMapping("/clubs/{clubId}/delete")
    public String deleteClub(@PathVariable("clubId") long clubId) {
        clubService.delete(clubId);
        return "redirect:/clubs";
    }
}
