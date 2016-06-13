package ch.isitar.oop2.projectOscar.presenter.test;

import static org.junit.Assert.*;

import java.util.Date;

import org.junit.Assert;
import org.junit.Test;

import ch.isitar.oop2.projectOscar.model.Movie;
import ch.isitar.oop2.projectOscar.presenter.MoviePresenter;
import ch.isitar.oop2.projectOscar.view.MockupView.MockupView;

public class MoviePresenterTest {

	private MoviePresenter createPresenter() {
		return new MoviePresenter(new MockupView());
	}

	@Test
	public void testChangeYear() {
		MoviePresenter presenter = createPresenter();
		Movie m = new Movie();
		m.getYearOfAward().set(1990);

		presenter.ChangeYear(m, 1920);
		if (m.getYearOfAward().get() == 1920)
			fail("has to be 1990");

		presenter.ChangeYear(m, 1940);
		assertEquals(1940, m.getYearOfAward().get());
	}

	@Test
	public void testChangeTitle() {
		MoviePresenter p = createPresenter();
		Movie m = new Movie();
		m.getTitle().set("title1");
		p.ChangeTitle(m, "title2");
		assertEquals("title2", m.getTitle().get());
	}

	@Test
	public void testChangeDirector() {
		MoviePresenter p = createPresenter();
		Movie m = new Movie();
		m.getDirector().set("dir1");
		p.ChangeDirector(m, "dir2");
		assertEquals("dir2", m.getDirector().get());
	}

	@Test
	public void testChangeMainActor() {
		MoviePresenter p = createPresenter();
		Movie m = new Movie();
		m.getMainActor().set("ac1");
		p.ChangeMainActor(m, "ac2");
		assertEquals("ac2", m.getMainActor().get());
	}

	@Test
	public void testChangeEnglishTitle() {
		MoviePresenter p = createPresenter();
		Movie m = new Movie();
		m.getTitleEnglish().set("ti1");
		p.ChangeEnglishTitle(m, "ti2");
		assertEquals("ti2", m.getTitleEnglish().get());
	}

	@Test
	public void testChangeGenre() {
		MoviePresenter p = createPresenter();
		Movie m = new Movie();
		m.getGenre().set("g1");
		p.ChangeGenre(m, "g2");
		assertEquals("g2", m.getGenre().get());
	}

	@Test
	public void testChangeYearOfProduction() {
		MoviePresenter p = createPresenter();
		Movie m = new Movie();
		m.getYearOfProduction().set(1900);
		p.ChangeYearOfProduction(m, 1990);
		assertEquals(1990, m.getYearOfProduction().get());
	}

	@Test
	public void testChangeCountry() {
		MoviePresenter p = createPresenter();
		Movie m = new Movie();
		m.getCountry().set("c1");
		p.ChangeCountry(m, "c2");
		assertEquals("c2", m.getCountry().get());
	}

	@Test
	public void testChangeDuration() {
		MoviePresenter p = createPresenter();
		Movie m = new Movie();
		m.getDuration().set(120);
		p.ChangeDuration(m, 129);
		assertEquals(129, m.getDuration().get());
	}

	@Test
	public void testChangeFSK() {
		MoviePresenter p = createPresenter();
		Movie m = new Movie();
		m.getFsk().set(1);
		p.ChangeFSK(m, 120);
		assertEquals(120, m.getFsk().get());
	}

	@Test
	public void testChangeNumberOfOscars() {
		MoviePresenter p = createPresenter();
		Movie m = new Movie();
		m.getNumberOfOscars().set(1);
		p.ChangeNumberOfOscars(m, 120);
		assertEquals(120, m.getNumberOfOscars().get());
	}

	@SuppressWarnings("deprecation") // i don't care

	@Test
	public void testChangeCinemaStart() {
		MoviePresenter p = createPresenter();
		Movie m = new Movie();
		Date d = new Date();
		d.setYear(2012);
		d.setMonth(01);
		d.setDate(1);
		m.getStartDate().set(d);

		Date d2 = new Date();
		d2.setYear(2013);
		d2.setMonth(01);
		d2.setDate(1);
		p.ChangeCinemaStart(m, d2);
		assertEquals(d2, m.getStartDate().get());
	}

	@Test
	public void testUndoRedo() {
		MoviePresenter p = createPresenter();
		Movie m = new Movie();
		m.getFsk().set(1);
		p.ChangeFSK(m, 120);
		p.undo();
		assertEquals(1, m.getFsk().get());
		p.redo();
		assertEquals(120, m.getFsk().get());

	}

	@Test
	public void testGetUndoRedoEnabledProperty() {
		MoviePresenter p = createPresenter();
		assertEquals(false, p.getUndoEnabledProperty().get());
		assertEquals(false, p.getRedoEnabledProperty().get());

		Movie m = new Movie();
		m.getFsk().set(1);
		p.ChangeFSK(m, 120);

		assertEquals(true, p.getUndoEnabledProperty().get());
		assertEquals(false, p.getRedoEnabledProperty().get());

		p.undo();

		assertEquals(false, p.getUndoEnabledProperty().get());
		assertEquals(true, p.getRedoEnabledProperty().get());

		p.redo();

		assertEquals(true, p.getUndoEnabledProperty().get());
		assertEquals(false, p.getRedoEnabledProperty().get());

		p.ChangeTitle(m, "asdf");
		p.ChangeTitle(m, "asdfc");
		p.ChangeTitle(m, "asdfcg");
		p.undo();
		assertEquals(true, p.getUndoEnabledProperty().get());
		assertEquals(true, p.getRedoEnabledProperty().get());

	}
}
