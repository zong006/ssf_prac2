package vttp.ssf_prac2.service;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.json.Json;
import jakarta.json.JsonArray;
import jakarta.json.JsonObject;
import jakarta.json.JsonReader;
import vttp.ssf_prac2.model.Event;
import vttp.ssf_prac2.repo.RedisRepo;

@Service
public class DatabaseService {
   
    @Autowired
    RedisRepo redisRepo;

    public List<Event> getAllEventsFromRedis(){
        List<Event> eventList = new ArrayList<>();
        for (int i=0 ; i<redisRepo.getNumberOfEvents() ; i++){
            Event event = redisRepo.getEvent(i);
            eventList.add(event);
        }
        return eventList;
    }

    public List<Event> readFile(String fileName) throws IOException{

        JsonArray jsonData = loadFileAsArray(fileName);
        List<Event> eventList = new ArrayList<>();

        for (int i = 0 ; i<jsonData.size() ; i++){
            
            Event event = new Event();
            JsonObject jsonObject = jsonData.getJsonObject(i);
            event.setEventId(jsonObject.getInt("eventId"));
            event.setEventName(jsonObject.getString("eventName"));
            event.setEventSize(jsonObject.getInt("eventSize"));
            event.setEventDate(jsonObject.getJsonNumber("eventDate").longValue());
            event.setParticipants(jsonObject.getInt("participants"));

            eventList.add(event);
        }
        return eventList;
        
    }
    
    public JsonArray loadFileAsArray(String fileName) throws IOException{

        File f = new File(fileName);
        // FileReader fr = new FileReader(f);
        InputStream is = getClass().getClassLoader().getResourceAsStream("static/data/events.json");
        InputStreamReader isr = new InputStreamReader(is);
        BufferedReader br = new BufferedReader(isr);
        JsonReader jsonReader = Json.createReader(br);
        JsonArray jsonData = jsonReader.readArray();
        jsonReader.close();
        br.close();

        return jsonData;
    }

    public boolean checkForKey(String key){
        return redisRepo.checkForKey(key);
    }

    public void saveRecord(Event event){
        redisRepo.saveRecord(event);
    }
}
