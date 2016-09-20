package fr.telecom.todolist;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

public class StructuredTodoTest extends TestCase {

	public StructuredTodoTest(String testName) {
		super(testName);
	}

	public static Test suite() {
		return new TestSuite(StructuredTodoTest.class);
	}

	public static final String CURRENT_DIRECTORY = "/home/glegoux/projects/eclipse/test-java/src/test/java/fr/telecom/todolist/";

	public void testEmpty01() {
		StructuredTodo structuredTodo = new StructuredTodo();
		assertEquals(true, structuredTodo.load(CURRENT_DIRECTORY + "structured-data-empty.txt"));
		assertEquals(true, structuredTodo.getTodos().isEmpty());
	}
	
	public void testEmpty02() {
		StructuredTodo structuredTodo = new StructuredTodo();
		assertEquals(false, structuredTodo.load(CURRENT_DIRECTORY + "structured-data-first-blank-line.txt"));
		assertEquals(true, structuredTodo.getTodos().isEmpty());
	}
	
	public void testEmpty03() {
		StructuredTodo structuredTodo = new StructuredTodo();
		assertEquals(false, structuredTodo.load(CURRENT_DIRECTORY + "structured-data-missing-last-blank-line.txt"));
		assertEquals(true, structuredTodo.getTodos().isEmpty());
	}
	
	public void testEmpty04() {
		StructuredTodo structuredTodo = new StructuredTodo();
		assertEquals(true, structuredTodo.load(CURRENT_DIRECTORY + "structured-data.txt"));
		assertEquals(false, structuredTodo.getTodos().isEmpty());
		System.out.println(structuredTodo);
		structuredTodo.setIteratorOrder("level");
		System.out.println(structuredTodo);
		structuredTodo.setIteratorOrder("date");
		System.out.println(structuredTodo);
	}
	
}
