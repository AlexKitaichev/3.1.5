package ru.kata.spring.boot_security.demo.services;


import org.springframework.context.annotation.Lazy;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.kata.spring.boot_security.demo.models.Person;
import ru.kata.spring.boot_security.demo.repositories.PersonRepository;

import java.util.List;

@Service
public class PersonServiceImpl implements PersonService {

    private final PersonRepository personRepository;
    private final PasswordEncoder passwordEncoder;

    public PersonServiceImpl(PersonRepository personRepository, @Lazy PasswordEncoder passwordEncoder) {
        this.personRepository = personRepository;
        this.passwordEncoder = passwordEncoder;
    }


    @Override
    @Transactional(readOnly = true)
    public List<Person> getAllPersons() {
        return personRepository.findAll();
    }

    @Override
    @Transactional
    public Person getPersonById(Long id) {
        return personRepository.findById(id).get();
    }

    @Override
    @Transactional
    public Person getPersonByLogin(String login) {
        return personRepository.findByLogin(login);
    }


    @Override
    @Transactional
    public void savePerson(Person person) {
        person.setPassword(passwordEncoder.encode(person.getPassword()));
        personRepository.save(person);
    }


    @Override
    @Transactional
    public void updatePerson(Person updatePerson) {
        updatePerson.setPassword(passwordEncoder.encode(updatePerson.getPassword()));
        personRepository.save(updatePerson);
    }


    @Override
    @Transactional
    public void deletePerson(Long id) {personRepository.deleteById(id);
    }


}
