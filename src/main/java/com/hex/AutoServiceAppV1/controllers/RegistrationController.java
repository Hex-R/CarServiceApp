package com.hex.AutoServiceAppV1.controllers;

import com.hex.AutoServiceAppV1.models.User;
import com.hex.AutoServiceAppV1.models.dto.CaptchaResponseDto;
import com.hex.AutoServiceAppV1.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import javax.validation.Valid;
import java.util.Collections;

@Controller
@RequestMapping("/register")
public class RegistrationController {

    @Autowired
    private UserService userService = new UserService();

    @Autowired
    private RestTemplate restTemplate;

    @Value("${recaptcha.secret}")
    private String recaptchaSecret;

    @Value("${recaptcha.url}")
    private String recaptchaUrl;

    @GetMapping
    public String showRegistrationForm(@ModelAttribute("user") User user) {
        return "registration";
    }

    @PostMapping
    public String processRegistration(@Valid @ModelAttribute User user,
                                      BindingResult bindingResult,
                                      Model model,
                                      @RequestParam("passwordConfirmation") String passwordConfirmation,
                                      @RequestParam("g-recaptcha-response") String recaptchaClientResponse) {

        String url = String.format(recaptchaUrl, recaptchaSecret, recaptchaClientResponse);
        CaptchaResponseDto recaptchaServerResponse = restTemplate.postForObject(url, Collections.emptyList(), CaptchaResponseDto.class);

        if (!recaptchaServerResponse.isSuccess()){
            model.addAttribute("recaptchaError", "Пройдите капчу");
        }

        boolean isPasswordConfirmationIncorrect = !user.getPassword().equals(passwordConfirmation);

        if (isPasswordConfirmationIncorrect){
            model.addAttribute("passwordConfirmationError", "Пароли не совпадают");
        }

        if (bindingResult.hasErrors() || isPasswordConfirmationIncorrect || !recaptchaServerResponse.isSuccess())
            return "registration";

        if (userService.addUser(user)) {
            return "redirect:/login";
        } else {
            model.addAttribute("usernameError", "Пользователь с таким логином уже существует");
            return "registration";
        }
    }

    @GetMapping("/activation/{code}")
    public String activateAccount(Model model, @PathVariable String code) {

        boolean isActivated = userService.activateUser(code);

        if (isActivated) {
            model.addAttribute("messageType", "success");
            model.addAttribute("message", "User successfully activated");
        } else {
            model.addAttribute("messageType", "danger");
            model.addAttribute("message", "Activation code not found");
        }

        return "/login";
    }
}
