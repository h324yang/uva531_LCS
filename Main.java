package uva531_LCS;

import java.io.IOException;
import java.util.Scanner;

class Main {
	//For recording LCS processing direction
	public enum Direction{
		left, diagnal, up;
	}
	
	public static String getLCS(String s1, String s2){
		String[] sent1 = s1.split(" ");
		String[] sent2 = s2.split(" ");
		//Create a 2d array for LCS count record
		int[][] int_2dArray = new int[sent1.length+1][sent2.length+1];
		
		//Create a 2d array for LCS direction record
		Direction[][] directions = new Direction[sent1.length+1][sent2.length+1];
		
		//Processing LCS
		for(int i=1;i<sent1.length+1;i++){
			for(int j=1;j<sent2.length+1;j++){
				if(sent1[i-1].equals(sent2[j-1])){
					int_2dArray[i][j] = int_2dArray[i-1][j-1] + 1; //recording LCS count
					directions[i][j] = Direction.diagnal; //recording LCS processing direction
				}else{
					if(int_2dArray[i][j-1] > int_2dArray[i-1][j]){
						int_2dArray[i][j] = int_2dArray[i][j-1];
						directions[i][j] = Direction.left;
					}else{
						int_2dArray[i][j] = int_2dArray[i-1][j];
						directions[i][j] = Direction.up;
					}
				}
			}
		}
		
		//Extracting LCS elements based on LCS direction record
		int i = sent1.length;
		int j = sent2.length;
		String LCS = "";
		while(int_2dArray[i][j] != 0){
			if(directions[i][j].equals(Direction.diagnal)){
				LCS = " " + sent1[i-1] + LCS;
				i -= 1;
				j -= 1;
			}
			else if(directions[i][j].equals(Direction.left)){
				j -= 1;
			}else{
				i -= 1;
			}
		}
		
		return LCS.trim(); //Removing the first blank
	}
	
	public static void main(String args[]) throws IOException{
		String tmp = "";
		Scanner sc = new Scanner(System.in);
		int sharps = 0;
		while(sc.hasNext()){
			String token = sc.next();
			tmp += token + " ";
			if(token.equals("#")) sharps += 1;
			if(sharps == 2){
				String s1 = tmp.split("#")[0].trim();
				String s2 = tmp.split("#")[1].trim();
				System.out.println(getLCS(s1, s2));	
				sharps = 0;
				tmp = "";
			}
		}		
	}
}
