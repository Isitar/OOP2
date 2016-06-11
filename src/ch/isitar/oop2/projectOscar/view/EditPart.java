package ch.isitar.oop2.projectOscar.view;

import java.time.ZoneId;
import java.util.Date;

import ch.isitar.oop2.projectOscar.model.Movie;
import ch.isitar.oop2.projectOscar.presenter.MoviePresenter;
import javafx.beans.property.ReadOnlyObjectProperty;
import javafx.geometry.Insets;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.util.Callback;

public class EditPart extends GridPane {
	private boolean internalUpdate = false;

	private Label lblYear = new Label();
	private Label lblTitle = new Label();
	private Label lblDirector = new Label();
	private Label lblMainActor = new Label();
	private Label lblEnglishTitle = new Label();
	private Label lblGenre = new Label();
	private Label lblCountry = new Label();
	private Label lblFsk = new Label();
	private Label lblOscar = new Label();
	private Label lblProductionYear = new Label();
	private Label lblDuration = new Label();
	private Label lblCinemaStart = new Label();

	private Spinner<Integer> spnYear = new Spinner<>();
	private TextField txtTitle = new TextField();
	private TextField txtDirector = new TextField();
	private TextField txtMainActor = new TextField();
	private TextField txtEnglishTitle = new TextField();
	private TextField txtGenre = new TextField();
	private TextField txtCountry = new TextField();
	private ComboBox<FSK> cmbFsk = new ComboBox<>();
	private Spinner<Integer> spnOscar = new Spinner<>();
	private Spinner<Integer> spnProductionYear = new Spinner<>();
	private Spinner<Integer> spnDuration = new Spinner<>();
	private DatePicker dpkCinemaStart = new DatePicker();

	private ReadOnlyObjectProperty<Movie> selectedMovie;
	private MoviePresenter presenter;

	private void initControls() {
		lblTitle.setText("Titel");
		lblYear.setText("Jahr");
		lblDirector.setText("Regisseur");
		lblCinemaStart.setText("Kinostart");
		lblCountry.setText("Land");
		lblDuration.setText("Länge (Minuten)");
		lblMainActor.setText("Hauptdarsteller");
		lblEnglishTitle.setText("englischer Titel");
		lblGenre.setText("Genre");
		lblFsk.setText("FSK-Altersfreigabe");
		lblOscar.setText("Oscars");
		lblProductionYear.setText("Produktionsjahr");

		cmbFsk.getItems().addAll(FSK.FSK0, FSK.FSK6, FSK.FSK12, FSK.FSK16, FSK.FSK18);
		cmbFsk.setCellFactory(new Callback<ListView<FSK>, ListCell<FSK>>() {
			@Override
			public ListCell<FSK> call(ListView<FSK> param) {
				return new ListCell<FSK>() {
					private final ImageView imageView;
					{
						setContentDisplay(ContentDisplay.GRAPHIC_ONLY);
						imageView = new ImageView();
					}

					@Override
					protected void updateItem(FSK fsk, boolean empty) {
						super.updateItem(fsk, empty);
						if (empty)
							fsk = FSK.none;

						switch (fsk) {

						case FSK0:
							imageView.setImage(new Image(
									getClass().getResourceAsStream("fsk_labels/FSK_ab_0_logo_Dec_2008.svg.png")));
							break;
						case FSK6:
							imageView.setImage(new Image(
									getClass().getResourceAsStream("fsk_labels/FSK_ab_6_logo_Dec_2008.svg.png")));
							break;
						case FSK12:
							imageView.setImage(new Image(
									getClass().getResourceAsStream("fsk_labels/FSK_ab_12_logo_Dec_2008.svg.png")));
							break;
						case FSK16:
							imageView.setImage(new Image(
									getClass().getResourceAsStream("fsk_labels/FSK_ab_16_logo_Dec_2008.svg.png")));
							break;
						case FSK18:
							imageView.setImage(new Image(
									getClass().getResourceAsStream("fsk_labels/FSK_ab_18_logo_Dec_2008.svg.png")));
							break;
						default:
							imageView.setImage(null);
						}
						setGraphic(imageView);
					}
				};

			}
		});
		cmbFsk.setButtonCell(cmbFsk.getCellFactory().call(null));
	}

	private void Update(Movie m) {
		internalUpdate = true;
		spnYear.getValueFactory().setValue(m.getYearOfAward().get());
		txtTitle.setText(m.getTitle().get());
		txtDirector.setText(m.getDirector().get());
		txtMainActor.setText(m.getMainActor().get());
		txtCountry.setText(m.getCountry().get());
		spnDuration.getValueFactory().setValue(m.getDuration().get());
		txtEnglishTitle.setText(m.getTitleEnglish().get());
		txtGenre.setText(m.getGenre().get());
		spnOscar.getValueFactory().setValue(m.getNumberOfOscars().get());
		spnProductionYear.getValueFactory().setValue(m.getYearOfProduction().get());
		try {
			dpkCinemaStart
					.setValue(m.getStartDate().getValue().toInstant().atZone(ZoneId.systemDefault()).toLocalDate());
		} catch (Exception e) {
			dpkCinemaStart.setValue(null);
		}

		cmbFsk.getSelectionModel().select(FSK.valueOf("FSK" + m.getFsk().get()));
		internalUpdate = false;
	}

	private void layoutControls() {
		setPadding(new Insets(10));
		setHgap(5);
		setVgap(5);

		int row = 0;
		add(lblYear, 0, row);
		add(spnYear, 1, row);
		row++;
		add(lblTitle, 0, row);
		add(txtTitle, 1, row, 3, 1);
		row++;
		add(lblDirector, 0, row);
		add(txtDirector, 1, row, 3, 1);
		row++;
		add(lblMainActor, 0, row);
		add(txtMainActor, 1, row, 3, 1);
		row++;
		add(lblEnglishTitle, 0, row);
		add(txtEnglishTitle, 1, row, 3, 1);
		row++;
		add(lblGenre, 0, row);
		add(txtGenre, 1, row);
		add(lblProductionYear, 2, row);
		add(spnProductionYear, 3, row);

		row++;
		add(lblCountry, 0, row);
		add(txtCountry, 1, row);
		add(lblDuration, 2, row);
		add(spnDuration, 3, row);
		row++;
		add(lblFsk, 0, row);
		add(cmbFsk, 1, row);
		add(lblCinemaStart, 2, row);
		add(dpkCinemaStart, 3, row);
		row++;
		add(lblOscar, 0, row);
		add(spnOscar, 1, row);
		row++;

		spnDuration.setMaxWidth(Double.MAX_VALUE);
		spnOscar.setMaxWidth(Double.MAX_VALUE);
		spnProductionYear.setMaxWidth(Double.MAX_VALUE);
		spnYear.setMaxWidth(Double.MAX_VALUE);

		dpkCinemaStart.setMaxWidth(Double.MAX_VALUE);

		ColumnConstraints cs = new ColumnConstraints();
		cs.setHgrow(Priority.NEVER);

		ColumnConstraints cs2 = new ColumnConstraints();
		cs2.setHgrow(Priority.ALWAYS);
		getColumnConstraints().addAll(cs, cs2, cs, cs2);

		setStyle("-fx-background-color: red");
		setMaxWidth(Double.MAX_VALUE);
	}

	private void changeListeners() {
		spnYear.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(1920, 2070));
		spnYear.getValueFactory().valueProperty().addListener((prop, oldV, newV) -> {
			if (!internalUpdate)
				presenter.ChangeYear(selectedMovie.get(), newV);
		});

		txtTitle.textProperty().addListener((prop, oldV, newV) -> {
			if (!internalUpdate)
				presenter.ChangeTitle(selectedMovie.get(), newV);
		});

		txtDirector.textProperty().addListener((prop, oldV, newV) -> {
			if (!internalUpdate)
				presenter.ChangeDirector(selectedMovie.get(), newV);
		});

		txtMainActor.textProperty().addListener((prop, oldV, newV) -> {
			if (!internalUpdate)
				presenter.ChangeMainActor(selectedMovie.get(), newV);
		});

		txtCountry.textProperty().addListener((prop, oldV, newV) -> {
			if (!internalUpdate)
				presenter.ChangeCountry(selectedMovie.get(), newV);
		});

		txtEnglishTitle.textProperty().addListener((prop, oldV, newV) -> {
			if (!internalUpdate)
				presenter.ChangeEnglishTitle(selectedMovie.get(), newV);
		});

		txtGenre.textProperty().addListener((prop, oldV, newV) -> {
			if (!internalUpdate)
				presenter.ChangeGenre(selectedMovie.get(), newV);
		});

		spnDuration.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 9999));
		spnDuration.getValueFactory().valueProperty().addListener((prop, oldV, newV) -> {
			if (!internalUpdate)
				presenter.ChangeDuration(selectedMovie.get(), newV);
		});

		spnOscar.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(1, 50));
		spnOscar.getValueFactory().valueProperty().addListener((prop, oldV, newV) -> {
			if (!internalUpdate)
				presenter.ChangeNumberOfOscars(selectedMovie.get(), newV);
		});

		spnProductionYear.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(1920, 2070));
		spnProductionYear.getValueFactory().valueProperty().addListener((prop, oldV, newV) -> {
			if (!internalUpdate)
				presenter.ChangeYearOfProduction(selectedMovie.get(), newV);
		});

		dpkCinemaStart.valueProperty().addListener((prop, oldV, newV) -> {
			if (!internalUpdate)
				presenter.ChangeCinemaStart(selectedMovie.get(),
						Date.from(newV.atStartOfDay().atZone(ZoneId.systemDefault()).toInstant()));
		});

		cmbFsk.valueProperty().addListener((prop, oldV, newV) -> {
			if (!internalUpdate)
				presenter.ChangeFSK(selectedMovie.get(), newV.getVal());
		});
	}

	public EditPart(ReadOnlyObjectProperty<Movie> selectedMovie, MoviePresenter presenter) {
		this.selectedMovie = selectedMovie;
		this.presenter = presenter;
		initControls();
		layoutControls();

		changeListeners();
		selectedMovie.addListener((prop, oldV, newV) -> {
			Update(newV);
		});
	}

}
