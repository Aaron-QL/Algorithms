package test;

import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;


public class Trade {
    private static int crowdNum = 100;
    private static int dealNum = 100000;
    private static int initID = 1;
    private static double initMoney = 100;
    private static double dealMoney = 6;


    public static void main(String[] args) {
        Person[] crowd = new Person[crowdNum];
        for (int i = 0; i < crowdNum; i++) {
            crowd[i] = new Person(initID++, initMoney);
        }

        for (int i = 0; i < dealNum; i++) {
            Person p1 = crowd[StdRandom.uniform(0, crowdNum - 1)];
            Person p2 = crowd[StdRandom.uniform(0, crowdNum - 1)];
            if (!p1.deal(p2, dealMoney)) {
//                StdOut.printf("第%d轮交易：%d 破产了\n", i+1, p1.id);
            }
        }


        for (int i = 0; i < crowdNum; i++) {
            Person p = crowd[i];
            if (p.money < 11) {
//                StdOut.printf("%d 破产了\n", p.id);
            }
            if (p.money > 250) {
                StdOut.printf("%d: %.1f\n", p.id, p.money);
            }
        }
    }
}
