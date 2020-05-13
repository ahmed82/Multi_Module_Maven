package com.rbs.multimodule.backend.controller;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rbs.multimodule.backend.util.Manifest;

@RestController
public class UtilityController {

    private final Logger logger = LoggerFactory.getLogger(UtilityController.class);
	
    @GetMapping("/api/util/version")
    public ResponseEntity<?> getVersion(HttpServletRequest request) {
    	
    	try {
    		String version = Manifest.getVersion(request.getServletContext());
        	
        	return ResponseEntity.ok()
                    .contentType(MediaType.TEXT_PLAIN)
                    .body(version);
    	}
    	catch (Throwable e) {
    		return handleThrowable(e, "getVersion");
    	}
    	
    }
    
    
    private ResponseEntity<?> handleThrowable(Throwable e, String routineName) {
		String body;
		int httpCode = 500;
		String msg = e.getMessage();
		if (msg == null) {
			msg = e.toString();
		}
		if (e instanceof RuntimeException) {
			httpCode = 400;
		}
		else {
			this.logger.error("Issue calling "+routineName,e);
		}
		body = "{\"status\":"+httpCode+", \"error\":\""+e.getClass().getSimpleName()+"\", \"message\": \""+msg+"\"}";
		return ResponseEntity.status(httpCode).contentType(MediaType.APPLICATION_JSON).body(body);
	}
    
    
    @RequestMapping(value = "{_:^(?!index\\.html|api).*$}")
    public String redirectApi() {
    	logger.info("URL entered directly into the Browser, so we need to redirect...");
        return "forward:/";
    }
    

}
