package com.example;

import java.util.*;
public class Program1 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String str = "adsdfsdfgdgdffdfgffghllsgkiwwrpopwrttw";
		int n = 3;
		List<String> strList = new ArrayList<>();
		for(int i=0;i+n<=str.length();i++) {
			String subStr = str.substring(i,i+n);
			for(int j =0; j<subStr.length();j++) {
				Character ch = subStr.charAt(j);
				if(subStr.indexOf(ch) != subStr.lastIndexOf(ch)) {
					strList.add(subStr);
					break;
				}
			}
		}
		System.out.println(strList);
	}

}
