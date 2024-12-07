package vttp.ssf_prac2.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import vttp.ssf_prac2.model.Participant;
import vttp.ssf_prac2.service.EventService;

@Controller
public class RegistrationController {

    @Autowired
    EventService eventService;

    @GetMapping("/register")
    public String register(Model model, 
                            @RequestParam("eventName") String eventName,
                            @RequestParam("eventDate") String eventDate, 
                            HttpSession httpSession){
        Long epochDate = Long.parseLong(eventDate);

        httpSession.setAttribute("eventName", eventName);
        httpSession.setAttribute("eventDate", epochDate);

        model.addAttribute("eventName", eventName);
        model.addAttribute("eventDate", epochDate);
        model.addAttribute("participant", new Participant());

        return "eventRegister";
    }


    @PostMapping("/register")
    public String addParticipant(@Valid @ModelAttribute(value="participant") Participant p, BindingResult bindingResult, 
                            Model model,
                            @RequestParam("eventName") String eventName,
                            @RequestParam("eventDate") String eventDate,
                            HttpSession httpSession) throws IllegalArgumentException, IllegalAccessException, IOException{
        System.out.println(p.toString());
        if (bindingResult.hasErrors()){

            model.addAttribute("eventName", httpSession.getAttribute("eventName"));
            model.addAttribute("eventDate", httpSession.getAttribute("eventDate"));
            
            return "eventRegister";
        }

        int numTickets = p.getNumTickets();
        List<String> errorMsgs = new ArrayList<>();
        if (eventService.meetAgeRequirement(p)){

            if (eventService.sufficientTickets(eventName, numTickets)){
                eventService.incrementParticipants(eventName, numTickets);
                Long epochDate = Long.parseLong(eventDate);
                model.addAttribute("eventName", eventName);
                model.addAttribute("eventDate", epochDate);
        
                return "successRegistration";
            }
            else{
                errorMsgs.add("Insuffient free slots.");
            }
        }
        else{
            errorMsgs.add("Age requirement is 21.");
        }

        model.addAttribute("errorMsgs", errorMsgs);
        return "errorRegistration";

    }

    

    
}
