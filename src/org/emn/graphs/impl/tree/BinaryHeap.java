package org.emn.graphs.impl.tree;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by pierreg on 03/02/16.
 * Binary heap representation of a binary tree
 */
public class BinaryHeap {

    private List<Integer> values;

    /** Default constructor */
    public BinaryHeap(){
        values = new ArrayList<>();
    }

    /**
     * Insertion d'un element en respectant l'ordre du tas binaire
     * @param n l'identifiant du nouveau noeud
     */
    public void insert(int n){
        values.add(n);
        int indexOfN = values.size() - 1;
        while((indexOfN > 0) && values.get(indexOfN) < values.get(father(indexOfN))) {
            int father = father(indexOfN);
            Collections.swap(values, indexOfN, father);
            indexOfN = father;
        }
    }

    public int father(int i){
        return Math.floorDiv((i-1),2);
    }

    public int leftChildren(int i){
        return 2*i + 1;
    }

    public int rightChildren(int i){
        return 2*i + 2;
    }
}
