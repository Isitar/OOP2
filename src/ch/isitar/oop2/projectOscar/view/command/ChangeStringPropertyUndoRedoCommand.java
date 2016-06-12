package ch.isitar.oop2.projectOscar.view.command;

import javafx.beans.property.StringProperty;

public class ChangeStringPropertyUndoRedoCommand implements UndoRedoCommand {
	private StringProperty prop;
	private String oldVal;
	private String newVal;

	public ChangeStringPropertyUndoRedoCommand(StringProperty prop, String newV) {
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
