package com.charniauski.training.horsesrace.services.impl;


import com.charniauski.training.horsesrace.daoapi.GenericDao;
import com.charniauski.training.horsesrace.daoapi.RaceCardDao;
import com.charniauski.training.horsesrace.datamodel.RaceCard;
import com.charniauski.training.horsesrace.datamodel.RaceDetail;
import com.charniauski.training.horsesrace.datamodel.Racecourse;
import com.charniauski.training.horsesrace.services.RaceCardService;
import com.charniauski.training.horsesrace.services.RaceDetailService;
import com.charniauski.training.horsesrace.services.RacecourseService;
import com.charniauski.training.horsesrace.services.cacherequest.Cached;
import com.charniauski.training.horsesrace.services.exception.NoSuchEntityException;
import com.charniauski.training.horsesrace.services.wrapper.RaceCardWrapper;
import com.charniauski.training.horsesrace.services.wrapper.RaceDetailWrapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by Andre on 19.10.2016.
 */
@Service
public class RaceCardServiceImpl extends AbstractService<RaceCard, Long> implements RaceCardService {

    private static final Logger LOGGER = LoggerFactory.getLogger(RaceCardServiceImpl.class);
    @Inject
    private RaceCardDao raceCardDao;

    @Inject
    private RacecourseService racecourseService;

    @Inject
    private RaceDetailService raceDetailService;

    @Override
    public GenericDao<RaceCard, Long> getGenericDao() {
        return raceCardDao;
    }

    @Cached(timeToLiveSeconds = 100)
    @Override
    public List<RaceCard> getAllByRacecourseAfterCurrentDate(Long racecourseId) {
        return raceCardDao.getAllByRacecourseAfterCurrentDate(racecourseId);
    }

    @Transactional
    @Override
    public Long save(RaceCard raceCard) {
//        validateDataRaceCard(raceCard);
        Racecourse racecourse = racecourseService.get(raceCard.getRacecourseId());
        if (racecourse.getId() == null)
            throw new NoSuchEntityException("RacecourseId " + raceCard.getRacecourseId() + " not found. Enter valid id!");
        Long raceCardId;
        if (raceCard.getId() == null) {
            raceCardId = raceCardDao.insert(raceCard);
        } else {
            raceCardDao.update(raceCard);
            raceCardId = raceCard.getId();
        }
        return raceCardId;
    }

//    private void validateDataRaceCard(RaceCard raceCard) {
//        Validate.notNull(raceCard.getRacecourseId(),"Arguments RacecourseId may not by null");
//        Validate.notNull(raceCard.getDateStart(),"Arguments DateStart may not by null");
//        Validate.notNull(raceCard.getRaceType(),"Arguments RaceType may not by null");
//    }

    @Cached
    @Override
    public Date getDateStartByEvent(Long eventId) {
        return raceCardDao.getByEvent(eventId).getDateStart();
    }


    @Override
    public RaceCardWrapper getAllDataForRaceCard(Long raceCardId) {
        RaceCard raceCard = get(raceCardId);
        if (raceCard==null){throw new NoSuchEntityException("RaceCard not found");}
        RaceCardWrapper raceCardWrapper = new RaceCardWrapper();
        raceCardWrapper.setRacecourse(racecourseService.get(raceCard.getRacecourseId()));
        List<RaceDetail> raceDetails = raceDetailService.getAllByRaceCard(raceCardId);
        List<RaceDetailWrapper> raceDetailWrappers = new ArrayList<>();
        raceDetails.forEach(rd -> raceDetailWrappers.add(raceDetailService.getAllDataForRaceDetail(rd.getId())));
        raceCardWrapper.setRaceCard(raceCard);
        raceCardWrapper.setRaceDetailWrappers(raceDetailWrappers);
        return raceCardWrapper;
    }

    @Override
    public List<RaceCard> getAllForIntervalTime(Date firstDate, Date lastDate) {
        return raceCardDao.getAllForIntervalTime(firstDate,lastDate);
    }

    @Override
    public List<RaceCardWrapper> getAllDataForIntervalTime(Date firstDate, Date lastDate) {
        List<RaceCard> list = getAllForIntervalTime(firstDate,lastDate);
        List<RaceCardWrapper> raceCardWrapperList = new ArrayList<>();
        list.forEach(rc -> raceCardWrapperList.add(getAllDataForRaceCard(rc.getId())));
        return raceCardWrapperList;
    }

    @Override
    public List<RaceCardWrapper> getAllDataForAllRaceCardAfterCurrentDate(Long racecourseId) {
        List<RaceCard> list = getAllByRacecourseAfterCurrentDate(racecourseId);
        List<RaceCardWrapper> raceCardWrapperList = new ArrayList<>();
        list.forEach(rc -> raceCardWrapperList.add(getAllDataForRaceCard(rc.getId())));
        return raceCardWrapperList;
    }

    @Override
    public List<RaceCardWrapper> getAllDataForTreeRaceCardAfterCurrentDate(Long racecourseId) {
        List<RaceCardWrapper> list = getAllDataForAllRaceCardAfterCurrentDate(racecourseId);
        List<RaceCardWrapper> threeNextAfterCurrentDate = new ArrayList<>();
        if (list.isEmpty()) {
            return threeNextAfterCurrentDate;
        }
        int size;
        if (list.size() <= 3) {
            size = list.size();
        } else {
            size = 3;
        }
        for (int i = 0; i < size; i++) {
            threeNextAfterCurrentDate.add(list.get(i));
        }
        return threeNextAfterCurrentDate;
    }
}
