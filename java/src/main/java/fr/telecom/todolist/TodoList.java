package fr.telecom.todolist;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

public abstract class TodoList {

	protected List<TodoEntry> todos;

	public enum KEY {
		date, level, entree
	}

	public TodoList() {
		this.todos = new ArrayList<>();
	}

	public abstract boolean load(String filename);

	public static ArrayList<String> readFile(String filename) {
		List<String> lines = null;
		try {
			lines = Files.readAllLines(Paths.get(filename), Charset.forName("UTF-8"));
		} catch (IOException e) {
			System.out.println("Error during the reading of " + filename);
			System.exit(1);
		}
		return (ArrayList<String>) lines;
	}

	public TodoEntry[] filter(String key, String value) {
		List<TodoEntry> todos = new ArrayList<>();
		for (TodoEntry todo : this.todos) {
			if (Objects.equals(key, KEY.date.name()) && Objects.equals(todo.getDate(), value)
					|| Objects.equals(key, KEY.level.name()) && Objects.equals(todo.getLevel(), value)) {
				todos.add(new TodoEntry(todo.getDate(), todo.getLevel(), todo.getDesc()));
			}
		}
		return todos.toArray(new TodoEntry[todos.size()]);
	}

	public void setIteratorOrder(String order) {
		if (Objects.equals(order, KEY.date.name())) {
			Collections.sort(todos, new TodoEntry.DateComparator());
		} else if (Objects.equals(order, KEY.level.name())) {
			Collections.sort(todos, Collections.reverseOrder(new TodoEntry.LevelComparator()));
		}
	}

	@Override
	public String toString() {
		String result = "*** TODOLIST ***\n";
		for (TodoEntry todo : todos) {
			result += todo + "\n";
		}
		return result;
	}

	public List<TodoEntry> getTodos() {
		return todos;
	}

}
