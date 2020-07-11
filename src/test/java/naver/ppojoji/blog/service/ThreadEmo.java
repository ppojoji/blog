package naver.ppojoji.blog.service;

import java.util.Date;

public class ThreadEmo {

	public static void main(String[] args) {
		Thread scheduleTread = new Thread(new Scheduling());
		scheduleTread.start();
	}
	
	static class Scheduling implements Runnable {
		
		public void run() {
			while (true) {
				System.out.println(new Date());
				try {
					Thread.sleep(20*60*60*1000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}
}
