package ch4.test;

public interface DigraphInterface {

    int vertices();
    int edges();
    Iterable<Integer> adjacent(int vertex);
    DigraphInterface reverse();

}