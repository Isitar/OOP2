package ch.isitar.oop2.projectOscar.view;

import ch.isitar.oop2.projectOscar.model.Movie;
import ch.isitar.oop2.projectOscar.presenter.MoviePresenter;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.ObservableList;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.util.converter.IntegerStringConverter;

public class TablePart extends TableView<Movie> {

	private SimpleObjectProperty<Movie> selectedMovie;
	private MoviePresenter presenter;

	TableColumn<Movie, Integer> yearOfAwardColumn;
	TableColumn<Movie, String> titleColumn;
	TableColumn<Movie, String> directorColumn;
	TableColumn<Movie, String> mainActorColumn;

	public TablePart(SimpleObjectProperty<Movie> selectedMovie, MoviePresenter presenter) {
		this.selectedMovie = selectedMovie;
		this.presenter = presenter;

		initControls();
		addListener();
	}

	private void initControls() {
		this.setEditable(true);

		yearOfAwardColumn = new TableColumn<>("Jahr");
		yearOfAwardColumn.setEditable(true);
		titleColumn = new TableColumn<>("Titel");
		directorColumn = new TableColumn<>("Regisseur");
		mainActorColumn = new TableColumn<>("Hauptdarsteller");

		titleColumn.setCellFactory(TextFieldTableCell.<Movie> forTableColumn());
		titleColumn.setCellValueFactory(e -> e.getValue().getTitle());

		directorColumn.setCellFactory(TextFieldTableCell.<Movie> forTableColumn());
		directorColumn.setCellValueFactory(e -> e.getValue().getDirector());

		mainActorColumn.setCellFactory(TextFieldTableCell.<Movie> forTableColumn());
		mainActorColumn.setCellValueFactory(e -> e.getValue().getMainActor());

		yearOfAwardColumn
				.setCellFactory(TextFieldTableCell.<Movie, Integer> forTableColumn(new IntegerStringConverter()));
		yearOfAwardColumn.setCellValueFactory(e -> e.getValue().getYearOfAward().asObject());

		yearOfAwardColumn.setMaxWidth(Double.MAX_VALUE * 0.1);
		titleColumn.setMaxWidth(Double.MAX_VALUE * 0.3);
		directorColumn.setMaxWidth(Double.MAX_VALUE * 0.3);
		mainActorColumn.setMaxWidth(Double.MAX_VALUE * 0.3);

		getColumns().add(yearOfAwardColumn);
		getColumns().add(titleColumn);
		getColumns().add(directorColumn);
		getColumns().add(mainActorColumn);
		setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
	}

	public void setData(ObservableList<Movie> movies) {
		setItems(movies);
		if (getSelectionModel().selectedIndexProperty().get() == -1) {
			getSelectionModel().selectFirst();

		}
		selectedMovie.set(null);
		selectedMovie.set(getSelectionModel().getSelectedItem());
	}

	private void addListener() {

		getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
			if (newSelection != null)
				selectedMovie.set(newSelection);
		});

		yearOfAwardColumn.setOnEditCommit((cellEditEvent) -> {
			presenter.ChangeYear(selectedMovie.get(), cellEditEvent.getNewValue());
		});

		titleColumn.setOnEditCommit((cellEditEvent) -> {
			presenter.ChangeTitle(selectedMovie.get(), cellEditEvent.getNewValue());
		});
		directorColumn.setOnEditCommit((cellEditEvent) -> {
			presenter.ChangeDirector(selectedMovie.get(), cellEditEvent.getNewValue());
		});

		mainActorColumn.setOnEditCommit((cellEditEvent) -> {
			presenter.ChangeMainActor(selectedMovie.get(), cellEditEvent.getNewValue());
		});
	}
}
