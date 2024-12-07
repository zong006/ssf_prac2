package vttp.ssf_prac2.controller;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import vttp.ssf_prac2.model.Event;
import vttp.ssf_prac2.service.DatabaseService;
import vttp.ssf_prac2.util.Util;


@Controller
public class EventController {

    @Autowired
    DatabaseService databaseService;

    @GetMapping("/")
    public String displayEvents(Model model) throws IOException{
        
        List<Event> eventList = databaseService.readFile(Util.filePath);

        model.addAttribute("eventList", eventList);
        return "view0";
    }
    
}
