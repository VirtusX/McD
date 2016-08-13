package sample;

import javafx.application.Platform;

import java.util.List;
import java.util.Random;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import static sample.Main.Cooking;

/**
 * Created by x-13 on 09.08.2016.
 */

class Cook extends Thread {
    private final Lock lock = new ReentrantLock();
    SampleController sp;
    String Name;


    public Cook(SampleController _sp, String i) {
        sp = _sp;
        Name = i;
    }

    private void doHamburger(List<String> i) {
        if (lock.tryLock()) {
            i.add("гамбургер");
        }
    }

    private void doSandwich(List<String> i) {
        if (lock.tryLock())
            i.add("чізбургер");
    }

    private void doChips(List<String> i) {
        if (lock.tryLock())
            i.add("картопля фрі");
    }

    @Override
    public void run() {
        Random rand = new Random();
        try {
            Thread.sleep((rand.nextInt(3))*1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        while (true) {
            while (sp.alive) {
                while (Cooking.size() < 30 && sp.alive) {
                    int n = 0;
                    while (n < 9) {
                        for (int i = 0; i < 3; i++) {
                            switch (i) {
                                case 0:
                                    this.doHamburger(Cooking);
                                    break;
                                case 1:
                                    this.doChips(Cooking);
                                    break;
                                case 2:
                                    this.doSandwich(Cooking);
                                    break;
                            }
                            n++;
                            Platform.runLater(() -> sp.Cooks.setText("Хавки зроблено: " + Cooking.size()));
                            try {
                                Thread.sleep(rand.nextInt(7) * 1000);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                    try {
                        Platform.runLater(() -> sp.CooksEvent.setText("Кухарка " + Name + " заїбалась\n та пішла покурити"));
                        Thread.sleep(20000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    Platform.runLater(() -> sp.CooksEvent.setText("Кухарка " + Name + " повернулась"));
                }
            }
        }
    }
}