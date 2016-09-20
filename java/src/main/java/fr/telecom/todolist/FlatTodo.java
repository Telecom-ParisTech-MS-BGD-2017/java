package fr.telecom.todolist;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;

public class FlatTodo extends TodoList {
	
	public static final String CURRENT_DIRECTORY = "/home/glegoux/projects/eclipse/test-java/src/main/java/fr/telecom/todolist/";

	public static void main(String[] args) {
		FlatTodo todoList = new FlatTodo();
		todoList.load(CURRENT_DIRECTORY + "flat-data.txt");
		todoList.setIteratorOrder("date");
		System.out.println(todoList);
		System.out.println(todoList);
		todoList.setIteratorOrder("level");
		System.out.println(todoList);
	}

	@Override
	public boolean load(String filename) {
		ArrayList<String> lines = TodoList.readFile(filename);
		for (String line : lines) {
			String[] values = line.split(";");
			if (values.length != 3) {
				return false;
			}
			try {
				Date date = TodoEntry.FORMAT_DATE.parse(values[0]);
				int level = Integer.parseInt(values[1]);
				String desc = values[2];
				todos.add(new TodoEntry(date, level, desc));
			} catch (ParseException | NumberFormatException e) {
				return false;
			}
		}
		return true;
	}

}
