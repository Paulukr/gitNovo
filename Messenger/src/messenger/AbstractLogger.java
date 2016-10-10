package messenger;

import java.sql.Timestamp;
import java.time.Instant;

abstract class AbstractLogger {
	
	int startDaySince1970;
	int maxLocalSize;
	public abstract int currentDaySince1970();
	public abstract int currentLocalSize();
	
	public abstract void createNewLocal();
	public abstract void localAddEntry(AbstractLogEntry entry);
	//addToDBQueue()
	public abstract void pushLastLocalStore();
	public abstract void createNewTable();
	public abstract void createNewLocalStore();
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
			data[1] = message;
			for(int i = 0; (i < str.length)&&(i < 4); i++) data[i+1] = str[i];
			if(str.length > 4) System.out.println("too arguments for log");//slog(clientNo, "too arguments " + str[5] + " ...");
			ts  = Timestamp.from(Instant.now());
		}
	}//end inner class
}
