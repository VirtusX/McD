package sample;

import javafx.application.Platform;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import static sample.Main.Cooking;
import static sample.Main.order;

/**
 * Created by x-13 on 10.08.2016.
 */
public class Cashier extends Thread {


    SampleController sp;

    private int nomer = 0;
    private final Lock lock = new ReentrantLock();
    public Cashier(SampleController _sp, int num)
    {
        sp = _sp;
        nomer = num;
    }


    public synchronized String Manual() {
        while (order.isEmpty()) {
        }
        while (!Cooking.contains(order.get(0))) {
        }
        sp.i++;
        String ordered = order.get(0);
        double price = 00;
        switch (ordered){
            case "гамбургер":
                price = 10.00;
                break;
            case "чізбургер":
                price = 12.00;
                break;
            case "картопля фрі":
                price = 9.00;
                break;
        }
        sp.action.setText("Замовлення №" + sp.i + ", " + ordered + ", виконано.\n З вас " + price + " гривень. Ідіть нахуй");
        Cooking.remove(order.get(0));
        order.remove(0);
        sp.money+=price;
        return "Замовлення №"+sp.i+", "+ ordered+ ", виконано. З вас " +price+ " гривень. Ідіть нахуй";
    }

    public void run() {
        try {
            Thread.sleep(nomer*2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        while (true) {
            while (sp.alive) {
                while (order.isEmpty()) {
                }
                while (!Cooking.contains(order.get(0))) {
                }
                sp.i++;
                Platform.runLater(() -> {
                    String ordered = order.get(0);
                    double price = 00;
                    switch (ordered) {
                        case "гамбургер":
                            price = 10.00;
                            break;
                        case "чізбургер":
                            price = 12.00;
                            break;
                        case "картопля фрі":
                            price = 9.00;
                            break;
                    }
                    if (nomer == 1)
                        sp.action.setText("Замовлення №"+sp.i+", "+ ordered+ ", виконано.\n З вас " +price+ " гривень. Ідіть нахуй");
                    else
                        sp.action1.setText("Замовлення №"+sp.i+", "+ ordered+ ", виконано.\n З вас " +price+ " гривень. Ідіть нахуй");
                    if (lock.tryLock()) {
                        Cooking.remove(order.get(0));
                        order.remove(0);}
                    sp.money += price;
                });
                try {
                    Thread.sleep(4000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}


