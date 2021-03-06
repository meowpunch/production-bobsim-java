package com.bobsim.server.service;

import com.bobsim.server.model.Item;
import com.bobsim.server.model.Recipe;
import com.bobsim.server.repository.RecipeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class RecipeItemService {

    final ItemService itemService;

    final RecipeRepository recipeRepository;

    @Autowired
    public RecipeItemService(ItemService itemService, RecipeRepository recipeRepository) {
        this.itemService = itemService;
        this.recipeRepository = recipeRepository;
    }

    public List<Item> findAllItemsById(List<Integer> ids) {
        return itemService.findAllByIds(ids);
    }

    public Optional<Recipe> findRecipeById(Integer id) {
        return recipeRepository.findById(id);
    }

    public void save(Recipe recipe) {
        recipeRepository.save(recipe);
    }
}
