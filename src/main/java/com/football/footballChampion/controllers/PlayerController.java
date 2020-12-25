package com.football.footballChampion.controllers;

import com.football.footballChampion.entities.GoalEntity;
import com.football.footballChampion.entities.PlayerEntity;
import com.football.footballChampion.entities.TeamEntity;
import com.football.footballChampion.services.GoalService;
import com.football.footballChampion.services.PlayerService;
import com.football.footballChampion.services.TeamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.Mapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class PlayerController {

    private final PlayerService playerService;
    private final TeamService teamService;

    @Autowired
    public PlayerController(PlayerService playerService, TeamService teamService) {
        this.teamService = teamService;
        this.playerService = playerService;

    }

    @GetMapping("/players")
    public String allPlayersRequest(Model model) {
        List<PlayerEntity> players = playerService.listAll();
        model.addAttribute("playersList", players);
        return "/playerDir/players";
    }

    @GetMapping("/players/{id}")
    public String playerByIdRequest(@PathVariable("id") Integer id, Model model) {
        PlayerEntity player = playerService.getPlayerById(id);
        List<GoalEntity> goals = player.getGoals();
        model.addAttribute("goals", goals);
        model.addAttribute("player", player);
        return "playerDir/playerById";
    }

    @GetMapping("/players/new")
    @PreAuthorize("hasAuthority('write')")
    public String addPlayerPage(Model model){
        List<TeamEntity> teams = teamService.listAll();
        model.addAttribute("teams", teams);
        model.addAttribute("player", new PlayerEntity());

        return "playerDir/newPlayer";
    }
    @PostMapping("/players/new")
    @PreAuthorize("hasAuthority('WRITE')")
    public String addPlayer(PlayerEntity player){
        playerService.save(player);
        return "redirect:/players";
    }

    @GetMapping("/players/delete/{id}")
    @PreAuthorize("hasAuthority('WRITE')")
    public String deletePlayer(@PathVariable("id") Integer id){
        playerService.deleteById(id);
        return "redirect:/players";
    }

    @GetMapping("/players/edit/{id}")
    @PreAuthorize("hasAuthority('WRITE')")
    public String editPlayerPage(@PathVariable("id") Integer id, Model model){
        PlayerEntity player = playerService.getPlayerById(id);
        List<TeamEntity> teams = teamService.listAll();
        model.addAttribute("teams", teams);
        model.addAttribute("player", player);


        return "/playerDir/editPlayer";
    }

    @PostMapping("/players/edit")
    @PreAuthorize("hasAuthority('WRITE')")
    public String editPlayer(PlayerEntity playerEntity){
        playerService.save(playerEntity);
        return "redirect:/players";
    }


}
