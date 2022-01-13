package application;

import java.io.PrintStream;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import javafx.application.Platform;
import javafx.scene.control.TextArea;

/**
 * The Class Logger.
 * Responsible for collecting syste.out requests and present it to the TextArea.
 */
public class Logger extends PrintStream {
	
	/** The old stream.save's original system.out  */
	private PrintStream oldStream;
	
	/** The new out put stream. */
	private TextArea newOutPutStream;
	
	/** The console parallel printing on old stream */
	private boolean console;
	
	/**
	 * Instantiates a new logger.
	 *
	 * @param newOutPutStream the new out put stream
	 * @param boolean console - parallel printing 
	 */
	public Logger(TextArea newOutPutStream, boolean console) {
		super(System.out);
		this.console=console;	
		this.newOutPutStream=newOutPutStream;
		this.oldStream=System.out;
		newOutPutStream.setText("");//reset TextArea
		System.setOut(this);//define the logger as new system.out
	}

	/* (non-Javadoc)
	 * @see java.io.PrintStream#close()
	 * close the logger and define old system.out
	 */
	@Override
	public void close() {
		System.setOut(oldStream);
	}
	
	/**
	 * New string print.
	 * Create new string to print(add date and time)
	 * @param string arg0
	 * @return the string new print
	 */
	private String newStringPrint(String arg0) {
		return String.format("[%s]	%s", new SimpleDateFormat("dd/MM/yyyy HH:mm:ss").format(Calendar.getInstance().getTime()), arg0);
	}
	
	/* (non-Javadoc)
	 * @see java.io.PrintStream#println(java.lang.String)
	 */
	@Override
	public void println(String arg0) {
		print(arg0 + "\n");
	}

	/* (non-Javadoc)
	 * @see java.io.PrintStream#print(java.lang.String)
	 * Print the string on TextArea using platform.runlater method
	 */
	@Override
	public void print(String arg0) {
		//If need to update a GUI component from a non-GUI thread, you can use that to put your update in a queue and it will be handle by the GUI thread as soon as possible.
		Platform.runLater(new Runnable() {
			@Override
			public void run() {
				String toPrint = newStringPrint(arg0);
				if(console) oldStream.print(toPrint);
				newOutPutStream.appendText(toPrint);
			}
		});
	}
	
	/**
	 * Clean text of output stream
	 */
	public void clean() {
		Platform.runLater(new Runnable() {
			@Override
			public void run() {
				newOutPutStream.setText("");
			}
		});
		
	}
}
