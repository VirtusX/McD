package sample;

import javafx.application.Platform;

import java.util.List;
import java.util.Random;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import static sample.Main.order;

/**
 * Created by x-13 on 10.08.2016.
 */
class Client extends Thread {

    SampleController sp;


    Client(SampleController _sp)
    {
        sp = _sp;
    }

    private final Lock lock = new ReentrantLock();

    private void orderHamburger(List<String> i) {
        Platform.runLater(() -> {
            if (lock.tryLock()) {
                i.add("гамбургер");
                sp.Orders.setText("Замовлено гамбургер");
            }
        });
    }

    private void orderSandwich(List<String> i) {
        Platform.runLater(() -> {
        if (lock.tryLock()) {
            i.add("чізбургер");
            sp.Orders.setText("Замовлено чізбургер");
        }});
    }

    private void orderFry(List<String> i) {
        Platform.runLater(() -> {
        if (lock.tryLock()) {
            i.add("картопля фрі");
            sp.Orders.setText("Замовлена картопля фрі");
        }});
    }

    private void orderNuggets(List<String> i) {
        Platform.runLater(() -> {
            if (lock.tryLock()) {
                i.add("макНагетси");
                sp.Orders.setText("Замовлені МакНагетси");
            }});
    }

    private void orderMuffin(List<String>i){
        Platform.runLater(() -> {
            if (lock.tryLock()) {
                i.add("мафін");
                sp.Orders.setText("Замовлено мафін");
            }});
    }

        public void run() {
            while (true) {
                System.out.print("Client" + this.toString()+ "alive is " + sp.alive+"\n");
                while (sp.alive) {
                    Random rand = new Random();
                    try {
                        Thread.sleep((rand.nextInt(3))*1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    while (sp.alive) {
                        switch (rand.nextInt(5)) {
                            case 0:
                                this.orderHamburger(order);
                                break;
                            case 1:
                                this.orderSandwich(order);
                                break;
                            case 2:
                                this.orderFry(order);
                                break;
                            case 3:
                                this.orderNuggets(order);
                                break;
                            case 4:
                                this.orderMuffin(order);
                                break;
                        }
                        Platform.runLater(() -> {
                            if (order.size() < 10)
                                sp.OrdersQueue.setText("В черзі " + order.size() + " заказів");
                            else
                                sp.OrdersQueue.setText("В черзі вже хєрова туча заказів!\n Їх вже аж " + order.size() + "! Ану піздуй працювать!");
                        });
                        try {
                            Thread.sleep(4000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
                try {
                    Thread.sleep(300);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
}
