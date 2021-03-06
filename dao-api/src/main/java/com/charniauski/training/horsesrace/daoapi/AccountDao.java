package com.charniauski.training.horsesrace.daoapi;

import com.charniauski.training.horsesrace.datamodel.Account;
import com.charniauski.training.horsesrace.datamodel.enums.Status;

import java.util.List;

/**
 * Created by Andre on 19.10.2016.
 */
public interface AccountDao extends GenericDao<Account, Long>{

    Account getByLogin(String login);

    List<Account> getAllByStatus(Status status);

}
