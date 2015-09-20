/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package astaralgorithm;

/**
 *
 * @author HYPPY
 */
public class Node {

    private int manhattan;
    private float distance;
    private int x;
    private int y;
    private Node parrentNode;

    public Node(){
        this.parrentNode = null;
        this.manhattan =0;
        this.distance = 0;
        this.x = 0;
        this.y = 0;
    }

    public Node(int x, int y) {
        this.parrentNode = null;
        this.manhattan = 0;
        this.distance = 0;
        this.x = x;
        this.y = y;
    }

    public Node(Node parrentNode, int manhattan, float distance, int x, int y) {
        this.parrentNode = parrentNode;
        this.manhattan = manhattan;
        this.distance = distance;
        this.x = x;
        this.y = y;
    }

    public void setParrentNode(Node parrentNode) {
        this.parrentNode = parrentNode;
    }
    
    public Node getParrentNode() {
        return parrentNode;
    }
    
    public void setManhattan(int manhattan) {
        this.manhattan = manhattan;
    }

    public int getManhattan() {
        return manhattan;
    }

    public void setDistance(float distance) {
        this.distance = distance;
    }

    public float getDistance() {
        return distance;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getX() {
        return x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getY() {
        return y;
    }

}
