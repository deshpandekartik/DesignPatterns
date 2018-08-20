package wordCount.util;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import wordCount.util.MyLogger.DebugLevel;

public class Results implements FileDisplayInterface, StdoutDisplayInterface{

	private List<String> resultsList;
	
	private BufferedWriter bufferedWriter;

	/**
	 * @param outputFile
	 * @throws Exception
	 * @return Results 
	 */
	public Results(String outputFile){
		this.setResultsList(new ArrayList<String> ());
        try {
            bufferedWriter = new BufferedWriter(new FileWriter(outputFile));
    		MyLogger.writeMessage("Initialized the constructor of output file \n", DebugLevel.CONSTRUCTOR);
		} catch (IOException e) {
			MyLogger.writeMessage("Error occured while opening the output file", DebugLevel.FILE_PROCESSOR);
			MyLogger.writeMessage("Error occured while opening the output file", DebugLevel.ERROR);
			MyLogger.writeMessage("Error occured while opening the output file", DebugLevel.DEBUG);
			System.err.println("Error occured while opening the output file");
			System.err.println("Exiting the program. \n");
			System.exit(0);
		}finally {
			//nothing implemented
		}
	}

	/* (non-Javadoc)
	 * @see myArrayList.util.StdoutDisplayInterface#writeToStdout(java.lang.String)
	 * @return void
	 */
	@Override
	public void writeToStdout(String s) {
		System.out.println(s+"\n");
	}
	
	
	/**
	 * @param output
	 * @return void
	 */
	public void storeNewResult(String output) {
		getResultsList().add(output);
	}
	

	/* (non-Javadoc)
	 * @see myArrayList.util.FileDisplayInterface#writeToFile(java.lang.String)
	 * @return void
	 */
	@Override
	public void writeToFile(String s) {
        try {
            bufferedWriter.write(s);
            bufferedWriter.newLine();
        }
        catch(IOException ex) {
			MyLogger.writeMessage("Error occured while writing to the output file", DebugLevel.FILE_PROCESSOR);
			MyLogger.writeMessage("Error occured while writing to the output file", DebugLevel.ERROR);
			MyLogger.writeMessage("Error occured while writing to the output file", DebugLevel.DEBUG);
			System.err.println("Error occured while writing to the output file");
			System.err.println("Exiting the program. \n");
			System.exit(0);
        }finally {
			//nothing implemented
		}
	}


	/**
	 * @return void
	 */
	public void closeBufferWriter(){
		try {
			getBufferedWriter().close();
		} catch (IOException e) {
			MyLogger.writeMessage("Error while closing the output file", DebugLevel.FILE_PROCESSOR);
			MyLogger.writeMessage("Error while closing the output file", DebugLevel.ERROR);
			MyLogger.writeMessage("Error while closing the output file", DebugLevel.DEBUG);
			System.err.println("Error while closing the output file");
			System.err.println("Exiting the program. \n");
			System.exit(0);
		}finally {
			//nothing implemented
		}
	}
	
	
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 * @return String
	 */
	@Override
	public String toString(){
		return "";
	}
	

	/**
	 * @return
	 */
	public List<String> getResultsList() {
		return resultsList;
	}

	/**
	 * @param resultsList
	 */
	public void setResultsList(List<String> resultsList) {
		this.resultsList = resultsList;
	}

	/**
	 * @return
	 */
	public BufferedWriter getBufferedWriter() {
		return bufferedWriter;
	}

	/**
	 * @param bufferedWriter
	 */
	public void setBufferedWriter(BufferedWriter bufferedWriter) {
		this.bufferedWriter = bufferedWriter;
	}
	
}
