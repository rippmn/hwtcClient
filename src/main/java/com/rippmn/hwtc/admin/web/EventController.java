package com.rippmn.hwtc.admin.web;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.rippmn.hwtc.admin.domain.TrickorTreatEvent;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gcp.pubsub.core.PubSubTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.view.RedirectView;

@RestController
public class EventController {
    @Autowired
    private PubSubTemplate pubSubTemplate;
    
    private static final ObjectMapper om = new ObjectMapper();

    private int internalCount = 0;

    @PostMapping("/trickOrTreat")
    public Integer count(@RequestParam("count") int count) {
        System.out.println("Got Count: " + count);
        internalCount += count;
        try {
            //System.out.println(om.writeValueAsString(new TrickorTreatEvent(count)));
            pubSubTemplate.publish("ripka-test", om.writeValueAsString(new TrickorTreatEvent(count)));
        } catch (JsonProcessingException err) {
            System.err.println(err);
        }
        
        return Integer.valueOf(internalCount);
    }

    @GetMapping("/thisYear")
	public Integer thisYear() {
		return internalCount;
	}
}