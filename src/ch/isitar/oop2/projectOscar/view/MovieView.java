package ch.isitar.oop2.projectOscar.view;

import java.util.List;

import ch.isitar.oop2.projectOscar.model.Movie;

/**
 * the view of the application
 */
public interface MovieView {

	/**
	 * sets the list of election results
	 *
	 * @param results
	 *            the list of election results
	 */
	public void setResults(List<Movie> results);

	public void DisplayError(String errorMessage);
}
