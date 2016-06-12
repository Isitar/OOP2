package ch.isitar.oop2.projectOscar.view.command;

/**
 * an undo redo command
 * 
 * @author PALU
 *
 */
public interface UndoRedoCommand extends Command {

	/**
	 * undoes the command
	 */
	public void undo();

}
