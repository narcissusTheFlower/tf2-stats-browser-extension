package org.narcissus.tf2statsbrowserextension.rest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController("/api")
public class MainHandler {

    Logger logger = LoggerFactory.getLogger(MainHandler.class);

//    @PostMapping("/getStats")
//    public ResponseEntity<?> upload(@RequestParam("steamId") Integer steamId) {
//        logger.info("Controller '/getStats' called.");
//
//        return ResponseEntity.ok()
//                .contentType(Media)
//                .body(fileContent);
//    }
}
