package wordCount.treeOperations.treeInsertOperations;

import java.io.FileNotFoundException;
import java.io.IOException;

import wordCount.treesForStrings.MyTreeImpl;
import wordCount.treesForStrings.Node;
import wordCount.util.FileProcessor;
import wordCount.util.MyLogger;
import wordCount.util.MyLogger.DebugLevel;

public class PopulateMyTreeVisitor implements Visitor {


	/* (non-Javadoc)
	 * @see wordCount.treeOperations.treeInsertOperations.Visitor#visit(wordCount.treesForStrings.MyTreeImpl)
	 */
	@Override
	public void visit(MyTreeImpl myTree) {
		// TODO Auto-generated method stub
		buildTree(myTree);
	}
	
	
	/**
	 * @param inputFile
	 * @throws IOException
	 * @throws FileNotFoundException
	 * @throws Exception
	 * return null
	 */
	public void buildTree(MyTreeImpl myTree){
		String line = null;
		FileProcessor procObj = new FileProcessor(myTree.getInputFile());
        while((line = (String)procObj.readLine()) != null) {

        	String[] Allwords = line.split("\\W+");

            for (String word: Allwords) {

                if ( !word.isEmpty()) {

                    Node nodeObj = myTree.searchNode(word);
                    if (nodeObj == null) {
                        nodeObj = new Node();
                        nodeObj.setWord(word);
                        nodeObj.setOccurenceCount(1);
                        myTree.insertNode(nodeObj);
                    } else {
                        nodeObj.setOccurenceCount(nodeObj.getOccurenceCount() + 1);
                    }
                }
            }
        }
		MyLogger.writeMessage("input file read and courses updated accordingly. \n", DebugLevel.INFO);		
		MyLogger.writeMessage("input file read and courses updated accordingly. \n", DebugLevel.DEBUG);
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
