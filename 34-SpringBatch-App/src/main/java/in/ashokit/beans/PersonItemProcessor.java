package in.ashokit.beans;

import org.jspecify.annotations.Nullable;
import org.springframework.batch.infrastructure.item.ItemProcessor;

import in.ashokit.record.Person;

public class PersonItemProcessor implements ItemProcessor<Person, Person> {

	@Override
	public @Nullable Person process(Person item) throws Exception {

		String firstName = item.firstName().toUpperCase();
		String lastName = item.lastName().toUpperCase();

		Person transformedPerson = new Person(firstName, lastName);

		return transformedPerson;
	}
}
