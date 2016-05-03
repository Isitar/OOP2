package ch.isitar.oop2.projectOscar.model;

import java.util.Date;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.StringProperty;

public class Movie implements MovieInterface {
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

	/*
	 * (non-Javadoc)
	 * 
	 * @see ch.isitar.oop2.projectOscar.model.MovieInterface#getId()
	 */
	@Override
	public IntegerProperty getId() {
		return id;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ch.isitar.oop2.projectOscar.model.MovieInterface#setId(javafx.beans.
	 * property.IntegerProperty)
	 */
	@Override
	public void setId(IntegerProperty id) {
		this.id = id;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ch.isitar.oop2.projectOscar.model.MovieInterface#getTitle()
	 */
	@Override
	public StringProperty getTitle() {
		return title;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * ch.isitar.oop2.projectOscar.model.MovieInterface#setTitle(javafx.beans.
	 * property.StringProperty)
	 */
	@Override
	public void setTitle(StringProperty title) {
		this.title = title;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ch.isitar.oop2.projectOscar.model.MovieInterface#getYearOfAward()
	 */
	@Override
	public IntegerProperty getYearOfAward() {
		return yearOfAward;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * ch.isitar.oop2.projectOscar.model.MovieInterface#setYearOfAward(javafx.
	 * beans.property.IntegerProperty)
	 */
	@Override
	public void setYearOfAward(IntegerProperty yearOfAward) {
		this.yearOfAward = yearOfAward;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ch.isitar.oop2.projectOscar.model.MovieInterface#getDirector()
	 */
	@Override
	public StringProperty getDirector() {
		return director;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * ch.isitar.oop2.projectOscar.model.MovieInterface#setDirector(javafx.beans
	 * .property.StringProperty)
	 */
	@Override
	public void setDirector(StringProperty director) {
		this.director = director;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ch.isitar.oop2.projectOscar.model.MovieInterface#getMainActor()
	 */
	@Override
	public StringProperty getMainActor() {
		return mainActor;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * ch.isitar.oop2.projectOscar.model.MovieInterface#setMainActor(javafx.
	 * beans.property.StringProperty)
	 */
	@Override
	public void setMainActor(StringProperty mainActor) {
		this.mainActor = mainActor;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ch.isitar.oop2.projectOscar.model.MovieInterface#getTitleEnglish()
	 */
	@Override
	public StringProperty getTitleEnglish() {
		return titleEnglish;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * ch.isitar.oop2.projectOscar.model.MovieInterface#setTitleEnglish(javafx.
	 * beans.property.StringProperty)
	 */
	@Override
	public void setTitleEnglish(StringProperty titleEnglish) {
		this.titleEnglish = titleEnglish;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * ch.isitar.oop2.projectOscar.model.MovieInterface#getYearOfProduction()
	 */
	@Override
	public IntegerProperty getYearOfProduction() {
		return yearOfProduction;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * ch.isitar.oop2.projectOscar.model.MovieInterface#setYearOfProduction(
	 * javafx.beans.property.IntegerProperty)
	 */
	@Override
	public void setYearOfProduction(IntegerProperty yearOfProduction) {
		this.yearOfProduction = yearOfProduction;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ch.isitar.oop2.projectOscar.model.MovieInterface#getCountry()
	 */
	@Override
	public StringProperty getCountry() {
		return country;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * ch.isitar.oop2.projectOscar.model.MovieInterface#setCountry(javafx.beans.
	 * property.StringProperty)
	 */
	@Override
	public void setCountry(StringProperty country) {
		this.country = country;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ch.isitar.oop2.projectOscar.model.MovieInterface#getDuration()
	 */
	@Override
	public IntegerProperty getDuration() {
		return duration;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * ch.isitar.oop2.projectOscar.model.MovieInterface#setDuration(javafx.beans
	 * .property.IntegerProperty)
	 */
	@Override
	public void setDuration(IntegerProperty duration) {
		this.duration = duration;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ch.isitar.oop2.projectOscar.model.MovieInterface#getFsk()
	 */
	@Override
	public IntegerProperty getFsk() {
		return fsk;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * ch.isitar.oop2.projectOscar.model.MovieInterface#setFsk(javafx.beans.
	 * property.IntegerProperty)
	 */
	@Override
	public void setFsk(IntegerProperty fsk) {
		this.fsk = fsk;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ch.isitar.oop2.projectOscar.model.MovieInterface#getGenre()
	 */
	@Override
	public StringProperty getGenre() {
		return genre;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * ch.isitar.oop2.projectOscar.model.MovieInterface#setGenre(javafx.beans.
	 * property.StringProperty)
	 */
	@Override
	public void setGenre(StringProperty genre) {
		this.genre = genre;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ch.isitar.oop2.projectOscar.model.MovieInterface#getStartDate()
	 */
	@Override
	public SimpleObjectProperty<Date> getStartDate() {
		return startDate;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * ch.isitar.oop2.projectOscar.model.MovieInterface#setStartDate(javafx.
	 * beans.property.SimpleObjectProperty)
	 */
	@Override
	public void setStartDate(SimpleObjectProperty<Date> startDate) {
		this.startDate = startDate;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ch.isitar.oop2.projectOscar.model.MovieInterface#getNumberOfOscars()
	 */
	@Override
	public IntegerProperty getNumberOfOscars() {
		return numberOfOscars;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * ch.isitar.oop2.projectOscar.model.MovieInterface#setNumberOfOscars(javafx
	 * .beans.property.IntegerProperty)
	 */
	@Override
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
