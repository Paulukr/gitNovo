package messenger;

import java.util.EventListener;

public interface StringListener extends EventListener {
	 void stringReceived(String string);
}
