package ch.isitar.oop2.projectOscar.model;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class FileRepository {

	private List<MovieInterface> data;

	public List<MovieInterface> getData() {
		return getData(getClass().getResourceAsStream("movies.csv"));
	}

	/**
	 * returns the data from a given file
	 *
	 * @param path
	 *            the name of the given file
	 * @return the election results
	 */
	public List<MovieInterface> getData(String path) {
		try {
			return getData(new FileInputStream(path));
		} catch (FileNotFoundException ex) {
			// TODO
		}
		return null;
	}

	/**
	 * returns the data from a given InputStream
	 *
	 * @param inputSream
	 *            the InputStream to read from
	 * @return the election results
	 */
	public List<MovieInterface> getData(InputStream inputSream) {

		data = new ArrayList<>();
		try (BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputSream, "UTF-8"))) {
			bufferedReader.lines().skip(1).forEach(x -> {

				String[] splitted = x.split(";");

				IntegerProperty id = new SimpleIntegerProperty();
				id.set(Integer.parseInt(splitted[0]));
				StringProperty title = new SimpleStringProperty();
				title.set(splitted[1]);
				IntegerProperty yearOfAward = new SimpleIntegerProperty();
				yearOfAward.set(Integer.parseInt(splitted[2]));
				StringProperty director = new SimpleStringProperty();
				director.set(splitted[3]);
				StringProperty mainActor = new SimpleStringProperty();
				mainActor.set(splitted[4]);
				StringProperty titleEnglish = new SimpleStringProperty();
				titleEnglish.set(splitted[5]);
				IntegerProperty yearOfProduction = new SimpleIntegerProperty();
				yearOfProduction.set(Integer.parseInt(splitted[6]));
				StringProperty country = new SimpleStringProperty();
				country.set(splitted[7]);
				IntegerProperty duration = new SimpleIntegerProperty();
				duration.set(Integer.parseInt(splitted[8]));
				IntegerProperty fsk = new SimpleIntegerProperty();
				fsk.set(Integer.parseInt(splitted[9]));
				StringProperty genre = new SimpleStringProperty();
				genre.set(splitted[10]);
				SimpleObjectProperty<Date> startDate = new SimpleObjectProperty<Date>();

				DateFormat df = new SimpleDateFormat("dd.MM.yyyy", Locale.GERMAN);

				try {
					startDate.set(df.parse(splitted[11]));
				} catch (Exception e) {
				}

				IntegerProperty numberOfOscars = new SimpleIntegerProperty();
				numberOfOscars.set(Integer.parseInt(splitted[12]));

				data.add(new Movie(id, title, yearOfAward, director, mainActor, titleEnglish, yearOfProduction, country,
						duration, fsk, genre, startDate, numberOfOscars));

			});

		} catch (IOException exception) {
			// TODO: crash and burn
		}
		return data;
	}
}
