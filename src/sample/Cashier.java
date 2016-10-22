package sample;

import javafx.application.Platform;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import static sample.Main.foodQueue;
import static sample.Main.ordersQueue;

class Cashier extends Thread {

    private static final String ORDER_DONE = "Order №%d, %s, executed.\nYou have to pay %s $. Have a nice day";
    private final Lock lock = new ReentrantLock();
    private SampleController sp;
    private int number;

    Cashier(SampleController _sp, int number)
    {
        sp = _sp;
        this.number = number;
    }

    synchronized void TakeOrder() throws InterruptedException {
        sp.takeOrders.setDisable(true);
        Thread.sleep(300);
        Platform.runLater(() -> {
            if (lock.tryLock()) {
                while (ordersQueue.isEmpty() || foodQueue.isEmpty()) {
                    sp.cashierWorkEvent1.setText("Wait for orders!");
                }
                while (!foodQueue.contains(ordersQueue.get(0))) {
                    sp.cashierWorkEvent1.setText("We have not cooked it yet!");
                }
            }});
        if (lock.tryLock())
            sp.orderNumber++;
        String ordered = ordersQueue.get(0);
        double price = pay(ordered);
        sp.cashierWorkEvent1.setText(String.format(ORDER_DONE, sp.orderNumber, ordered, price));
        foodQueue.remove(ordersQueue.get(0));
        ordersQueue.remove(0);
        sp.money+=price;
        sp.takeOrders.setDisable(false);
    }

    public void run() {
        while (!currentThread().isInterrupted()) {
            System.out.print("Cashier" + this.toString()+ "alive is " + sp.alive+"\n");
            while (sp.alive) {
                try {
                    sleep(number * 2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                while (sp.alive) {
                    while (ordersQueue.isEmpty() || foodQueue.isEmpty()) {
                    }
                    while (!foodQueue.contains(ordersQueue.get(0))) {
                    }
                    sp.orderNumber++;
                    Platform.runLater(() -> {
                        String ordered = ordersQueue.get(0);
                        double price = pay(ordered);
                        if (number == 1)
                            sp.cashierWorkEvent1.setText(String.format(ORDER_DONE, sp.orderNumber, ordered, price));
                        else
                            sp.cashierWorkEvent2.setText(String.format(ORDER_DONE, sp.orderNumber, ordered, price));
                        if (lock.tryLock()) {
                            foodQueue.remove(ordersQueue.get(0));
                            ordersQueue.remove(0);
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

    private double pay(String ordered) {
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
        return price;
    }
}


