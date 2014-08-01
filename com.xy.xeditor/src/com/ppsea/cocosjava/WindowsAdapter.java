package com.ppsea.cocosjava;

public class WindowsAdapter {
	/**
	 * 
	 * @param hWND
	 * @return
	 */
	public native static void setupView(int hWND);
	public native static void setSearchPath(String path);
	public native static void resize(int width,int height);
	public native static void procMessage(int message,int wParam,int lParam);
	public native static void mainLoop();
	public native static void distroyView();
	
	
}
