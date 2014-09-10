package controllers;

import play.mvc.*;
import play.data.validation.*;

import java.util.*;

import models.*;

public class Application extends Controller {

    public static void index() {
        render();
    }
    
    // method to list all the contacts in the db
    public static void list() {
        List<Contact> contacts = Contact.find("order by name").fetch();
        render(contacts);
    }
    
    // method to edit to contact
    public static void form(Long id) {
        if(id == null) {
            render();
        }
        Contact contact = Contact.findById(id);
        render(contact);
    }
    
    // method to save the contacts to the
    public static void save(@Valid Contact contact) {
        if(validation.hasErrors()) {
            if(request.isAjax()) error("Invalid value");
            render("@form", contact);
        }
        contact.save();
        list();
    }
    
    // method to delete contact
    public static void delete(Long id){
        Contact contact = Contact.findById(id);
        contact.delete();
        
        list();
    }
    

}