
package application;

import java.io.PrintStream;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import javafx.application.Platform;
import javafx.scene.control.TextArea;

// TODO: Auto-generated Javadoc
/**
 * The Class Logger.
 * Responsible for collecting syste.out requests and present it to the TextArea.
 */
public class Logger extends PrintStream {
	
	/**
	 * The Enum Print.
	 */
	public static enum Print{
		
		/** The nobody. */
		NOBODY,
		
		/** The console. */
		CONSOLE,
		
		/** The newoutput. */
		NEWOUTPUT,
		
		/** The both. */
		BOTH;
	};
	/** The old stream.save's original system.out  */
	private PrintStream oldStream;
	
	/** The new out put stream. */
	private TextArea newOutPutStream;
	
	/**  The type of printing;. */
	private Print print;
	
	/** The max KB buffer. */
	private int maxKBBuffer;
	
	/**
	 * Instantiates a new logger.
	 *
	 * @param print the print
	 */
	public Logger(Print print) {
		this(null,print,500);
	}
	
	/**
	 * Instantiates a new logger.
	 *
	 * @param newOutPutStream the new out put stream
	 * @param print the print
	 */
	public Logger(TextArea newOutPutStream, Print print) {
		this(newOutPutStream,print,500);
	}

	/**
	 * Instantiates a new logger.
	 *
	 * @param newOutPutStream the new out put stream
	 * @param print the print
	 * @param maxKBBuffer max kb of newOutPutStream buffer
	 */
	public Logger(TextArea newOutPutStream, Print print, int maxKBBuffer) {
		super(System.out);
		this.print=print;
		setNewOutPutStream(newOutPutStream);
		setPrint(print);
		this.maxKBBuffer=maxKBBuffer;
		this.oldStream=System.out;
		System.setOut(this);//define the logger as new system.out
	}
	

	/**
	 * Sets the new out put stream.
	 *
	 * @param newOutPutStream the new new out put stream
	 */
	public void setNewOutPutStream(TextArea newOutPutStream) {
		this.newOutPutStream=newOutPutStream;
		if(newOutPutStream != null) {
			newOutPutStream.setText("");//reset TextArea
		}
	}

	/**
	 * Gets the prints the.
	 *
	 * @return the prints the
	 */
	public Print getPrint() {
		return print;
	}

	/**
	 * Sets the prints the.
	 *
	 * @param print the new prints the
	 */
	public void setPrint(Print print) {
		this.print = print;
		if(newOutPutStream == null) {
			if(this.print == Print.BOTH)
				this.print = Print.CONSOLE;
			else if(print == Print.NEWOUTPUT)
				this.print = Print.NOBODY;
		}
	}

	/**
	 * Gets the max KB buffer.
	 *
	 * @return the max KB buffer
	 */
	public int getMaxKBBuffer() {
		return maxKBBuffer;
	}

	/**
	 * Sets the max KB buffer.
	 *
	 * @param maxKBBuffer the new max KB buffer
	 */
	public void setMaxKBBuffer(int maxKBBuffer) {
		this.maxKBBuffer = maxKBBuffer;
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
	 *
	 * @param arg0 the arg 0
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
		if(print ==  Print.NOBODY)
			return;
		//If need to update a GUI component from a non-GUI thread, you can use that to put your update in a queue and it will be handle by the GUI thread as soon as possible.
		Platform.runLater(new Runnable() {
			@Override
			public void run() {
				String toPrint = newStringPrint(arg0);
				if(print == Print.BOTH) {
					oldStream.print(toPrint);
					newOutPutStream.appendText(toPrint);
				}else
				{
					if(print == Print.CONSOLE) oldStream.print(toPrint);
					if(print == Print.NEWOUTPUT) newOutPutStream.appendText(toPrint);
				}
			
				if((print == Print.BOTH) || (print == Print.NEWOUTPUT))
					if(newOutPutStream.getText().length() > maxKBBuffer * 1024)
						clean();
			}
		});		
	}
	
	/**
	 * Clean text of output stream.
	 */
	public void clean() {
		Platform.runLater(new Runnable() {
			@Override
			public void run() {
				newOutPutStream.clear();
			}
		});
	}
}
