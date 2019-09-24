package ruslan.kovshar.final_project.service;

import org.springframework.stereotype.Service;
import ruslan.kovshar.final_project.entity.Buyer;
import ruslan.kovshar.final_project.entity.User;
import ruslan.kovshar.final_project.repository.BuyerRepository;
import ruslan.kovshar.final_project.repository.UserRepository;

@Service
public class PaymentService {

    private final UserRepository userRepository;
    private final BuyerRepository buyerRepository;

    public PaymentService(UserRepository userRepository, BuyerRepository buyerRepository) {
        this.userRepository = userRepository;
        this.buyerRepository = buyerRepository;
    }

    public void makePay(User user, Buyer buyer) {
        buyerRepository.save(buyer);
        userRepository.save(user);
    }

    public void returnMoney(User user) {
        userRepository.save(user);
    }
}
