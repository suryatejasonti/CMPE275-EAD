package edu.sjsu.cmpe275.aop;

import java.util.ArrayList;
import java.util.Map;
import java.util.TreeMap;

public class TweetStatsImpl implements TweetStats {
    /***
     * Following is a dummy implementation.
     * You are expected to provide an actual implementation based on the requirements.
     */

	private TreeMap<String,ArrayList<String>> usersMap = new TreeMap<String,ArrayList<String>>();
	private TreeMap<String,ArrayList<String>> followsMap = new TreeMap<String,ArrayList<String>>();
	private TreeMap<String,ArrayList<String>> tweetsMap = new TreeMap<String,ArrayList<String>>();
	private TreeMap<String,ArrayList<String>> blocksMap = new TreeMap<String,ArrayList<String>>();
	
	public  void addTweet(String str1,String  str2){
		if(usersMap.containsKey(str1)){
			ArrayList<String> al = usersMap.get(str1);
			al.add(str2);
			usersMap.put(str1, al);
		}else{			
			ArrayList<String> al = new ArrayList<String>();
			al.add(str2);
			usersMap.put(str1, al);
		}
		if(tweetsMap.containsKey(str2)){
			ArrayList<String> al = tweetsMap.get(str2);
			al.add(str1);
			tweetsMap.put(str2, al);
		}else{			
			ArrayList<String> al = new ArrayList<String>();
			al.add(str1);
			tweetsMap.put(str2, al);
		}
	}
	/*
	 * Utlity function to add Follow data to the Follow Data Store.
	 */
	public void addFollow(String str1,String str2){
		
		if(followsMap.containsKey(str2)){
			ArrayList<String> al = followsMap.get(str2);
			al.add(str1);
			followsMap.put(str2, al);
		}else{			
			ArrayList<String> al = new ArrayList<String>();
			al.add(str1);
			followsMap.put(str2, al);
		}		
	}
	public void addBlock(String str1,String str2){
		if(blocksMap.containsKey(str2)){
			ArrayList<String> al = blocksMap.get(str2);
			al.add(str1);
			blocksMap.put(str2, al);
		}else{			
			ArrayList<String> al = new ArrayList<String>();
			al.add(str1);
			blocksMap.put(str2, al);
		}		
	}
	public void resetStatsAndSystem() {
		this.usersMap.clear();
		this.followsMap.clear();
		this.blocksMap.clear();
		this.tweetsMap.clear();
	}
    
	
	public int getLengthOfLongestTweet() {
		int sum =0;
		for(Map.Entry<String, ArrayList<String>> map : this.usersMap.entrySet()){
			ArrayList<String> al = map.getValue();
			for(int i=0; i<al.size();i++){
				if(al.get(i).length()>sum)
					sum=al.get(i).length();
			}
		}
		return sum;
	}

	
	public String getMostFollowedUser() {
		TreeMap<String,ArrayList<String>> sortedMap = new TreeMap<String,ArrayList<String>>(this.followsMap);
		String name=null;
		int count = 0;
		for(Map.Entry<String,ArrayList<String>> data : sortedMap.entrySet()){
			if(data.getValue().size()>count){
				name = data.getKey();
				count = data.getValue().size();
			}
		}		
		return name;
	}
	
	public String getMostBlockedFollower() {
		TreeMap<String,ArrayList<String>> sortedMap = new TreeMap<String,ArrayList<String>>(this.blocksMap);
		String name=null;
		int count = 0;
		for(Map.Entry<String,ArrayList<String>> data : sortedMap.entrySet()){
			if(data.getValue().size() > count){
				name = data.getKey();
				count = data.getValue().size();
			}
		}		
		return name;
	}
	
	public String getMostProductiveUser() {
		int max=0;
		String user=null;
		for(Map.Entry<String, ArrayList<String>> map : this.usersMap.entrySet()){
			ArrayList<String> al = map.getValue();
			int sum =0;
			for(int i=0; i<al.size();i++){
					sum+=al.get(i).length();
			}
			if(sum>max){
				user=map.getKey();
				max = sum;
			}
		}
		return user;
	}

	public String getMostPopularMessage() {
		TreeMap<String,ArrayList<String>> sortedMap = new TreeMap<String,ArrayList<String>>(this.tweetsMap);
		String message=null;
		int count = 0;
		for(Map.Entry<String,ArrayList<String>> data : sortedMap.entrySet()){
			if(data.getValue().size()>count){
				message = data.getKey();
				count = data.getValue().size();
			}
		}		
		return message;
	}
	
	
	
}



