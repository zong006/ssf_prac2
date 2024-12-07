package vttp.ssf_prac2.repo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import vttp.ssf_prac2.model.Event;
import vttp.ssf_prac2.util.Util;

@Repository
public class RedisRepo {
    
    @Autowired
    @Qualifier(Util.template)
    RedisTemplate<String, String> stringTemplate;

    public void saveRecord(Event event){
        stringTemplate.opsForList().leftPush(Util.redisKey, event.toString());
    }

    public int getNumberOfEvents(){
        Long size = stringTemplate.opsForList().size(Util.redisKey);
        return size.intValue();
    }

    public Event getEvent(Integer index){
        
        String entry = stringTemplate.opsForList().index(Util.redisKey, index);
        String[] terms = entry.split(Util.delimiter);

        Event event = new Event();
        event.setEventId(Integer.valueOf(terms[0]));
        event.setEventName(terms[1]);
        event.setEventSize(Integer.valueOf(terms[2]));
        event.setEventDate(Long.parseLong(terms[3]));
        event.setParticipants(Integer.valueOf(terms[4]));

        return event;
    }

    public boolean checkForKey(String key){
        return stringTemplate.hasKey(key);
    }
    
    public void incrementParticipants(String eventName, int numTickets){
        
        for(int i=0 ; i<getNumberOfEvents() ; i++){
            Event event = getEvent(i);
            if (event.getEventName().equals(eventName)){
                stringTemplate.opsForList().remove(Util.redisKey, 1, event.toString());
                event.incrementParticipants(numTickets);    
                stringTemplate.opsForList().leftPush(Util.redisKey, event.toString());
            }
        }
    }
}
