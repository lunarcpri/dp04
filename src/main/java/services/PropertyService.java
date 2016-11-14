package services;

import domain.Property;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;
import repositories.PropertyRepository;
import security.UserAccount;
import security.UserAccountService;

import java.util.Collection;

@Service
@Transactional
public class PropertyService {

    @Autowired
    private PropertyRepository propertyRepository;

    @Autowired
    private UserAccountService userAccountService;


    public PropertyService(){
        super();
    }

    public Property findOne(int id){
        Property result;

        result = propertyRepository.findOne(id);
        Assert.notNull(result);

        return result;
    }

    public Collection<Property> findAll(){
        Collection<Property> result;

        result = propertyRepository.findAll();
        Assert.notNull(result);

        return result;
    }

    public void save(Property property){
        Assert.notNull(property);

        propertyRepository.save(property);
    }

    public void create(Property property){
        userAccountService.assertRole("NUTRITIONIST");
        Assert.notNull(property);

        save(property);
    }


    public void modify(Property property){
        userAccountService.assertRole("NUTRITIONIST");
        Assert.notNull(property);
        Assert.isTrue(property.getIngredients().size()==0);

        save(property);
    }
}
