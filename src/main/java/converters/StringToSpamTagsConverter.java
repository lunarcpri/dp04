package converters;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import repositories.SpamTagsRepository;
import domain.SpamTags;

@Component
@Transactional
public class StringToSpamTagsConverter implements Converter<String, SpamTags> {

	@Autowired
	SpamTagsRepository spamTagsRepository;

	@Override
	public SpamTags convert(String text) {
		SpamTags result;
		int id;

		try {
			id = Integer.valueOf(text);
			result = spamTagsRepository.findOne(id);
		} catch (Throwable oops) {
			throw new IllegalArgumentException(oops);
		}

		return result;
	}

}
