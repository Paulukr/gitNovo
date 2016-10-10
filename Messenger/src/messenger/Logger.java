package messenger;
import java.sql.Timestamp;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;

class Logger extends AbstractLogger{
	StringListener feedback;
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

		void log(int clientNo, String message, String...str){
			logData.add(new LogEntry(clientNo, message, str));
			feedback.stringReceived(message + " " + Arrays.toString(str));//?
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

		int currentDayOfMonth() {
			Timestamp ts  = Timestamp.from(Instant.now());
			Calendar cal = Calendar.getInstance();
			cal.setTimeInMillis(ts.getTime());
			return cal.get(Calendar.DAY_OF_MONTH);}
		@Override
		public int currentDaySince1970() {
			return (int) (System.currentTimeMillis()/86400);
		}

		@Override
		public void createNewLocal() {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void localAddEntry(AbstractLogEntry entry) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void pushLastLocalStore() {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void createNewTable() {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void createNewLocalStore() {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void pushDBQueue() {
			// TODO Auto-generated method stub
			
		}

		@Override
		public int currentLocalSize() {
			// TODO Auto-generated method stub
			return 0;
		}

}
