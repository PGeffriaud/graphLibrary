package org.emn.graphs.components;

/**
 * Created by pierreg on 03/02/16.
 * Class for Edge Object
 */
public class Arete implements Comparable<Arete> {

    public int from;
    public int to;
    public int weight;

    public Arete(int from, int to, int weight) {
        this.from = from;
        this.to = to;
        this.weight = weight;
    }

    @Override
    public int compareTo(Arete o) {
        return new Integer(weight).compareTo(o.weight);
    }
}
