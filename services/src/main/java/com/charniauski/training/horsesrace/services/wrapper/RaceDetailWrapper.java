package com.charniauski.training.horsesrace.services.wrapper;

import com.charniauski.training.horsesrace.datamodel.*;

import java.util.List;

/**
 * Created by Andre on 05.11.2016.
 */
public class RaceDetailWrapper {
    private RaceDetail raceDetail;
    private RaceCard raceCard;
    private Horse horse;
    private Command command;
    private List<Event> events;

    public RaceDetail getRaceDetail() {
        return raceDetail;
    }

    public void setRaceDetail(RaceDetail raceDetail) {
        this.raceDetail = raceDetail;
    }

    public RaceCard getRaceCard() {
        return raceCard;
    }

    public void setRaceCard(RaceCard raceCard) {
        this.raceCard = raceCard;
    }

    public Horse getHorse() {
        return horse;
    }

    public void setHorse(Horse horse) {
        this.horse = horse;
    }

    public Command getCommand() {
        return command;
    }

    public void setCommand(Command command) {
        this.command = command;
    }

    public List<Event> getEvents() {
        return events;
    }

    public void setEvents(List<Event> events) {
        this.events = events;
    }

    @Override
    public String toString() {
        return "RaceDetailWrapper{" +
                "raceDetail=" + raceDetail +
                ", raceCard=" + raceCard +
                ", horse=" + horse +
                ", command=" + command +
                ", events=" + events +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof RaceDetailWrapper)) return false;

        RaceDetailWrapper that = (RaceDetailWrapper) o;

        if (raceDetail != null ? !raceDetail.equals(that.raceDetail) : that.raceDetail != null) return false;
        if (raceCard != null ? !raceCard.equals(that.raceCard) : that.raceCard != null) return false;
        if (horse != null ? !horse.equals(that.horse) : that.horse != null) return false;
        if (command != null ? !command.equals(that.command) : that.command != null) return false;
        return events != null ? events.equals(that.events) : that.events == null;

    }

    @Override
    public int hashCode() {
        int result = raceDetail != null ? raceDetail.hashCode() : 0;
        result = 31 * result + (raceCard != null ? raceCard.hashCode() : 0);
        result = 31 * result + (horse != null ? horse.hashCode() : 0);
        result = 31 * result + (command != null ? command.hashCode() : 0);
        result = 31 * result + (events != null ? events.hashCode() : 0);
        return result;
    }
}
