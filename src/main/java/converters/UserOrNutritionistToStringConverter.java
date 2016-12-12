package converters;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import domain.UserOrNutritionist;

@Component
@Transactional
public class UserOrNutritionistToStringConverter implements Converter<UserOrNutritionist, String> {

	@Override
	public String convert(UserOrNutritionist userOrNutritionist) {
		String result;

		if (userOrNutritionist == null)
			result = null;
		else
			result = String.valueOf(userOrNutritionist.getId());

		return result;
	}

}
