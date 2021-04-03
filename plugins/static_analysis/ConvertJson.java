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
public class ConvertJson {
	BufferedReader reader;
	public String readFile(String path) throws IOException, ParseException {
		reader = new BufferedReader(new FileReader(path));
		BufferedWriter writer = new BufferedWriter(new FileWriter("out.json"));
		JSONObject parser = new JSONObject();
		JSONArray array = new JSONArray();
		String line = "";
		// result의 결과 이후로 받기 위해 
		do {
			line = reader.readLine();
		} while(!(line.contains("# Results")));
		
		line = reader.readLine();
		while(line != null){
			ArrayList list = new ArrayList();
			for (int i = 0; i<=3; i++){
				String data[] = line.split(":");
				for (int j = 0; j<data.length; j++){
					list.add(data[j]);
				}
				line = reader.readLine();
			}
			array.add(writeJson(list));
		}
		parser.put("error", array);
		//System.out.println(parser.toJSONString());
		
		writer.write(parser.toJSONString());
		reader.close();
		writer.close();
		return "./out.json";
	}
	public JSONObject writeJson(ArrayList list){
		JSONObject object = new JSONObject();

		object.put("file", list.get(0));
		object.put("function", list.get(1));
		object.put("column", list.get(3));
		object.put("row", list.get(4));
		object.put("status", list.get(5));
		object.put("info", list.get(6));
		object.put("code", list.get(7));
		object.put("check", list.get(8));
		
		return object;
	}


}