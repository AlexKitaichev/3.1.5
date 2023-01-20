package ru.kata.spring.boot_security.demo.controllers;


import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.kata.spring.boot_security.demo.models.Person;
import ru.kata.spring.boot_security.demo.models.Role;
import ru.kata.spring.boot_security.demo.services.PersonService;
import ru.kata.spring.boot_security.demo.services.RoleService;

import java.util.List;

@Controller
@RequestMapping(value = "/admin")
public class AdminControllers {

    private final PersonService personService;
    private final RoleService roleService;

    public AdminControllers(PersonService personService, RoleService roleService) {
        this.personService = personService;
        this.roleService = roleService;
    }

    @GetMapping("/all")
    public String showAllPerson(Model model, @AuthenticationPrincipal Person principal) {
        List<Person> allPersons = personService.getAllPersons();
        model.addAttribute("allPersons", allPersons);
        model.addAttribute("principal", principal);
        return "admin/allPersons";
    }

    @GetMapping("/new")
    public String newUser(Model model, @ModelAttribute("person") Person person) {
        List<Role> roles = roleService.getUniqAllRoles();
        model.addAttribute("rolesAdd", roles);
        return "admin/allPersons";
    }

    @PostMapping("/new")
    public String createUser(Person person) {
        personService.savePerson(person);
        return "redirect:/admin/all";
    }




    @GetMapping("/{id}/edit")
    public String edit(Model model, @PathVariable("id") Long id) {
        model.addAttribute("person", personService.getPersonById(id));
        List<Role> roles = roleService.getUniqAllRoles();
        model.addAttribute("rolesUpdate", roles);
        return "admin/allPersons";
    }

    @PatchMapping("/{id}")
    public String update(@ModelAttribute("person") Person person) {
        personService.updatePerson(person);
        return "redirect:/admin/all";
    }


    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") Long id) {
        personService.deletePerson(id);
        return "redirect:/admin/all";
    }
}

