package FTransmit.filetransmit;

import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;




public class test {

	public static void main(String[] args) {
		try {
			String str = "[ { \"Uid\": 1, \"Uname\": null, \"Uphone\": \"9527\", \"Uemail\": null, \"Usex\": null, \"Uage\": null } ]";
//			Matcher m1 = Pattern.compile("(.*?)(id:)(.*?)(\\})").matcher(str);
//			System.out.println(m1.find());
//			System.out.println(m1.group(3));
			str = str.split("\\{")[1].split("\\}")[0];
			String strs[] = str.split(",");
			for (String string : strs) {				
				System.out.println(string + '\n');
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
