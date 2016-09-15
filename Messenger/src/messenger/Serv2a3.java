package messenger;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;

public class Serv2a3 extends Serv2a2 {


	final static  double version = 3.9 ;//:)
	public static void main(String[] args) {
		System.out.println(version);
		new Serv2a2().go();

	}
	protected class Logger{
		final static int MAX_LOCAL_SIZE = 1000000;
		ArrayList<LogEntry> logData = new ArrayList<LogEntry>();
		ArrayList<ArrayList<LogEntry>> localStorage = new ArrayList<ArrayList<LogEntry>>();
		Object baseAval;
		int currentDayofMonth;
		int startDaySince1970;
		Logger(){
			startDaySince1970 = (int) (System.currentTimeMillis()/86400);			
//			   Timestamp ts  = Timestamp.from(Instant.now());
//			   Calendar cal = Calendar.getInstance();
//			   cal.setTimeInMillis(ts.getTime());
//			   currentDayofMonth = cal.get(Calendar.DAY_OF_MONTH);
//			   log("logger start", cal.getTime().toLocaleString()); 
			   
		}
		void log(String message, String...str){
			logData.add(new LogEntry(message, str));
		}
		void log(int clientNo, String message, String...str){
			logData.add(new LogEntry(clientNo, message, str));
		}
		void theLog() {
			if((int) (System.currentTimeMillis()/86400) > startDaySince1970) {//date>
				createNewLocal();//new table
				//add entry
				//add entry new date
//				dbque.add(){
//				push last local
				//create new table
//				}
			}
			if(logData.size() >  MAX_LOCAL_SIZE) {
				createNewLocal();//current table
				//add entry
				//add entry size over
//				dbque.add(){ 
//				push last local
				//create new table
//				}
			}
// 			dbque.push();
		}
		private void pushToBase() {} 
		private void createNewLocal() {}
		int currentDayOfMonth() {
			Timestamp ts  = Timestamp.from(Instant.now());
			Calendar cal = Calendar.getInstance();
			cal.setTimeInMillis(ts.getTime());
			return cal.get(Calendar.DAY_OF_MONTH);
		}
		int currentDaySince1970() {
			return (int) (System.currentTimeMillis()/86400);
		}


	}
	static class LogEntry{
		//database description
		//id(automatic), clientNo, message, whatHappend, value1, value2, value3, timeStamp
		int clientNo;
		String[] data = new String[5];
		Timestamp ts;
		LogEntry(int clientNo, String message, String...str){

			this.clientNo = clientNo;
			data[1] = message;
			for(int i = 0; (i < str.length)&&(i < 4); i++) data[i+1] = str[i];
			if(str.length > 4) System.out.println("too arguments for log");//slog(clientNo, "too arguments " + str[5] + " ...");
			ts  = Timestamp.from(Instant.now());
		}
		LogEntry(String message, String...str){

			this.clientNo = -1;
			data[1] = message;
			for(int i = 0; (i < str.length)&&(i < 4); i++) data[i+1] = str[i];
			if(str.length > 4) System.out.println("too arguments for log");//slog(clientNo, "too arguments " + str[5] + " ...");
			ts  = Timestamp.from(Instant.now());
		}
	}
	void log(String message, String...str) {};
	void slog(String message, String...str) {//String message, String whatHappend, String...values
		log(message, str) ; 
		tellEveryone(message + " " + Arrays.toString(str));
	}
}
