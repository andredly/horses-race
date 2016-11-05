package com.charniauski.training.horsesrace.services;

import com.charniauski.training.horsesrace.datamodel.RaceDetail;
import com.charniauski.training.horsesrace.services.exception.NoSuchEntityException;
import com.charniauski.training.horsesrace.services.wrapper.RaceDetailWrapper;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by Andre on 18.10.2016.
 */
public interface RaceDetailService extends GenericService<RaceDetail,Long> {

    boolean saveHorseResult(Long raceCardId, Long horseId, Integer result);

    RaceDetail getByRaceCardAndHorse(Long raceCardId, Long horseId);

    RaceDetail getByRaceCardAndCommand(Long raceCardId, Long commandId);

    RaceDetail getByRaceCardAndNumberStartBox(Long raceCardId, Integer numberStartBox);

    List<RaceDetail> getByRaceCard(Long raceCardId);

    RaceDetailWrapper getRaceDetailWrapper(Long raceDetailId);

}
