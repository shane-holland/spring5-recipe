package com.medsis.education.spring5recipeapp.bootstrap;

import com.medsis.education.spring5recipeapp.domain.*;
import com.medsis.education.spring5recipeapp.repositories.CategoryRepository;
import com.medsis.education.spring5recipeapp.repositories.RecipeRepository;
import com.medsis.education.spring5recipeapp.repositories.UnitOfMeasureRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.util.*;

@Slf4j
@Component
public class RecipeBootstrap implements ApplicationListener<ContextRefreshedEvent> {

    private CategoryRepository categoryRepository;
    private UnitOfMeasureRepository unitOfMeasureRepository;
    private RecipeRepository recipeRepository;

    public RecipeBootstrap(CategoryRepository categoryRepository, UnitOfMeasureRepository unitOfMeasureRepository, RecipeRepository recipeRepository) {
        this.categoryRepository = categoryRepository;
        this.unitOfMeasureRepository = unitOfMeasureRepository;
        this.recipeRepository = recipeRepository;
    }

    @Override
    @Transactional
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        initData();
    }


    private void initData() {
        log.debug("We're initializing the databases bo");

        recipeRepository.saveAll(getRecipes());


    }

    private List<Recipe> getRecipes() {
        List<Recipe> recipes = new ArrayList<>(2);

        // get UOMs
        Optional<UnitOfMeasure> eachUomOptional = unitOfMeasureRepository.findByDescription("Each");

        if (!eachUomOptional.isPresent()) {
            throw new RuntimeException("Expected UOM Not Found");
        }

        Optional<UnitOfMeasure> tablespoonUomOptional = unitOfMeasureRepository.findByDescription("Tablespoon");

        if (!tablespoonUomOptional.isPresent()) {
            throw new RuntimeException("Expected UOM Not Found");
        }

        Optional<UnitOfMeasure> teaspoonUomOptional = unitOfMeasureRepository.findByDescription("Teaspoon");

        if (!teaspoonUomOptional.isPresent()) {
            throw new RuntimeException("Expected UOM Not Found");
        }

        Optional<UnitOfMeasure> dashUomOptional = unitOfMeasureRepository.findByDescription("Dash");

        if (!dashUomOptional.isPresent()) {
            throw new RuntimeException("Expected UOM Not Found");
        }

        Optional<UnitOfMeasure> pintUomOptional = unitOfMeasureRepository.findByDescription("Pint");

        if (!pintUomOptional.isPresent()) {
            throw new RuntimeException("Expected UOM Not Found");
        }

        Optional<UnitOfMeasure> cupUomOptional = unitOfMeasureRepository.findByDescription("Cup");

        if (!cupUomOptional.isPresent()) {
            throw new RuntimeException("Expected UOM Not Found");
        }

        // get optionals
        UnitOfMeasure eachUom = eachUomOptional.get();
        UnitOfMeasure tablespoonUom = tablespoonUomOptional.get();
        UnitOfMeasure teaspoonUom = teaspoonUomOptional.get();
        UnitOfMeasure dashUom = dashUomOptional.get();
        UnitOfMeasure pintUom = pintUomOptional.get();
        UnitOfMeasure cupUom = cupUomOptional.get();

        // get Categories
        Optional<Category> americanCategoryOptional = categoryRepository.findByDescription("American");

        if (!americanCategoryOptional.isPresent()) {
            throw new RuntimeException("Expected Category Not Found");
        }

        Optional<Category> mexicanCategoryOptional = categoryRepository.findByDescription("Mexican");

        if (!mexicanCategoryOptional.isPresent()) {
            throw new RuntimeException("Expected Category Not Found");
        }

        Category americanCategory = americanCategoryOptional.get();
        Category mexicanCategory = mexicanCategoryOptional.get();


        // *********************************************************
        // Perfect Guacamole
        // *********************************************************

        Recipe guacRecipe = new Recipe();

        guacRecipe.setDescription("Perfect Guacamole");
        guacRecipe.setPrepTime(10);
        guacRecipe.setCookTime(0);
        guacRecipe.setServings(2);
        guacRecipe.setDifficulty(Difficulty.EASY);
        guacRecipe.setDirections("1 Cut avocado, remove flesh: Cut the avocados in half. Remove seed. Score the inside of the avocado with a blunt knife and scoop out the flesh with a spoon. (See How to Cut and Peel an Avocado.) Place in a bowl.\n" +
                "2 Mash with a fork: Using a fork, roughly mash the avocado. (Don't overdo it! The guacamole should be a little chunky.)\n" +
                "3 Add salt, lime juice, and the rest: Sprinkle with salt and lime (or lemon) juice. The acid in the lime juice will provide some balance to the richness of the avocado and will help delay the avocados from turning brown.\n" +
                "Add the chopped onion, cilantro, black pepper, and chiles. Chili peppers vary individually in their hotness. So, start with a half of one chili pepper and add to the guacamole to your desired degree of hotness.\n" +
                "Remember that much of this is done to taste because of the variability in the fresh ingredients. Start with this recipe and adjust to your taste.\n" +
                "4 Cover with plastic and chill to store: Place plastic wrap on the surface of the guacamole cover it and to prevent air reaching it. (The oxygen in the air causes oxidation which will turn the guacamole brown.) Refrigerate until ready to serve.\n" +
                "Chilling tomatoes hurts their flavor, so if you want to add chopped tomato to your guacamole, add it just before serving.");

        Notes guacNotes = new Notes();
        guacNotes.setRecipeNotes("Guacamole, a dip made from avocados, is originally from Mexico. The name is derived from two Aztec Nahuatl words—ahuacatl (avocado) and molli (sauce).\n" +
                "All you really need to make guacamole is ripe avocados and salt. After that, a little lime or lemon juice—a splash of acidity to balance the richness of the avocado. Then comes chopped cilantro, chiles, onion, and tomato, if you want.\n" +
                "The trick to making perfect guacamole is using good, ripe avocados. Check for ripeness by gently pressing the outside of the avocado. If there is no give, the avocado is not ripe yet and will not taste good. If there is a little give, the avocado is ripe. If there is a lot of give, the avocado may be past ripe and not good. In this case, taste test first before using.");

        guacRecipe.setNotes(guacNotes);

        guacRecipe.addIngredient(new Ingredient("Ripe Avocados", BigDecimal.valueOf(2), eachUom));
        guacRecipe.addIngredient(new Ingredient("Kosher Salt", BigDecimal.valueOf(.5), teaspoonUom));
        guacRecipe.addIngredient(new Ingredient("Fresh Lime or Lemon Juice", BigDecimal.valueOf(1), tablespoonUom));
        guacRecipe.addIngredient(new Ingredient("Minced Red Onion or Thinly Sliced Green Onion", BigDecimal.valueOf(.25), tablespoonUom));
        guacRecipe.addIngredient(new Ingredient("Serrano Chiles, Stems and Seeds removed, minced ", BigDecimal.valueOf(2), eachUom));
        guacRecipe.addIngredient(new Ingredient("Cilantro (leaves and tender stems), finely chopped", BigDecimal.valueOf(2), tablespoonUom));
        guacRecipe.addIngredient(new Ingredient("Freshly Grated Black Pepper", BigDecimal.valueOf(1), dashUom));
        guacRecipe.addIngredient(new Ingredient("Ripe Tomato, seeds and pulp removed, chopped", BigDecimal.valueOf(.5), eachUom));

        guacRecipe.getCategories().add(americanCategory);
        guacRecipe.getCategories().add(mexicanCategory);

        // add to return list
        recipes.add(guacRecipe);


        // *********************************************************
        // Spicy Grilled Chicken Tacos
        // *********************************************************

        Recipe tacoRecipe = new Recipe();

        tacoRecipe.setDescription("Spicy Grilled Chicken Tacos");
        tacoRecipe.setPrepTime(20);
        tacoRecipe.setCookTime(15);
        tacoRecipe.setServings(5);
        tacoRecipe.setDifficulty(Difficulty.MODERATE);

        tacoRecipe.setDirections("1 Prepare a gas or charcoal grill for medium-high, direct heat.\n" +
                "2 Make the marinade and coat the chicken: In a large bowl, stir together the chili powder, oregano, cumin, sugar, salt, garlic and orange zest. Stir in the orange juice and olive oil to make a loose paste. Add the chicken to the bowl and toss to coat all over.\n" +
                "Set aside to marinate while the grill heats and you prepare the rest of the toppings.\n" +
                "Spicy Grilled Chicken Tacos\n" +
                "3 Grill the chicken: Grill the chicken for 3 to 4 minutes per side, or until a thermometer inserted into the thickest part of the meat registers 165F. Transfer to a plate and rest for 5 minutes.\n" +
                "4 Warm the tortillas: Place each tortilla on the grill or on a hot, dry skillet over medium-high heat. As soon as you see pockets of the air start to puff up in the tortilla, turn it with tongs and heat for a few seconds on the other side.\n" +
                "Wrap warmed tortillas in a tea towel to keep them warm until serving.\n" +
                "5 Assemble the tacos: Slice the chicken into strips. On each tortilla, place a small handful of arugula. Top with chicken slices, sliced avocado, radishes, tomatoes, and onion slices. Drizzle with the thinned sour cream. Serve with lime wedges.");

        Notes tacoNotes = new Notes();
        tacoNotes.setRecipeNotes("We have a family motto and it is this: Everything goes better in a tortilla.\n" +
                "Any and every kind of leftover can go inside a warm tortilla, usually with a healthy dose of pickled jalapenos. I can always sniff out a late-night snacker when the aroma of tortillas heating in a hot pan on the stove comes wafting through the house.\n" +
                "Today’s tacos are more purposeful – a deliberate meal instead of a secretive midnight snack!\n" +
                "First, I marinate the chicken briefly in a spicy paste of ancho chile powder, oregano, cumin, and sweet orange juice while the grill is heating. You can also use this time to prepare the taco toppings.\n" +
                "Grill the chicken, then let it rest while you warm the tortillas. Now you are ready to assemble the tacos and dig in. The whole meal comes together in about 30 minutes!");

        tacoRecipe.setNotes(tacoNotes);

        // For the chicken
        tacoRecipe.addIngredient(new Ingredient("Ancho Chili Powder", BigDecimal.valueOf(2), tablespoonUom));
        tacoRecipe.addIngredient(new Ingredient("Dried Oregano", BigDecimal.valueOf(1), teaspoonUom));
        tacoRecipe.addIngredient(new Ingredient("Dried Cumin", BigDecimal.valueOf(1), teaspoonUom));
        tacoRecipe.addIngredient(new Ingredient("Sugar", BigDecimal.valueOf(1), teaspoonUom));
        tacoRecipe.addIngredient(new Ingredient("Salt", BigDecimal.valueOf(.5), teaspoonUom));
        tacoRecipe.addIngredient(new Ingredient("Clove Garlic, finely chopped", BigDecimal.valueOf(1), eachUom));
        tacoRecipe.addIngredient(new Ingredient("Finely Graded Orange Zest", BigDecimal.valueOf(1), tablespoonUom));
        tacoRecipe.addIngredient(new Ingredient("Fresh-Squeezed Orange Juice", BigDecimal.valueOf(3), tablespoonUom));
        tacoRecipe.addIngredient(new Ingredient("Olive Oil", BigDecimal.valueOf(3), tablespoonUom));
        tacoRecipe.addIngredient(new Ingredient("Skinless, Boneless Chicken Thighs (1 ¼ lbs)", BigDecimal.valueOf(5), eachUom));

        // To serve
        tacoRecipe.addIngredient(new Ingredient("Small Corn Tortillas", BigDecimal.valueOf(8), eachUom));
        tacoRecipe.addIngredient(new Ingredient("Packed Baby Arugula (3 ounces)", BigDecimal.valueOf(3),cupUom));
        tacoRecipe.addIngredient(new Ingredient("Ripe Avocados", BigDecimal.valueOf(2), eachUom));
        tacoRecipe.addIngredient(new Ingredient("Radishes, Thinly Sliced", BigDecimal.valueOf(4), eachUom));
        tacoRecipe.addIngredient(new Ingredient("Cherry Tomatoes, Halved", BigDecimal.valueOf(.5), pintUom));
        tacoRecipe.addIngredient(new Ingredient("Red Onion, Thinly Sliced", BigDecimal.valueOf(.25), eachUom));
        tacoRecipe.addIngredient(new Ingredient("Cilantro, Roughly Chopped", BigDecimal.valueOf(1), eachUom));
        tacoRecipe.addIngredient(new Ingredient("Sour Cream thinned with ¼ Cup Milk", BigDecimal.valueOf(.5), cupUom));
        tacoRecipe.addIngredient(new Ingredient("Lime, Cut into Wedges", BigDecimal.valueOf(1), eachUom));

        tacoRecipe.getCategories().add(americanCategory);
        tacoRecipe.getCategories().add(mexicanCategory);

        // add to return list
        recipes.add(tacoRecipe);


        return recipes;
    }

}
