package ch.isitar.oop2.projectOscar.model;

import java.util.Date;
import java.util.List;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.StringProperty;

public interface MovieModel {

    public List<Movie> getData();
}