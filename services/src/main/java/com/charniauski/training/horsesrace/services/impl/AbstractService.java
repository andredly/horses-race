package com.charniauski.training.horsesrace.services.impl;

import com.charniauski.training.horsesrace.datamodel.AbstractModel;
import com.charniauski.training.horsesrace.services.GenericService;
import com.charniauski.training.horsesrace.services.cacherequest.Cached;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ivc4 on 24.10.2016.
 */
@Service
public abstract class AbstractService<T extends AbstractModel, PK> implements GenericService<T, PK> {


    @Transactional
    @Override
    public List<PK> saveAll(List<T> listEntity) {
        ArrayList<PK> listPK=new ArrayList<>();
        listEntity.forEach(entity -> listPK.add(save(entity)));
        return listPK;
    }

    @SuppressWarnings("unchecked")
    @Transactional
    @Override
    public PK save(T entity)  {
        if (entity.getId() == null) {return getGenericDao().insert(entity);
        }else {
            getGenericDao().update(entity);
            return (PK) entity.getId();
        }
    }

    @SuppressWarnings("unchecked")
    @Transactional
    @Override
    public boolean delete(PK id) {
        return getGenericDao().delete(id);
    }

    @SuppressWarnings("unchecked")
    @Cached(timeToLiveSeconds = 20)
    @Override
    public T get(PK id) {
        return getGenericDao().get(id);
    }


    @SuppressWarnings("unchecked")
    @Cached
    @Override
    public List<T> getAll() {
        return getGenericDao().getAll();
    }
}
