import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
 
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
public class Parser extends ConvertJson{
	
	public static void main(String[] args) throws FileNotFoundException, IOException, ParseException 
    {
        //JSON parser object to parse read file

        JSONParser jsonParser = new JSONParser();
        Object obj = jsonParser.parse(new FileReader(new ConvertJson().readFile("/home/user/parser/Plugin/res.txt")));
        JSONObject jsonObject = (JSONObject) obj;
        //Get reports object
        JSONArray name = (JSONArray)jsonObject.get("error");
         //Print all items
        for (int i = 0; i< name.size(); i++){
        	JSONObject ob = (JSONObject) name.get(i);
        	String file = (String) ob.get("file");
        	String function = (String) ob.get("function");
        	String column = (String) ob.get("column");
        	String row = (String) ob.get("row");
        	String status = (String) ob.get("status");
        	String info = (String) ob.get("info");
        	String code = (String) ob.get("code");
        	String check = (String) ob.get("check");
        	
        	System.out.println(i+1+"th");
        	System.out.println(file + " "+function +" " + column +":"+row + " " +status +" "+info);
        	System.out.println(code);
        	System.out.println(check);
        	
        	//System.out.println(e.updatestatus((Long)ob.get("kind")));
        }
    }
}
