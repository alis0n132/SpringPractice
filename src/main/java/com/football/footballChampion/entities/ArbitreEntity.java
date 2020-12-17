package com.football.footballChampion.entities;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "arbitre", schema = "public", catalog = "FootballChampion")
public class ArbitreEntity {
    private int arbitreId;
    private String name;
    private String bio;

    @Id
    @GeneratedValue
    @Column(name = "arbitre_id")
    public int getArbitreId() {
        return arbitreId;
    }

    public void setArbitreId(int arbitreId) {
        this.arbitreId = arbitreId;
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
        ArbitreEntity that = (ArbitreEntity) o;
        return arbitreId == that.arbitreId &&
                Objects.equals(name, that.name) &&
                Objects.equals(bio, that.bio);
    }

    @Override
    public int hashCode() {
        return Objects.hash(arbitreId, name, bio);
    }
}
