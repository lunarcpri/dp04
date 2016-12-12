package converters;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import repositories.DomainEntityRepository;
import domain.DomainEntity;

@Component
@Transactional
public class StringToDomainEntityConverter implements Converter<String, DomainEntity> {

	@Autowired
	DomainEntityRepository domainEntityRepository;

	@Override
	public DomainEntity convert(String text) {
		DomainEntity result;
		int id;

		try {
			id = Integer.valueOf(text);
			result = domainEntityRepository.findOne(id);
		} catch (Throwable oops) {
			throw new IllegalArgumentException(oops);
		}

		return result;
	}

}
