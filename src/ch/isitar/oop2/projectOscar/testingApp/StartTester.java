package ch.isitar.oop2.projectOscar.testingApp;

import ch.isitar.oop2.projectOscar.model.MovieFileRepository;

public class StartTester {

	public static void main(String[] args) {
		MovieFileRepository fr = new MovieFileRepository();
		fr.getData().forEach(System.out::println);

	}

}
