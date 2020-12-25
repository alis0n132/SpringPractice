package com.football.footballChampion.controllers;

import com.football.footballChampion.entities.*;
import com.football.footballChampion.services.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.cassandra.CassandraProperties;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.context.HttpSessionSecurityContextRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import java.security.Security;
import java.util.List;

@Controller
public class MatchController {

    private final MatchService matchService;
    private final TeamService teamService;
    private final ArbitreService arbitreService;
    private final GoalService goalService;

    @Autowired
    public MatchController(MatchService matchService, TeamService teamService, ArbitreService arbitreService,
                           GoalService goalService) {
        this.matchService = matchService;
        this.teamService = teamService;
        this.arbitreService = arbitreService;
        this.goalService = goalService;
    }

    @GetMapping("/matches")
    public String allMatchesRequest(Model model){
        List<MatchEntity> matches = matchService.listAll();
        model.addAttribute("matchesList", matches);
        SecurityContext context = SecurityContextHolder.getContext();
        model.addAttribute("roleName", context.getAuthentication().getName());
        return "matchesDir/matches";
    }

    @GetMapping("/matches/{id}")
    public String matchById(@PathVariable("id") Integer id, Model model){
        MatchEntity match = matchService.findById(id);
        model.addAttribute("match", match);
        return "matchesDir/matchById";
    }

    @GetMapping("/matches/new")
    @PreAuthorize("hasAuthority('WRITE')")
    public String newMatch(Model model){
        List<TeamEntity> teams = teamService.listAll();
        List<ArbitreEntity> arbitersList = arbitreService.listAll();
        model.addAttribute("teamsList", teams);
        model.addAttribute("arbitersList", arbitersList);
        model.addAttribute("matchEntity", new MatchEntity());
        return "matchesDir/newMatch";
    }

    @PostMapping("/matches/new")
    @PreAuthorize("hasAuthority('WRITE')")
    public String addNewMatch(MatchEntity newMatch){
        matchService.save(newMatch);
        return "redirect:/matches";
    }

    @PreAuthorize("hasAuthority('WRITE')")
    @GetMapping("/deleteMatch/{id}")
    public String deleteMatch(@PathVariable("id") Integer id){
        matchService.deleteById(id);
        return "redirect:/matches";
    }

    @GetMapping("/addGoal/{id}")
    @PreAuthorize("hasAuthority('write')")
    public String addGoalPage(@PathVariable("id") Integer id, Model model){
        MatchEntity matchEntity = matchService.findById(id);
        List<PlayerEntity> players = matchEntity.getHomeTeam().getPlayers();
        players.addAll(matchEntity.getGuestTeam().getPlayers());
        GoalEntity futureGoal = new GoalEntity();
        model.addAttribute("goal", futureGoal);
        model.addAttribute("players", players);
        model.addAttribute("match", matchEntity);
        return "matchesDir/addGoal";
    }

    @PostMapping("/addGoal/")
    @PreAuthorize("hasAuthority('WRITE')")
    public String addGoalPage(@ModelAttribute("goal") GoalEntity goal){
        goalService.save(goal);
        System.out.println(goal.getGoalId());
        System.out.println(goal.getMatch().getMatchId());
        return "redirect:/matches/" + goal.getMatch().getMatchId();
    }

    @GetMapping("/last")
    public String lastMatch(){
        return "matchesDir/lastMatch";
    }
}
