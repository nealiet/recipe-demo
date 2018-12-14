package com.njt.recipedemo.bootstrap;


import com.njt.recipedemo.domain.*;
import com.njt.recipedemo.repositories.CategoryRepository;
import com.njt.recipedemo.repositories.RecipeRepository;
import com.njt.recipedemo.repositories.UnitOfMeasureRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Slf4j
@Component
public class RecipeBootstrap implements ApplicationListener<ContextRefreshedEvent> {


    private RecipeRepository recipeRepository;
        private CategoryRepository categoryRepository;
        private UnitOfMeasureRepository unitOfMeasureRepository;


        public RecipeBootstrap(RecipeRepository recipeRepository, CategoryRepository categoryRepository, UnitOfMeasureRepository unitOfMeasureRepository) {
            this.recipeRepository = recipeRepository;
            this.categoryRepository = categoryRepository;
            this.unitOfMeasureRepository = unitOfMeasureRepository;
        }

        @Override
        @Transactional
        public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
            log.debug("************************  HERE NOW *****************************");
            recipeRepository.saveAll(getRecipes());
        }

        private List getRecipes() {
            log.debug("--->  Listing Recipes");
            List<Recipe> recipes= new ArrayList(2);
            Recipe guacRecipe = new Recipe("Perfect Guacomole",10, 10,4,"Simply Recipes","https://www.simplyrecipes.com/recipes/perfect_guacamole/",Difficulty.EASY);
            Recipe chixRecipe = new Recipe("Spicy Grilled Chicken Tacos",20, 15,4,"Simply Recipes","https://www.simplyrecipes.com/recipes/spicy_grilled_chicken_tacos/",Difficulty.MODERATE);
            Optional<Category> catMexican = categoryRepository.findByDescription("Mexican");
            if (!catMexican.isPresent()) {
                log.debug("---> Mexican category not found");
            }
            else log.debug("--->catMexican.value =" + catMexican.get().getDescription());
            Optional<Category> catAmerican = categoryRepository.findByDescription("American");

            UnitOfMeasure eachUom;
            Optional<UnitOfMeasure> eachUomOptional = unitOfMeasureRepository.findByDescription("Each");
            if (!eachUomOptional.isPresent()) {
                throw new RuntimeException("'Each' unit of measure not found");
            } else eachUom=eachUomOptional.get();
            guacRecipe.addCategory(catMexican.get());
            guacRecipe.addCategory(catAmerican.get());
            guacRecipe.setDirections("1 Cut avocado, remove flesh: Cut the avocados in half. Remove seed. Score the inside of the avocado with a blunt knife and scoop out the flesh with a spoon. (See How to Cut and Peel an Avocado.) Place in a bowl.\n"
            );
                   /* +
                   /* "\n" +
                    "2 Mash with a fork: Using a fork, roughly mash the avocado. (Don't overdo it! The guacamole should be a little chunky.)\n" +
                    "\n" +
                    "3 Add salt, lime juice, and the rest: Sprinkle with salt and lime (or lemon) juice. The acid in the lime juice will provide some balance to the richness of the avocado and will help delay the avocados from turning brown.\n" +
                    "\n" +
                    "Add the chopped onion, cilantro, black pepper, and chiles. Chili peppers vary individually in their hotness. So, start with a half of one chili pepper and add to the guacamole to your desired degree of hotness.\n" +
                    "\n" +
                    "Remember that much of this is done to taste because of the variability in the fresh ingredients. Start with this recipe and adjust to your taste.\n" +
                    "\n" +
                    "4 Cover with plastic and chill to store: Place plastic wrap on the surface of the guacamole cover it and to prevent air reaching it. (The oxygen in the air causes oxidation which will turn the guacamole brown.) Refrigerate until ready to serve.\n" +
                    "\n" +
                    "Chilling tomatoes hurts their flavor, so if you want to add chopped tomato to your guacamole, add it just before serving.\n" +
                    "\n" +
                    "Variations\n" +
                    "\n" +
                    "For a very quick guacamole just take a 1/4 cup of salsa and mix it in with your mashed avocados.\n" +
                    "\n" +
                    "Feel free to experiment! One classic Mexican guacamole has pomegranate seeds and chunks of peaches in it (a Diana Kennedy favorite). Try guacamole with added pineapple, mango, or strawberries (see our Strawberry Guacamole).\n" +
                    "\n" +
                    "The simplest version of guacamole is just mashed avocados with salt. Don't let the lack of availability of other ingredients stop you from making guacamole.\n" +
                    "\n" +
                    "To extend a limited supply of avocados, add either sour cream or cottage cheese to your guacamole dip. Purists may be horrified, but so what? It tastes great.\n" +
                    "\n"
                    "For a deviled egg version with guacamole, try our Guacamole Deviled Eggs!"); */
        guacRecipe.addIngredient(new Ingredient("ripe avocados",new BigDecimal(2),eachUom));
        recipes.add(guacRecipe);
        recipes.add(chixRecipe);
        log.debug("Added recipes");
        return recipes;
        }
    }


