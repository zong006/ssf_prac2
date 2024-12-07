package vttp.ssf_prac2.service;

import java.util.Calendar;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import vttp.ssf_prac2.model.Event;
import vttp.ssf_prac2.model.Participant;
import vttp.ssf_prac2.repo.RedisRepo;

@Service
public class EventService {
    
    @Autowired
    RedisRepo redisRepo;

    public void incrementParticipants(String eventName, int numTickets){
        redisRepo.incrementParticipants(eventName, numTickets);
    }

    public boolean meetAgeRequirement(Participant p){
        
        Date dob = p.getDateOfBirth();
        Calendar dateOfBirth = Calendar.getInstance();
        dateOfBirth.setTime(dob);

        Calendar rightNow = Calendar.getInstance();

        int age = rightNow.get(Calendar.YEAR) - dateOfBirth.get(Calendar.YEAR);

        if (dateOfBirth.get(Calendar.MONTH) > rightNow.get(Calendar.MONTH)){
            age -= 1;
        }
        return age>=21;
    }

    public boolean sufficientTickets(String eventName, int numTickets){

        for (int i=0 ; i<redisRepo.getNumberOfEvents() ; i++){
            Event event = redisRepo.getEvent(i);
            
            if (event.getEventName().equals(eventName)){
                int freeSlots = event.getEventSize() - event.getParticipants();
                if (freeSlots >= numTickets){
                    return true;
                }
            }
        }
        return false;
    }

}
