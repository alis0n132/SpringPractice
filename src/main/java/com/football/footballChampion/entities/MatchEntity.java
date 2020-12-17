package com.football.footballChampion.entities;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "match", schema = "public", catalog = "FootballChampion")
public class MatchEntity {
    private int matchId;
    private Integer score;
    private String stadion;
    private TeamEntity homeTeam;
    private TeamEntity guestTeam;
    private ArbitreEntity mainJudge;
    private ArbitreEntity secondJudge;
    private ArbitreEntity thirdJudge;
    private List<GoalEntity> goals;
    private List<CardEntity> cards;


    @Id
    @GeneratedValue
    @Column(name = "match_id")
    public int getMatchId() {
        return matchId;
    }

    public void setMatchId(int matchId) {
        this.matchId = matchId;
    }

    @OneToOne
    @JoinColumn(name = "home_team", referencedColumnName = "team_id", unique = true)
    public TeamEntity getHomeTeam() {
        return homeTeam;
    }

    public void setHomeTeam(TeamEntity homeTeam) {
        this.homeTeam = homeTeam;
    }

    @OneToOne
    @JoinColumn(name = "guest_team", referencedColumnName = "team_id", unique = true)
    public TeamEntity getGuestTeam() {
        return guestTeam;
    }

    public void setGuestTeam(TeamEntity guestTeam) {
        this.guestTeam = guestTeam;
    }


    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "main_judge", referencedColumnName = "arbitre_id", unique = true)
    public ArbitreEntity getMainJudge() {
        return mainJudge;
    }

    public void setMainJudge(ArbitreEntity mainJudge) {
        this.mainJudge = mainJudge;
    }

    @OneToMany(mappedBy = "match")
    @OrderBy("time")
    public List<GoalEntity> getGoals() {
        return goals;
    }

    public void setGoals(List<GoalEntity> goals) {
        this.goals = goals;
    }

    @OneToMany(mappedBy = "match")
    public List<CardEntity> getCards() {
        return cards;
    }

    public void setCards(List<CardEntity> cards) {
        this.cards = cards;
    }

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "second_judge", referencedColumnName = "arbitre_id", unique = true)
    public ArbitreEntity getSecondJudge() {
        return secondJudge;
    }

    public void setSecondJudge(ArbitreEntity secondJudge) {
        this.secondJudge = secondJudge;
    }

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "third_judge", referencedColumnName = "arbitre_id", unique = true)
    public ArbitreEntity getThirdJudge() {
        return thirdJudge;
    }

    public void setThirdJudge(ArbitreEntity thirdJudge) {
        this.thirdJudge = thirdJudge;
    }


    @Column(name = "score")
    public Integer getScore() {
        return score;
    }

    public void setScore(Integer score) {
        this.score = score;
    }

    @Column(name = "stadion")
    public String getStadion() {
        return stadion;
    }

    public void setStadion(String stadion) {
        this.stadion = stadion;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MatchEntity that = (MatchEntity) o;
        return matchId == that.matchId &&
                Objects.equals(score, that.score) &&
                Objects.equals(stadion, that.stadion);
    }

    @Override
    public int hashCode() {
        return Objects.hash(matchId, score, stadion);
    }
}
