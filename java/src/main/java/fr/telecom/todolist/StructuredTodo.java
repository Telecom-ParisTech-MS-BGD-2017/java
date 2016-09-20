package fr.telecom.todolist;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Objects;

public class StructuredTodo extends TodoList {
	
	public static final String CURRENT_DIRECTORY = "/home/glegoux/projects/eclipse/test-java/src/main/java/fr/telecom/todolist/";

	public static void main(String[] args) {
		StructuredTodo todoList = new StructuredTodo();
		todoList.load(CURRENT_DIRECTORY + "structured-data.txt");
		System.out.println(todoList);
	}

	@Override
	public boolean load(String filename) {
		ArrayList<String> lines = TodoList.readFile(filename);
		TodoEntry todo = new TodoEntry();
		boolean error = false;
		int lineNumber = 0;

		if (!lines.isEmpty() && lines.get(0).trim().isEmpty()) {
			System.err.println(String.format("ERROR: file %s begins by an empty line", filename));
			return false;
		}

		if (!lines.isEmpty() && !lines.get(lines.size() - 1).trim().isEmpty()) {
			System.err.println(String.format("ERROR: file %s should end by an empty line", filename));
			return false;
		}

		for (String line : lines) {
			lineNumber++;

			if (line.trim().isEmpty()) {

				if (todo.getDesc() == null) {
					System.err.println(String.format("ERROR: missing description for %s", todo.toString()));
					error = true;
					break;
				} else {
					todos.add(todo);
				}

				todo = new TodoEntry();
				continue;

			}

			String[] values = line.trim().split(":");

			if (values.length != 2) {
				error = true;
				break;
			}

			String key = values[0].trim();
			String value = values[1].trim();

			try {
				if (Objects.equals(key, KEY.date.name())) {
					todo.setDate(TodoEntry.FORMAT_DATE.parse(value));
				} else if (Objects.equals(key, KEY.entree.name())) {
					todo.setDesc(value);
				} else if (Objects.equals(key, KEY.level.name())) {
					todo.setLevel(Integer.parseInt(value));
				}
			} catch (ParseException | NumberFormatException e) {
				error = true;
				break;
			}

		}

		if (error) {
			System.err.println(String.format("ERROR: line %d in file %s", lineNumber, filename));
			todos.clear();
		}

		return !error;
	}

}
