package first_project.controllers;

import first_project.models.Person;
import first_project.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class PersonController {
    private final PersonService personService;

    @Autowired
    public PersonController(PersonService personService) {
        this.personService = personService;
    }

    @GetMapping("/people")
    public String showAllPeople(Model model) {
        model.addAttribute("people", personService.getAllPeople());
        return "person/people";
    }

    @GetMapping ("/people/new")
    public String newPerson (@ModelAttribute("newPerson")Person person) {
        //personService.addPerson(person);
        return "person/registered-form";
    }

    @PostMapping("/creat-new-person")
    public String createNewPerson (@ModelAttribute ("newPerson") Person person) {
        personService.addPerson(person);
        return "redirect:/people";
    }





}
