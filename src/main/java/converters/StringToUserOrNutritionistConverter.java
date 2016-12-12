package converters;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import repositories.UserOrNutritionistRepository;
import domain.UserOrNutritionist;

@Component
@Transactional
public class StringToUserOrNutritionistConverter implements Converter<String, UserOrNutritionist> {

	@Autowired
	UserOrNutritionistRepository userOrNutritionistRepository;

	@Override
	public UserOrNutritionist convert(String text) {
		UserOrNutritionist result;
		int id;

		try {
			id = Integer.valueOf(text);
			result = userOrNutritionistRepository.findOne(id);
		} catch (Throwable oops) {
			throw new IllegalArgumentException(oops);
		}

		return result;
	}

}
