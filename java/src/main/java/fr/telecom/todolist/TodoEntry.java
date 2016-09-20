package fr.telecom.todolist;

import java.text.SimpleDateFormat;
import java.util.Comparator;
import java.util.Date;

public class TodoEntry {

	private Date date;
	private String desc;
	private int level;

	public static final SimpleDateFormat FORMAT_DATE = new SimpleDateFormat("dd-MM-yyyy");
	
	public TodoEntry() {
		this.level = 1;
		this.date = new Date();
	}
	
	public TodoEntry(String desc) {
		this.desc = desc;
		this.level = 1;
		this.date = new Date();
	}

	public TodoEntry(Date date, int level, String desc) {
		this.date = date;
		this.desc = desc;
		this.level = level;
	}

	@Override
	public String toString() {
		return String.format("[TODO: %d] %s (%s)", level, desc, FORMAT_DATE.format(date));
	}

	public static class LevelComparator implements Comparator<TodoEntry> {

		@Override
		public int compare(TodoEntry o1, TodoEntry o2) {
			if (o1.getLevel() == o2.getLevel()) {
				return 0;
			} else if (o1.getLevel() > o2.getLevel()) {
				return 1;
			} else {
				return -1;
			}
		}

	}

	public static class DateComparator implements Comparator<TodoEntry> {

		@Override
		public int compare(TodoEntry o1, TodoEntry o2) {
			return o1.getDate().compareTo(o2.getDate());
		}

	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		this.level = level;
	}

}
