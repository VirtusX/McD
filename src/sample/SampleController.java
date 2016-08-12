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
    private Cook doCook1 = new Cook(this, "баба Люся");
    private Cook doCook2 = new Cook(this, "Павло Зібров");
    private Client doOrder1 = new Client(this);
    private Client doOrder2 = new Client(this);
    private Client doOrder3 = new Client(this);
    private Cashier doCash = new Cashier(this);
    private boolean Pause = false;
    private String returnOrder = null;

    public void orderAction(ActionEvent actionEvent) {
        doCash.run();
        takeStats.setVisible(true);
    }

    public void start(ActionEvent actionEvent) throws InterruptedException {
        action.setText("Починай вже працювати, лінива задниця");
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
            action.setText(returnOrder);
            Pause = false;
            takeOrders.setDisable(false);
            doCook1.alive = true;
            doCook2.alive = true;
            doOrder1.alive = true;
            doOrder2.alive = true;
            doOrder3.alive = true;
        }
        else  {
            returnOrder = action.getText();
            action.setText("Оброблено замовлень: " + doCash.i + ", зароблено бабок: " + doCash.money + " гривень");
            Pause = true;
            takeStats.setText("Продовжити працювати");
            takeOrders.setDisable(true);
            doCook1.alive = false;
            doCook2.alive = false;
            doOrder1.alive = false;
            doOrder2.alive = false;
            doOrder3.alive = false;
        }

    }
}