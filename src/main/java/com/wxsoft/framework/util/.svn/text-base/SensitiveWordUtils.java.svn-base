package com.wxsoft.framework.util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

public class SensitiveWordUtils {

	static ArrayList<String> first = new ArrayList<String>();
	static String[] sortFirst;
	static char[] charFirst;
	static HashMap<String, ArrayList<String>> map = new HashMap<String, ArrayList<String>>();
	static HashMap<String, String[]> sortMap = new HashMap<String, String[]>();
	static HashMap<String, char[]> charMap = new HashMap<String, char[]>();

	static ArrayList<String> temp;
	static String key, value;
	int length;

	/**
	 * 带参数的构造函数
	 * 
	 * @param keys
	 *            敏感词
	 * @param tContent
	 *            需要过滤的内容
	 */
	public SensitiveWordUtils(String tContent, String[] keys) {
		for (String k : keys) {
			if (!first.contains(k.substring(0, 1))) {
				first.add(k.substring(0, 1));
			}
			length = k.length();
			for (int i = 1; i < length; i++) {
				key = k.substring(0, i);
				value = k.substring(i, i + 1);
				if (i == 1 && !first.contains(key)) {
					first.add(key);
				}

				// 有，添加
				if (map.containsKey(key)) {
					if (!map.get(key).contains(value)) {
						map.get(key).add(value);
					}
				}
				// 没有添加
				else {
					temp = new ArrayList<String>();
					temp.add(value);
					map.put(key, temp);
				}
			}
		}
		sortFirst = first.toArray(new String[first.size()]);
		Arrays.sort(sortFirst); // 排序

		charFirst = new char[first.size()];
		for (int i = 0; i < charFirst.length; i++) {
			charFirst[i] = first.get(i).charAt(0);
		}
		Arrays.sort(charFirst); // 排序

		String[] sortValue;
		ArrayList<String> v;
		Map.Entry<String, ArrayList<String>> entry;
		Iterator<Entry<String, ArrayList<String>>> iter = map.entrySet()
				.iterator();
		while (iter.hasNext()) {
			entry = (Map.Entry<String, ArrayList<String>>) iter.next();
			v = (ArrayList<String>) entry.getValue();
			sortValue = v.toArray(new String[v.size()]);
			Arrays.sort(sortValue); // 排序
			sortMap.put(entry.getKey(), sortValue);
		}

		char[] charValue;
		iter = map.entrySet().iterator();
		while (iter.hasNext()) {
			entry = (Map.Entry<String, ArrayList<String>>) iter.next();
			v = (ArrayList<String>) entry.getValue();
			charValue = new char[v.size()];
			for (int i = 0; i < charValue.length; i++) {
				charValue[i] = v.get(i).charAt(0);
			}
			Arrays.sort(charValue); // 排序
			charMap.put(entry.getKey(), charValue);
		}
	}

	/**
	 * 把敏感词替换成*
	 * 
	 * @param content
	 *            需要过滤的内容
	 * @return true 为存在,false 为不存在
	 */
	public synchronized boolean isHave(String content) {
		String r = null, f, c = content;
		String replacedword = content;
		boolean result = false;
		char g;
		char[] temps;
		int length = c.length();
		for (int i = 0; i < length - 1; i++) {
			g = c.charAt(i);
			// 二分查找
			if (Arrays.binarySearch(charFirst, g) > -1) {
				tag: for (int j = i + 1; j < length; j++) {
					f = c.substring(i, j);
					g = c.charAt(j);
					temps = charMap.get(f);
					if (temps == null) { // 找到了
						// System.out.println("ok");
						r = f;
						String str = "";
						for (int m = 1; m <= r.length(); m++) {
							// str = str + "*";
							result = true;
						}
						replacedword = c.replace(r, str);
						c = replacedword;
						break tag;
					}
					// 二分查找
					if (Arrays.binarySearch(temps, g) > -1) {
						if (j == length - 1) {
							// print("find!");
							// System.out.println("find!");
							r = c.substring(i, j + 1);
							String str = "";
							for (int m = 1; m <= r.length(); m++) {
								// str = str + "*";
								result = true;
							}
							replacedword = c.replace(r, str);
							c = replacedword;
							break tag;
						}
					} else { // 没有找到了
						break;
					}
				}
			}
		}
		return result;
	}

	public static void main(String[] args) {
		// 被过滤内容
		String contentStr = "哈哈";

		SensitiveWordUtils swu = new SensitiveWordUtils(contentStr,
				"色情".split("@"));
		System.out.println(swu.isHave(contentStr));
	}
}
