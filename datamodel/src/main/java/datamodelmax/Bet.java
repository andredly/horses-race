package datamodelmax;

import java.util.Date;

public class Bet extends AbstractModel {

    private Date dateBet;
    private String typeBet;
    private Double sum;
    private Boolean isWin;
    private Double calculation;
    private User user;
    private Event event;

    public Bet() {
    }


    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Event getEvent() {
        return event;
    }

    public void setEvent(Event event) {
        this.event = event;
    }

    public Date getDateBet() {
        return dateBet;
    }

    public void setDateBet(Date dateBet) {
        this.dateBet = dateBet;
    }

    public String getTypeBet() {
        return typeBet;
    }

    public void setTypeBet(String typeBet) {
        this.typeBet = typeBet;
    }

    public Double getSum() {
        return sum;
    }

    public void setSum(Double sum) {
        this.sum = sum;
    }

    public Boolean getWin() {
        return isWin;
    }

    public void setWin(Boolean win) {
        isWin = win;
    }

    public Double getCalculation() {
        return calculation;
    }

    public void setCalculation(Double calculation) {
        this.calculation = calculation;
    }

    public boolean isWinning() {
        // TODO: 04.10.2016
        return false;
    }

    public int calculateWinnings() {
        //TODO: 04.10.2016
        return 0;
    }
}