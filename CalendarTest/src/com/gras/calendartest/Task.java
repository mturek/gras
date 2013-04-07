package com.gras.calendartest;


public class Task {
	String name, time, group;
	
	public Task(String name, String time, String group) {
		this.name = name;
		this.time = time;
		this.group = group;		
	}
	
	public String getName() {
		return name;
	}
	
	public String getTime() {
		return time;
	}
	
	public String getGroup() {
		return group;
	}
}
