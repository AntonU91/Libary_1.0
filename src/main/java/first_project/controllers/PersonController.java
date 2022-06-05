package first_project.controllers;

import first_project.models.Person;
import first_project.service.PersonService;
import first_project.util.PersonValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
public class PersonController {
    private final PersonService personService;
    private final PersonValidator personValidator;

    @Autowired
    public PersonController(PersonService personService, PersonValidator personValidator) {
        this.personService = personService;
        this.personValidator = personValidator;
    }

    @GetMapping("/people")
    public String showAllPeople(Model model) {
        model.addAttribute("people", personService.getAllPeople());
        return "person/people";
    }

    @GetMapping("/people/new")
    public String newPerson(@ModelAttribute("newPerson") Person person) {

        //personService.addPerson(person);
        return "person/registered-form";
    }

    @PostMapping("/creat-new-person")
    public String createNewPerson(@ModelAttribute("newPerson") @Valid Person person, BindingResult bindingResult) {
        personValidator.validate(person, bindingResult);
        if (bindingResult.hasErrors()) {
            return "person/registered-form";
        }
        personService.addPerson(person);
        return "redirect:/people";
    }

    @GetMapping("/people/{id}")
    public String showPersonInfo(@PathVariable String id, Model model) {

        model.addAttribute("person", personService.getPersonById(Integer.parseInt(id)));
        return "person/person-info";

    }

    @GetMapping("/people/{id}/edit")
    public String updatePersonInfo(@PathVariable String id, Model model) {
        Person person = personService.getPersonById(Integer.parseInt(id));
        model.addAttribute("person", person);
        return "person/update-person";
    }

    @PatchMapping("/update-person/{id}")
    public String executeUpdatingPersonInfo(@ModelAttribute("person") @Valid Person person, BindingResult bindingResult, @PathVariable String id) {
        personService.updatePerson(Integer.parseInt(id), person);
        personValidator.validate(person, bindingResult);
        if (bindingResult.hasErrors()) return "person/update-person";
        return "redirect:/people/{id}";
    }

    @DeleteMapping("/delete-person/{id}")
    public String deletePerson(@PathVariable String id) {
        personService.deletePerson(Integer.parseInt(id));
        return "redirect:/people";
    }


}
