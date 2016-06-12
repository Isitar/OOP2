package ch.isitar.oop2.projectOscar.view.command;

/**
 * a command
 */
public interface Command {

	/**
	 * executes the command
	 */
	public void execute();

	/**
	 * undoes the command
	 */
	public void undo();
}
