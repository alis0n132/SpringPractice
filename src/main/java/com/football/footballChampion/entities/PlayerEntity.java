package com.football.footballChampion.entities;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "player", schema = "public", catalog = "FootballChampion")
public class PlayerEntity {
    private int playerId;
    private String name;
    private String bio;
    private TeamEntity team;
    private List<GoalEntity> goals = new ArrayList<>();
    private List<CardEntity> cards = new ArrayList<>();

    @OneToMany(mappedBy = "player", fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
    public List<GoalEntity> getGoals() {
        return goals;
    }

    public void setGoals(List< GoalEntity> goals) {
        this.goals = goals;
    }

    @OneToMany(mappedBy = "player", fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
    public List< CardEntity> getCards() {
        return cards;
    }

    public void setCards(List< CardEntity> cards) {
        this.cards = cards;
    }

    public PlayerEntity() {
    }

    public PlayerEntity(int playerId, String name, String bio,  TeamEntity team) {
        this.playerId = playerId;
        this.name = name;
        this.bio = bio;
        this.team = team;
    }



    @ManyToOne(targetEntity =  TeamEntity.class)
    @JoinColumn(name = "team")
    public  TeamEntity getTeam() {
        return team;
    }

    public void setTeam( TeamEntity team) {
        this.team = team;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "player_id")
    public int getPlayerId() {
        return playerId;
    }

    public void setPlayerId(int playerId) {
        this.playerId = playerId;
    }

    @Basic
    @Column(name = "name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Basic
    @Column(name = "bio")
    public String getBio() {
        return bio;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        PlayerEntity that = (PlayerEntity) o;

        if (playerId != that.playerId) return false;
        if (name != null ? !name.equals(that.name) : that.name != null) return false;
        if (bio != null ? !bio.equals(that.bio) : that.bio != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = playerId;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (bio != null ? bio.hashCode() : 0);
        return result;
    }
    @Override
    public String toString() {
        return "PlayerEntity{" +
                "playerId=" + playerId +
                ", name='" + name + '\'' +
                ", bio='" + bio + '\'';
    }
}
