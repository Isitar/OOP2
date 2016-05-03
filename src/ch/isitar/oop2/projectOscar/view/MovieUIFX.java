package ch.isitar.oop2.projectOscar.view;

import java.util.Date;
import java.util.List;

import ch.isitar.oop2.projectOscar.model.Movie;
import ch.isitar.oop2.projectOscar.presenter.MoviePresenter;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Label;
import javafx.scene.control.SplitPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;

/**
 * the application user interface
 */
public class MovieUIFX extends VBox implements MovieView {

	private final MoviePresenter presenter;
	private TableView<Movie> leftTable;
	private SplitPane splitPane;
	private VBox rightPart;
	private VBox editPart;
	private HBox labelPart;

	public MovieUIFX() {
		initializeControls();
		layoutControls();
		addEventHandlers();
		addValueChangeListeners();
		addBindings();

		presenter = new MoviePresenter(this);
	}

	@Override
	public void setResults(List<Movie> movies) {
		ObservableList<Movie> observableList = FXCollections.observableArrayList();
		observableList.addAll(movies);
		updateTable(observableList);
	}

	private void updateTable(ObservableList<Movie> movies)
	{
		leftTable.setItems(movies);
		TableColumn<Movie, Integer> idColumn = new TableColumn<>("id");
		TableColumn<Movie, String> titleColumn = new TableColumn<>("title");
		TableColumn<Movie, Integer> yearOfAwardColumn = new TableColumn<>("yearOfAward");
		TableColumn<Movie, String> directorColumn = new TableColumn<>("director");
		TableColumn<Movie, String> mainActorColumn = new TableColumn<>("mainActor");
		TableColumn<Movie, String> titleEnglishColumn = new TableColumn<>("titleEnglish");
		TableColumn<Movie, Integer> yearOfProductionColumn = new TableColumn<>("yearOfProduction");
		TableColumn<Movie, String> countryColumn = new TableColumn<>("country");
		TableColumn<Movie, Integer> durationColumn = new TableColumn<>("duration");
		TableColumn<Movie, Integer> fskColumn = new TableColumn<>("fsk");
		TableColumn<Movie, String> genreColumn = new TableColumn<>("genre");
		TableColumn<Movie, Date> startDateColumn = new TableColumn<>("startDate");
		TableColumn<Movie, Integer> numberOfOscarsColumn = new TableColumn<>("numberOfOscars");

		idColumn.setCellValueFactory(e -> e.getValue().getId().asObject());
		titleColumn.setCellValueFactory(e -> e.getValue().getTitle());
		yearOfAwardColumn.setCellValueFactory(e -> e.getValue().getYearOfAward().asObject());
		directorColumn.setCellValueFactory(e -> e.getValue().getDirector());
		mainActorColumn.setCellValueFactory(e -> e.getValue().getMainActor());
		titleEnglishColumn.setCellValueFactory(e -> e.getValue().getTitleEnglish());
		yearOfProductionColumn.setCellValueFactory(e -> e.getValue().getYearOfProduction().asObject());
		countryColumn.setCellValueFactory(e -> e.getValue().getCountry());
		durationColumn.setCellValueFactory(e -> e.getValue().getDuration().asObject());
		fskColumn.setCellValueFactory(e -> e.getValue().getFsk().asObject());
		genreColumn.setCellValueFactory(e -> e.getValue().getGenre());
		startDateColumn.setCellValueFactory(e -> e.getValue().getStartDate());
		numberOfOscarsColumn.setCellValueFactory(e -> e.getValue().getNumberOfOscars().asObject());

		leftTable.getColumns().add(idColumn);
		leftTable.getColumns().add(titleColumn);
		leftTable.getColumns().add(yearOfAwardColumn);
		leftTable.getColumns().add(directorColumn);
		leftTable.getColumns().add(mainActorColumn);
		leftTable.getColumns().add(titleEnglishColumn);
		leftTable.getColumns().add(yearOfProductionColumn);
		leftTable.getColumns().add(countryColumn);
		leftTable.getColumns().add(durationColumn);
		leftTable.getColumns().add(fskColumn);
		leftTable.getColumns().add(genreColumn);
		leftTable.getColumns().add(startDateColumn);
		leftTable.getColumns().add(numberOfOscarsColumn);
	}
	
	private void initializeControls() {
		leftTable = new TableView<>();
		splitPane = new SplitPane();
		rightPart = new VBox();
		editPart = new VBox();
		labelPart = new HBox();
	}

	private void layoutControls() {
		getChildren().add(splitPane);
		splitPane.getItems().add(leftTable);
		splitPane.getItems().add(rightPart);
		rightPart.getChildren().add(labelPart);
		rightPart.getChildren().add(editPart);
	}

	private void addEventHandlers() {
	}

	private void addValueChangeListeners() {
	}

	private void addBindings() {
	}
}
