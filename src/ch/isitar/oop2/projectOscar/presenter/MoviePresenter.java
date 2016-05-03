package ch.isitar.oop2.projectOscar.presenter;

import ch.isitar.oop2.projectOscar.model.*;
import ch.isitar.oop2.projectOscar.view.*;

/**
 * the presentation model of the application
 */
public class MoviePresenter {

	private final MovieView applicationView;
	private final MovieModel applicationModel;

	/**
	 * creates a new PresentationModel
	 *
	 * @param applicationView
	 *            the ApplicationView
	 */
	public MoviePresenter(MovieView applicationView) {
		this.applicationView = applicationView;

		applicationModel = new MovieFileRepository();
		this.applicationView.setResults(applicationModel.getData());
	}
}

