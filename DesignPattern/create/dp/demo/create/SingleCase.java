package dp.demo.create;

class HungeryCase {
	//饿
	private static HungeryCase _instance = new HungeryCase();
	private HungeryCase() {	}
	public static HungeryCase getInstance() {
		return _instance;
	}
}

class LazyCase {
	//懒
	private static LazyCase _instance = null;
	private LazyCase() { }
	synchronized public static LazyCase getInstance() {
		if(_instance == null) {
			_instance = new LazyCase();
		}
		return _instance;
	}
}

/*
 * 使用单例设计模式作为计数器，访问次数等
 */
class Counter {
	private static Counter _instance = new Counter();
	private int num = 0;
	private Counter() {}
	
	//返回唯一的实例
	public static Counter getInstance() {
		return _instance;
	}
	
	//计数函数:该函数一次只能被一个对象访问
	public synchronized int getCount() {
		return num++;
	}
}

/*
 * 测试方法 利用多线程去访问
 */
class TestThread implements Runnable {
	private String threadName;
	public TestThread(String threadName) {
		this.threadName = threadName;
	}
	public void run() {
		try{
			for(int i=0; i<10000; i++) {
				System.out.println(threadName+":"+Counter.getInstance().getCount());
				Thread.sleep(Math.abs((int)Math.random())+20);
			}
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}
}
class SingleCase {

	public static void main(String[] args) {
		/*for(int i=0; i<10; i++) {
			System.out.println(Counter.getInstance().getCount());
		}*/
		
		/*Thread tt1 = new Thread(new TestThread("tt1"));
		Thread tt2 = new Thread(new TestThread("tt2"));
		Thread tt3 = new Thread(new TestThread("tt3"));
		tt1.start();
		tt2.start();
		tt3.start();*/
		
		
	}
	
	

}
