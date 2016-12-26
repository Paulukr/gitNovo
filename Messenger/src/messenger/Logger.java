package messenger;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.LinkedList;
import java.util.Queue;

class Logger extends AbstractLogger{
	StringListener feedback;
		final static int MAX_LOCAL_SIZE = 1000000;
		ArrayList<LogEntry> logData = new ArrayList<LogEntry>();
		ArrayList<ArrayList<LogEntry>> localStorage = new ArrayList<ArrayList<LogEntry>>();
		Object baseAval;
		String currentTableName;
		
		Queue<String> dbQueue = new LinkedList<String>();
		
		Logger(){
			startDaySince1970 = (int) (System.currentTimeMillis()/86400);
			maxLocalSize = 100;
			createNewTable();
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
			localStorage.add(logData);
			logData = new ArrayList<LogEntry>();
		}

		@Override
		public void localAddEntry(LogEntry entry) {
			logData.add(entry);
		}

		@Override
		public void pushLastLocalStore() {
			// TODO Auto-generated method stub
			for(LogEntry entry:logData)
			dbQueue.add("insert into " + currentTableName + "values(" + entry.toString() + ")");
		}
		@Override
		public void createNewTable() {
			// TODO Auto-generated method stub
			//get date
			DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");//"yyyy/MM/dd HH:mm:ss"
			Date date = Date.from(Instant.now());//			Date date = Date.valueOf(LocalDate.now());//new Date(0);
			currentTableName = "messengerLog-" + dateFormat.format(date).toString();
			String createTableQuery = "create table " + currentTableName + 
					"(" +
					" id serial primary key" + 
					" clientNo int NOT NULL," +
					" message TEXT NOT NULL," +
					" whatHappend TEXT," +
					" value1 TEXT," +
					" value2 TEXT," +
					" value3 TEXT," +
					" timeStamp timestamp NOT NULL" +
					//", column_name3 data_type(size)" +
					");";
			dbQueue.add(createTableQuery);
		}
//		id(automatic), clientNo, message, whatHappend, value1, value2, value3, timeStamp
		@Override
		public void pushDBQueue() {
			// TODO Auto-generated method stub
			
		}

		@Override
		public int currentLocalSize() {
			return logData.size();
		}

		@Override
		public void localAddEntry(AbstractLogEntry entry) {
			// TODO Auto-generated method stub
			
		}

}
