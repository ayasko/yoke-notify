package com.epam.yoke.notify.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.stereotype.Component;

import com.epam.yoke.notify.configuration.NotifierBinding;
import com.epam.yoke.notify.model.rq.AttenderEventBody;
import com.epam.yoke.notify.model.rq.EventBody;

@Component
@EnableBinding(NotifierBinding.class)
public class StreamService {
	
	@Autowired
	NotifyService notifyService;
	
	@StreamListener(NotifierBinding.INPUT_CREATE_EVENT)
	public void createEvent(EventBody event) {
		notifyService.saveEvent(event);
	}
	
	@StreamListener(NotifierBinding.INPUT_DELETE_EVENT)
	public void createEvent(String eventId) {
		notifyService.deleteEvent(eventId);
	}
	
	@StreamListener(NotifierBinding.INPUT_ADD_EVENT_ATTENDER)
	public void addEventAttender(AttenderEventBody eventAttender) {
		notifyService.saveEvent(eventAttender);
	}
	
	@StreamListener(NotifierBinding.INPUT_DELETE_EVENT_ATTENDER)
	public void deleteEventAttender(AttenderEventBody eventAttender) {
		notifyService.deleteAttender(eventAttender.getEventId(), eventAttender.getEmails().get(0));
	}
}
