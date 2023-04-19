public class Tree {
    private Node root;

    
    private class Node {
        private int value;
        private Color color; 
        private Node leftChild;
        private Node rightChild;
    }

    private enum Color {Red, Black}


    // Вывод списка в консоль 

    private void print(Node node) {
        if (node.leftChild != null) {
            if (node.leftChild.leftChild == null) {
                System.out.printf("%d ", node.leftChild.value);
                System.out.printf("%d ", node.value);
                if (node.rightChild != null) {
                    if (node.rightChild.leftChild != null){
                        print(node.rightChild);
                    } else {
                        System.out.printf("%d ", node.rightChild.value);
                    }
                }
            } else {
                print(node.leftChild);
                System.out.printf("%d ", node.value);
                print(node.rightChild);
            }
        }
    }


    public void print() {
        System.out.print("Текущий массив: ");
        print(root);
        System.out.println();
    }

    // Добавление новых элементов в список

    private void add(int value, Node node) {
        if (value > node.value) {
            if (node.rightChild == null) {
                node.rightChild = new Node();
                node.rightChild.color = Color.Red;
                node.rightChild.value = value;
            } else {
                add(value, node.rightChild);
                node.rightChild = rebalance(node.rightChild);
            }
        } else if (value < node.value) {
            if (node.leftChild == null){
                node.leftChild = new Node();
                node.leftChild.color = Color.Red;
                node.leftChild.value = value;
            } else {
                add(value, node.leftChild);
                node.leftChild = rebalance(node.leftChild);
            }
        } else {
            return;
        }
    }


    public void add(int value) {
        if (root == null) {
            root = new Node();
            root.value = value;
            root.color = Color.Black;
        } else {
            add(value, root);
            root = rebalance(root);
            root.color = Color.Black;
        }
    }


    private Node rebalance(Node node) {
        Node result = node;
        Boolean needRebalance;
        do {
            needRebalance = false;
            if (result.rightChild != null && result.rightChild.color == Color.Red &&
                (result.leftChild == null || result.leftChild.color == Color.Black)) {
                    needRebalance = true;
                    result = turnRight(result);
            }
            if (result.leftChild != null && result.leftChild.color == Color.Red &&
                result.leftChild.leftChild != null && result.leftChild.leftChild.color == Color.Red) {
                    needRebalance = true;
                    result = turnLeft(result);
            }
            if (result.leftChild != null && result.leftChild.color == Color.Red &&
                result.rightChild != null && result.rightChild.color == Color.Red) {
                    needRebalance = true;
                    colorSwap(result);
            }
        } while (needRebalance);
        return result;
    }


    private Node turnRight(Node node) {
        Node rightChild = node.rightChild;
        Node beetwenChild = rightChild.leftChild;
        rightChild.leftChild = node;
        node.rightChild = beetwenChild;
        rightChild.color = node.color;
        node.color = Color.Red;
        return rightChild;
    }


    private Node turnLeft(Node node) {
        Node leftChild = node.leftChild;
        Node beetwenChild = leftChild.rightChild;
        leftChild.rightChild = node;
        node.leftChild = beetwenChild;
        leftChild.color = node.color;
        node.color = Color.Red;
        return leftChild;
    }


    private void colorSwap(Node node) {
        node.leftChild.color = Color.Black;
        node.rightChild.color = Color.Black;
        node.color = Color.Red;
    }



}
