package converters;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import domain.Star;

@Component
@Transactional
public class StarToStringConverter implements Converter<Star, String> {

	@Override
	public String convert(Star star) {
		String result;

		if (star == null)
			result = null;
		else
			result = String.valueOf(star.getId());

		return result;
	}

}
