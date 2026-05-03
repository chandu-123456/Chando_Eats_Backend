
package com.springbootproject.ChandoEats.Request;
import lombok.Data;

@Data
public class CreateIngredientCategoryRequest {
    private Long restaurantId;
    private String name;
}