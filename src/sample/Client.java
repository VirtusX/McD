package sample;

import javafx.application.Platform;

import java.util.List;
import java.util.Random;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import static sample.Main.order;

class Client extends Thread {

    private SampleController sp;


    Client(SampleController _sp)
    {
        sp = _sp;
    }

    private final Lock lock = new ReentrantLock();

    private void orderHamburger(List<String> i) {
        Platform.runLater(() -> {
            if (lock.tryLock()) {
                i.add("hamburger");
                sp.Orders.setText("Hamburger ordered");
            }
        });
    }

    private void orderSandwich(List<String> i) {
        Platform.runLater(() -> {
            if (lock.tryLock()) {
                i.add("cheeseburger");
                sp.Orders.setText("Cheeseburger ordered");
            }});
    }

    private void orderFry(List<String> i) {
        Platform.runLater(() -> {
            if (lock.tryLock()) {
                i.add("french fries");
                sp.Orders.setText("French fries\nordered");
            }});
    }

    private void orderNuggets(List<String> i) {
        Platform.runLater(() -> {
            if (lock.tryLock()) {
                i.add("mcNuggets");
                sp.Orders.setText("McNuggets ordered");
            }});
    }

    private void orderMuffin(List<String>i){
        Platform.runLater(() -> {
            if (lock.tryLock()) {
                i.add("muffin");
                sp.Orders.setText("Muffin ordered");
            }});
    }

    public void run() {
        while (!currentThread().isInterrupted()) {
            System.out.print("Client" + this.toString() + "alive is " + sp.alive + "\n");
            while (sp.alive) {
                Random rand = new Random();
                try {
                    Thread.sleep((rand.nextInt(3)) * 1000);
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
                            sp.OrdersQueue.setText("The queue of " + order.size() + " orders");
                        else
                            sp.OrdersQueue.setText("Too many orders in the queue!\nAs many as " + order.size() + "! Get to work!");
                    });
                    try {
                        Thread.sleep(7000);
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
        try {
            Thread.sleep(2);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}