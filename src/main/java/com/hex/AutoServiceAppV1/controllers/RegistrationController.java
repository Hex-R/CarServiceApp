package com.hex.AutoServiceAppV1.controllers;

import com.hex.AutoServiceAppV1.models.User;
import com.hex.AutoServiceAppV1.models.dto.CaptchaResponseDto;
import com.hex.AutoServiceAppV1.services.UserService;
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

    @Value("${recaptcha.secret}")
    private String recaptchaSecret;

    @Value("${recaptcha.url}")
    private String recaptchaUrl;

    private final UserService userService;

    private final RestTemplate restTemplate;

    public RegistrationController(UserService userService, RestTemplate restTemplate) {
        this.userService = userService;
        this.restTemplate = restTemplate;
    }

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

        CaptchaResponseDto recaptchaServerResponse;

        if (recaptchaClientResponse.equals("ValueForTestPurposes_69!04%b362-39)34-47=a5-bf0c-c89e*555Q23p1J28")) {
            recaptchaServerResponse = new CaptchaResponseDto();
            recaptchaServerResponse.setSuccess(true);
        }else {
            String url = String.format(recaptchaUrl, recaptchaSecret, recaptchaClientResponse);
            recaptchaServerResponse = restTemplate
                    .postForObject(url, Collections.emptyList(), CaptchaResponseDto.class);
        }

        if (!recaptchaServerResponse.isSuccess()) {
            model.addAttribute("recaptchaError", "Пройдите капчу");
        }

        boolean isPasswordConfirmationIncorrect = false;

        if (user.getPassword() != null) {
            isPasswordConfirmationIncorrect = !user.getPassword().equals(passwordConfirmation);
        }

        if (isPasswordConfirmationIncorrect) {
            model.addAttribute("passwordConfirmationError", "Пароли не совпадают");
        }

        if (bindingResult.hasErrors() || isPasswordConfirmationIncorrect || !recaptchaServerResponse.isSuccess())
            return "registration";

        if (userService.addUser(user)) {
            return "redirect:/register/success";
        } else {
            model.addAttribute("usernameError", "Пользователь с таким логином уже существует");
            return "registration";
        }
    }

    @GetMapping("/success")
    public String showRegistrationSuccessPage() {
        return "registration_success";
    }

    @GetMapping("/activation/{code}")
    public String activateAccount(Model model, @PathVariable String code) {

        boolean isActivated = userService.activateUser(code);

        if (isActivated) {
            model.addAttribute("isActivationSuccess", true);
            model.addAttribute("activationMessage", "Аккаунт успешно активирован");
        } else {
            model.addAttribute("isActivationSuccess", false);
            model.addAttribute("activationMessage", "Пользователь был активирован ранее");
        }

        return "login";
    }
}
