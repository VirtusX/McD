package sample;

import static sample.Main.Cooking;
import static sample.Main.order;

/**
 * Created by x-13 on 10.08.2016.
 */
public class Cashier {

    int i = 0;
    int money = 0;
    SampleController sp;

    public Cashier(SampleController _sp)
    {
        sp = _sp;
    }


    public synchronized String run() {
        while (order.isEmpty()) {
        }
        while (!Cooking.contains(order.get(0))) {
        }
        i++;
        String ordered = order.get(0);
        double price = 00;
        switch (ordered){
            case "гамбургер":
                price = 10.00;
                break;
            case "чізбургер":
                price = 12.00;
                break;
            case "картопля фрі":
                price = 9.00;
                break;
        }
        sp.action.setText("Замовлення №"+i+", "+ ordered+ ", виконано.\n З вас " +price+ " гривень. Ідіть нахуй");
        Cooking.remove(order.get(0));
        order.remove(0);
        money+=price;
        return "Замовлення №"+i+", "+ ordered+ ", виконано. З вас " +price+ " гривень. Ідіть нахуй";
    }
}


