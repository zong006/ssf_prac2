package vttp.ssf_prac2.util;

import java.util.Arrays;
import java.util.List;

public interface Util {

    String redisKey = "prac2";


    String url = "https://min-api.cryptocompare.com/data/v2/news/?lang=EN&api_key=";

    String template = "stringTemplate";

    String delimiter = "THIS_IS_A_DELIMITER";

    String filePath = "events.json";
    // String filePath = "./src/main/resources/static/events.json";

    List<String> eventNames = Arrays.asList("Christmas Eve Party", "Round Singapore Cycling", "Intro to SCRATCH", "JB Shopping !");
    
}
