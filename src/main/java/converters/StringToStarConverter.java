package converters;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import repositories.StarRepository;
import domain.Star;

@Component
@Transactional
public class StringToStarConverter implements Converter<String, Star> {

	@Autowired
	StarRepository starRepository;

	@Override
	public Star convert(String text) {
		Star result;
		int id;

		try {
			id = Integer.valueOf(text);
			result = starRepository.findOne(id);
		} catch (Throwable oops) {
			throw new IllegalArgumentException(oops);
		}

		return result;
	}

}
