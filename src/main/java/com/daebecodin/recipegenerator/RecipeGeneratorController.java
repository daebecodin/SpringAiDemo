package com.daebecodin.recipegenerator;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RecipeGeneratorController {

    private final RecipeGeneratorService recipeGeneratorService;

    public  RecipeGeneratorController(RecipeGeneratorService recipeGeneratorService) {
        this.recipeGeneratorService = recipeGeneratorService;
    }


    @GetMapping(value = "generate-recipe")
    public String generateRecipe(@RequestParam String ingredients,
                                 @RequestParam String mealType,
                                 @RequestParam String dietaryRestrictions
                                 ) {
        return recipeGeneratorService.generateRecipe(ingredients, mealType, dietaryRestrictions);
    }
}
