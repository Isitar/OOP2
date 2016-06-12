package ch.isitar.oop2.projectOscar.view;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import ch.isitar.oop2.projectOscar.model.Movie;
import ch.isitar.oop2.projectOscar.presenter.MoviePresenter;
import ch.isitar.oop2.projectOscar.view.command.UndoRedoCommand;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.ListChangeListener;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListCell;
import javafx.scene.control.Separator;
import javafx.scene.control.TextField;
import javafx.scene.control.ToolBar;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;

public class MovieToolbar extends ToolBar {

	private final int toolbarHeight = 48;

	private Button saveButton;
	private Button addButton;
	private Button removeButton;
	private Button undoButton;
	private Button redoButton;

	private ComboBox<String> undoList;
	private ComboBox<String> redoList;

	private TextField searchField;

	private MoviePresenter presenter;

	private SimpleObjectProperty<Movie> selectedMovie;

	public MovieToolbar(SimpleObjectProperty<Movie> selectedMovie, MoviePresenter presenter) {
		this.selectedMovie = selectedMovie;
		this.presenter = presenter;

		initControls();
		layoutControls();
		listeners();
	}

	private void initControls() {
		saveButton = createToolbarButton("Icons/save.svg.png");
		addButton = createToolbarButton("Icons/add.svg.png");
		removeButton = createToolbarButton("Icons/remove.svg.png");
		undoButton = createToolbarButton("Icons/undo.svg.png");
		redoButton = createToolbarButton("Icons/redo.svg.png");

		searchField = new TextField();

		undoButton.disableProperty().bind(presenter.getUndoEnabledProperty().not());
		redoButton.disableProperty().bind(presenter.getRedoEnabledProperty().not());

		undoList = new ComboBox<>();
		undoList.setButtonCell(new ListCell<String>());
		undoList.setMaxWidth(5);
		undoList.setMinHeight(60);
		undoList.setPrefHeight(60);
		undoList.disableProperty().bind(presenter.getUndoEnabledProperty().not());

		redoList = new ComboBox<>();
		redoList.setMaxWidth(5);
		redoList.setMinHeight(60);
		redoList.setPrefHeight(60);
		redoList.disableProperty().bind(presenter.getRedoEnabledProperty().not());
	}

	private void listeners() {
		saveButton.setOnMouseClicked((e) -> {
			presenter.save();
		});

		addButton.setOnMouseClicked((e) -> {
			selectedMovie.set(presenter.createMovie());
		});

		removeButton.setOnMouseClicked((e) -> {
			presenter.deleteMovie(selectedMovie.get());
		});

		undoButton.setOnMouseClicked((e) -> {
			presenter.undo();
		});

		redoButton.setOnMouseClicked((e) -> {
			presenter.redo();
		});

		presenter.getUndoListProperty().addListener((ListChangeListener.Change<? extends UndoRedoCommand> c) -> {
			undoList.getItems().clear();
			List<String> items = presenter.getUndoListProperty().stream().map(x -> x.toString())
					.collect(Collectors.toList());
			items.add("abbrechen");
			Collections.reverse(items);
			undoList.getItems().addAll(items);
		});

		presenter.getRedoListProperty().addListener((ListChangeListener.Change<? extends UndoRedoCommand> c) -> {
			redoList.getItems().clear();
			List<String> items = presenter.getRedoListProperty().stream().map(x -> x.toString())
					.collect(Collectors.toList());
			items.add("abbrechen");
			Collections.reverse(items);
			redoList.getItems().addAll(items);
		});

		undoList.valueProperty().addListener((o, oldV, newV) -> {

			int selectedIndex = undoList.getSelectionModel().selectedIndexProperty().get();
			if (selectedIndex == 0)
				return;

			for (int i = 0; i < selectedIndex; i++)
				presenter.undo();
		});
		redoList.selectionModelProperty().addListener((o, oldV, newV) -> {
			int selectedIndex = redoList.getSelectionModel().selectedIndexProperty().get();
			if (selectedIndex == 0)
				return;

			for (int i = 0; i < selectedIndex; i++)
				presenter.redo();
		});

	}

	private void layoutControls() {
		getItems().add(saveButton);
		getItems().add(new Separator());
		getItems().add(addButton);
		getItems().add(removeButton);
		getItems().add(new Separator());
		getItems().add(undoButton);
		getItems().add(undoList);
		getItems().add(redoButton);
		getItems().add(redoList);

		HBox spacer = new HBox();
		HBox.setHgrow(spacer, Priority.ALWAYS);
		getItems().add(spacer);
		getItems().add(searchField);
	}

	private Button createToolbarButton(String imageURL) {
		Button btn = new Button();

		btn.setGraphic(new ImageView(new Image(getClass().getResourceAsStream(imageURL))));

		// this code disables the button ? why?
		// btn.backgroundProperty()
		// .set(new Background(new BackgroundImage(new
		// Image(getClass().getResourceAsStream(imageURL)),
		// BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT,
		// BackgroundPosition.DEFAULT,
		// BackgroundSize.DEFAULT)));

		btn.setPrefWidth(toolbarHeight);
		btn.setPrefHeight(toolbarHeight);

		return btn;
	}
}
