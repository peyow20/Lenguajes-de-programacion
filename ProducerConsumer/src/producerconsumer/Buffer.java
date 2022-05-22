
package producerconsumer;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Buffer {
    
    private final BlockingQueue<String> buffer;
    private GUIFrame gui;
    private int Size;
    
    Buffer(int size, GUIFrame giu) {
        this.buffer = new ArrayBlockingQueue<>(size);
        this.gui = gui;
        this.Size = size;
    }
  
    
    synchronized String consume(int id) {
        String product; 
        while (this.buffer.isEmpty()) {
            try {
                wait();
            } catch (InterruptedException ex) {
                Logger.getLogger(Buffer.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        product = this.buffer.remove();
        String result = PrefixEvaluation(product);
        gui.completarTareaPorHacer();
        gui.TareasRealizadas(id, product, result);
        Buffer.print("Consumer consumed: " + product);
        notify();
        return product;
    }
    
    synchronized void produce(String product, int id) {
        while (this.buffer.remainingCapacity() == 0) {
            try {
                wait();
            } catch (InterruptedException ex) {
                Logger.getLogger(Buffer.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        //Verificar porque no esta generando el product
        this.buffer.add(product);
        gui.addTareaPorHacer(id, product);
        print("Producer produced: " + product);
        notify();
    }
    
    static String PrefixEvaluation(String product) {
        String[] Caracteres = product.substring(1, product.length() - 1).split(" ");
        char operations = Caracteres[0].charAt(0);
        double num1 = Double.parseDouble(Caracteres[1]);
        double num2 = Double.parseDouble(Caracteres[2]);
        
        double result = 0.0;
        
        switch (operations) {
            case '+':
                result = num1 + num2;
                break;
            case '-':
                result = num1 - num2;
                break;
            case '*':
                result = num1 * num2;
                break;
            case '/':
                if (num2 == 0) {
                    return("Cannot divide by zero");
                }
                result = num1 / num2;
                break;
        }  
        return String.valueOf(result);
    }
    static int count = 1;
    synchronized static void print(String string) {
        System.out.print(count++ + " ");
        System.out.println(string);
    }
    
    public void Restart(){
        count = 1;
        gui.ContadorTareasR();
    }
    
}
