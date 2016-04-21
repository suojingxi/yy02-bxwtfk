package test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Test {

	public static void main(String[] args) {
		List<Map<String, Object>> lui = new ArrayList<Map<String,Object>>();
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("a", "1");
		map.put("b", "");
		lui.add(map);
		map = new HashMap<String, Object>();
		map.put("a", "1");
		map.put("b", "1");
		lui.add(map);
		map = new HashMap<String, Object>();
		map.put("a", "1");
		map.put("b", "");
		lui.add(map);
		map = new HashMap<String, Object>();
		map.put("a", "1");
		map.put("b", "");
		lui.add(map);
		for(int i = 0; i < lui.size(); i++){
			if(lui.get(i).get("b")==null||lui.get(i).get("b").equals("")){
				lui.remove(i);
				i--;
			}
		}
		System.out.println(lui);
		String str = "abcdefg";
		System.out.println(str.indexOf("x"));
	}

}
