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
	public static String readFile() throws IOException{
		BufferedReader reader;
		reader = new BufferedReader(new FileReader("/home/work/res.txt"));
		BufferedWriter writer = new BufferedWriter(new FileWriter("od.json"));
		JSONObject parser = new JSONObject();
		JSONArray array = new JSONArray();
		
		String line = "";
		
		do {
			line = reader.readLine();
			if (line.contains("The program is ")){
				if (!line.contains("UNSAFE")){
					parser.put("Program Status", line.substring(line.lastIndexOf(" ")+1));
					line = reader.readLine();
					System.out.println(line);
				}
			}	
		} while(!(line.contains("# Results")));

		line = reader.readLine();
		if (!(line.contains("No entries."))){
			while(line != null ){
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
		}
		parser.put("issue", array);
		//System.out.println(parser.toJSONString());
		
		writer.write(parser.toJSONString());
		reader.close();
		writer.close();
		return "./out.json";
	}
	
	public static JSONObject writeJson(ArrayList list){
		JSONObject object = new JSONObject();

		object.put("fileName", list.get(0));
		object.put("function", list.get(1));
		object.put("columnStart", list.get(3));
		object.put("columnEnd", list.get(3));
		object.put("lineStart", list.get(4));
		object.put("lineEnd", list.get(4));
		object.put("severity", list.get(5));
		object.put("message", list.get(6));
		object.put("description", list.get(7));
		object.put("check", list.get(8));
		
		return object;
	}
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		try {
			readFile();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}

