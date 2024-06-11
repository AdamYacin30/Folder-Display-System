// File Structure java program created by Adam Yassine
import java.util.Iterator;
import java.util.ArrayList;

public class FileStructure {

	// create a reference to the root node
	private NLNode<FileObject> root;

	public FileStructure(String fileName) throws FileObjectException {
		// create a constructor and create a file object 
		FileObject fileObject = new FileObject(fileName);
		// set root to a new NLNode object
		root = new NLNode<FileObject>(fileObject, null);
		if (!fileObject.isDirectory()) {
			return;
		// if fileObject is the name of the file, call the buildFolder method. otherwise return nothing
		}
		else {
			createFolder(root);
		}
	}

	private void createFolder(NLNode<FileObject> r) {
			// create an iterator object from the data from the r NLNode
		Iterator<FileObject> iterator = r.getData().directoryFiles();
			// while loop to see if there are any files still in the current directory
		while (!iterator.hasNext() == false) {
			// move the iterator to the next item in the object/Node
			FileObject childFileObject = iterator.next();
			// create a new NLNode for the child with the parameters childFileObject and r
			NLNode<FileObject> childNode = new NLNode<FileObject>(childFileObject, r);
			// add the r node to the child node
			r.addChild(childNode);
			if (childFileObject.isDirectory()) {
				// if the childFileObject returns a name then recursively go through the iterator until you reach the end
				createFolder(childNode);
			}
		}
	}
	

	public NLNode<FileObject> getRoot() {
		// return the root node
		return root;
	}

	public Iterator<String> filesType(String type) {
		// create new array 'f' for strings
		ArrayList<String> f = new ArrayList<String>();
		// call find file type method and return the iterative form of the array
		findFileType(root, type, f);
		return f.iterator();
	}

	public void findFileType(NLNode<FileObject> node, String type, ArrayList<String> files) {
		// if the node does not have a file name and ends with given type add the longName of the node to the files node
		if (node.getData().isDirectory() == false && node.getData().getName().endsWith(type)) {
			boolean file = files.add(node.getData().getLongName());
		} else {
			// otherwise create an iterator object with the children nodes
			Iterator<NLNode<FileObject>> iterator = node.getChildren();
			while (iterator.hasNext() == true) {
				//iterate through the iterator and do this recursively by calling the method again until loop breaks
				NLNode<FileObject> childNode = iterator.next();
				findFileType(childNode, type, files);

			}
		}
	}
	
	public String findFile(String name) {
		// create a new string 'f' and set it as an empty String
		String f = new String();
		f = "";
		// let f equal the string representation of the file name from the findFileName method
		f = findFileName(root, name);
		// return the file name
		return f;
	}
	
	public String findFileName(NLNode<FileObject> node, String name) {
		// helper method to find the name of the file
		FileObject fileObj = node.getData();
		// create an empty string
		String str = "";
		// if the fileObject has does not have a name the name of fileObject is equal to 'name' then get the name of the file of fileObject
		if (fileObj.isDirectory() == false && fileObj.getName().equals(name)) {
			str = fileObj.getLongName();
		} else {
			// use the same code as the findFiletype and check if the string representation is empty
			Iterator<NLNode<FileObject>> iterator = node.getChildren();
			while (iterator.hasNext() == true) {
				NLNode<FileObject> childNode = iterator.next();
			if (!str.equals("")) {
				// if the string representation is empty then break
				break;
				}
			// let the string variable equal to the output of calling the method findFileName with the childNode and node parameters
			str = findFileName(childNode, name);
			// check if the string is empty. if so break)
			if (!str.equals("")) {
				break;
				}
			}
		}
		return str;
	}
}