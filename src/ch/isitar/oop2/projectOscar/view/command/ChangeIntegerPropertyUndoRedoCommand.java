package ch.isitar.oop2.projectOscar.view.command;

import javafx.beans.property.IntegerProperty;

public class ChangeIntegerPropertyUndoRedoCommand implements UndoRedoCommand {
	private IntegerProperty prop;
	private int oldVal;
	private int newVal;

	public ChangeIntegerPropertyUndoRedoCommand(IntegerProperty prop, int newV) {
		this.prop = prop;
		this.oldVal = prop.get();
		this.newVal = newV;
	}

	@Override
	public void execute() {
		prop.set(newVal);

	}

	@Override
	public void undo() {
		prop.set(oldVal);
	}

	@Override
	public String toString() {
		return prop.getName().toString() + " von " + oldVal + " zu " + newVal;
	}
}
