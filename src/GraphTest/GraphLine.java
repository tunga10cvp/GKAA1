package GraphTest;

import java.util.Objects;

public class GraphLine {

    private String nodeSource;
    private String nodeTarget;
    private boolean isDirected;
    private Integer weight;
    private String name = "";

    public String getNodeSource(){
        return nodeSource;
    }

    public void setNodeSource(String nodeSourcen){
        this.nodeSource = nodeSource;
    }

    public String getNodeTarget(){
        return nodeTarget;
    }

    public void setNodeTarget(String nodeSourcen){
        this.nodeTarget = nodeTarget;
    }

    public boolean isDirected(){
        return isDirected;
    }

    public void setDirected(String isDirected){
        this.isDirected = isDirected;
    }

    public Integer getWeight(){
        return weight;
    }

    public void setWeight(Integer weight){
        this.weight = weight;
    }

    public String getName(){
        return name;
    }

    public void setName(String name){
        this.name = name;
    }

    public boolean SingleNode(){
        return nodeTarget == null;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder(nodeSource);
        sb.append(nodeSource);
        if(isDirected){
            sb.append("->")
        }
        else {
            sb.append("--");
        }
        sb.append(nodeTarget + ";");
        sb.append("w =");
        sb.append(weight);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof GraphLine)) return false;
        GraphLine GraphLine = (GraphLine) o;
        return isDirected == GraphLine.isDirected &&
                Objects.equals(nodeSource, GraphLine.nodeSource) &&
                Objects.equals(nodeTarget, GraphLine.nodeTarget) &&
                Objects.equals(weight, GraphLine.weight) &&
                Objects.equals(name, GraphLine.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nodeSource, nodeTarget, isDirected, weight, name);
    }
}
