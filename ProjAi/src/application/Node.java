package application;

class Node {
    private int Des;
    private int cost;
    private String SourceN;
    private String DesN;
    private Node parent;
    private int f;
    private int walkCost;

    Node(String SourceN, Node parent) {
        this.SourceN = SourceN;
        this.parent = parent;
        f = 0;
    }

    Node(String SourceN, Node parent, int cost) {
        this(SourceN, parent);
        this.cost = cost;
    }

    Node(int Des, int cost,int walkCost, String SourceN, String DesN) {
        this.Des = Des;
        this.cost = cost; //g(n)
        this.walkCost=walkCost;
        this.SourceN = SourceN;
        this.DesN = DesN;
        f = 0;
    }


    public int getDes() {
        return Des;
    }

    public void setDes(int des) {
        Des = des;
    }

    public int getwalkCost() {
        return walkCost;
    }

    public void setwalkCost(int walkCost) {
        this.walkCost = walkCost;
    }

    public void setSourceN(String sourceN) {
        SourceN = sourceN;
    }

    public void setDesN(String desN) {
        DesN = desN;
    }

    public int getF() {
        return f;
    }

    public int getCost() {
        return cost;
    }

    public String getSourceN() {
        return SourceN;
    }

    public String getDesN() {
        return DesN;
    }

    public Node getParent() {
        return parent;
    }

    public void setF(int f) {
        this.f = f;
    }

    public void setCost(int cost) {
        this.cost = cost;
    }

    public void setParent(Node parent) {
        this.parent = parent;
    }

    public String DesString() {
        return "Node [Des=" + Des + ", cost=" + cost + ", SourceN=" + SourceN + ", DesN=" + DesN + "]";
    }
}