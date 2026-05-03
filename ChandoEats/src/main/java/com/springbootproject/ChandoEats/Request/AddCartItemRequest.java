package com.springbootproject.ChandoEats.Request;


import com.springbootproject.ChandoEats.model.IngredientsItem;
import lombok.Data;

import java.util.List;

@Data
public class AddCartItemRequest {

    private Long menuItemId;
    private int quantity;
    private List<String> ingredients;

}