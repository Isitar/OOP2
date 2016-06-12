package ch.isitar.oop2.projectOscar.view;

import java.util.Arrays;

import ch.isitar.oop2.projectOscar.model.Movie;
import javafx.beans.property.ReadOnlyObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;

public class LabelPart extends GridPane {

	private final int OSCARLISTSPLITPOINT = 14;
	private Label lblYearValue = new Label();
	private Label lblTitleValue = new Label();
	private Label lblDirectorValue = new Label();
	private Label lblMainActorValue = new Label();
	private HBox firstRow = new HBox();
	private HBox countryList;
	private ImageView poster = new ImageView();
	private HBox oscarList;

	private ReadOnlyObjectProperty<Movie> selectedMovie;

	public LabelPart(ReadOnlyObjectProperty<Movie> selectedMovie) {
		this.selectedMovie = selectedMovie;
		initControls();
		layoutControls();
		changeListeners();
	}

	private void initControls() {
		// setStyle("-fx-background-color: blue");

		oscarList = new HBox();
		countryList = new HBox();

		firstRow.getChildren().add(lblYearValue);
		HBox spacer = new HBox();
		HBox.setHgrow(spacer, Priority.ALWAYS);
		firstRow.getChildren().add(spacer);
		firstRow.getChildren().add(countryList);

		add(firstRow, 0, 0);
		add(lblTitleValue, 0, 1);
		add(lblDirectorValue, 0, 2);
		add(lblMainActorValue, 0, 3);
		add(oscarList, 0, 4);
		add(poster, 1, 0, 1, 5);

		GridPane.setHgrow(poster, Priority.NEVER);
		GridPane.setHgrow(oscarList, Priority.ALWAYS);
	}

	private void layoutControls() {
		lblYearValue.setStyle("-fx-font: 28 arial;");
		lblTitleValue.setStyle("-fx-font: 32 arial;");
		lblMainActorValue.setStyle("-fx-font: 24 arial;");
		lblDirectorValue.setStyle("-fx-font: 24 arial;");

	}

	private void updateCountryList(String countries) {
		countryList.getChildren().clear();
		try {
			Arrays.stream(countries.split(";")).forEach(c -> {
				try {
					countryList.getChildren()
							.add(new ImageView(new Image(getClass().getResourceAsStream("flags/" + c + ".png"))));
				} catch (Exception e) {
					// Pokemon Exceptionhandling :P
				}
			});
		} catch (NullPointerException e) {

		}

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

	private void updatePoster(int id) {
		String imgURL = "posters/" + id + ".jpg";
		if (getClass().getResourceAsStream(imgURL) == null)
			imgURL = "posters/no_poster.gif";

		poster.setImage(new Image(getClass().getResourceAsStream(imgURL)));
		poster.setPreserveRatio(true);
		poster.setFitHeight(256);
	}

	private void changeListeners() {

		selectedMovie.addListener((obs, oldV, newV) -> {
			if (newV == null)
				return;

			StringProperty fromProp = new SimpleStringProperty();
			fromProp.set("von ");

			StringProperty withProp = new SimpleStringProperty();
			withProp.set("mit ");
			lblYearValue.textProperty().bind(newV.getYearOfAward().asString());
			lblTitleValue.textProperty().bind(newV.getTitle());
			lblDirectorValue.textProperty().bind(fromProp.concat(newV.getDirector()));
			lblMainActorValue.textProperty().bind(withProp.concat(newV.getMainActor()));

			updateCountryList(newV.getCountry().get());
			updateOscarList(newV.getNumberOfOscars().get());
			updatePoster(newV.getId().get());
		});
	}

}
