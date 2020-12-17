package com.football.footballChampion.entities;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "goal", schema = "public", catalog = "FootballChampion")
public class GoalEntity {
    private int goalId;
    private String time;
    private MatchEntity match;
    private PlayerEntity player;

    @Id
    @GeneratedValue
    @Column(name = "goal_id")
    public int getGoalId() {
        return goalId;
    }

    public void setGoalId(int goalId) {
        this.goalId = goalId;
    }


    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "match", unique = false)
    public MatchEntity getMatch() {
        return match;
    }

    public void setMatch(MatchEntity match) {
        this.match = match;
    }

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "player", unique = false)
    public PlayerEntity getPlayer() {
        return player;
    }

    public void setPlayer(PlayerEntity player) {
        this.player = player;
    }

    @Basic
    @Column(name = "time")
    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        GoalEntity that = (GoalEntity) o;
        return goalId == that.goalId &&
                Objects.equals(time, that.time);
    }

    @Override
    public int hashCode() {
        return Objects.hash(goalId, time);
    }
}
