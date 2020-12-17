package com.football.footballChampion.entities;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "team", schema = "public", catalog = "FootballChampion")
public class TeamEntity {
    private int teamId;
    private String name;
    private String description;
    private Integer score;
    private String image;

    public TeamEntity(int teamId, String name, String description, Integer score, String image) {
        this.teamId = teamId;
        this.name = name;
        this.description = description;
        this.score = score;
        this.image = image;
    }

    @Column(name = "photo_url")
    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    private List<PlayerEntity> players = new ArrayList<>();

    public TeamEntity(){}


    @Id
    @GeneratedValue
    @Column(name = "team_id")
    public int getTeamId() {
        return teamId;
    }

    public void setTeamId(int teamId) {
        this.teamId = teamId;
    }

    @OneToMany(mappedBy = "team", cascade = CascadeType.ALL)
    @OrderBy(value = "name")
    public List<PlayerEntity> getPlayers() {
        return players;
    }

    public void setPlayers(List<PlayerEntity> players) {
        this.players = players;
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
    @Column(name = "description")
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Basic
    @Column(name = "score")
    public Integer getScore() {
        return score;
    }

    public void setScore(Integer score) {
        this.score = score;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TeamEntity that = (TeamEntity) o;

        if (teamId != that.teamId) return false;
        if (name != null ? !name.equals(that.name) : that.name != null) return false;
        if (description != null ? !description.equals(that.description) : that.description != null) return false;
        if (score != null ? !score.equals(that.score) : that.score != null) return false;

        return true;
    }

    @Override
    public String toString() {
        return "TeamEntity{" +
                "teamId=" + teamId +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", score=" + score + '}';
    }

    @Override
    public int hashCode() {
        int result = teamId;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (description != null ? description.hashCode() : 0);
        result = 31 * result + (score != null ? score.hashCode() : 0);
        return result;
    }
}
