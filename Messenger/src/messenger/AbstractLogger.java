package messenger;

import java.sql.Timestamp;
import java.time.Instant;

import messenger.AbstractLogger.LogEntry;

abstract class AbstractLogger {
	
	int startDaySince1970;
	int maxLocalSize;
	public abstract int currentDaySince1970();
	public abstract int currentLocalSize();
	
	public abstract void createNewLocal();
	public abstract void localAddEntry(AbstractLogEntry entry);
	public abstract void localAddEntry(LogEntry entry);
	//addToDBQueue()
	public abstract void pushLastLocalStore();//adds local log just added to local store to the queue of db queries
//	now it seems useless, because I can check if there anything in local store every pushDBQueue call. But there can be a problem with right sequence of adding new
//	tables and inserting local stores into it
	public abstract void createNewTable();
	public abstract void pushDBQueue();
	
	public void log(LogEntry entry) {
		if(currentDaySince1970() > startDaySince1970)
		{
			startDaySince1970++;
			localAddEntry( new LogEntry(0,"new date"));
			createNewLocal();//new date
			localAddEntry( entry);
			localAddEntry( new LogEntry(0,"new date"));
			
			//add to db queue
			pushLastLocalStore();
			createNewTable();
			pushDBQueue();// if two 1st true
		}
		else if(currentLocalSize() > maxLocalSize)// if(localFull) //boolean localFool flag is set by localAddEntry
		{
			createNewLocal();//current date
			localAddEntry( entry);
			localAddEntry( new LogEntry(0,"size over"));
			
			//add to db queue
			pushLastLocalStore();	
			pushDBQueue();// if two 1st true
		}else {
		localAddEntry( entry);//!
		}
//		pushDBQueue();// if two 1st true
	}
	abstract class AbstractLogEntry {
		
	}//end inner class
	class LogEntry extends AbstractLogEntry{
		//database description
		//id(automatic), clientNo, message, whatHappend, value1, value2, value3, timeStamp
		int clientNo;
		String[] data = new String[5];
		Timestamp ts;
		LogEntry(int clientNo, String message, String...str){
			this.clientNo = clientNo;
			data[0] = message;
			for(int i = 0; (i < str.length)&&(i < 4); i++) data[i+1] = str[i];
			if(str.length > 4) System.out.println("too arguments for log");//slog(clientNo, "too arguments " + str[5] + " ...");
			ts  = Timestamp.from(Instant.now());
		}
		public String toString(){
			String rs = Integer.toString(clientNo);
			for(String s:data) rs+= ", " + s;
			return rs+= ", " + ts.toString();
		}
	}//end inner class

}
