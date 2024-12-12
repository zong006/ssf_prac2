package vttp.ssf_prac2.controller;

import java.io.File;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import vttp.ssf_prac2.util.Util;

@RestController
public class HealthController {
    
    @GetMapping("/health")
    public ResponseEntity<String> checkHealth(){

        File f = new File(Util.filePath);
        if (f.exists()){
            System.out.println("File exist");
            return ResponseEntity.status(200).body("healthy");
        }
        System.out.println("file not found");
        return ResponseEntity.status(400).body("not healthy");
    }
}