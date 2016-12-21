
public class Ex10TreadGroup {
// primitive thread pull, wrapper to thread array
	public static void main(String[] args) {
		ThreadGroup tg = new ThreadGroup("TG");
		
		tg.setMaxPriority(5);
		//new thread priority will be lowered to 5
		// changing tg priority do nothing on threads already added
		
		// tg can be destroyed only after all its threads terminated
		
		Ex10Thread et1 = new Ex10Thread(1);
		Thread t1 = new Thread(tg, et1, "t1");
		
		
	}

}

class Ex10Thread implements Runnable{
	int id;

	Ex10Thread(int id) {
		this.id = id;
	}

	@Override
	public void run() {
		System.out.println("Ex10Thread" + id);
	}
	
}