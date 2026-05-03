package com.springbootproject.ChandoEats.service;

import com.springbootproject.ChandoEats.Exception.RestaurantException;
import com.springbootproject.ChandoEats.model.IngredientCategory;
import com.springbootproject.ChandoEats.model.IngredientsItem;
import com.springbootproject.ChandoEats.model.Restaurant;
import com.springbootproject.ChandoEats.repository.IngredientsCategoryRepository;
import com.springbootproject.ChandoEats.repository.IngredientsItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class IngredientsServiceImplementation implements IngredientsService {
    @Autowired
    private IngredientsCategoryRepository ingredientsCategoryRepo;


    @Autowired
    private IngredientsItemRepository ingredientsItemRepository;



    @Autowired
    private RestaurantService restaurantService;

    @Override
    public IngredientCategory createIngredientsCategory(
            String name,Long restaurantId) throws RestaurantException{

        IngredientCategory isExist=ingredientsCategoryRepo
                .findByRestaurantIdAndNameIgnoreCase(restaurantId,name);

        if(isExist!=null) {
            return isExist;
        }

        Restaurant restaurant=restaurantService.findRestaurantById(restaurantId);

        IngredientCategory ingredientCategory=new IngredientCategory();
        ingredientCategory.setRestaurant(restaurant);
        ingredientCategory.setName(name);

        IngredientCategory createdCategory = ingredientsCategoryRepo.save(ingredientCategory);

        return createdCategory;
    }

    @Override
    public IngredientCategory findIngredientsCategoryById(Long id) throws Exception {
        Optional<IngredientCategory> opt=ingredientsCategoryRepo.findById(id);
        if(opt.isEmpty()){
            throw new Exception("ingredient category not found");
        }
        return opt.get();
    }

    @Override
    public List<IngredientCategory> findIngredientsCategoryByRestaurantId(Long id) throws Exception {
        return ingredientsCategoryRepo.findByRestaurantId(id);
    }

    @Override
    public List<IngredientsItem> findRestaurantsIngredients(Long restaurantId) {

        return ingredientsItemRepository.findByRestaurantId(restaurantId);
    }


    @Override
    public IngredientsItem createIngredientsItem(Long restaurantId,
                                                 String ingredientName, Long ingredientCategoryId) throws Exception {

        IngredientCategory category = findIngredientsCategoryById(ingredientCategoryId);

        IngredientsItem isExist = ingredientsItemRepository.
                findByRestaurantIdAndNameIgnoreCase(restaurantId, ingredientName,category.getName());
        if(isExist!=null) {
            System.out.println("is exists-------- item");
            return isExist;
        }

        Restaurant restaurant=restaurantService.findRestaurantById(
                restaurantId);
        IngredientsItem item=new IngredientsItem();
        item.setName(ingredientName);
        item.setRestaurant(restaurant);
        item.setCategory(category);

        IngredientsItem savedIngredients = ingredientsItemRepository.save(item);
        category.getIngredients().add(savedIngredients);

        return savedIngredients;
    }


    @Override
    public IngredientsItem updateStock(Long id) throws Exception {
        Optional<IngredientsItem> item=ingredientsItemRepository.findById(id);
        if(item.isEmpty()) {
            throw new Exception("ingredient not found with id "+item);
        }
        IngredientsItem ingredient=item.get();
        ingredient.setInStock(!ingredient.isInStock());
        return ingredientsItemRepository.save(ingredient);
    }





}