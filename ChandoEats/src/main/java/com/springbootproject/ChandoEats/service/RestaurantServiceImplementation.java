package com.springbootproject.ChandoEats.service;

import com.springbootproject.ChandoEats.Dto.RestaurantDto;
import com.springbootproject.ChandoEats.Exception.RestaurantException;
import com.springbootproject.ChandoEats.Request.CreateRestaurantRequest;
import com.springbootproject.ChandoEats.model.Address;
import com.springbootproject.ChandoEats.model.Food;
import com.springbootproject.ChandoEats.model.Order;
import com.springbootproject.ChandoEats.model.Restaurant;
import com.springbootproject.ChandoEats.model.Review;
import com.springbootproject.ChandoEats.model.User;
import com.springbootproject.ChandoEats.repository.AddressRepository;
import com.springbootproject.ChandoEats.repository.FoodRepository;
import com.springbootproject.ChandoEats.repository.OrderRepository;
import com.springbootproject.ChandoEats.repository.RestaurantRepository;
import com.springbootproject.ChandoEats.repository.ReviewRepository;
import com.springbootproject.ChandoEats.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class RestaurantServiceImplementation implements  RestaurantService{
    @Autowired
    private RestaurantRepository restaurantRepository;
    @Autowired
    private AddressRepository addressRepository;
    @Autowired
    private FoodRepository foodRepository;
    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private ReviewRepository reviewRepository;

    @Autowired
    private UserService userService;

    @Autowired
    private UserRepository userRepository;


    @Override
    public Restaurant createRestaurant(CreateRestaurantRequest req, User user) {
        Address address=new Address();
        address.setCity(req.getAddress().getCity());
        address.setCountry(req.getAddress().getCountry());
        address.setFullName(req.getAddress().getFullName());
        address.setPostalCode(req.getAddress().getPostalCode());
        address.setState(req.getAddress().getState());
        address.setStreetAddress(req.getAddress().getStreetAddress());
        Address savedAddress = addressRepository.save(address);

        Restaurant restaurant = new Restaurant();

        restaurant.setAddress(savedAddress);
        restaurant.setContactInformation(req.getContactInformation());
        restaurant.setCuisineType(req.getCuisineType());
        restaurant.setDescription(req.getDescription());
        restaurant.setImages(req.getImages());
        restaurant.setName(req.getName());
        restaurant.setOpeningHours(req.getOpeningHours());
        restaurant.setRegistrationDate(req.getRegistrationDate());
        restaurant.setOwner(user);
        Restaurant savedRestaurant = restaurantRepository.save(restaurant);

        return savedRestaurant;
    }

    @Override
    public Restaurant updateRestaurant(Long restaurantId, CreateRestaurantRequest updatedReq)
            throws RestaurantException {
        Restaurant restaurant = findRestaurantById(restaurantId);
        if (restaurant.getCuisineType() != null) {
            restaurant.setCuisineType(updatedReq.getCuisineType());
        }
        if (restaurant.getDescription() != null) {
            restaurant.setDescription(updatedReq.getDescription());
        }
        return restaurantRepository.save(restaurant);
    }

    @Override
    public Restaurant findRestaurantById(Long restaurantId) throws RestaurantException {
        Optional<Restaurant> restaurant = restaurantRepository.findById(restaurantId);
        if (restaurant.isPresent()) {
            return restaurant.get();
        } else {
            throw new RestaurantException("Restaurant with id " + restaurantId + "not found");
        }
    }

    @Override
    @Transactional
    public void deleteRestaurant(Long restaurantId) throws RestaurantException {
        Restaurant restaurant = restaurantRepository.findById(restaurantId)
                .orElseThrow(() -> new RuntimeException("Restaurant not found"));

        restaurantRepository.delete(restaurant);
    }

    @Override
    public List<Restaurant> getAllRestaurant() {
        return restaurantRepository.findAll();
    }


    @Override
    public Restaurant getRestaurantsByUserId(Long userId) throws RestaurantException {
        Restaurant restaurants=restaurantRepository.findByOwnerId(userId);
        return restaurants;
    }



    @Override
    public List<Restaurant> searchRestaurant(String keyword) {
        return restaurantRepository.findBySearchQuery(keyword);
    }

    @Override
    public RestaurantDto addToFavorites(Long restaurantId, User user) throws RestaurantException {
        Restaurant restaurant=findRestaurantById(restaurantId);

        RestaurantDto dto=new RestaurantDto();
        dto.setTitle(restaurant.getName());
        dto.setImages(restaurant.getImages());
        dto.setId(restaurant.getId());
        dto.setDescription(restaurant.getDescription());

        boolean isFavorited = false;
        List<RestaurantDto> favorites = user.getFavourites();
        for (RestaurantDto favorite : favorites) {
            if (favorite.getId().equals(restaurantId)) {
                isFavorited = true;
                break;
            }
        }

        if (isFavorited) {
            favorites.removeIf(favorite -> favorite.getId().equals(restaurantId));
        } else {
            favorites.add(dto);
        }

        User updatedUser = userRepository.save(user);
        return dto;
    }

    @Override
    public Restaurant updateRestaurantStatus(Long id) throws RestaurantException {
        Restaurant restaurant=findRestaurantById(id);
        restaurant.setOpen(!restaurant.isOpen());
        return restaurantRepository.save(restaurant);
    }
}
