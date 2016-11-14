package domain;

import org.hibernate.validator.constraints.NotBlank;

import javax.persistence.*;
import javax.validation.Valid;
import java.util.Collection;

@Entity
@Access(AccessType.PROPERTY)
public class Ingredient extends domain.DomainEntity{

    private String name;
    private String description;
    private Collection<Property> properties;
    private Collection<Quantity> quantities;

    public Ingredient()
    {
        super();
    }

    @NotBlank
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @NotBlank
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }


    //Relationships

    @Valid
    @OneToMany(mappedBy = "ingredient")
    public Collection<Quantity> getQuantities()
    {
        return this.quantities;
    }

    public void setQuantities(Collection<Quantity> quantities)
    {
        this.quantities = quantities;
    }


    @Valid
    @ManyToMany
    public Collection<Property> getProperties()
    {
        return this.properties;
    }

    public void setProperties(Collection<Property> properties)
    {
        this.properties = properties;
    }

}
