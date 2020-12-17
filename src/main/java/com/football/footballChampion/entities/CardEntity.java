package com.football.footballChampion.entities;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "card", schema = "public", catalog = "FootballChampion")
public class CardEntity {
    private int cardId;
    private String color;
    private MatchEntity match;
    private PlayerEntity player;

    @Id
    @GeneratedValue
    @Column(name = "card_id")
    public int getCardId() {
        return cardId;
    }

    public void setCardId(int cardId) {
        this.cardId = cardId;
    }

    @ManyToOne
    @JoinColumn(name = "match", nullable = false)
    public MatchEntity getMatch() {
        return match;
    }

    public void setMatch(MatchEntity match) {
        this.match = match;
    }

    @ManyToOne
    @JoinColumn(name = "player", nullable = false)
    public PlayerEntity getPlayer() {
        return player;
    }

    public void setPlayer(PlayerEntity player) {
        this.player = player;
    }

    @Basic
    @Column(name = "color")
    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CardEntity that = (CardEntity) o;
        return cardId == that.cardId &&
                Objects.equals(color, that.color);
    }

    @Override
    public int hashCode() {
        return Objects.hash(cardId, color);
    }
}
