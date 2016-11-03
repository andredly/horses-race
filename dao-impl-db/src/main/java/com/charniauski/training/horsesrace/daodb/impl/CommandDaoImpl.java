package com.charniauski.training.horsesrace.daodb.impl;

import com.charniauski.training.horsesrace.daodb.CommandDao;
import com.charniauski.training.horsesrace.datamodel.Command;
import com.charniauski.training.horsesrace.datamodel.RaceDetail;
import org.springframework.stereotype.Repository;

import static com.charniauski.training.horsesrace.daodb.util.SqlBuilder.sqlSelectEntity;
import static java.lang.String.format;

/**
 * Created by Andre on 19.10.2016.
 */
@Repository
public class CommandDaoImpl extends AbstractDao<Command,Long> implements CommandDao{

    @Override
    public Command getByTrainerAndJockeyAndUrl(String trainer, String jockey, String urlImage) {
        String sql = format("%s WHERE trainer=%s AND jockey=%s AND url_image_color=%s;",
                sqlSelectEntity(RaceDetail.class), trainer, jockey,urlImage);
        return getEntity(sql, Command.class);
    }
}
