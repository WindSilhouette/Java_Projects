//Written by niemi170

public class LinkedList <T extends Comparable<T>> implements List<T> {

    private boolean isSorted;
    private Node<T> head;
    private int size = 0;

    //contructor
    public LinkedList() {
        head = null;
        isSorted = true;
    }

    //list.java methods
    @Override
    public boolean add(T element) {
        if(element == null){ //case 1 element is null
            return false;
        }
        Node<T> newNode = new Node<T>(element);
        if(head != null){ //case 2 head is not null, items in list
            Node<T> endNode = head;
            while(endNode.getNext() != null){
                endNode = endNode.getNext();
            }
            endNode.setNext(newNode);

            if (newNode.getData().compareTo(endNode.getData()) < 0) {
                isSorted = false;
            }
        }else{ //case 3 head is null
            head = newNode;
            head.setNext(null);
            isSorted = true;
        }
        size++;
        return true;
    }

    @Override
    public boolean add(int index, T element) {
        if(element == null || index < 0 || index >= size) { // case 1, element is null or index is out of bounds
            return false;
        }
        Node<T> newNode = new Node<T>(element);
        Node<T> tmp2 = null;
        Node<T> tmp1 = head;
        if(index == 0) { // case 2 adding to index 0
            newNode.setNext(head);
            head = newNode;
        }else { //case 3 adding to middle list
            for (int i = 0; i < index - 1; i++) { //Find the index node
                tmp1 = tmp1.getNext();
            }
            tmp2 = tmp1.getNext();
            tmp1.setNext(newNode);
            newNode.setNext(tmp2);

            if (index == size) {  // case 4 adding to end of list
                for (int i = 0; i < index - 1; i++) { //Find the index node
                    tmp1 = tmp1.getNext();
                }
                tmp1.setNext(newNode);
            }
        }
        if (newNode.getData().compareTo(tmp1.getData()) > 0) {
            isSorted = false;
        }
        size++; //increment size
        return true;
    }

    @Override
    public void clear() {
        head = null;
        size = 0;
        isSorted = true;
    }

    @Override
    public T get(int index) {
        if (index >= size || index < 0) return null;
        Node<T> temp = head;
        for (int i = 0; i < index; i++) { //Find the index node
            if (temp.getNext() != null) {
                temp = temp.getNext();
            }
            else {
                return null;
            }
        }
        if (index == 0) {
            return head.getData();
        }
        return temp.getData();
    }

    @Override
    public int indexOf(T element) {
        if (element == null){
            return -1;
        }
        Node<T> tmp = head;
        int count = 0;
        while(tmp != null){
            if (tmp.getData() == element){
                return count;
            }
            tmp = tmp.getNext();
            count++;
        }
        return -1;
    }

    @Override
    public boolean isEmpty() {
        if (head == null){
            return true;
        }
        return false;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public void sort() {  // i used bubble sort where you swap two values
        if(isSorted == true || size == 1 || size == 0 || isSorted){ //increased efficiency if isSorted is true
            return;
        }
        for(int i = 0; i < size; i++){
            Node<T> curr = head.getNext();
            Node<T> prev = head;
            Node<T> prev2 = null;
            while(curr != null) {
                if (curr.getData().compareTo(prev.getData()) <= 0) { // pair needs to be switched
                    if (prev == head) {
                        prev.setNext(curr.getNext());
                        curr.setNext(prev);
                        head = curr;
                        prev2 = curr;
                        curr = prev.getNext();
                    } else {
                        prev.setNext(curr.getNext());
                        prev2.setNext(curr);
                        curr.setNext(prev);
                        prev2 = curr;
                        curr = prev.getNext();
                    }


                } else { // the pair is in the correct position already
                    prev2 = prev;
                    prev = curr;
                    curr = curr.getNext();
                }
            }
        }

        isSorted = true;
    }

    @Override
    public T remove(int index) {
        if (index < 0 || index >= size){ // case 4: out of bounds
            return null;
        }
        Node<T> tmp = head; //current
        Node<T> tmp2 = head; // previous
        if (index == 0){ // case 1: index is 0
            head = head.getNext();
            tmp.setNext(null);
            isSorted = checkSorted();
            size--;
            return tmp.getData();
        }else if(index != size-1){ // case 2: middle index
            for (int i = 0; i < index-1; i++){
                tmp2 = tmp2.getNext();
            }
            tmp = tmp2.getNext();
            tmp2.setNext(tmp.getNext());
            isSorted = checkSorted();
            size--;
            return tmp.getData();
        }else{
            for (int i = 0; i < index-1; i++){ // case 3: last index
                tmp2 = tmp2.getNext();
            }
            tmp = tmp2.getNext();
            tmp2.setNext(null);
            isSorted = checkSorted();
            size--;
            return tmp.getData();
        }
    }

    @Override
    public void equalTo(T element) {
        Node<T> tmp = null;
        Node<T> tmp2 = head;//keep track of current and previous linked list points
        while (tmp2 != null) {
            if(tmp2.getData() != element){ //remove
                if (tmp2 == head){  // case 1 remove first element
                    head = tmp2.getNext();
                    tmp2 = head;
                }else if(tmp2.getNext() != null){ // case 2 middle element removed
                    tmp.setNext(tmp2.getNext());
                    tmp2 = tmp.getNext();

                }else {  //case 3 end element removed
                    tmp.setNext(null);
                    tmp2=null;
                }
                size --;
            }else{//no removal
                tmp = tmp2;
                tmp2 = tmp2.getNext();
            }
        }
        isSorted = true;
    }
    @Override
    public void reverse() {
        if (size == 1 || size == 0){
            return;
        }
        Node<T> prev = null;
        Node<T> curr = head;
        Node<T> next = curr.getNext();
        while(curr != null){  //iterative method
            next = curr.getNext();
            curr.setNext(prev);
            prev = curr;
            curr = next;
        }
        head = prev;
        isSorted = checkSorted();
    }

    @Override
    public void merge(List<T> otherList) {
        if (otherList == null){
            return;
        }
        LinkedList<T> other = (LinkedList<T>) otherList;
        sort();
        other.sort();
        for (int index1 = 0, indexOther = 0; indexOther < other.size(); index1++) { // go through both lists, keeping track of both indexes
            if (index1 == this.size()-1 || this.get(index1).compareTo(other.get(indexOther))  > 0) { //finds the correct place to add the index from OtherList
                this.add(index1, other.get(indexOther++));
            }
        }
        isSorted = true;
    }

    @Override
    public void pairSwap() {
        if (size == 0 || size == 1){
            return;
        }
        int pair = size/2; // amount of swaps we need to do
        Node<T> prev = head;
        Node<T> curr = head.getNext();
        for (int i = 0; i < pair; i++){
            T temp = prev.getData();
            temp = curr.getData();
            curr.setData(prev.getData());
            prev.setData(temp);
            if (curr.getNext() != null){
                prev = curr.getNext();
                curr = prev.getNext();
            }

        }
        isSorted = checkSorted();
    }

    @Override
    public boolean isSorted() {
        return isSorted;
    }

    @Override
    public String toString(){
        String final1 = "";
        Node<T> temp = head;
        while (temp != null){
            final1 += temp.getData();
            final1 += "\n";
            temp = temp.getNext();
        }
        return final1;
    }

    private boolean checkSorted() {
        boolean final1 = true;
        if(size == 0 || size == 1){
            return true;
        }
        Node<T> prev = head;
        Node<T> curr = head.getNext();
        while(curr != null){

            if (prev.getData().compareTo(curr.getData()) > 0) {
                final1 = false;
            }
            prev = curr;
            curr = curr.getNext();
        }

        return final1;
    }
}
