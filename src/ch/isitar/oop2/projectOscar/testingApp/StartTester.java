package ch.isitar.oop2.projectOscar.testingApp;

import ch.isitar.oop2.projectOscar.model.FileRepository;

public class StartTester {

	public static void main(String[] args) {
		FileRepository fr = new FileRepository();
		fr.getData().forEach(System.out::println);

	}

}
