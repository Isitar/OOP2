package ch.isitar.oop2.projectOscar.view.command;

import java.util.List;

import ch.isitar.oop2.projectOscar.model.Movie;

public class CreateMovieUndoRedoCommand implements UndoRedoCommand {
	private List<Movie> movies;
	private Movie newMovie;

	public CreateMovieUndoRedoCommand(List<Movie> movies, Movie newMovie) {
		this.movies = movies;
		this.newMovie = newMovie;
	}

	@Override
	public void execute() {
		movies.add(newMovie);
	}

	@Override
	public void undo() {
		movies.remove(newMovie);
	}

	@Override
	public String toString() {
		return "Film erstellen";
	}

}
