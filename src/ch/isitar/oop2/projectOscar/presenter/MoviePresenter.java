package ch.isitar.oop2.projectOscar.presenter;

import java.util.Date;
import java.util.List;

import org.omg.PortableServer.ServantRetentionPolicy;

import ch.isitar.oop2.projectOscar.model.*;
import ch.isitar.oop2.projectOscar.view.*;
import javafx.beans.property.SimpleObjectProperty;

/**
 * the presentation model of the application
 */
public class MoviePresenter {

	private final MovieView applicationView;
	private final MovieModel applicationModel;

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
	}

	public void fillView() {
		setResults();
	}

	public boolean ChangeMovie(int id, Movie changedMovie) {
		// movies.set(id, changedMovie);
		return true;
	}

	private void setResults() {
		applicationView.setResults(movies);
	}

	private Movie getMovie(Movie m) {
		return m;
		/*
		 * return movies.stream().filter(x -> { return x.getYearOfAward().get()
		 * == m.getYearOfAward().get(); }).findFirst().get();
		 */
	}

	public void ChangeYear(Movie m, int year) {
		if (year < 1929) {
			applicationView.DisplayError("Jahr muss ab 1929 sein.");
		} else {
			getMovie(m).getYearOfAward().set(year);
		}
		setResults();
	}

	public void ChangeTitle(Movie m, String title) {
		if (title.equals("oop2"))
			applicationView.DisplayError("oop2 ist kein film sondern ein super modul !");
		else
			getMovie(m).getTitle().set(title);
		setResults();
	}

	public void ChangeDirector(Movie m, String newV) {
		getMovie(m).getDirector().set(newV);
		setResults();
	}

	public void ChangeMainActor(Movie m, String newV) {
		getMovie(m).getMainActor().set(newV);
		setResults();
	}

	public void ChangeEnglishTitle(Movie m, String newV) {
		getMovie(m).getTitleEnglish().set(newV);
		setResults();
	}

	public void ChangeGenre(Movie m, String newV) {
		getMovie(m).getGenre().set(newV);
		setResults();
	}

	public void ChangeYearOfProduction(Movie m, int newV) {
		getMovie(m).getYearOfProduction().set(newV);
		setResults();
	}

	public void ChangeCountry(Movie m, String newV) {
		getMovie(m).getCountry().set(newV);
		setResults();
	}

	public void ChangeDuration(Movie m, int newV) {
		getMovie(m).getDuration().set(newV);
		setResults();
	}

	public void ChangeFSK(Movie m, int newV) {
		getMovie(m).getFsk().set(newV);
		setResults();
	}

	public void ChangeNumberOfOscars(Movie m, int newV) {
		getMovie(m).getNumberOfOscars().set(newV);
		setResults();
	}

	public void ChangeCinemaStart(Movie m, Date date) {
		getMovie(m).getStartDate().set(date);
		setResults();
	}
}
