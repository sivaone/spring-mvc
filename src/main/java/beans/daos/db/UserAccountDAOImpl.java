package beans.daos.db;

import beans.daos.AbstractDAO;
import beans.daos.UserAccountDAO;
import beans.models.User;
import beans.models.UserAccount;
import java.util.List;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

/**
 *
 * @author siva
 */
@Repository("userAccountDAO")
public class UserAccountDAOImpl extends AbstractDAO implements UserAccountDAO{

    @Override
    public double refillMoney(double money, User user) {
        UserAccount userAccount = (UserAccount) 
                createBlankCriteria(UserAccount.class).add(Restrictions.eq("user", user.getId())).uniqueResult();
        userAccount.setPrepaidMoney(userAccount.getPrepaidMoney() + money);
        getCurrentSession().update(userAccount);
        return userAccount.getPrepaidMoney();
    }
    
    @Override
    public UserAccount create(UserAccount userAccount) {
        Long id = (Long) getCurrentSession().save(userAccount);
        return getCurrentSession().get(UserAccount.class, id);
    }
    
    @Override
    public void delete(UserAccount userAccount) {
        getCurrentSession().delete(userAccount);
    }
    
    @Override
    @SuppressWarnings("unchecked")
    public List<UserAccount> getAll() {
        return ((List<UserAccount>) createBlankCriteria(UserAccount.class).list());
    }
    
    @Override
    public UserAccount getByUserId(User user) {
        return (UserAccount) 
                createBlankCriteria(UserAccount.class)
                        .createAlias("user", "user")
                        .add(Restrictions.eq("user.id", user.getId()))
                        .uniqueResult();
        
    }
}
