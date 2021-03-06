package com.charniauski.training.horsesrace.web.dto.wrapper;

import com.charniauski.training.horsesrace.web.dto.CommandDTO;
import com.charniauski.training.horsesrace.web.dto.EventDTO;
import com.charniauski.training.horsesrace.web.dto.HorseDTO;
import com.charniauski.training.horsesrace.web.dto.RaceDetailDTO;

import java.util.List;

/**
 * Created by Andre on 05.11.2016.
 */
public class RaceDetailWrapperDTO {
    private RaceDetailDTO raceDetail;
    private HorseDTO horse;
    private CommandDTO command;
    private List<EventDTO> events;

    public void setRaceDetail(RaceDetailDTO raceDetail) {
        this.raceDetail = raceDetail;
    }

    public void setHorse(HorseDTO horse) {
        this.horse = horse;
    }

    public void setCommand(CommandDTO command) {
        this.command = command;
    }

    public void setEvents(List<EventDTO> events) {
        this.events = events;
    }


    public RaceDetailDTO getRaceDetail() {
        return raceDetail;
    }

    public HorseDTO getHorse() {
        return horse;
    }

    public CommandDTO getCommand() {
        return command;
    }

    public List<EventDTO> getEvents() {
        return events;
    }

    @Override
    public String toString() {
        return "RaceDetailWrapperDTO{" +
                "raceDetail=" + raceDetail +
                ", horse=" + horse +
                ", command=" + command +
                ", events=" + events +
                '}';
    }
}
