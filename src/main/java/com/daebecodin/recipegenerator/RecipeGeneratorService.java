package com.daebecodin.recipegenerator;

import org.springframework.ai.chat.model.ChatModel;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.ai.chat.prompt.PromptTemplate;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class RecipeGeneratorService {

    private final ChatModel chatModel;

    public RecipeGeneratorService(ChatModel chatModel) {
        this.chatModel = chatModel;
    }

    /**
     *
     * @param ingredients param values to give to model
     * @param mealType meal type value to give to model
     * @param dietaryRestrictions dietary restrictions value to give to model
     * @return
     */
    public String generateRecipe(String ingredients,
                                 String mealType,
                                 String dietaryRestrictions) {

        // our template for the general  function of the recipe generator
        // dynamic variables will be mapped to ur map keys
        String template = """
                I want to you create a recipe using the following ingredients: {ingredients}.
                The meal type will be: {mealType}.
                Please consider the following dietary restrictions: {dietaryRestrictions}
                Please provide a detailed response include sections for the title, overview, cooking ingredients,
                cooking instructions, cooking tools, and a section for information on the recipe such as origins,
                history, creator/creators, etc.
                """;
        // prompt template instance to hold and represent our template
        PromptTemplate promptTemplate = new PromptTemplate(template);

        // map of our recipe item keys mapped to their corresponding variable
        Map<String, Object> recipeItems = Map.of(
                "ingredients", ingredients,
                "mealType", mealType,
                "dietaryRestrictions", dietaryRestrictions
        );

        // prompt instance to hold our template and create the recipe with the given recipe items
        Prompt recipePrompt = promptTemplate.create(recipeItems);

        // final call to chat model that receives the prompts and gives the recipe
        return chatModel.call(recipePrompt).getResult().getOutput().getText();
    }
}
