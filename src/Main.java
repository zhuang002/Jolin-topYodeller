import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);
		
		int n = sc.nextInt();
		int k = sc.nextInt();
		
		Record[] records = new Record[n];
		for (int i=0;i<n;i++) {
			records[i] = new Record();
			records[i].id = i;
		}
		
		ArrayList<Record> dynRecords = new ArrayList<>();
		for (int round=0;round<k;round++) {
			dynRecords.clear();
			for (int person=0;person<n;person++) {
				int score = sc.nextInt();
				records[person].score+=score;
				dynRecords.add(records[person]);
			}
			
			Collections.sort(dynRecords, (x,y)->{
				int temp=y.score-x.score;
				if (temp!=0) return temp;
				return x.id-y.id;
			});;
			
			int currentRank=0;
			int currentScore = Integer.MAX_VALUE;
			for (int p=0;p<n;p++) {
				Record record = dynRecords.get(p);
				if (record.score < currentScore) {
					currentRank = p+1;
					currentScore = record.score;
				}
				if (currentRank > record.worstRank) 
					record.worstRank = currentRank;
			}
		}
		
		int score = dynRecords.get(0).score;
		for (Record record: dynRecords) {
			if (record.score!=score) break;
			System.out.println("Yodeller " + (record.id+1) + " is the TopYodeller: score "+score+", worst rank "+record.worstRank);
		}
	}

}

class Record {
	int id=0;
	int worstRank=0;
	int score=0;
}
