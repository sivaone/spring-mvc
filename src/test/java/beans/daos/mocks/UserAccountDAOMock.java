package beans.daos.mocks;

import beans.daos.db.UserAccountDAOImpl;
import beans.models.UserAccount;
import java.util.List;

/**
 *
 * @author siva
 */
public class UserAccountDAOMock extends UserAccountDAOImpl{
    
    private final List<UserAccount> userAccounts;

    public UserAccountDAOMock(List<UserAccount> userAccounts) {
        this.userAccounts = userAccounts;
    }

    public void init() {
        cleanup();
        userAccounts.forEach(this :: create);
    }

    public void cleanup() {
        getAll().forEach(this :: delete);
    }
}
