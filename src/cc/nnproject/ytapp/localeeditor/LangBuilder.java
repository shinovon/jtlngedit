package cc.nnproject.ytapp.localeeditor;

import java.io.*;
import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import javax.swing.JFileChooser;
import javax.swing.JFrame;

import java.util.Scanner;

public class LangBuilder {
	
	public static void main(String[] args) {
		Class lc = LocaleConstants.class;
        Scanner in = new Scanner(System.in);
        System.out.print("Locale identificator: ");
        String lang = in.nextLine();
        System.out.print("Author: ");
        String author = in.nextLine();
        Map<Integer, String> map = new HashMap<Integer, String>();
    	try {
    		for(Field f: lc.getFields()) {
    			String n = f.getName();
				int i = f.getInt(null);
				System.out.print(n + "=");
				map.put(i, in.nextLine());
        	}
    		write(lang, map, author, null);
		} catch (Exception e) {
			System.out.println();
			e.printStackTrace();
		}
    	in.close();
	}

	public static void write(String lang, Map<Integer, String> map, String author, JFrame frm) {
		try {
			File f = new File("./jtlng." + lang.toLowerCase());
			if(frm != null) {
				JFileChooser fc = new JFileChooser(".");
				fc.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
				fc.showOpenDialog(frm);
				f = new File(fc.getSelectedFile().getAbsolutePath() + "/jtlng." + lang.toLowerCase());
			}
			if(f.exists())
				f.delete();
			f.createNewFile();
			DataOutputStream os = new DataOutputStream(new FileOutputStream(f));
			os.writeShort(0);
			os.writeUTF(author);
			for(Entry<Integer, String> entry: map.entrySet()) {
				System.out.println(entry.getKey() + "=" + entry.getValue());
				os.writeShort(entry.getKey().intValue());
				os.writeUTF(entry.getValue());
			}
			os.writeShort(-1);
			os.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}

}
