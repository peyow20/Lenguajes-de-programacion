

package producerconsumer;

import java.util.logging.Level;
import java.util.logging.Logger;


public class Buffer {
    
    private String buffer;
    private String id;
    
    Buffer() {
        this.buffer = "";
    }
    
    synchronized String consume() {
        String products;
        
        if(this.buffer == "") {
            try {
                wait(1000);
            } catch (InterruptedException ex) {
                Logger.getLogger(Buffer.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        products = this.buffer;
        this.buffer = "";
        notify();
        
        return id;
    }
    
    synchronized void produce(String products, String id) {
       this.id = id;
        if(this.buffer != "") {
            try {
                wait(1000);
            } catch (InterruptedException ex) {
                Logger.getLogger(Buffer.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        this.buffer = products;
        
        notify();
    }
    
    static int count = 1;
    synchronized static void print(String string) {
        System.out.print(count++ + " ");
        System.out.println(string);
    }
    
}

