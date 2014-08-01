package com.ppsea.cocosjava.swt;

import org.eclipse.swt.SWT;
import org.eclipse.swt.dnd.DND;
import org.eclipse.swt.dnd.DropTarget;
import org.eclipse.swt.dnd.DropTargetEvent;
import org.eclipse.swt.dnd.DropTargetListener;
import org.eclipse.swt.dnd.FileTransfer;
import org.eclipse.swt.dnd.Transfer;
import org.eclipse.swt.events.ControlAdapter;
import org.eclipse.swt.events.DragDetectEvent;
import org.eclipse.swt.events.DragDetectListener;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.internal.win32.MSG;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Listener;

import com.ppsea.cocosjava.WindowsAdapter;
import com.xy.xeditor.Log;

@SuppressWarnings("restriction")
public class CocosView extends Composite {
	static{
		System.loadLibrary("cocos-view");
	}
	Listener listener = new Listener() {
		@Override
		public void handleEvent(Event event) {
			MSG msg = getDisplay().msg;
			if(msg.hwnd==handle){
				WindowsAdapter.procMessage((int)msg.message,(int) msg.wParam,(int) msg.lParam);
			}
		}
	};
	Runnable runner = new Runnable() {
		@Override
		public void run() {
			WindowsAdapter.mainLoop();
			if(!isDisposed())
				getDisplay().timerExec(1, runner);
		}
	};
	private ControlAdapter resizeListener = new ControlAdapter() {
		public void controlResized(org.eclipse.swt.events.ControlEvent e) {
			Point size = getSize();
			WindowsAdapter.resize(size.x, size.y);
			
			Control cs[] = CocosView.this.getChildren();
			
			
		};
	};
//	DropTarget dropTarget = new DropTarget(this,DND.DROP_LINK);
//	
//	dropTarget.setTransfer(new Transfer[]{FileTransfer.getInstance()});
//	dropTarget.addDropListener(new DropTargetListener() {
//		
//		@Override
//		public void dropAccept(DropTargetEvent event) {
//			Log.info(event);
//		}
//		
//		@Override
//		public void drop(DropTargetEvent event) {
//			Log.info(event);
//		}
//		
//		@Override
//		public void dragOver(DropTargetEvent event) {
//			Log.info(event);
//		}
//		
//		@Override
//		public void dragOperationChanged(DropTargetEvent event) {
//			Log.info(event);
//		}
//		
//		@Override
//		public void dragLeave(DropTargetEvent event) {
//			Log.info(event);
//		}
//		
//		@Override
//		public void dragEnter(DropTargetEvent event) {
//			Log.info(event);
//		}
//	});
	/**
	 * Create the composite.
	 * @param parent
	 * @param style
	 */
	public CocosView(Composite parent, int style) {
		super(parent, style);
		
		WindowsAdapter.setupView((int) handle);
		addControlListener(resizeListener);
		this.getChildren();
		addListener(SWT.MouseDown, listener);
		addListener(SWT.MouseMove, listener);
		
		
		
		
		//addListener(SWT.MouseEnter, listener);
		addListener(SWT.MouseExit, listener);
		//addListener(SWT.MouseHover, listener);
		addListener(SWT.MouseUp, listener);
		//addListener(SWT.MouseWheel, listener);
		addListener(SWT.Paint, listener);
		getDisplay().timerExec(18, runner);
	}
	
	@Override
	protected void checkSubclass() {
		// Disable the check that prevents subclassing of SWT components
	}
}
