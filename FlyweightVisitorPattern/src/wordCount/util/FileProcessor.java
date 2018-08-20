package wordCount.util;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import wordCount.util.MyLogger.DebugLevel;

public class FileProcessor {
	
	private FileReader fileReader;
	private BufferedReader bufferedReader;


	/**
	 * @param inputFileName
	 * @throws Exception
	 * @return FileProcessor
	 */
	public FileProcessor(String inputFileName){
        try {
        	fileReader = new FileReader(inputFileName);		
    		bufferedReader =  new BufferedReader(fileReader);
			MyLogger.writeMessage("Initialized the input file processor constructor \n", DebugLevel.CONSTRUCTOR);
		} catch (FileNotFoundException e) {
			MyLogger.writeMessage("Error occured while opening the file"+ inputFileName, DebugLevel.FILE_PROCESSOR);
			MyLogger.writeMessage("Error occured while opening the file"+ inputFileName, DebugLevel.ERROR);
			MyLogger.writeMessage("Error occured while opening the file"+ inputFileName, DebugLevel.DEBUG);
            System.err.println("Error occured while opening the input file. \n");
            System.err.println("Exiting the program. \n");
            System.exit(0);
		}finally {
			//nothing implemented
		}
	}

	
	/**
	 * @return
	 * @throws IOException
	 */
	public Object readLine(){
		String line = null;
		try {			
			line = bufferedReader.readLine();
			if(line == null) {
				bufferedReader.close();				
			}
		}catch (IOException e) {
			MyLogger.writeMessage("Error occured while reading the file", DebugLevel.FILE_PROCESSOR);
			MyLogger.writeMessage("Error occured while reading the file", DebugLevel.DEBUG);
			MyLogger.writeMessage("Error occured while reading the file", DebugLevel.ERROR);
            System.err.println("Error occured while reading the input file. \n");
            System.err.println("Exiting the program. \n");
            System.exit(0);
		}finally {
			//nothing implemented
		}
		return (Object)line;
	}
	
	
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 * @return String
	 */
	@Override
	public String toString(){
		return "";
	}
	
}
