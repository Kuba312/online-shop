package pl.me.shop.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import pl.me.shop.model.dao.Basket;
import pl.me.shop.model.dao.Product;
import pl.me.shop.repository.BasketRepository;
import pl.me.shop.repository.ProductRepository;
import pl.me.shop.repository.UserRepository;
import pl.me.shop.service.BasketService;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BasketServiceImpl implements BasketService {


    private final BasketRepository basketRepository;
    private final ProductRepository productRepository;
    private final UserRepository userRepository;


    @Override
    public List<Basket> getUserBasket() {
        String name = SecurityContextHolder.getContext().getAuthentication().getName();

        return basketRepository.getBasketByUserName(name);
    }

    @Override
    public Basket addToBasket(Product product) {
        //Pobiera informacje o zalogowanym użytkowniku
        String login = SecurityContextHolder.getContext().getAuthentication().getName();
        return userRepository.findByLogin(login)
                .map(user -> productRepository.findById(product.getId())
                        .map(productDb -> {
                            Optional<Basket> optionalBasket
                                    = basketRepository.findByProductIdAndUserId(productDb.getId(), user.getId());

                            if (optionalBasket.isPresent()) {
                                return updateQuantityProduct(product.getQuantity(), optionalBasket.get());
                            }
                            Basket basket = new Basket();
                            basket.setQuantity(product.getQuantity());
                            basket.setProduct(productDb);
                            basket.setUser(user);

                            return basketRepository.save(basket);
                        }).orElseThrow(() -> new EntityNotFoundException("Product doesn't exit"))
                ).orElseThrow(() -> new EntityNotFoundException("User doesn't exist"));


    }


    @Override
    public void deleteProductFromBasket(Long id) {
        String login = SecurityContextHolder.getContext().getAuthentication().getName();
        basketRepository.deleteByUserLoginAndProductId(login, id);


    }

    @Override
    public void deleteUserBasket() {
        String login = SecurityContextHolder.getContext().getAuthentication().getName();
        basketRepository.deleteByUserLogin(login);
    }

    private Basket updateQuantityProduct(Double quantity, Basket basket) {
        basket.setQuantity(quantity);
        return basketRepository.save(basket);

    }
}
