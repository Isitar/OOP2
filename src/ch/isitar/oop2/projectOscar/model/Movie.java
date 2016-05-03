package ch.isitar.oop2.projectOscar.model;

import java.util.Date;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.StringProperty;

public class Movie {
	private IntegerProperty id;
	private StringProperty title;
	private IntegerProperty yearOfAward;
	private StringProperty director;
	private StringProperty mainActor;
	private StringProperty titleEnglish;
	private IntegerProperty yearOfProduction;
	private StringProperty country;
	private IntegerProperty duration;
	private IntegerProperty fsk;
	private StringProperty genre;
	private SimpleObjectProperty<Date> startDate;
	private IntegerProperty numberOfOscars;

	public Movie(IntegerProperty id, StringProperty title, IntegerProperty yearOfAward, StringProperty director,
			StringProperty mainActor, StringProperty titleEnglish, IntegerProperty yearOfProduction,
			StringProperty country, IntegerProperty duration, IntegerProperty fsk, StringProperty genre,
			SimpleObjectProperty<Date> startDate, IntegerProperty numberOfOscars) {
		super();
		this.id = id;
		this.title = title;
		this.yearOfAward = yearOfAward;
		this.director = director;
		this.mainActor = mainActor;
		this.titleEnglish = titleEnglish;
		this.yearOfProduction = yearOfProduction;
		this.country = country;
		this.duration = duration;
		this.fsk = fsk;
		this.genre = genre;
		this.startDate = startDate;
		this.numberOfOscars = numberOfOscars;
	}

	public IntegerProperty getId() {
		return id;
	}

	public void setId(IntegerProperty id) {
		this.id = id;
	}

	public StringProperty getTitle() {
		return title;
	}

	public void setTitle(StringProperty title) {
		this.title = title;
	}

	public IntegerProperty getYearOfAward() {
		return yearOfAward;
	}

	public void setYearOfAward(IntegerProperty yearOfAward) {
		this.yearOfAward = yearOfAward;
	}

	public StringProperty getDirector() {
		return director;
	}

	public void setDirector(StringProperty director) {
		this.director = director;
	}

	public StringProperty getMainActor() {
		return mainActor;
	}

	public void setMainActor(StringProperty mainActor) {
		this.mainActor = mainActor;
	}

	public StringProperty getTitleEnglish() {
		return titleEnglish;
	}

	public void setTitleEnglish(StringProperty titleEnglish) {
		this.titleEnglish = titleEnglish;
	}

	public IntegerProperty getYearOfProduction() {
		return yearOfProduction;
	}

	public void setYearOfProduction(IntegerProperty yearOfProduction) {
		this.yearOfProduction = yearOfProduction;
	}

	public StringProperty getCountry() {
		return country;
	}

	public void setCountry(StringProperty country) {
		this.country = country;
	}

	public IntegerProperty getDuration() {
		return duration;
	}

	public void setDuration(IntegerProperty duration) {
		this.duration = duration;
	}

	public IntegerProperty getFsk() {
		return fsk;
	}

	public void setFsk(IntegerProperty fsk) {
		this.fsk = fsk;
	}

	public StringProperty getGenre() {
		return genre;
	}

	public void setGenre(StringProperty genre) {
		this.genre = genre;
	}

	public SimpleObjectProperty<Date> getStartDate() {
		return startDate;
	}

	public void setStartDate(SimpleObjectProperty<Date> startDate) {
		this.startDate = startDate;
	}

	public IntegerProperty getNumberOfOscars() {
		return numberOfOscars;
	}

	public void setNumberOfOscars(IntegerProperty numberOfOscars) {
		this.numberOfOscars = numberOfOscars;
	}

	@Override
	public String toString() {
		return id + " " + title + " " + yearOfAward + " " + director + " " + mainActor + " " + titleEnglish + " "
				+ yearOfProduction + " " + country + " " + duration + " " + fsk + " " + genre + " " + startDate + " "
				+ numberOfOscars;

	}
}
