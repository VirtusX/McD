package sample;

import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;


public class SampleController {

    public Button cashierStartButton;
    public Button takeStats;
    public Button managerButton;
    public Button takeOrders;

    public Label startMessage;
    public Label cashierWorkEvent1;
    public Label cashierWorkEvent2;
    public Label newOrderEvent;
    public Label countOrdersEvent;
    public Label cookingEvent;
    public Label cooksRestEvent;

    boolean alive = true;
    int orderNumber = 0;
    int money = 0;
    private boolean automate = false;
    private boolean Pause = false;
    private Cook doCook1 = new Cook(this, "Cook №1",1);
    private Cook doCook2 = new Cook(this, "Cook №2",2);
    private Client doOrder1 = new Client(this);
    private Client doOrder2 = new Client(this);
    private Client doOrder3 = new Client(this);
    private Cashier doCash1 = new Cashier(this, 1);
    private Cashier doCash2 = new Cashier(this, 2);
    private String returnOrder = null;

    public void orderAction(ActionEvent actionEvent) throws InterruptedException {
        takeOrders.setText("Take ordersQueue");
        doCash1.TakeOrder();
        takeStats.setVisible(true);
        managerButton.setVisible(false);
    }

    public void start(ActionEvent actionEvent) throws InterruptedException {
        cashierWorkEvent1.setText("Choose your path");
        managerButton.setVisible(true);
        startMessage.setVisible(false);
        cashierStartButton.setVisible(false);
        takeOrders.setVisible(true);
        doCook1.start();
        doCook2.start();
        doOrder1.start();
        doOrder2.start();
        doOrder3.start();
    }

    public void Stats(ActionEvent actionEvent) throws InterruptedException {
        if (Pause) {
            takeStats.setText("Calculate income");
            if (!automate) {
                cashierWorkEvent1.setText(returnOrder);
            }
            Pause = false;
            takeOrders.setDisable(false);
            alive = true;
        }
        else  {
            returnOrder = cashierWorkEvent1.getText();
            cashierWorkEvent1.setText("Proceeded orders: " + orderNumber + ", received money: " + money + " $");
            if(automate) {
                cashierWorkEvent2.setText("");
            }
            Pause = true;
            takeStats.setText("Continue to work");
            takeOrders.setDisable(true);
            alive = false;
        }

    }

    public void auto(ActionEvent actionEvent) {
        takeOrders.setVisible(false);
        takeStats.setVisible(true);
        managerButton.setVisible(false);
        doCash1.start();
        doCash2.start();
        automate = true;
    }
}
