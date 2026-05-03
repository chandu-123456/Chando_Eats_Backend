package com.springbootproject.ChandoEats.config;

import com.springbootproject.ChandoEats.model.Category;
import com.springbootproject.ChandoEats.model.Food;
import com.springbootproject.ChandoEats.model.Restaurant;
import com.springbootproject.ChandoEats.repository.CategoryRepository;
import com.springbootproject.ChandoEats.repository.FoodRepository;
import com.springbootproject.ChandoEats.repository.RestaurantRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ThreadLocalRandom;

@Component
public class FoodDataSeeder implements CommandLineRunner {

    private final FoodRepository foodRepository;
    private final RestaurantRepository restaurantRepository;
    private final CategoryRepository categoryRepository;

    @Value("${app.seed.food.enabled:false}")
    private boolean seedEnabled;

    @Value("${app.seed.food.count:500}")
    private int targetCount;

    public FoodDataSeeder(FoodRepository foodRepository,
                          RestaurantRepository restaurantRepository,
                          CategoryRepository categoryRepository) {
        this.foodRepository = foodRepository;
        this.restaurantRepository = restaurantRepository;
        this.categoryRepository = categoryRepository;
    }

    @Override
    @Transactional
    public void run(String... args) {
        if (!seedEnabled) {
            return;
        }

        long existingCount = foodRepository.count();
        if (existingCount >= targetCount) {
            System.out.println("Food seeding skipped. Existing food count: " + existingCount);
            return;
        }

        List<Restaurant> restaurants = restaurantRepository.findAll();
        if (restaurants.isEmpty()) {
            System.out.println("Food seeding skipped. No restaurants found.");
            return;
        }

        Map<Long, List<Category>> categoriesByRestaurant = loadOrCreateCategories(restaurants);
        int toCreate = (int) (targetCount - existingCount);

        List<Food> batch = new ArrayList<>();
        int batchSize = 100;

        for (int i = 0; i < toCreate; i++) {
            Food food = buildFood(i, restaurants, categoriesByRestaurant);
            batch.add(food);

            if (batch.size() == batchSize) {
                foodRepository.saveAll(batch);
                batch.clear();
            }
        }

        if (!batch.isEmpty()) {
            foodRepository.saveAll(batch);
        }

        System.out.println("Food seeding completed. Added " + toCreate + " new food items.");
    }

    private Map<Long, List<Category>> loadOrCreateCategories(List<Restaurant> restaurants) {
        Map<Long, List<Category>> map = new HashMap<>();

        for (Restaurant restaurant : restaurants) {
            List<Category> categories = categoryRepository.findByRestaurantId(restaurant.getId());
            if (categories.isEmpty()) {
                categories = createDefaultCategories(restaurant);
            }
            map.put(restaurant.getId(), categories);
        }

        return map;
    }

    private List<Category> createDefaultCategories(Restaurant restaurant) {
        List<String> names = Arrays.asList("Starters", "Main Course", "Biryani", "Desserts", "Beverages");
        List<Category> created = new ArrayList<>();

        for (String name : names) {
            Category category = new Category();
            category.setName(name);
            category.setRestaurant(restaurant);
            created.add(category);
        }

        return categoryRepository.saveAll(created);
    }

    private Food buildFood(int index,
                           List<Restaurant> restaurants,
                           Map<Long, List<Category>> categoriesByRestaurant) {
        ThreadLocalRandom random = ThreadLocalRandom.current();

        Restaurant restaurant = restaurants.get(random.nextInt(restaurants.size()));
        List<Category> categories = categoriesByRestaurant.get(restaurant.getId());
        Category category = categories.get(random.nextInt(categories.size()));

        String[] prefixes = {"Spicy", "Classic", "Crispy", "Tandoori", "Cheesy", "Smoky", "Royal", "Fresh"};
        String[] items = {"Paneer Wrap", "Chicken Burger", "Veg Pizza", "Mutton Biryani", "Pasta", "Noodles", "Falafel Bowl", "Sandwich", "Dosa", "Paratha"};

        String foodName = prefixes[random.nextInt(prefixes.length)] + " " + items[random.nextInt(items.length)] + " #" + (index + 1);

        Food food = new Food();
        food.setName(foodName);
        food.setDescription("Freshly prepared " + foodName + " with house-style flavors.");
        food.setPrice((long) random.nextInt(120, 750));
        food.setFoodCategory(category);
        food.setImages(List.of("https://images.unsplash.com/photo-1546069901-ba9599a7e63c"));
        food.setAvailable(true);
        food.setRestaurant(restaurant);
        food.setVegetarian(random.nextBoolean());
        food.setSeasonal(random.nextInt(100) < 20);
        food.setCreationDate(new Date());

        return food;
    }
}