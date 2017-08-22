package beans.daos;

import beans.models.User;
import beans.models.UserAccount;
import java.util.List;

/**
 *
 * @author siva
 */
public interface UserAccountDAO {
    double refillMoney(double money, User user);
    UserAccount create(UserAccount userAccount);
    void delete(UserAccount event);
    List<UserAccount> getAll();
    UserAccount getByUserId(User user);
}
