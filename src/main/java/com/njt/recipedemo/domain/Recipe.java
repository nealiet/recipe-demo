package com.njt.recipedemo.domain;

import lombok.*;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
@Entity
public class Recipe {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;

    private String description;
    private Integer prepTime;
    private Integer cookTime;
    private Integer servings;
    private String source;
    private String url;
    private String directions;

    @Lob
    private Byte[] image;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name="recipe_id")
    private Set<Ingredient> ingredients = new HashSet<>();

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="recipe_id")
    private Notes notes;

    @Enumerated(value=EnumType.STRING)
    private Difficulty difficulty;

    @ManyToMany
    @JoinTable(name="recipe_category",
            joinColumns= @JoinColumn(name="recipe_id"),
            inverseJoinColumns = @JoinColumn(name="category_id")
    )
    private Set<Category> categories=new HashSet<>();

    //-----------------------------------------------------

    public Recipe(String description, Integer prepTime, Integer cookTime, Integer servings, String source, String url, Difficulty difficulty) {
        this.description = description;
        this.prepTime = prepTime;
        this.cookTime = cookTime;
        this.servings = servings;
        this.source = source;
        this.url = url;
        this.difficulty = difficulty;
    }

    public void addIngredient(Ingredient ingredient) {
        this.ingredients.add(ingredient);
    }

    public void addCategory(Category category) {
        this.categories.add(category);
    }

}
