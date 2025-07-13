package algorithm.highfrequency;
// 实现LRU结沟
public class Videos_035_2_LRU {
    public static class DoubleNode{
        public int key;
        public double value;
        public DoubleNode next;
        public DoubleNode prev;
        public DoubleNode(int key, int value) {
            this.key = key;
            this.value = value;
        }
    }
    public static class DoubleLinkedList{
        public DoubleNode head;
        public DoubleNode tail;
        public DoubleLinkedList(){
            head = null;
            tail = null;
        }
        public void addNode(DoubleNode node){
            if(node == null){
                return;
            }
            if(head == null){
                head = tail = node;
            }
            else{
                tail.next = node;
                node.prev = tail;
                tail = node;
            }
        }
        public void moveNodeToTail(DoubleNode node){
            if(node == tail){
                return;
            }
            if(node == head){

            }
        }
    }
}
