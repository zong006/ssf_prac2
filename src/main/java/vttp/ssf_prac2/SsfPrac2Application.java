package vttp.ssf_prac2;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import vttp.ssf_prac2.model.Event;
import vttp.ssf_prac2.service.DatabaseService;
import vttp.ssf_prac2.util.Util;

@SpringBootApplication
public class SsfPrac2Application implements CommandLineRunner{

	@Autowired
	DatabaseService databaseService;
	public static void main (String[] args) {
		SpringApplication.run(SsfPrac2Application.class, args);
	}

	@Override
	public void run(String... args) throws IOException {
		
		List<Event> eventList = databaseService.readFile(Util.filePath);

		if (!databaseService.checkForKey(Util.redisKey)){
			for(Event e : eventList){
				databaseService.saveRecord(e);
			}
		}

		List<Event> events = databaseService.getAllEventsFromRedis();
		for (Event e : events){
			System.out.println(e.toString());
		}
		
		
	}

}
