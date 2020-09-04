package ch4.test;

public interface GraphInterface {
    int vertices();
    int edges();
    Iterable<Integer> adjacent(int vertex);
}