//**************************  SLL.java  *********************************
//           a generic singly linked list class 


public class MyLinkedList<T> {
    

    private class SLLNode<T> {
      private T info;
      private SLLNode<T> next;
      public SLLNode() {
         this(null,null);
      }
      public SLLNode(T el) {
        this(el,null);
      }
      public SLLNode(T el, SLLNode<T> ptr) {
         info = el; next = ptr;
      }
    }

    protected SLLNode<T> head, tail;
    protected int length;
    public MyLinkedList() {
        head = tail = null;
        length = 0;
    }
    public boolean isEmpty() {
        return head == null;
    }
    public void addToHead(T el) {
        head = new SLLNode<T>(el,head);
        if (tail == null)
            tail = head;
        length++;
    }
    public void addToTail(T el) {
        if (!isEmpty()) {
            tail.next = new SLLNode<T>(el);
            tail = tail.next;
        }
        else head = tail = new SLLNode<T>(el);

        length++;
    }
    public T deleteFromHead() { // delete the head and return its info; 
        if (isEmpty()) 
             return null;
        T el = head.info;
        if (head == tail)       // if only one node on the list;
             head = tail = null;
        else head = head.next;
        length--;
        return el;
    }
    public T deleteFromTail() { // delete the tail and return its info;
        if (isEmpty()) 
             return null;
        T el = tail.info;
        if (head == tail)       // if only one node in the list;
             head = tail = null;
        else {                  // if more than one node in the list,
             SLLNode<T> tmp;    // find the predecessor of tail;
             for (tmp = head; tmp.next != tail; tmp = tmp.next);
             tail = tmp;        // the predecessor of tail becomes tail;
             tail.next = null;
        }
        length--;
        return el;
    }
    public void delete(T el) {  // delete the node with an element el;
        if (!isEmpty()) {
            if (head == tail && el.equals(head.info)) // if only one
                 head = tail = null;       // node on the list;
            else if (el.equals(head.info)) // if more than one node on the list;
                 head = head.next;    // and el is in the head node;
            else {                    // if more than one node in the list
                 SLLNode<T> pred, tmp;// and el is in a nonhead node;
                 for (pred = head, tmp = head.next;  
                      tmp != null && !tmp.info.equals(el); 
                      pred = pred.next, tmp = tmp.next);
                 if (tmp != null) {   // if el was found;
                     pred.next = tmp.next;
                     if (tmp == tail) // if el is in the last node;
                        tail = pred;
                 }
            }
            length--;
        }
    }
    
    @Override
    public String toString() {
       if(head == null)
          return "[ ]";
       String str = "[ ";   
       SLLNode<T> tmp = head;
       while(tmp != null){
         str += tmp.info + " ";
         tmp = tmp.next;
       }
       return str+"]";             
    }
    
    public boolean contains(T el) {
        if(head == null)
            return false;
        SLLNode<T> tmp = head;
        while(tmp != null){
           if(tmp.info.equals(el))
              return true;
           tmp = tmp.next;
        }
        return false;
    }
    
    public int size(){
        if(head == null)
          return 0;
          
        int count = 0;
        SLLNode<T> p = head;
        while(p != null) {
           count++;
           p = p.next;
        }
           
        return count;
    }
    
    //  Please write the methods of Task02 here:

    public T delete(int index) {
        // check the index
        if (this.isEmpty() || index >= this.size() || index < 0) {
            throw new IndexOutOfBoundsException("List is empty or index is invalid");
        }
        // there are multiple special cases

        // if the first node is removed
        //    a- if there is only one node, tail and head are null
        //    b- otherwise, the head should be head.next
        if (index == 0) {
            T info = head.info;
            if (head == tail) {
                head = tail = null;
            }
            else {
                head = head.next;

            }
            length--;
            return info;
        }
        SLLNode<T> temp = head;

        // start a counter
        int i = 0;
        // loop until the node before the target is reached
        while (i != index-1) {
            i++;
            temp = temp.next;
        }
        // keep the info to return it
        T info = temp.next.info;
        // check if the removed node is the last element to update the tail
        if (index == this.size()-1) {tail = temp;}
        // connect the node to the node after the removed node
        temp.next = temp.next.next;
        length--;
        return info;
    }
    
    public String[] toArray() {
        String[] array = new String[length];
        SLLNode<T> tmp = head;
        int i = 0;
        while (tmp != null) {
            array[i++] = (String) tmp.info;
            tmp = tmp.next;
        }
        return array;
    }

    public int getLength() {
        return length;
    }
}