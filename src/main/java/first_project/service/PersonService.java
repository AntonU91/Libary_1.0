package first_project.service;

import first_project.models.Person;

import java.util.List;

public interface PersonService {
    public Person getPersonById (int id);
    public List<Person> getAllPeople ();
    public void addPerson (Person person);
    public void updatePerson (int id, Person updatedPerson);
    public void deletePerson (int id);
}
