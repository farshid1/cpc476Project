package com.codeiners.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.Hashtable;
import java.util.Map;

/**
 * Created by razorhead on 10/25/14.
 */
@Controller
public class AuthenticationController {

    //@Autowired
    //UserRepository userRepository;

    private static final Map<String, String> userDatabase = new Hashtable<String, String>();

    static {
        userDatabase.put("Nicholas@derp.com", "password");
        userDatabase.put("Sarah@derp.com", "drowssap");
        userDatabase.put("Mike@derp.com", "wordpass");
        userDatabase.put("John", "green");
        userDatabase.put("fa@derp.com", "fa");
    }

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String index(Model model, HttpSession session) {
        if (session.getAttribute("email") != null) {
            return "redirect:/derp";
        }
        model.addAttribute("user", new User());
        return "index";
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public String login(@Valid User user, BindingResult bindingResult, Model model, HttpSession session)
    {
        if (bindingResult.hasErrors()) return "index";

        if (user.getEmail() == null || user.getPassword() == null ||
                !userDatabase.containsKey(user.getEmail()) ||
                !user.getPassword().equals(userDatabase.get(user.getEmail())))
        {
            model.addAttribute("loginFailed", true);
            return "index";
        }

        session.setAttribute("email", user.getEmail());
        return "redirect:/derp";

    }


    @RequestMapping(value = "/register", method = RequestMethod.GET)
    public String showRegister(Model model) {
        model.addAttribute("user", new User());
        return "register";
    }

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public String register(@Valid User user, BindingResult bindingResult, Model model)
    {
        if (bindingResult.hasErrors()) return "register";

        this.userDatabase.put(user.getEmail(), user.getPassword());
        return "index";

    }

    @RequestMapping(value = "/logout", method = RequestMethod.GET)
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/";
    }
}
