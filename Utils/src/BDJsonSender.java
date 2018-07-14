/**
 * UtilsForJSONBuild
 *
 * @auther SamJ
 * @create 2018 - 07 - 08 12:06
 */
import java.io.File;
import java.io.FileWriter;
import java.text.SimpleDateFormat;
import java.time.temporal.TemporalAmount;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.oracle.xmlns.internal.webservices.jaxws_databinding.ExistingAnnotationsType;
import org.json.JSONObject;

import javax.sound.midi.Soundbank;

public class BDJsonSender {
	public static void main(String[] args) {
		List<JSONObject> lstObj = new ArrayList<JSONObject>();
		int targetAmount = 0;
		int GAP = 0;
		String targetFile = null;
		if(args.length < 3){
			System.out.println("arg1 : filepath, arg2 : JsonAmount ,arg3 : Gap");
			System.exit(args.length);
		}else{
			targetFile = args[0];
			targetAmount = Integer.parseInt(args[1]);
			GAP = Integer.parseInt(args[2]);
		}
		for (int i = 0; (i < targetAmount); i++) {
			JSONObject jso = new JSONObject();
			jso.put("userId", i);
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			jso.put("day", sdf.format(new Date()));
			long startTime = System.currentTimeMillis()/(600*1000)*(600*1000)+i*(600*1000);
			jso.put("begintime", startTime);
			long useTime = (long) (600 * 1000);
			jso.put("endtime", useTime + startTime);
			int useAmount = (int) (Math.random() * 10 + 1);
			List<JSONObject> jsos = new ArrayList<JSONObject>();
			int tmpTime = (int) (Math.random() * useTime);
			for (int j = 0; j < useAmount; j++) {
				JSONObject tmp = new JSONObject();
				tmp.put("package", "app" + (int) (Math.random() * 10));
				tmp.put("activetime", tmpTime);
				jsos.add(tmp);
				tmpTime = (int) (Math.random() * tmpTime);

			}
			jso.put("data", jsos.toArray());
			lstObj.add(jso);
		}
		File file = new File(targetFile);
		try {
			if (!file.exists()){
				System.out.println("new file:" + file.getPath());
				file.createNewFile();
			}
			FileWriter fw = new FileWriter(file, true);
			for (JSONObject tmp : lstObj) {
				System.out.println(tmp.toString());
				fw.append(tmp.toString()+"\n");
				fw.flush();
				Thread.sleep(GAP);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}