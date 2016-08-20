package sample;

import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;


public class SampleController {

    public Button Start;
    public Label action;
    public Button takeOrders;
    public Label Cooks;
    public Label Orders;
    public Label OrdersQueue;
    public Label CooksEvent;
    public Label Hello;
    public Button takeStats;
    public Button auto;
    public Label action1;
    boolean alive = true;
    private boolean automate = false;
    private Cook doCook1 = new Cook(this, "баба Люся",1);
    private Cook doCook2 = new Cook(this, "Павло Зібров",2);
    private Client doOrder1 = new Client(this);
    private Client doOrder2 = new Client(this);
    private Client doOrder3 = new Client(this);
    private Cashier doCash1 = new Cashier(this, 1);
    private Cashier doCash2 = new Cashier(this, 2);
    private boolean Pause = false;
    private String returnOrder = null;

    int i = 0;
    int money = 0;

    public void orderAction(ActionEvent actionEvent) throws InterruptedException {
        doCash1.Manual();
        takeStats.setVisible(true);
        auto.setVisible(false);
    }

    public void start(ActionEvent actionEvent) throws InterruptedException {
        action.setText("То ти будеш вже працювать, чи тіки байдики бити?");
        auto.setVisible(true);
        Hello.setVisible(false);
        Start.setVisible(false);
        takeOrders.setVisible(true);
        doCook1.start();
        doCook2.start();
        doOrder1.start();
        doOrder2.start();
        doOrder3.start();
    }

    public void Stats(ActionEvent actionEvent) throws InterruptedException {
        if (Pause) {
            takeStats.setText("Підрахуй");
            if (!automate) {
                action.setText(returnOrder);
            }
            Pause = false;
            takeOrders.setDisable(false);
            alive = true;
        }
        else  {
            returnOrder = action.getText();
            action.setText("Оброблено замовлень: " + i + ", зароблено бабок: " + money + " гривень");
            if(automate) {
                action1.setText("А скільки за день пороблено хавки я навіть не збираюсь рахувати");
            }
            Pause = true;
            takeStats.setText("Продовжити працювати");
            takeOrders.setDisable(true);
            alive = false;
        }

    }

    public void auto(ActionEvent actionEvent) {
        takeOrders.setVisible(false);
        takeStats.setVisible(true);
        auto.setVisible(false);
        doCash1.start();
        doCash2.start();
        automate = true;
    }
}