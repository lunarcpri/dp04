package domain;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;

@Entity
@Access(AccessType.PROPERTY)
public class Quantity extends domain.DomainEntity
{
    private enum Unit {
        gram, kilograms, ounces, pounds, militres, litres, spoons, cups, pieces
    }

    private Unit unit;
    private double quantity;
    private Ingredient ingredient;
    private Recipe recipe;

    public Quantity()
    {
        super();
    }

    @Enumerated(EnumType.STRING)
    public Unit getUnit()
    {
        return unit;
    }

    public void setUnit(Unit unit)
    {
        this.unit = unit;
    }

    public double getQuantity() {
        return quantity;
    }

    public void setQuantity(double quantity) {
        this.quantity = quantity;
    }



    //Relantionships


    @Valid
    @ManyToOne(optional = false)
    @NotNull
    public Ingredient getIngredient()
    {
        return this.ingredient;
    }

    public void setIngredient(Ingredient ingredient)
    {
        this.ingredient = ingredient;
    }



    @Valid
    @ManyToOne(optional = false)
    @NotNull
    public Recipe getRecipe() {
        return recipe;
    }

    public void setRecipe(Recipe recipe) {
        this.recipe = recipe;
    }





}
