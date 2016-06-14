package ch.isitar.oop2.projectOscar.presenter;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import ch.isitar.oop2.projectOscar.model.*;
import ch.isitar.oop2.projectOscar.view.*;
import ch.isitar.oop2.projectOscar.view.command.ChangeIntegerPropertyUndoRedoCommand;
import ch.isitar.oop2.projectOscar.view.command.ChangeObjectPropertyUndoRedoCommand;
import ch.isitar.oop2.projectOscar.view.command.ChangeStringPropertyUndoRedoCommand;
import ch.isitar.oop2.projectOscar.view.command.CreateMovieUndoRedoCommand;
import ch.isitar.oop2.projectOscar.view.command.DeleteMovieUndoRedoCommand;
import ch.isitar.oop2.projectOscar.view.command.UndoRedoCommand;
import ch.isitar.oop2.projectOscar.view.command.UndoRedoController;
import javafx.beans.property.BooleanProperty;
import javafx.collections.ObservableList;

/**
 * the presentation model of the application
 */
public class MoviePresenter {

	private final MovieView applicationView;
	private final MovieModel applicationModel;

	private final UndoRedoController controller;

	private List<Movie> movies;

	/**
	 * creates a new PresentationModel
	 *
	 * @param applicationView
	 *            the ApplicationView
	 */
	public MoviePresenter(MovieView applicationView) {
		this.applicationView = applicationView;

		applicationModel = new MovieFileRepository();
		movies = applicationModel.getData();
		controller = new UndoRedoController();
	}

	public void fillView() {
		setResults();
	}

	private void setResults() {
		applicationView.setResults(movies);
	}

	public void ChangeYear(Movie m, int year) {
		if (year < 1929) {
			applicationView.DisplayError("Jahr muss ab 1929 sein.");
		} else {
			controller.ExecuteCommand(new ChangeIntegerPropertyUndoRedoCommand(m.getYearOfAward(), year));
		}
		setResults();
	}

	public void ChangeTitle(Movie m, String title) {
		if (title.toLowerCase().equals("oop2"))
			applicationView.DisplayError("oop2 ist kein Film sondern ein super Modul!");
		else
			controller.ExecuteCommand(new ChangeStringPropertyUndoRedoCommand(m.getTitle(), title));
		setResults();
	}

	public void ChangeDirector(Movie m, String newV) {
		controller.ExecuteCommand(new ChangeStringPropertyUndoRedoCommand(m.getDirector(), newV));
		setResults();
	}

	public void ChangeMainActor(Movie m, String newV) {
		controller.ExecuteCommand(new ChangeStringPropertyUndoRedoCommand(m.getMainActor(), newV));
		setResults();
	}

	public void ChangeEnglishTitle(Movie m, String newV) {
		controller.ExecuteCommand(new ChangeStringPropertyUndoRedoCommand(m.getTitleEnglish(), newV));
		setResults();
	}

	public void ChangeGenre(Movie m, String newV) {
		controller.ExecuteCommand(new ChangeStringPropertyUndoRedoCommand(m.getGenre(), newV));
		setResults();
	}

	public void ChangeYearOfProduction(Movie m, int newV) {
		controller.ExecuteCommand(new ChangeIntegerPropertyUndoRedoCommand(m.getYearOfProduction(), newV));
		setResults();
	}

	public void ChangeCountry(Movie m, String newV) {
		controller.ExecuteCommand(new ChangeStringPropertyUndoRedoCommand(m.getCountry(), newV));
		setResults();
	}

	public void ChangeDuration(Movie m, int newV) {
		controller.ExecuteCommand(new ChangeIntegerPropertyUndoRedoCommand(m.getDuration(), newV));
		setResults();
	}

	public void ChangeFSK(Movie m, int newV) {
		controller.ExecuteCommand(new ChangeIntegerPropertyUndoRedoCommand(m.getFsk(), newV));
		setResults();
	}

	public void ChangeNumberOfOscars(Movie m, int newV) {
		controller.ExecuteCommand(new ChangeIntegerPropertyUndoRedoCommand(m.getNumberOfOscars(), newV));
		setResults();
	}

	public void ChangeCinemaStart(Movie m, Date date) {
		controller.ExecuteCommand(new ChangeObjectPropertyUndoRedoCommand<Date>(m.getStartDate(), date));
		setResults();
	}

	public Movie createMovie() {
		Movie m = new Movie();
		Movie lastMovie = movies.get(movies.size() - 1);
		m.getYearOfAward().set(lastMovie.getYearOfAward().get() + 1);
		m.getId().set(lastMovie.getId().get() + 1);
		m.getNumberOfOscars().set(1);
		m.getFsk().set(0);
		controller.ExecuteCommand(new CreateMovieUndoRedoCommand(movies, m));
		setResults();
		return m;
	}

	public void deleteMovie(Movie m) {
		controller.ExecuteCommand(new DeleteMovieUndoRedoCommand(movies, m));
		setResults();
	}

	public void undo() {
		controller.undo();
		setResults();
	}

	public void redo() {
		controller.redo();
		setResults();
	}

	public BooleanProperty getUndoEnabledProperty() {
		return controller.undoEnabledProperty();
	}

	public BooleanProperty getRedoEnabledProperty() {
		return controller.redoEnabledProperty();
	}

	public ObservableList<UndoRedoCommand> getUndoListProperty() {
		return controller.getUndoListProperty();
	}

	public ObservableList<UndoRedoCommand> getRedoListProperty() {
		return controller.getRedoListProperty();
	}

	public void save() {
		applicationModel.saveData(movies);
	}

	public void filter(String filterString) {
		if (filterString.isEmpty())
			setResults();
		else
			applicationView.setResults(
					movies.parallelStream().filter(m -> LevenshteinDistance.match(m.getTitle().get(), filterString))
							.collect(Collectors.toList()));

	}

	private static class LevenshteinDistance {
		private static int minimum(int a, int b, int c) {
			return Math.min(Math.min(a, b), c);
		}

		private static int computeLevenshteinDistance(CharSequence lhs, CharSequence rhs) {
			int[][] distance = new int[lhs.length() + 1][rhs.length() + 1];

			for (int i = 0; i <= lhs.length(); i++)
				distance[i][0] = i;
			for (int j = 1; j <= rhs.length(); j++)
				distance[0][j] = j;

			for (int i = 1; i <= lhs.length(); i++)
				for (int j = 1; j <= rhs.length(); j++)
					distance[i][j] = minimum(distance[i - 1][j] + 1, distance[i][j - 1] + 1,
							distance[i - 1][j - 1] + ((lhs.charAt(i - 1) == rhs.charAt(j - 1)) ? 0 : 1));

			return distance[lhs.length()][rhs.length()];
		}

		public static boolean match(String lhs, String rhs) {
			double distance = computeLevenshteinDistance(lhs.toLowerCase(), rhs.toLowerCase());

			return distance / lhs.length() < 0.2;
		}
	}
}
