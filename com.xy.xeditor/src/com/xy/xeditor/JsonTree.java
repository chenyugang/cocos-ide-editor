package com.xy.xeditor;

import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Tree;
import org.eclipse.jface.viewers.IContentProvider;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.jface.viewers.ITreeContentProvider;
import org.json.JSONObject;

public class JsonTree extends Composite {
	
	static class JSONItem{
		Object obj;
		String name;
		JSONItem parent;
		public JSONItem(JSONItem parent,String name,Object obj) {
			this.name = name;
			this.parent = parent;
			this.obj = obj;
		}
	}
	private static class TreeContentProvider implements ITreeContentProvider {
		public void inputChanged(Viewer viewer, Object oldInput, Object newInput) {
		}
		public void dispose() {
		}
		public Object[] getElements(Object inputElement) {
			if (inputElement instanceof JSONItem) {
				JSONItem parentItem = (JSONItem) inputElement;
				
				String names[] = JSONObject.getNames(parentItem);
				for(String name:names){
					//JSONItem item = new JSONItem(null, name, obj)TODO
				}
			}
			return getChildren(inputElement);
		}
		public Object[] getChildren(Object parentElement) {
			return new Object[] { "item_0", "item_1", "item_2" };
		}
		public Object getParent(Object element) {
			return null;
		}
		public boolean hasChildren(Object element) {
			return getChildren(element).length > 0;
		}
	}

	/**
	 * Create the composite.
	 * @param parent
	 * @param style
	 */
	public JsonTree(Composite parent, int style) {
		super(parent, style);
		setLayout(new FillLayout(SWT.HORIZONTAL));
		
		TreeViewer treeViewer = new TreeViewer(this, SWT.BORDER);
		Tree tree = treeViewer.getTree();
		treeViewer.setContentProvider(new TreeContentProvider());
		

	}

	@Override
	protected void checkSubclass() {
		// Disable the check that prevents subclassing of SWT components
	}
}
