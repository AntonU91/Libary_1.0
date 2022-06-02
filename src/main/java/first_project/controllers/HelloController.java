package first_project.controllers;

import first_project.dao.PersonDao;
import first_project.models.Person;
import first_project.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.ArrayList;
import java.util.List;

@Controller
public class HelloController {
    private final PersonService personService;

    @Autowired
    public HelloController(PersonService personService) {
        this.personService = personService;
    }

    @GetMapping("/hello")
    public String greeting() {
        return "hello";
    }


    @GetMapping("/person/{id}")
    public String showPerson(@PathVariable(name = "id") String id, Model model) {
        int parsedId = Integer.parseInt(id);
        Person person = personService.getPersonById(parsedId);
       // System.out.println(person.getFullName());
        model.addAttribute("user", person.getFullName());
        return "person";
    }

    @GetMapping("/allPeople")
    public String showAllPeople (Model model) {
        return "allPeople";
    }



    @ModelAttribute ("person")
    Person createDefault () {
        Person person = new Person();
        person.setFullName("guest");
        return person;
    }

    @ModelAttribute ("personList")
    List<Person> createPersonList () {
        List<Person> personList =  new ArrayList<>();
        personList = personService.getAllPeople();
        return personList;
    }



}
