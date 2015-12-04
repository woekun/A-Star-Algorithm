/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package astaralgorithm;

import astaralgorithm.Main.CellPane;
import java.awt.Color;
import static java.lang.Math.abs;
import static java.lang.Math.hypot;
import java.util.ArrayList;
import java.util.List;
import javax.swing.Timer;

/**
 *
 * @author HYPPY
 */
public final class AstarAlgorithm {

    private final int[][] A;
    public final List<Node> list;
    public final List<Node> trace;
    private final List<Node> road;
    private Node start;
    private Node goal;
    private Timer timer;

    public AstarAlgorithm(int[][] A) {
        this.A = A;
        this.road = new ArrayList<>();
        this.list = new ArrayList<>();
        this.trace = new ArrayList<>();
        this.start = search_StartNode();
        this.goal = search_GoalNode();
        searchNode(start);
    }
    
    public List<Node> getRoad(){
        return road;
    }
    
    public Node getGoal() {
        return goal;
    }
    
    public void setTimer(Timer timer){
        this.timer = timer;
    }
    
    public Timer getTimer(){
        return timer;
    }

    public Node search_GoalNode() {
        for (int i = 0; i < 20; i++) {
            for (int j = 0; j < 20; j++) {
                if (A[i][j] == 3) {// if color is green
                    goal = new Node(i, j);
                }
            }
        }
        return goal;
    }

    public Node search_StartNode() {
        for (int i = 0; i < 20; i++) {
            for (int j = 0; j < 20; j++) {
                if (A[i][j] == 2) { // if color is red
                    start = new Node(i, j);
                }
            }
        }
        return start;
    }

    public void createTrace(CellPane[][] cellPane) {
        while (!list.isEmpty()) {
            Node target = list.remove(0);
            cellPane[target.getX()][target.getY()].setBackground(Color.decode("#FFA3A3"));
            trace.add(target);
            searchNode(target);
        }
    }
    
    

    public void searchNode(Node parrentNode) {
        int x = parrentNode.getX();
        int y = parrentNode.getY();
        boolean check = false;

        for (int i = -1; i <= 1; i++) {
            for (int j = -1; j <= 1; j++) {
                if ((x + i > 0 && x + i < 20) && (y + j > 0 && y + j < 20) 
                 && (A[x + i][y + j] != 1 && checkNode(x + i, y + j) == false)) {
                    if (A[x + i][y + j] == 3) {
                        goal.setParrentNode(parrentNode);
                        System.out.println(""+goal.getParrentNode().getX()+","+goal.getParrentNode().getY());
                        check = true;
                        drawRoad(goal);
                        break;
                    }
                    
                    int mht = abs(goal.getX() - x - i) + abs(goal.getY() - y - j);
                    float ds = (float) (parrentNode.getDistance()
                            + hypot(abs(x + i - parrentNode.getX()), abs(y + j - parrentNode.getY())));
                    Node node = new Node(parrentNode, mht, ds, x + i, y + j);
                    
                    if (A[node.getX()][node.getParrentNode().getY()] != 1 
                            || A[node.getParrentNode().getX()][node.getY()] != 1) {
                        list.add(node);
                    }
                }
            }
            if (check == true) {
                list.clear();
                break;
            }
        }
        sortAscending(list);
    }

    public boolean checkNode(int i, int j) {
        boolean c = false;
        if (!list.isEmpty()) 
            for (Node list1 : list) 
                if (list1.getX() == i && list1.getY() == j) 
                    c = true;

        if (!trace.isEmpty()) 
            for (Node trace1 : trace)
                if (trace1.getX() == i && trace1.getY() == j) 
                    c = true;

        if (start.getX() == i && start.getY() == j) 
            c = true;
        
        return c;
    }

    public void sortAscending(List<Node> list) {
        if (!list.isEmpty()) {
            for (int i = 0; i < list.size(); i++) {
                for (int j = i + 1; j < list.size(); j++) {
                    if (list.get(i).getDistance() + list.get(i).getManhattan()
                            > list.get(j).getDistance() + list.get(j).getManhattan()) {
                        Node temp = list.get(j);
                        list.set(j, list.get(i));
                        list.set(i, temp);
                    }
                }
            }
        }
    }

    public void getRoad(Node node) {
        Node temp = node.getParrentNode();
        int i = start.getX();
        int j = start.getY();
        if(temp.getX() != i && temp.getY() != j){
            road.add(temp);
            getRoad(temp.getParrentNode());
        }
    }
    
    public void drawRoad(Node node){
        getRoad(node);
        road.stream().forEach((road1) -> {
            System.out.println("" + road1.getX() + "," + road1.getY());
        });
        road.clear();  
    }
}
