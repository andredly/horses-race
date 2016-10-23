package com.charniauski.training.horsesrace.datamodel;

/**
 * Created by ivc4 on 13.10.2016.
 */
@Entity(tableName = "account")
public class Account extends AbstractModel{
    @Column(columnName = "login")
    private String login;
    @Column(columnName = "password")
    private String password;
    @Column(columnName = "balance")
    private Double balance;
    @Column(columnName = "email")
    private String email;

//    private SecurityLevel securityLevel;
//    private User user;

    public Account() {
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Double getBalance() {
        return balance;
    }

    public void setBalance(Double balance) {
        this.balance = balance;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

}
