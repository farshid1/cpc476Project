package com.codeiners.derp;

import com.codeiners.component.DerpEmailService;
import org.apache.commons.mail.EmailException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.*;

/**
 * Created by razorhead on 10/26/14.
 */
@Controller
public class DerpController{

    private Map<String, List<Contact>> contactDatabase = new LinkedHashMap<String, List<Contact>>();

    //Add our email service to the controller
    @Autowired
    private DerpEmailService derpEmailService;

    private Model showTable(Model model, String user) {

        if (this.contactDatabase.get(user) == null)
            model.addAttribute("noContact", true);
        else
            model.addAttribute("noContact", false);

        return model;
    }

    @RequestMapping(value = "/derp", method = RequestMethod.GET)
    public String listContacts( Model model, HttpSession session) {

        String user = (String) session.getAttribute("email");

        model.addAttribute("contacts", this.contactDatabase.get(user));
        model.addAttribute("derpSent", false);
        model.addAttribute("inviteSent", false);
        model.addAttribute("contactRemoved", false);
        /* Determine weather to show the contact table */
        this.showTable(model, user);

        return "derp";
    }

    @RequestMapping(value = "/derp/invite", method = RequestMethod.GET)
    public String showInvite(Model model) {

        model.addAttribute("contact", new Contact());

        return "derpInvite";
    }

    @RequestMapping(value = "/derp/invite", method = RequestMethod.POST)
    public String createContact(@Valid Contact contact, BindingResult bindingResult, Model model, HttpSession session) throws EmailException {

        if (bindingResult.hasErrors()) return "derpInvite";
        String user = (String)session.getAttribute("email");
        String sendTo = contact.getEmail();

        /* Add contact to user's contact list */
        if (this.contactDatabase.get(user) == null) {
            List newContactList = new ArrayList<Contact>();
            newContactList.add(contact);
            this.contactDatabase.put(user, newContactList);
        }
        else {
            List contacts = this.contactDatabase.get(user);
            contacts.add(contact);
            this.contactDatabase.put(user, contacts);
        }

        /* Send Invite Email */
        derpEmailService.sendEmail(user, sendTo, "DERP Invite", "Please join me on Derp for a derpy experience");

        model.addAttribute("contacts", this.contactDatabase.get(user));
        model.addAttribute("derpSent", false);
        model.addAttribute("inviteSent", true);
        model.addAttribute("contactRemoved", false);
        /* Determine weather to show the contact table */
        this.showTable(model, user);


        return "derp";
    }

    @RequestMapping(value = "/derp/send", method = RequestMethod.GET)
    public String sendDerp(@RequestParam("email") String sendTo, Model model, HttpSession session) throws EmailException {

        String sendFrom = (String)session.getAttribute("email");

        /* Send Derp */
        derpEmailService.sendEmail(sendFrom, sendTo, "DERP", "Derp...");

        model.addAttribute("contacts", this.contactDatabase.get(sendFrom));
        model.addAttribute("derpSent", true);
        model.addAttribute("contactRemoved", false);
        model.addAttribute("drepped", sendTo);
        /* Determine weather to show the contact table */
        this.showTable(model, sendFrom);

        return "derp";
    }

    @RequestMapping(value = "/derp/delete", method = RequestMethod.GET)
    public String deleteContact(@RequestParam("email") String email, Model model, HttpSession session) {

        String user = (String)session.getAttribute("email");
        List contactList = this.contactDatabase.get(user);

        /*Removing from a list in java: http://stackoverflow.com/questions/17279519/removing-items-from-list-in-java */
        Iterator<Contact> i = contactList.listIterator();
        while(i.hasNext()) {
            Contact current = i.next();
            if (current.getEmail().equals(email)) {
                model.addAttribute("drepped", current);
                i.remove();
            }

        }

        this.contactDatabase.put(user, contactList);

        model.addAttribute("derpSent", false);
        model.addAttribute("contactRemoved", true);
        model.addAttribute("drepped", email);
        model.addAttribute("contacts", this.contactDatabase.get(user));
        this.showTable(model, user);

        return "derp";
    }




}
