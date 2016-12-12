package converters;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import domain.Reference;

@Component
@Transactional
public class ReferenceToStringConverter implements Converter<Reference, String> {

	@Override
	public String convert(Reference reference) {
		String result;

		if (reference == null)
			result = null;
		else
			result = String.valueOf(reference.getId());

		return result;
	}

}
