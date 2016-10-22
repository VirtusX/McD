package sample;

import javafx.application.Platform;

import java.util.List;
import java.util.Random;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import static sample.Main.ordersQueue;

class Client extends Thread {

    private final Lock lock = new ReentrantLock();
    private SampleController sp;

    Client(SampleController _sp)
    {
        sp = _sp;
    }

    private void orderHamburger(List<String> order) {
        Platform.runLater(() -> {
            if (lock.tryLock()) {
                order.add("hamburger");
                sp.newOrderEvent.setText("Hamburger ordered");
            }
        });
    }

    private void orderSandwich(List<String> order) {
        Platform.runLater(() -> {
            if (lock.tryLock()) {
                order.add("cheeseburger");
                sp.newOrderEvent.setText("Cheeseburger ordered");
            }});
    }

    private void orderFry(List<String> order) {
        Platform.runLater(() -> {
            if (lock.tryLock()) {
                order.add("french fries");
                sp.newOrderEvent.setText("French fries\nordered");
            }});
    }

    private void orderNuggets(List<String> order) {
        Platform.runLater(() -> {
            if (lock.tryLock()) {
                order.add("mcNuggets");
                sp.newOrderEvent.setText("McNuggets ordered");
            }});
    }

    private void orderMuffin(List<String> order) {
        Platform.runLater(() -> {
            if (lock.tryLock()) {
                order.add("muffin");
                sp.newOrderEvent.setText("Muffin ordered");
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
                            this.orderHamburger(ordersQueue);
                            break;
                        case 1:
                            this.orderSandwich(ordersQueue);
                            break;
                        case 2:
                            this.orderFry(ordersQueue);
                            break;
                        case 3:
                            this.orderNuggets(ordersQueue);
                            break;
                        case 4:
                            this.orderMuffin(ordersQueue);
                            break;
                    }
                    Platform.runLater(() -> {
                        if (ordersQueue.size() < 10)
                            sp.countOrdersEvent.setText("The queue of " + ordersQueue.size() + " orders");
                        else
                            sp.countOrdersEvent.setText("Too many orders in the queue!\nAs many as " + ordersQueue.size() + "! Get to work!");
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