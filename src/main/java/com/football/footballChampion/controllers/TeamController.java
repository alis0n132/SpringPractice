package com.football.footballChampion.controllers;

import com.football.footballChampion.entities.TeamEntity;
import com.football.footballChampion.services.TeamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.List;

@Controller
public class TeamController {

    @Value("${load.dir.path}")
    private String loadDirPath;

    private final TeamService teamService;

    @Autowired
    public TeamController(TeamService teamService) {
        this.teamService = teamService;
    }

    @GetMapping("/teams")
    public String allTeamsRequest(Model model) {
        List<TeamEntity> teams = teamService.listAll();
        model.addAttribute("teamList", teams);
        return "teamDir/teams";
    }

    @GetMapping("/teams/{id}")
    public String teamByIdRequest(@PathVariable("id") Integer id, Model model) {
        TeamEntity team = teamService.getTeamById(id);
        model.addAttribute("team", team);
        return "teamDir/teamById";
    }

    @GetMapping("/teams/new")
    @PreAuthorize("hasAuthority('write')")
    public String addTeamPage(Model model) {
        model.addAttribute("newTeam", new TeamEntity());
        return "teamDir/newTeam";
    }

    @PostMapping("/teams/new")
    @PreAuthorize("hasAuthority('WRITE')")
    public String addTeam(TeamEntity team, @RequestParam("file") MultipartFile file) {
        if (file != null) {
            try {
                file.transferTo(new File(loadDirPath + "/" + file.getOriginalFilename()));
            } catch (IOException e) {
                e.printStackTrace();
                }
            team.setImage(file.getOriginalFilename());
        }
        teamService.save(team);
        return "redirect:/teams";
    }

    @GetMapping("/deleteTeam/{id}")
    @PreAuthorize("hasAuthority('WRITE')")
    public String deletePlayer(@PathVariable("id") Integer id) {
        teamService.deleteById(id);
        return "redirect:/teams";
    }

    @GetMapping("/editTeam/{id}")
    @PreAuthorize("hasAuthority('WRITE')")
    public String editTeamPage(@PathVariable("id") Integer id, Model model) {
        TeamEntity team = teamService.getTeamById(id);
        model.addAttribute("team", team);

        return "/teamDir/editTeam";
    }

    @PostMapping("/editTeam")
    @PreAuthorize("hasAuthority('WRITE')")
    public String editTeam(TeamEntity team) {
        teamService.save(team);
        return "redirect:/teams";
    }
}
