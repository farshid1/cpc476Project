package com.codeiners.derp;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by razorhead on 10/26/14.
 */
@Controller
public class DerpController{
    private Map<String, List<String>> contactDatabase = new LinkedHashMap<String, List<String>>();

    @RequestMapping(value = "/derp", method = RequestMethod.GET)
    public String listContacts( Model model, HttpSession session) {

        String myEmail = (String) session.getAttribute("email");

//        for (int i = 0; i < 5; i++) {
//            System.out.println(this.contactDatabase.get(myEmail).get(i));
//        }
        model.addAttribute("contacts", this.contactDatabase.get(myEmail));
//        model.addAttribute("derpSent", false);
//        model.addAttribute("inviteSent", false);
//        model.addAttribute("contactRemoved", false);
        model.addAttribute("contact", new Contact());

        if (this.contactDatabase.get(myEmail) == null)
            model.addAttribute("noContact", true);
        else
            model.addAttribute("noContact", false);

        return "derp";
    }

    @RequestMapping(value = "/derp/invite", method = RequestMethod.GET)
    public String showInvite(Model model) {
        model.addAttribute("contact", new Contact());
        return "derpInvite";
    }

    @RequestMapping(value = "/derp/invite", method = RequestMethod.POST)
    public String createContact(@ModelAttribute("contact") Contact contact, Model model, HttpSession session) {
        String user = (String)session.getAttribute("email");
        String newContact  = contact.getEmail();

        if (this.contactDatabase.get(user) == null) {
            List newContactList = new ArrayList<String>();
            newContactList.add(newContact);
            this.contactDatabase.put(user, newContactList);
        }
        else {
            List contacts = this.contactDatabase.get(user);
            contacts.add(contact.getEmail());
            this.contactDatabase.put(user, contacts);
        }
        return "redirect:/derp";
    }




}
