package com.hex.AutoServiceAppV1.controllers;

import com.hex.AutoServiceAppV1.models.User;
import com.hex.AutoServiceAppV1.models.UserDetailsForm;
import com.hex.AutoServiceAppV1.repositories.ServiceOrderRepository;
import com.hex.AutoServiceAppV1.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

@Controller
@RequestMapping("/user_profile")
public class UserProfileController {

    @Autowired
    ServiceOrderRepository serviceOrderRepository;

    @Autowired
    private UserService userService;

    @GetMapping
    public String showUserProfilePage(@AuthenticationPrincipal User user, Model model) {

        addOrdersToModel(model, user);

        model.addAttribute("user", user);
        model.addAttribute("userDetailsForm", new UserDetailsForm(user));

        return "user_profile";
    }

    //Здесь должен быть PatchMapping
    @PostMapping
    public String updateUserProfile(@AuthenticationPrincipal User user,
                                    @Valid UserDetailsForm userDetailsForm,
                                    BindingResult bindingResult,
                                    Model model) {

        boolean isPasswordConfirmationIncorrect = !userDetailsForm.getPassword().equals(userDetailsForm.getPasswordConfirmation());

        if (isPasswordConfirmationIncorrect) {
            model.addAttribute("passwordConfirmationError", "Пароли не совпадают");
        }

        if (bindingResult.hasErrors() || isPasswordConfirmationIncorrect) {
            addOrdersToModel(model, user);
            model.addAttribute("isValidating", true);
            return "user_profile";
        }

        if (userService.updateUser(user, userDetailsForm)){
            addOrdersToModel(model, user);
            model.addAttribute("isValidating", true);
            model.addAttribute("userUpdateSuccess", "Данные успешно изменены");
        }else {
            addOrdersToModel(model, user);
            model.addAttribute("isValidating", true);
            model.addAttribute("passwordError", "Необходимо 6 - 30 символов");
        }

        return "user_profile";
    }

    private void addOrdersToModel(Model model, User user) {
        model.addAttribute("activeOrders", serviceOrderRepository.findByUserIdAndIsCompleted(user.getId(), false));
        model.addAttribute("completedOrders", serviceOrderRepository.findByUserIdAndIsCompleted(user.getId(), true));
    }
}
