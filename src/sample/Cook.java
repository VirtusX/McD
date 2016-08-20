package sample;

import javafx.application.Platform;

import java.util.List;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import static sample.Main.Cooking;

class Cook extends Thread {
    private final Lock lock = new ReentrantLock();
    private SampleController sp;
    private String Name;
    private int number = 0;

    Cook(SampleController _sp, String i, int n) {
        sp = _sp;
        Name = i;
        number = n;
    }

    private void doHamburger(List<String> i) {
        if (lock.tryLock()) {
            i.add("hamburger");
        }
    }

    private void doSandwich(List<String> i) {
        if (lock.tryLock())
            i.add("cheeseburger");
    }

    private void doFry(List<String> i) {
        if (lock.tryLock())
            i.add("french fries");
    }

    private void doNuggets(List<String> i) {
        if (lock.tryLock())
            i.add("mcNuggets");
    }

    private void doMuffin(List<String> i) {
        if (lock.tryLock())
            i.add("muffin");
    }

    @Override
    public void run() {
        int n = 0;
        while (!currentThread().isInterrupted()) {
            //System.out.print("Cook" + this.toString()+ "alive is " + sp.alive+"\n");
            while (sp.alive) {
                try {
                    Thread.sleep(number * 2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                while (sp.alive) {
                    if (number == 1) {
                        for (int i = 0; i < 2; i++) {
                            while (!sp.alive) {
                            }
                            switch (i) {
                                case 0:
                                    this.doHamburger(Cooking);
                                    n++;
                                    System.out.print(Name+" "+n+"\n");
                                    Platform.runLater(() -> sp.Cooks.setText("Made food: " + Cooking.size()));
                                    try {
                                        Thread.sleep(3000);
                                    } catch (InterruptedException e) {
                                        e.printStackTrace();
                                    }
                                    if (n > 7) {
                                        try {
                                            Platform.runLater(() -> sp.CooksEvent.setText(Name + " went to the break"));
                                            Thread.sleep(20000);
                                        } catch (InterruptedException e) {
                                            e.printStackTrace();
                                        }
                                        Platform.runLater(() -> sp.CooksEvent.setText(Name + " returned"));
                                        n = 0;
                                    }
                                    break;
                                case 1:
                                    this.doFry(Cooking);
                                    n++;
                                    System.out.print(Name+" "+n+"\n");
                                    Platform.runLater(() -> sp.Cooks.setText("Made food: " + Cooking.size()));
                                    try {
                                        Thread.sleep(3000);
                                    } catch (InterruptedException e) {
                                        e.printStackTrace();
                                    }
                                    if (n > 7) {
                                        try {
                                            Platform.runLater(() -> sp.CooksEvent.setText(Name + " went to the break"));
                                            Thread.sleep(20000);
                                        } catch (InterruptedException e) {
                                            e.printStackTrace();
                                        }
                                        Platform.runLater(() -> sp.CooksEvent.setText(Name + " returned"));
                                        n = 0;
                                    }
                                    break;
                            }
                        }
                    } else if (number == 2) {
                        for (int i = 0; i < 3; i++) {
                            while (!sp.alive) {
                            }
                            switch (i) {
                                case 0:
                                    this.doSandwich(Cooking);
                                    n++;
                                    System.out.print(Name + " " + n + "\n");
                                    Platform.runLater(() -> sp.Cooks.setText("Made food: " + Cooking.size()));
                                    try {
                                        Thread.sleep(3000);
                                    } catch (InterruptedException e) {
                                        e.printStackTrace();
                                    }
                                    if (n > 7) {
                                        try {
                                            Platform.runLater(() -> sp.CooksEvent.setText(Name + " went to the break"));
                                            Thread.sleep(20000);
                                        } catch (InterruptedException e) {
                                            e.printStackTrace();
                                        }
                                        Platform.runLater(() -> sp.CooksEvent.setText(Name + " returned"));
                                        n = 0;
                                    }
                                    break;
                                case 1:
                                    this.doNuggets(Cooking);
                                    n++;
                                    System.out.print(Name + " " + n + "\n");
                                    Platform.runLater(() -> sp.Cooks.setText("Made food: " + Cooking.size()));
                                    try {
                                        Thread.sleep(3000);
                                    } catch (InterruptedException e) {
                                        e.printStackTrace();
                                    }
                                    if (n > 7) {
                                        try {
                                            Platform.runLater(() -> sp.CooksEvent.setText(Name + " went to the break"));
                                            Thread.sleep(20000);
                                        } catch (InterruptedException e) {
                                            e.printStackTrace();
                                        }
                                        Platform.runLater(() -> sp.CooksEvent.setText(Name + " returned"));
                                        n = 0;
                                    }
                                    break;
                                case 2:
                                    this.doMuffin(Cooking);
                                    n++;
                                    System.out.print(Name + " " + n + "\n");
                                    Platform.runLater(() -> sp.Cooks.setText("Made food: " + Cooking.size()));
                                    try {
                                        Thread.sleep(3000);
                                    } catch (InterruptedException e) {
                                        e.printStackTrace();
                                    }
                                    if (n > 7) {
                                        try {
                                            Platform.runLater(() -> sp.CooksEvent.setText(Name + " went to the break"));
                                            Thread.sleep(20000);
                                        } catch (InterruptedException e) {
                                            e.printStackTrace();
                                        }
                                        Platform.runLater(() -> sp.CooksEvent.setText(Name + " returned"));
                                        n = 0;
                                    }
                                    break;
                            }
                        }
                    }
                }
            }
            try {
                Thread.sleep(2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
