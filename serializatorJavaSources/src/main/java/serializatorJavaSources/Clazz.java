package serializatorJavaSources;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class Clazz {

	String packageName="";
	String className="";
	String text ="";
	
	public Clazz(String packageName, String className, String text) {
		super();
		this.packageName = packageName;
		this.className = className;
		this.text = text;
	}

	public void writeTo(File dir) {
		File dirPackage = getDirPackage(dir);
		dirPackage.mkdirs();
		File f = new File(dirPackage,className+".java");
		
		 try {
             FileWriter writer = new FileWriter(f);
             writer.write(text);
             writer.close();
         } catch (IOException e) {
             e.printStackTrace();
         }
	}

	private File getDirPackage(File dir) {
		for(String p : packageName.split("\\.")) {
				dir = new File(dir,p);
		}
		return dir;
	}

}
