package wordCount.treeOperations.treeInsertOperations;

import wordCount.treesForStrings.MyTreeImpl;

public interface Visitor {

	/**
	 * @param myArray
	 * @return void
	 */
	public void visit(MyTreeImpl myTree);
}
