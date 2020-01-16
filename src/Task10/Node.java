package Task10;

import javax.swing.text.html.HTMLDocument;
import java.util.Iterator;

public class Node{
    private Object data;
    private Node next;
//    private Node prev;


     public Node(Object data) {
         this.data = data;
     }


     public Node(Object data, Node next) {
         this.data = data;
         this.next = next;
     }

     public Node getNext() {
         return next;
     }

     public void setNext(Node next) {
         this.next = next;
     }

     public Object getData() {
         return data;
     }

     public void setData(Object data) {
         this.data = data;
     }




}




