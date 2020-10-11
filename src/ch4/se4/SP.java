package ch4.se4;

public interface SP {

    public boolean hasPathTo(int v);

    public double distTo(int v);

    public Iterable<DirectedEdge> pathTo(int v);
}
