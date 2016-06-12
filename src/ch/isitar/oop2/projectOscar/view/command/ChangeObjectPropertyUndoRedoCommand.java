package ch.isitar.oop2.projectOscar.view.command;

import javafx.beans.property.ObjectProperty;

public class ChangeObjectPropertyUndoRedoCommand<T> implements UndoRedoCommand {
	private ObjectProperty<T> prop;
	private T oldVal;
	private T newVal;

	public ChangeObjectPropertyUndoRedoCommand(ObjectProperty<T> prop, T newV) {
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
