package ch.isitar.oop2.projectOscar.view;

import java.util.List;

import ch.isitar.oop2.projectOscar.model.Movie;
import ch.isitar.oop2.projectOscar.presenter.MoviePresenter;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;
import javafx.scene.control.SplitPane;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;

/**
 * the application user interface
 */
public class MovieUIFX extends VBox implements MovieView {

	private final MoviePresenter presenter;

	private SimpleObjectProperty<Movie> selectedMovie;

	private MovieToolbar toolbar;
	private TablePart leftTable;
	private SplitPane splitPane;
	private VBox rightPart;
	private LabelPart labelPart;
	private EditPart editPart;

	public MovieUIFX() {
		presenter = new MoviePresenter(this);
		initializeControls();
		layoutControls();
		addEventHandlers();
		addValueChangeListeners();
		presenter.fillView();
	}

	@Override
	public void setResults(List<Movie> movies) {
		ObservableList<Movie> observableList = FXCollections.observableArrayList();
		observableList.addAll(movies);

		leftTable.setData(observableList);

	}

	private void initializeControls() {
		selectedMovie = new SimpleObjectProperty<Movie>();
		selectedMovie.set(new Movie());
		leftTable = new TablePart(selectedMovie, presenter);
		splitPane = new SplitPane();
		rightPart = new VBox();
		labelPart = new LabelPart(selectedMovie);
		toolbar = new MovieToolbar(selectedMovie, presenter);
		editPart = new EditPart(selectedMovie, presenter);
	}

	private void layoutControls() {
		getChildren().add(toolbar);
		MovieUIFX.setVgrow(toolbar, Priority.NEVER);

		getChildren().add(splitPane);

		MovieUIFX.setVgrow(splitPane, Priority.ALWAYS);

		splitPane.getItems().add(leftTable);

		splitPane.getItems().add(rightPart);

		rightPart.getChildren().add(labelPart);
		rightPart.getChildren().add(editPart);
		VBox.setVgrow(labelPart, Priority.ALWAYS);
		VBox.setVgrow(editPart, Priority.NEVER);
	}

	private void addEventHandlers() {
	}

	private void addValueChangeListeners() {

	}

	@Override
	public void DisplayError(String errorMessage) {
		Alert alert = new Alert(AlertType.ERROR);
		alert.setContentText(errorMessage);
		alert.showAndWait();
	}
}
