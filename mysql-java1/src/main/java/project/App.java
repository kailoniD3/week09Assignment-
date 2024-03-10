package project;

import java.math.BigDecimal;
import java.util.List;
import java.util.Objects;
import java.util.Scanner;

import project.dao.DbConnection;
import project.dao.DbException;
import project.exception.ProjectService;

public class App {
	private Scanner scanner = new Scanner(System.in);
	private ProjectService projectService = new ProjectService();
			
	
	private List<String> operations = List.of( 
			"1) Add a project");
	
	public static void main(String[] args) {
		new App().processUserSelections();
	}
	
	private void processUserSelections() {
		// TODO Auto-generated method stub
		boolean done = false;
		while (!done) {
			try {
				int selection = getUserSelection1();
				
				switch(selection) {
				case -1: done = exitMenu();
				break;
				
				case 1: createProject();
				break;
				
				default:
					System.out.println("/n +" + selection + " is not a valid selection. Try again.");
				break;
				}
			}
			catch (Exception e) {
				System.out.println("/nError; " + e + "Try again.");
			}
		}
	}

	private <Project> void createProject() {
		// TODO Auto-generated method stub
		String projectName= getStringInput1("Enter the project name");
		BigDecimal estimatedHours = getDecimalInput("Enter the estimated hours");
		BigDecimal actualHours = getDecimalInput("Enter the actual hours");
		Integer difficulty = getIntInput("Enter the project difficulty (1-5)");
		String notes = getStringInput1("Enter the project notes");
		
		Project project = new Project();
		
		project.setProjectName(projectName);
		project.setEstimatedHours(estimatedHours);
		project.setActualHours(actualHours);
		project.setDifficulty(difficulty);
		project.setNotes(notes);
		
		Project dbProject = projectService.addProject(project);
		System.out.println("You have successfullt created project: " + dbProject);	
	}

	private BigDecimal getDecimalInput(String prompt) {
		// TODO Auto-generated method stub
		String input = getStringInput1(prompt);
		if(Objects.isNull(input)) {
			return null;
		}
	try {
		return new BigDecimal(input).setScale(2);
	}
	catch(numberFormatException e) {
		throw new DbException(input + "is not a valid decimal number.");
	}
}

	private boolean exitMenu() {
		System.out.println("Exiting the menu.");
		return true;
	}
	private int getUserSelection1() {
	printoperations();
	Integer input = getInput("Enter a menu selection");
	return Objects.isNull(input) ? -1: input;
}
	
	private Integer getInput(String string) {
		// TODO Auto-generated method stub
		return null;
	}

	private Integer getIntInput(String prompt) {
		// TODO Auto-generated method stub
		String input = getStringInput1(prompt);
		if (Objects.isNull(input)) {
				return null;
		}
			try {
				return Integer.valueOf(input);
			}
			catch(NumberFormatException e) {
				throw new DbException(input + " is not a valid number.");
			}
	}
	
	private String getStringInput1(String prompt) {
		System.out.print(prompt + " : ");
		String input = scanner.nextLine();
		
		return input.isBlank() ? null : input.trim();	
	}
	
	
	private void printoperations() {
	// TODO Auto-generated method stub
	System.out.println("/n These are the avaliable selections. Press the ENTER KEY to quit.");
	operations.forEach(line -> System.out.println("  " + line));
}


	

	
	


	}
