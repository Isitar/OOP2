package ch.isitar.oop2.projectOscar.view.command;

import java.util.List;

import ch.isitar.oop2.projectOscar.model.Movie;

public class DeleteMovieUndoRedoCommand implements UndoRedoCommand {
	private List<Movie> movies;
	private Movie removedMovie;

	public DeleteMovieUndoRedoCommand(List<Movie> list, Movie removedMovie) {
		this.movies = list;
		this.removedMovie = removedMovie;
	}

	@Override
	public void execute() {
		movies.remove(removedMovie);
	}

	@Override
	public void undo() {
		movies.add(removedMovie);
	}

	@Override
	public String toString() {
		return "Film löschen: " + removedMovie.getTitle().get();
	}
}
