package com.bobsim.server.controller;

import com.bobsim.server.model.Item;
import com.bobsim.server.model.Recipe;
import com.bobsim.server.service.RecipeItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/api")
public class RecipeItemController {

    final RecipeItemService service;

    @Autowired
    public RecipeItemController(RecipeItemService service) {
        this.service = service;
    }

    @PostMapping(value = "/recipes/{id}/items")
    public ResponseEntity<?> add(@PathVariable Integer id, @RequestBody List<Integer> itemIds) {
        try {
            Optional<Recipe> recipeOptional = service.findRecipeById(id);
            if (recipeOptional.isPresent()) {
                Recipe recipe =  recipeOptional.get();
                Set<Item> items = new HashSet<>(service.findAllItemsById(itemIds));
                recipe.setItems(items);
                service.save(recipe);
                return new ResponseEntity<>(HttpStatus.OK);
            } else {
                throw new NoSuchElementException();
            }
        } catch (NoSuchElementException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
