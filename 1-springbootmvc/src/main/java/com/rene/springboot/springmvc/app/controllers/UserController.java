package com.rene.springboot.springmvc.app.controllers;

import com.rene.springboot.springmvc.app.entities.User;
import com.rene.springboot.springmvc.app.services.UserService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Optional;

@Controller
@RequestMapping("/users")
@SessionAttributes({"user"})
public class UserController {

    private final UserService service;

    public UserController(UserService service) {
        this.service = service;
    }

    @GetMapping("/view")
    public String view(Model model) {

        model.addAttribute("title","Hola mundo spring boot");
        model.addAttribute("message","Este es un mensaje de ejemplo para la vista");
        model.addAttribute("user",new User("User","Test"));
        return "view";

    }

    @GetMapping("/list")
    public String list(Model model){
        model.addAttribute("title","Listado de usuarios");
        model.addAttribute("users", service.findAll());
        return "list";
    }

    @GetMapping("/form")
    public String form(Model model){
        model.addAttribute("title","Formulario de Creacion de usuario");
        model.addAttribute("user", new User());
        return "form";
    }

    @GetMapping("/form/{id}")
    public String form(@PathVariable Long id, Model model, RedirectAttributes redirect){
        Optional<User> optionalUser = service.findById(id);
        if (optionalUser.isPresent()){
            model.addAttribute("title","Formulario para editar usuario");
            model.addAttribute("user", new User());
            return "form";
        } else {
            redirect.addFlashAttribute("errorMessage","El usuario "+id+" no existe");
            return "redirect:/users/list";
        }

    }

    @PostMapping
    public String form(@Valid User user, BindingResult result, Model model, RedirectAttributes redirect, SessionStatus status){

        if (result.hasErrors()){
            model.addAttribute("title", (user.getId() != null && user.getId()>0)?"Formulario para editar usuario":"Formulario de Creacion de usuario");
            return "form";
        }
        String message = (user.getId() != null && user.getId()>0)?"Usuario editado con exito" : "Usuario creado con exito";
        service.save(user);
        status.setComplete();
        redirect.addFlashAttribute("messageSuccess",message);
        return "redirect:/users/list";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable Long id, RedirectAttributes redirect) {
        Optional<User> optionalUser = service.findById(id);
        if (optionalUser.isPresent()) {
            redirect.addFlashAttribute("messageSuccess","Usuario eliminado con exito");
            service.deleteById(id);
            return "redirect:/users/list";

        }
        redirect.addFlashAttribute("errorMessage","El usuario no existe");
        return "redirect:/users/list";
    }

}
