package ch.isitar.oop2.projectOscar.model;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
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
import sun.security.x509.DeltaCRLIndicatorExtension;

public class MovieFileRepository implements MovieModel {
	private final String PATH = "movies.csv";
	private List<Movie> data;

	public List<Movie> getData() {
		File f = new File(PATH);
		if (f.exists()) {
			try {
				return getData(new FileInputStream(f));
			} catch (FileNotFoundException e) {
				// is handled
				return null;
			}
		} else
			return getData(getClass().getResourceAsStream(PATH));
	}

	/**
	 * returns the data from a given file
	 *
	 * @param path
	 *            the name of the given file
	 * @return the election results
	 */
	public List<Movie> getData(String path) {
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
	public List<Movie> getData(InputStream inputSream) {

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

	@Override
	public void saveData(List<Movie> movies) {
		String delimiter = ";";
		File f = new File(PATH);
		if (!f.exists())
			try {
				f.createNewFile();
			} catch (IOException e) {
				e.printStackTrace();
			}
		try {
			PrintWriter writer = new PrintWriter(PATH, "UTF-8");

			writer.println(
					"ID;Title;YearOfAward;Director;MainActor;TitleEnglish;YearOfProduction;Country;Duration;FSK;Genre;StartDate;NumberOfOscars");
			movies.forEach(m -> {
				writer.println(m.getId().get() + delimiter + m.getTitle().get() + delimiter + m.getYearOfAward().get()
						+ delimiter + m.getDirector().get() + delimiter + m.getMainActor().get() + delimiter
						+ m.getTitleEnglish().get() + delimiter + m.getYearOfProduction().get() + delimiter
						+ m.getCountry().get() + delimiter + m.getDuration().get() + delimiter + m.getFsk().get()
						+ delimiter + m.getGenre().get() + delimiter + m.getStartDate() + delimiter
						+ m.getNumberOfOscars().get());
			});
			writer.close();
		} catch (FileNotFoundException | UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
