package ch.isitar.oop2.projectOscar.model;

import java.util.List;

public interface MovieModel {

	public List<Movie> getData();

	public void saveData(List<Movie> movies);
}