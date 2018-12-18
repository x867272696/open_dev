package com.htl.cloudmemory.util.coderobot.util;

/**
 * 
 * @author htl
 * @since CodeRobot 1.0
 */
public class StringFormat {
	
	/**
	 * 功能：将输入字符串的首字母改成大写
	 * 
	 * @param str
	 * @return
	 */
	public static String initcap(String str) {

		char[] ch = str.toCharArray();
		if (ch[0] >= 'a' && ch[0] <= 'z') {
			ch[0] = (char) (ch[0] - 32);
		}

		return new String(ch);
	}
	
	/**
	 * 驼峰命名字段
	 * 
	 * @param colName
	 * @return
	 */
	public static String getColnameToCamelCase(String colName) {
		String colnameCamelCase = "";
		String[] strs = colName.split("_");
		int tag = 0;
		for (String str : strs) {
			if(null==str || str.equals("")){
				continue;
			}
			if (tag != 0) {
				colnameCamelCase += initcap(str);
			} else {
				colnameCamelCase += str;
			}
			tag++;
		}
		return colnameCamelCase;
	}
	
	/**
	 * 函数:strchkp 作用:返回字符串形参strb第strn次出现在字符串形参stra的起始位置,如果不适配，则返回-1
	 * 示例:如果stra为"iloveyou",strb为"ve",strn为1,函数将返回3
	 */
	public static int strchkp(String stra, String strb, int strn) {
		int r, lena, lenb, cishu = 0;
		lena = stra.length();
		lenb = strb.length();
		if (lenb > lena)
			return -1;
		for (r = 0; r <= lena - lenb; r++)
			for (int j = 0; j < lenb; j++) {
				if (strb.charAt(j) != stra.charAt(j + r))
					break;
				if (j == lenb - 1)
					cishu++;
				if (cishu == strn)
					return r;
			}
		return -1;
	}
	// --------------------

	/**
	 * 函数:strchkq 作用:返回从指定位置开始计算的strb首次出现在stra中的末位置
	 * 示例:如果stra为"iloveyou",strb为"o",strn为4,函数将返回6
	 */
	public static int strchkq(String stra, String strb, int strn) {
		int lena, lenb, r;
		lena = stra.length();
		lenb = strb.length();
		if (lena - strn < lenb)
			return -1;
		for (r = strn; r <= lena - lenb; r++)
			for (int j = 0; j < lenb; j++) {
				if (strb.charAt(j) != stra.charAt(j + r))
					break;
				if (j == lenb - 1)
					return (j + r);
			}
		return -1;
	}
	
	/**
	 * 函数:strchks 作用:返回指定字符串区间第N次出现在特定字符串中的完整串
	 * 示例:如果stra为"iloveyou",strb为"o",strc为y，strn为1,函数将返回ve
	 */
	public static String strchks(String stra, String strb, String strc, int strn) {
		int iloop, strchkpr, strchkqr;
		StringBuffer rend = new StringBuffer("");
		String errrend = new String();
		strchkpr = strchkp(stra, strb, strn);
		if (strchkpr == -1)
			return errrend;
		strchkqr = strchkq(stra, strc, strchkpr + strb.length());
		if (strchkqr == -1)
			return errrend;
		for (iloop = strchkpr + strb.length(); iloop <= strchkqr - strc.length(); iloop++) {
			rend.append(stra.charAt(iloop));
		}
		return rend.toString();
	}
}
