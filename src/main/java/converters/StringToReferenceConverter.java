package converters;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import repositories.ReferenceRepository;
import domain.Reference;

@Component
@Transactional
public class StringToReferenceConverter implements Converter<String, Reference> {

	@Autowired
	ReferenceRepository referenceRepository;

	@Override
	public Reference convert(String text) {
		Reference result;
		int id;

		try {
			id = Integer.valueOf(text);
			result = referenceRepository.findOne(id);
		} catch (Throwable oops) {
			throw new IllegalArgumentException(oops);
		}

		return result;
	}

}
