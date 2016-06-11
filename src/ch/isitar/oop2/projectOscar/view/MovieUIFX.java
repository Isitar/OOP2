package ch.isitar.oop2.projectOscar.view;

import java.time.ZoneId;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import ch.isitar.oop2.projectOscar.model.Movie;
import ch.isitar.oop2.projectOscar.presenter.MoviePresenter;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.Separator;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.control.SplitPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.ToolBar;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.util.Callback;

/**
 * the application user interface
 */
public class MovieUIFX extends VBox implements MovieView {

	private final int OSCARLISTSPLITPOINT = 14;
	private final MoviePresenter presenter;

	private ObjectProperty<Movie> selectedMovie;

	private ToolBar toolbar;
	private TableView<Movie> leftTable;
	private SplitPane splitPane;
	private VBox rightPart;
	private GridPane labelPart;

	private Button saveButton = new Button();
	private Button addButton = new Button();
	private Button removeButton = new Button();
	private Button undoButton = new Button();
	private Button redoButton = new Button();

	private TextField searchField = new TextField();

	private Label lblYearValue = new Label();
	private Label lblTitleValue = new Label();
	private Label lblDirectorValue = new Label();
	private Label lblMainActorValue = new Label();
	private HBox firstRow = new HBox();
	private HBox countryList;
	private ImageView poster = new ImageView();
	private HBox oscarList;

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
		updateTable(observableList);

		updateCountryList(selectedMovie.get().getCountry().get());
		updateOscarList(selectedMovie.get().getNumberOfOscars().get());
	}

	private void updateTable(ObservableList<Movie> movies) {
		leftTable.setItems(movies);
		if (leftTable.getSelectionModel().selectedIndexProperty().get() == -1) {
			leftTable.getSelectionModel().selectFirst();

		}
		selectedMovie.set(leftTable.getSelectionModel().getSelectedItem());

	}

	private void setTxtFields(Movie m) {
		poster.setImage(new Image(getClass().getResourceAsStream("posters/" + m.getId().get() + ".jpg")));
		poster.setPreserveRatio(true);
		poster.setFitHeight(256);
	}

	private void updateCountryList(String countries) {
		countryList.getChildren().clear();
		Arrays.stream(countries.split(";")).forEach(c -> {
			try {
				countryList.getChildren()
						.add(new ImageView(new Image(getClass().getResourceAsStream("flags/" + c + ".png"))));
			} catch (Exception e) {
				// Pokemon Exceptionhandling :P
			}
		});

	}

	private void updateOscarList(int noOfOscars) {
		oscarList.getChildren().clear();
		Image image = new Image(getClass().getResourceAsStream("OscarResource/Oscar-logo.png"));

		double fitHeight = 60;
		double fitWidth = 30;

		if (noOfOscars < OSCARLISTSPLITPOINT) {
			fitHeight *= 2;
			fitWidth *= 2;
		}

		for (int i = 0; i < noOfOscars; i++) {
			ImageView iv = new ImageView(image);
			iv.setFitHeight(fitHeight);
			iv.setFitWidth(fitWidth);
			oscarList.getChildren().add(iv);
		}
	}

	private void createLabelPart() {
		labelPart.setStyle("-fx-background-color: blue");

		firstRow.getChildren().add(lblYearValue);
		HBox spacer = new HBox();
		HBox.setHgrow(spacer, Priority.ALWAYS);
		firstRow.getChildren().add(spacer);
		firstRow.getChildren().add(countryList);

		labelPart.add(firstRow, 0, 0);
		labelPart.add(lblTitleValue, 0, 1);
		labelPart.add(lblDirectorValue, 0, 2);
		labelPart.add(lblMainActorValue, 0, 3);
		labelPart.add(oscarList, 0, 4);
		labelPart.add(poster, 1, 0, 1, 5);

		GridPane.setHgrow(poster, Priority.NEVER);
		GridPane.setHgrow(oscarList, Priority.ALWAYS);

		// ColumnConstraints cs = new ColumnConstraints();
		// cs.setHgrow(Priority.NEVER);
		//
		// labelPart.getColumnConstraints().add(cs);

	}

	private void initializeControls() {
		selectedMovie = new SimpleObjectProperty<Movie>();
		selectedMovie.set(new Movie());
		leftTable = new TableView<>();
		splitPane = new SplitPane();
		rightPart = new VBox();
		labelPart = new GridPane();
		toolbar = new ToolBar();

		oscarList = new HBox();
		countryList = new HBox();
		createLabelPart();
		editPart = new EditPart(selectedMovie, presenter);
	}

	private void layoutControls() {
		getChildren().add(toolbar);
		MovieUIFX.setVgrow(toolbar, Priority.NEVER);
		toolbar.getItems().add(saveButton);
		toolbar.getItems().add(new Separator());
		toolbar.getItems().add(addButton);
		toolbar.getItems().add(removeButton);
		toolbar.getItems().add(new Separator());
		toolbar.getItems().add(undoButton);
		toolbar.getItems().add(redoButton);

		HBox spacer = new HBox();
		HBox.setHgrow(spacer, Priority.ALWAYS);
		toolbar.getItems().add(spacer);
		toolbar.getItems().add(searchField);

		getChildren().add(splitPane);

		MovieUIFX.setVgrow(splitPane, Priority.ALWAYS);

		TableColumn<Movie, Integer> yearOfAwardColumn = new TableColumn<>("Jahr");
		TableColumn<Movie, String> titleColumn = new TableColumn<>("Titel");
		TableColumn<Movie, String> directorColumn = new TableColumn<>("Regisseur");
		TableColumn<Movie, String> mainActorColumn = new TableColumn<>("Hauptdarsteller");

		titleColumn.setCellValueFactory(e -> e.getValue().getTitle());
		directorColumn.setCellValueFactory(e -> e.getValue().getDirector());
		mainActorColumn.setCellValueFactory(e -> e.getValue().getMainActor());
		yearOfAwardColumn.setCellValueFactory(e -> e.getValue().getYearOfAward().asObject());

		yearOfAwardColumn.setMaxWidth(Double.MAX_VALUE * 0.1);
		titleColumn.setMaxWidth(Double.MAX_VALUE * 0.3);
		directorColumn.setMaxWidth(Double.MAX_VALUE * 0.3);
		mainActorColumn.setMaxWidth(Double.MAX_VALUE * 0.3);

		leftTable.getColumns().add(yearOfAwardColumn);
		leftTable.getColumns().add(titleColumn);
		leftTable.getColumns().add(directorColumn);
		leftTable.getColumns().add(mainActorColumn);
		leftTable.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

		splitPane.getItems().add(leftTable);

		splitPane.getItems().add(rightPart);

		rightPart.getChildren().add(labelPart);
		rightPart.getChildren().add(editPart);
	}

	private void addEventHandlers() {
	}

	private void addValueChangeListeners() {
		leftTable.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
			if (newSelection != null) {
				selectedMovie.set(newSelection);
			}
		});

		selectedMovie.addListener((obs, oldV, newV) -> {
			setTxtFields(newV);
			lblYearValue.textProperty().bind(selectedMovie.get().getYearOfAward().asString());
			lblTitleValue.textProperty().bind(selectedMovie.get().getTitle());
			lblDirectorValue.textProperty().bind(selectedMovie.get().getDirector());
			lblMainActorValue.textProperty().bind(selectedMovie.get().getMainActor());
		});
	}

	@Override
	public void DisplayError(String errorMessage) {
		Alert alert = new Alert(AlertType.ERROR);
		alert.setContentText(errorMessage);
		alert.showAndWait();
	}
}
