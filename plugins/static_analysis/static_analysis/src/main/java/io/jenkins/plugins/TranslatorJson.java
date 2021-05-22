package io.jenkins.plugins;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.stream.Stream;
import org.json.simple.*;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class TranslatorJson {
	public static void readFile(String path, String ws) throws IOException{
		BufferedReader reader;
		String str = ws + "report.json";
		reader = new BufferedReader(new FileReader(path));
		File file = new File(str);
		FileWriter writer = new FileWriter(file);
		JSONObject parser = new JSONObject();
		JSONArray array = new JSONArray();
		
		String line = "";

		while((line = reader.readLine()) != null){

			if(line.contains("# Results")){
				while(((line = reader.readLine()) != null) &&(!line.contains("No entries")) && (!line.contains("[*]")) && (!line.isEmpty())){
					String error = reader.readLine();
					String code = reader.readLine();
					String p = reader.readLine();
					
					array.add(writeJson(line, error));
				}
				parser.put("issues",array);
			}	
		}
		writer.write(parser.toJSONString());
		reader.close();
		writer.close();
	}
			
	public static JSONObject writeJson(String line, String error) {
		JSONObject object = new JSONObject();
		
		String data1[] = line.split(":");
		String data2[] = error.split(":");
		
		object.put("fileName", data1[0]);
		object.put("columnStart", data2[2]);
		object.put("columnEnd", data2[2]);
		object.put("lineStart", data2[1]);
		object.put("lineEnd", data2[1]);
		object.put("severity", data2[3]);
		object.put("message", data2[4]);
		return object;
	}
}
