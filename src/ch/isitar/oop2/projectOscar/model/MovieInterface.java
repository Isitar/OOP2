package ch.isitar.oop2.projectOscar.model;

import java.util.Date;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.StringProperty;

public interface MovieInterface {

	IntegerProperty getId();

	void setId(IntegerProperty id);

	StringProperty getTitle();

	void setTitle(StringProperty title);

	IntegerProperty getYearOfAward();

	void setYearOfAward(IntegerProperty yearOfAward);

	StringProperty getDirector();

	void setDirector(StringProperty director);

	StringProperty getMainActor();

	void setMainActor(StringProperty mainActor);

	StringProperty getTitleEnglish();

	void setTitleEnglish(StringProperty titleEnglish);

	IntegerProperty getYearOfProduction();

	void setYearOfProduction(IntegerProperty yearOfProduction);

	StringProperty getCountry();

	void setCountry(StringProperty country);

	IntegerProperty getDuration();

	void setDuration(IntegerProperty duration);

	IntegerProperty getFsk();

	void setFsk(IntegerProperty fsk);

	StringProperty getGenre();

	void setGenre(StringProperty genre);

	SimpleObjectProperty<Date> getStartDate();

	void setStartDate(SimpleObjectProperty<Date> startDate);

	IntegerProperty getNumberOfOscars();

	void setNumberOfOscars(IntegerProperty numberOfOscars);

}