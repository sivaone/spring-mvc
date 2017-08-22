package beans.services;

import beans.daos.UserAccountDAO;
import beans.daos.UserDAO;
import beans.models.User;
import beans.models.UserAccount;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author siva
 */
@Service("userAccountService")
public class UserAccountServiceImpl implements UserAccountService {

    @Autowired
    private UserDAO userDao;
    private UserAccountDAO userAccountDAO;

    @Autowired
    public UserAccountServiceImpl(@Qualifier("userAccountDAO") UserAccountDAO userAccountDAO) {
        this.userAccountDAO = userAccountDAO;
    }

    @Transactional
    @Override
    public double refillMoney(double money, long userId) {
        User user = userDao.get(userId);
        if (user == null) {
            throw new RuntimeException("Not a valid user");
        }
        return userAccountDAO.refillMoney(money, user);
    }

    @Transactional
    @Override
    public void deductMoney(double money, long userId) {
        User user = userDao.get(userId);
        if (user == null) {
            throw new RuntimeException("Not a valid user");
        }
        UserAccount userAccount = userAccountDAO.getByUserId(user);
        if (userAccount != null) {
            if (userAccount.getPrepaidMoney() < money) {
                throw new RuntimeException("Not enough balacne in user account");
            }
            userAccountDAO.refillMoney(-money, user);
        }
    }

}
