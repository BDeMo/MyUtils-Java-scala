package TestTools;

import com.sun.jdi.Value;

import java.io.PrintStream;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @auther SamJ
 * @create 2018 - 07 - 01 11:46
 */
public class RegTest {
	public static void main(String[] args) {
		String value = "1363157985066 \t13726230503\t00-FD-07-A4-72-B8:CMCC\t120.196.100.82\ti02.c.aliimg.com\t\t24\t27\t2481\t24681\t200\n" +
				"1363157995052 \t13826544101\t5C-0E-8B-C7-F1-E0:CMCC\t120.197.40.4\t\t\t4\t0\t264\t0\t200\n" +
				"1363157991076 \t13926435656\t20-10-7A-28-CC-0A:CMCC\t120.196.100.99\t\t\t2\t4\t132\t1512\t200\n" +
				"1363154400022 \t13926251106\t5C-0E-8B-8B-B1-50:CMCC\t120.197.40.4\t\t\t4\t0\t240\t0\t200\n" +
				"1363157993044 \t18211575961\t94-71-AC-CD-E6-18:CMCC-EASY\t120.196.100.99\tiface.qiyi.com\t视频网站\t15\t12\t1527\t2106\t200\n" +
				"1363157995074 \t84138413\t5C-0E-8B-8C-E8-20:7DaysInn\t120.197.40.4\t122.72.52.12\t\t20\t16\t4116\t1432\t200\n" +
				"1363157993055 \t13560439658\tC4-17-FE-BA-DE-D9:CMCC\t120.196.100.99\t\t\t18\t15\t1116\t954\t200\n" +
				"1363157995033 \t15920133257\t5C-0E-8B-C7-BA-20:CMCC\t120.197.40.4\tsug.so.360.cn\t信息安全\t20\t20\t3156\t2936\t200\n" +
				"1363157983019 \t13719199419\t68-A1-B7-03-07-B1:CMCC-EASY\t120.196.100.82\t\t\t4\t0\t240\t0\t200\n" +
				"1363157984041 \t13660577991\t5C-0E-8B-92-5C-20:CMCC-EASY\t120.197.40.4\ts19.cnzz.com\t站点统计\t24\t9\t6960\t690\t200\n" +
				"1363157973098 \t15013685858\t5C-0E-8B-C7-F7-90:CMCC\t120.197.40.4\trank.ie.sogou.com\t搜索引擎\t28\t27\t3659\t3538\t200\n" +
				"1363157986029 \t15989002119\tE8-99-C4-4E-93-E0:CMCC-EASY\t120.196.100.99\twww.umeng.com\t站点统计\t3\t3\t1938\t180\t200\n" +
				"1363157992093 \t13560439658\tC4-17-FE-BA-DE-D9:CMCC\t120.196.100.99\t\t\t15\t9\t918\t4938\t200\n" +
				"1363157986041 \t13480253104\t5C-0E-8B-C7-FC-80:CMCC-EASY\t120.197.40.4\t\t\t3\t3\t180\t180\t200\n" +
				"1363157984040 \t13602846565\t5C-0E-8B-8B-B6-00:CMCC\t120.197.40.4\t2052.flash2-http.qq.com\t综合门户\t15\t12\t1938\t2910\t200\n" +
				"1363157995093 \t13922314466\t00-FD-07-A2-EC-BA:CMCC\t120.196.100.82\timg.qfc.cn\t\t12\t12\t3008\t3720\t200\n" +
				"1363157982040 \t13502468823\t5C-0A-5B-6A-0B-D4:CMCC-EASY\t120.196.100.99\ty0.ifengimg.com\t综合门户\t57\t102\t7335\t110349\t200\n" +
				"1363157986072 \t18320173382\t84-25-DB-4F-10-1A:CMCC-EASY\t120.196.100.99\tinput.shouji.sogou.com\t搜索引擎\t21\t18\t9531\t2412\t200\n" +
				"1363157990043 \t13925057413\t00-1F-64-E1-E6-9A:CMCC\t120.196.100.55\tt3.baidu.com\t搜索引擎\t69\t63\t11058\t48243\t200\n" +
				"1363157988072 \t13760778710\t00-FD-07-A4-7B-08:CMCC\t120.196.100.82\t\t\t2\t2\t120\t120\t200\n" +
				"1363157985066 \t13726238888\t00-FD-07-A4-72-B8:CMCC\t120.196.100.82\ti02.c.aliimg.com\t\t24\t27\t2481\t24681\t200\n" +
				"1363157993055 \t13560436666\tC4-17-FE-BA-DE-D9:CMCC\t120.196.100.99\t\t\t18\t15\t1116\t954\t200";
		String[] words  = value.split("\n");
		for(String str : words){
			Pattern p =Pattern.compile("(.*?)\t(.*?)\t(.*?)\t(.*?)\t(.*?)\t(.*?)\t(.*?)\t(.*?)\t(.*?)\t(.*?)\t(.*?)");
			Matcher m= p.matcher(str);
			int[] ints = new int[3];
			boolean flag = false;
			if(m.matches()){
				ints[0] = Integer.parseInt(m.group(9));
				ints[1] = Integer.parseInt(m.group(10));
				ints[2] = Integer.parseInt(m.group(9)) + Integer.parseInt(m.group(10));
				flag = true;
			}
			if(flag){
				System.out.println(m.group(2) + "\t" + ints[0] + "\t" + ints[1] + "\t" + ints[2]);
			}else{
				System.out.println(str);
			}
		}


		String str1 = "1363157985066 \t13726230503\t00-FD-07-A4-72-B8:CMCC\t120.196.100.82\ti02.c.aliimg.com\t\t24\t27\t2481\t24681\t200";
		String str2 ="1363157995052 \t13826544101\t5C-0E-8B-C7-F1-E0:CMCC\t120.197.40.4\t\t\t4\t0\t264\t0\t200";
		System.out.println(str1);
		System.out.println(str2);
		Matcher m1= Pattern.compile("(.*?)\t(.*?)\t(.*?)\t(.*?)\t(.*?)\t\t(.*?)\t(.*?)\t(.*?)\t(.*?)\t(.*?)").matcher(str1);
		if(m1.matches()){
			for(int i = 1; i < m1.groupCount(); i++){
				System.out.print("\t"+i+": " + m1.group(i));
			}
		}
		System.out.println("\n_____________________________");
		Matcher m2 = Pattern.compile("(.*?)\t(.*?)\t(.*?)\t(.*?)\t(.*?)\t\t(.*?)\t(.*?)\t(.*?)\t(.*?)\t(.*?)").matcher(str2);
		if(m2.matches()){
			for(int i = 1; i < m2.groupCount(); i++){
				System.out.print("\t"+i+": " + m2.group(i));
			}
		}
	}
}
