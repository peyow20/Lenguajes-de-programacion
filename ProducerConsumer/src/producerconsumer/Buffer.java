
package producerconsumer;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Buffer {
    //Inicializacion de valores
    private final BlockingQueue<String> buffer;
    private GUIFrame gui;
    private int Size;
    //Constructor
    Buffer(int tamanoBuf, GUIFrame gui) {
        this.buffer = new ArrayBlockingQueue<>(tamanoBuf);
        this.gui = gui;
        this.Size = tamanoBuf;
    }
  
    //Funcion sincronizada con Consumer
    synchronized String consume(int id) {
        String product; 
        while (this.buffer.isEmpty()) {
            try {
                wait();
            } catch (InterruptedException ex) {
                Logger.getLogger(Buffer.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        //Checar
        product = this.buffer.remove();
        
        //Retorno del producto
        String result = PrefixEvaluation(product);
        //Setear la tabla de Producer
        gui.TareasRealizadas(id, product, result);
        
        Buffer.print("Consumer consumed: " + product);
        notify();
        return product;
    }
    //Funcion sincronizada con Producer
    synchronized void produce(String product, int id) {
        while (this.buffer.remainingCapacity() == 0) {
            try {
                wait();
            } catch (InterruptedException ex) {
                Logger.getLogger(Buffer.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        this.buffer.add(product);
        //Setear la tabla de Producer
        gui.addTareaPorHacer(id, product);
        print("Producer produced: " + product);
        notify();
    }
    //Funcion para evaluar la operacion de Scheme
    static String PrefixEvaluation(String product) {
        String[] Caracteres = product.substring(1, product.length() - 1).split(" ");
        char operations = Caracteres[0].charAt(0);
        
        double num1 = Double.parseDouble(Caracteres[1]);
        double num2 = Double.parseDouble(Caracteres[2]);
        
        double r = 0.0;
        
        switch (operations) {
            case '+':
                r = num1 + num2;
                break;
            case '-':
                r = num1 - num2;
                break;
            case '*':
                r = num1 * num2;
                break;
            case '/':
                if (num2 == 0) {
                    return("Cannot divide by zero");
                }
                r = num1 / num2;
                break;
        }  

        return String.valueOf(r);
    }
    //Contador de procesos
    static int count = 1;
    
    synchronized static void print(String string) {
        System.out.print(count++ + " ");
        System.out.println(string);
    } 
    //Resetear procesos a 1
    public void Restart(){
        count = 1;
        gui.ContadorTareasR();
    }
    
}
