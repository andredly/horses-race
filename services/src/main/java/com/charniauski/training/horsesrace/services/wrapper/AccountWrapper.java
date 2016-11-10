package com.charniauski.training.horsesrace.services.wrapper;

import com.charniauski.training.horsesrace.datamodel.Account;
import com.charniauski.training.horsesrace.datamodel.Bet;
import com.charniauski.training.horsesrace.datamodel.Client;

import java.util.List;

/**
 * Created by Andre on 05.11.2016.
 */
public class AccountWrapper {
    private Account account;
    private Client client;
    private List<Bet> bets;

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    public List<Bet> getBets() {
        return bets;
    }

    public void setBets(List<Bet> bets) {
        this.bets = bets;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof AccountWrapper)) return false;

        AccountWrapper that = (AccountWrapper) o;

        if (account != null ? !account.equals(that.account) : that.account != null) return false;
        if (client != null ? !client.equals(that.client) : that.client != null) return false;
        return bets != null ? bets.equals(that.bets) : that.bets == null;

    }

    @Override
    public int hashCode() {
        int result = account != null ? account.hashCode() : 0;
        result = 31 * result + (client != null ? client.hashCode() : 0);
        result = 31 * result + (bets != null ? bets.hashCode() : 0);
        return result;
    }
}