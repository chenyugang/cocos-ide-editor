package com.xy.xeditor;

import java.io.File;
import java.util.HashSet;
import java.util.Set;


/**
 * @author xingyun
 *
 */
public class Log {
	static Throwable throwable = new Throwable();
	static Set<String> filter = new HashSet<String>();
	
	static void friend(Object o1,Object o2){}
	
	static{
//		filter.add("com.ppsea.");
		friend("logo","");
	}

	public static void info(Object msg){
		synchronized (throwable) {
			StackTraceElement te = throwable.fillInStackTrace().getStackTrace()[1];
			if(filter.isEmpty()||filter.contains(te.getClassName())){
				System.out.println((te.getFileName()+"("+te.getLineNumber()+"è¡?"+msg.toString()));
			}
			
		}
	}
	public static void warning(Object msg){
		synchronized (throwable) {
			StackTraceElement te = throwable.fillInStackTrace().getStackTrace()[1];
//			android.util.Log.e(te.getFileName(), msg.toString());
			System.err.println((te.getFileName()+"("+te.getLineNumber()+"è¡?"+msg.toString()));
		}
	}
	public static void err(Object msg){
		synchronized (throwable) {
			StackTraceElement te = throwable.fillInStackTrace().getStackTrace()[1];
			System.err.println((te.getFileName()+"("+te.getLineNumber()+"è¡?"+msg.toString()));
		}
	}
	public static void main(String[] args) {
		System.out.println(new File("c:\\testFolder").lastModified());
	}
}
