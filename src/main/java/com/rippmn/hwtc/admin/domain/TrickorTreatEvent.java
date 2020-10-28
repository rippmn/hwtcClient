package com.rippmn.hwtc.admin.domain;

import java.sql.Timestamp;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;

public class TrickorTreatEvent {

	@JsonSerialize(using = ToStringSerializer.class)
	private Timestamp eventDateTime = new Timestamp(System.currentTimeMillis());
	
	private int count;
	
	public TrickorTreatEvent(int count){
		this.count = count;
	}

	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
	public Timestamp getEventDateTime() {
		return eventDateTime;
	}
	public void setEventDateTime(Timestamp eventDateTime) {
		this.eventDateTime = eventDateTime;
	}
	
}
