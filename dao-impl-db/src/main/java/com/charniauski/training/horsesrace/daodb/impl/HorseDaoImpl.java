package com.charniauski.training.horsesrace.daodb.impl;


import com.charniauski.training.horsesrace.daoapi.HorseDao;
import com.charniauski.training.horsesrace.datamodel.Horse;
import org.springframework.stereotype.Repository;

import static com.charniauski.training.horsesrace.daodb.util.SqlBuilder.sqlSelectEntity;
import static java.lang.String.format;

/**
 * Created by Andre on 19.10.2016.
 */
@Repository
public class HorseDaoImpl extends AbstractDao<Horse,Long> implements HorseDao {

    @Override
    public Horse getByNickName(String nickName) {
        String sql = format("%s WHERE nick_name='%s';", sqlSelectEntity(Horse.class), nickName);
        return getEntity(sql,Horse.class);
    }

    @Override
    public Horse getByRaceDetail(Long raceDetail) {
        String sql = format("SELECT * FROM race_detail rd LEFT JOIN horse ON" +
                " rd.horse_id = horse.id WHERE rd.id=%d;", raceDetail);
        return getEntity(sql,Horse.class);
    }
}
