package ru.adel.springcourse.SpringSecurityapp.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import ru.adel.springcourse.SpringSecurityapp.models.Person;
import ru.adel.springcourse.SpringSecurityapp.services.PeopleService;
import ru.adel.springcourse.SpringSecurityapp.services.PersonDetailsService;
@Component
public class PersonValidator implements Validator {
    private final PersonDetailsService personDetailsService;
    private final PeopleService peopleService;

    @Autowired
    public PersonValidator(PersonDetailsService personDetailsService, PeopleService peopleService) {
        this.personDetailsService = personDetailsService;
        this.peopleService = peopleService;
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return Person.class.equals(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
        Person person =(Person) o;
        if(peopleService.findByUsername(person.getUsername()).isPresent())
            errors.rejectValue("username","","Человек с таким именем существует");

    }
}
