
package producerconsumer;


import java.util.logging.Level;
import java.util.logging.Logger;


public class Consumer extends Thread {
    Buffer buffer;
    private final int sleepConsumidores,idConsu;
    private boolean stop;
    
    Consumer(Buffer buffer, int idConsum, int sleepConsumidores) {
        this.buffer = buffer;
        this.sleepConsumidores = sleepConsumidores;
        this.idConsu = idConsum; 
        this.stop = true;
    }
 
    @Override
    public void run() {
        System.out.println("Running Consumer...");
        String product;
        while (stop) {
            System.out.println("Consumer consumed: ");
            
            try {
                Thread.sleep(sleepConsumidores);
            } catch (InterruptedException ex) {
                Logger.getLogger(Producer.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    public void Apagar(){
        this.stop = false;
    }
}
