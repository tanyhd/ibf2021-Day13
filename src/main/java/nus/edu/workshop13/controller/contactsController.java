package nus.edu.workshop13.controller;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.Charset;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import nus.edu.workshop13.model.Contacts;

@Controller
public class contactsController {

    //autowired the argument taken in from command prompt
    @Autowired
    private ApplicationArguments appArgs;
    
    @GetMapping("/form")
    public String showForm(Model model) {
        Contacts personContact = new Contacts();
        model.addAttribute("contacts", personContact);
        return "form";
    }

    @GetMapping("/contact/{id}")
    public String getContactFromId(@PathVariable String id, Model model) throws IOException{
        Contacts returnedPersonInfo = new Contacts();
        returnedPersonInfo = returnedPersonInfo.getContact(id, appArgs.getNonOptionArgs().get(0));
        if(returnedPersonInfo == null) {
            model.addAttribute("errorMessage", "File not found !");
            return "error";
        } else {
            model.addAttribute("returnedPersonInfo", returnedPersonInfo);
            return "personContact";
        }
    }

    @PostMapping("/contact")
    public String submitForm(@ModelAttribute Contacts contact, Model model) throws IOException {
        model.addAttribute("contactInfo", contact);
        String fileDir = appArgs.getNonOptionArgs().get(0);
        //Write the data into the file as text(UTF-8)
        FileWriter writter = new FileWriter(fileDir + "/" + contact.getId() + ".txt", Charset.forName("utf-8"));
        writter.write(contact.getName() + "\n");
        writter.write(contact.getEmail() + "\n");
        writter.write(contact.getPhoneNumber() + "\n");
        writter.close();
        return "contact";
    }
}
