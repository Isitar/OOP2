package ch.isitar.oop2.projectOscar.view.command;

import java.util.ArrayList;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class UndoRedoController {
	private ObservableList<UndoRedoCommand> undoList;
	// private Stack<UndoRedoCommand> undoList;
	private ObservableList<UndoRedoCommand> redoList;

	private SimpleBooleanProperty undoEnabled = new SimpleBooleanProperty();
	private SimpleBooleanProperty redoEnabled = new SimpleBooleanProperty();

	public UndoRedoController() {
		undoList = FXCollections.observableArrayList(new ArrayList<>());
		redoList = FXCollections.observableArrayList(new ArrayList<>());
	}

	public void ExecuteCommand(UndoRedoCommand c) {
		c.execute();
		undoList.add(c);
		redoList.clear();

		updateEnabled();
	}

	public void undo() {
		try {
			int lastIndex = undoList.size() - 1;
			UndoRedoCommand c = undoList.get(lastIndex);

			c.undo();
			undoList.remove(c);
			redoList.add(c);
		} catch (IndexOutOfBoundsException e) {

		}
		updateEnabled();
	}

	public void redo() {
		try {
			UndoRedoCommand c = redoList.get(redoList.size() - 1);

			c.execute();
			undoList.add(c);
		} catch (IndexOutOfBoundsException e) { // i don't care
		}
		updateEnabled();
	}

	public BooleanProperty undoEnabledProperty() {
		return undoEnabled;
	}

	public BooleanProperty redoEnabledProperty() {
		return redoEnabled;
	}

	private void updateEnabled() {
		undoEnabled.set(!undoList.isEmpty());
		redoEnabled.set(!redoList.isEmpty());
	}

	public ObservableList<UndoRedoCommand> getUndoListProperty() {
		return undoList;
	}

	public ObservableList<UndoRedoCommand> getRedoListProperty() {
		return redoList;
	}
}
