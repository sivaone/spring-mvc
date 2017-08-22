package beans.services;

/**
 *
 * @author siva
 */
public interface UserAccountService {
    double refillMoney(double money, long userId);
    void deductMoney(double money, long userId);
}
