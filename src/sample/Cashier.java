package sample;

import javafx.application.Platform;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import static sample.Main.Cooking;
import static sample.Main.order;

class Cashier extends Thread {


    private SampleController sp;

    private int nomer = 0;
    private final Lock lock = new ReentrantLock();
    Cashier(SampleController _sp, int num)
    {
        sp = _sp;
        nomer = num;
    }


    synchronized void Manual() throws InterruptedException {
        sp.takeOrders.setDisable(true);
        Thread.sleep(300);
        Platform.runLater(() -> {
            if (lock.tryLock()) {while (order.isEmpty() || Cooking.isEmpty()) {
                sp.action.setText("Wait for orders!");}
                while (!Cooking.contains(order.get(0))) {
                    sp.action.setText("We have not cooked it yet!");}
            }});
        if (lock.tryLock())
            sp.i++;
        String ordered = order.get(0);
        double price = 0;
        switch (ordered){
            case "hamburger":
                price = 10.00;
                break;
            case "cheeseburger":
                price = 12.00;
                break;
            case "french fries":
                price = 9.00;
                break;
            case "mcNuggets":
                price = 11.00;
                break;
            case "muffin":
                price = 20.00;
                break;
        }
        sp.action.setText("Orders №" + sp.i + ", " + ordered + ", executed.\nYou have to pay " + price + " $. Have a nice day");
        Cooking.remove(order.get(0));
        order.remove(0);
        sp.money+=price;
        sp.takeOrders.setDisable(false);
    }

    public void run() {
        while (!currentThread().isInterrupted()) {
            System.out.print("Cashier" + this.toString()+ "alive is " + sp.alive+"\n");
            while (sp.alive) {
                try {
                    sleep(nomer*2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                while (sp.alive) {
                    while (order.isEmpty() || Cooking.isEmpty()) {
                    }
                    while (!Cooking.contains(order.get(0))) {
                    }
                    sp.i++;
                    Platform.runLater(() -> {
                        String ordered = order.get(0);
                        double price = 0;
                        switch (ordered) {
                            case "hamburger":
                                price = 10.00;
                                break;
                            case "cheeseburger":
                                price = 12.00;
                                break;
                            case "french fries":
                                price = 9.00;
                                break;
                            case "mcNuggets":
                                price = 11.00;
                                break;
                            case "muffin":
                                price = 20.00;
                                break;
                        }
                        if (nomer == 1)
                            sp.action.setText("Orders №" + sp.i + ", " + ordered + ", executed.\nYou have to pay " + price + " $. Have a nice day");
                        else
                            sp.action1.setText("Orders №" + sp.i + ", " + ordered + ", executed.\nYou have to pay " + price + " $. Have a nice day");
                        if (lock.tryLock()) {
                            Cooking.remove(order.get(0));
                            order.remove(0);
                        }
                        sp.money += price;
                    });
                    try {
                        sleep(5000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
            try {
                sleep(300);
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


