package wordCount.treeOperations.treeInfoOperations;

import wordCount.treeOperations.treeInsertOperations.Visitor;
import wordCount.treesForStrings.MyTreeImpl;

public class TreeOperationsCountVisitor implements Visitor {


	/* (non-Javadoc)
	 * @see wordCount.treeOperations.treeInsertOperations.Visitor#visit(wordCount.treesForStrings.MyTreeImpl)
	 */
	@Override
	public void visit(MyTreeImpl myTree) {
		// TODO Auto-generated method stub
			myTree.CountOPs();
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
