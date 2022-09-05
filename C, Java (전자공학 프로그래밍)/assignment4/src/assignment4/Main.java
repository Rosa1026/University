package assignment4;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class Main implements Runnable{
    public static void main(String[] args) throws InterruptedException {
    	
    	Teller tellerA = new Teller("유동연");
    	Teller tellerB = new Teller("장현웅");
    	Teller tellerC = new Teller("박예슬");
    	
    	Waiting common = new Waiting("Common");
    	Waiting vip    = new Waiting("VIP");
    	
    	Managing managing = new Managing();
    	
    	managing.init(common, vip);
    	
        ScheduledExecutorService service = Executors.newSingleThreadScheduledExecutor();
        
        service.scheduleAtFixedRate(tellerA, 500, 1000, TimeUnit.MILLISECONDS);
        service.scheduleAtFixedRate(tellerB, 500, 1000, TimeUnit.MILLISECONDS);
        service.scheduleAtFixedRate(tellerC, 500, 1000, TimeUnit.MILLISECONDS);
        
        service.scheduleAtFixedRate(common, 0, 1000, TimeUnit.MILLISECONDS);
        service.scheduleAtFixedRate(vip, 0, 1000, TimeUnit.MILLISECONDS);
        
        service.scheduleAtFixedRate(managing, 900, 1000, TimeUnit.MILLISECONDS);
        
        Thread.sleep(15000);
        service.shutdown();
    	
    }

	@Override
	public void run() {
		// TODO Auto-generated method stub
		
	}

}