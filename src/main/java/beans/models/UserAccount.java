package beans.models;

/**
 *
 * @author siva
 */
public class UserAccount {
    private long id;
    private User user;
    private double prepaidMoney;

    public UserAccount(long id, User user, double prepaidMoney) {
        this.id = id;
        this.user = user;
        this.prepaidMoney = prepaidMoney;
    }
    

    public double getPrepaidMoney() {
        return prepaidMoney;
    }

    public void setPrepaidMoney(double prepaidMoney) {
        this.prepaidMoney = prepaidMoney;
    }
    
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
    
}
