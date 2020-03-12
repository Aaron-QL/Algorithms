package test;

public class Person implements Comparable<Person>{

    public int id;
    public double money;

    Person(int id, double money)
    {
        this.id = id;
        this.money = money;
    }

    public boolean deal(Person that, double money)
    {
        if (this.money < money || that.money < 11) {
            return false;
        }
        this.money -= money;
        that.money += money;
        return true;
    }

    @Override
    public int compareTo(Person o) {
        return Double.compare(this.money, o.money);
    }
}
