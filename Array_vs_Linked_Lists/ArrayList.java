//written by sargs005
public class ArrayList<T extends Comparable<T>> implements List<T> {
    private T[] array;
    private int numElements;
    private boolean isSorted;
    public ArrayList() {
        array = (T[]) new Comparable[2];
        numElements = 0;
        isSorted = true;
    }

    @Override
    public boolean add(T element) {
        if (element != null) {
            if (numElements >= array.length - 1)
                array = cloneArray();
            array[numElements] = element;
            numElements++;
            isSorted = checkSorted(numElements);
            return true;
        }
        else
            return false;

    }

    @Override
    public boolean add(int index, T element) {
        if (element != null && index >= 0 && index < numElements) {
            if (numElements == array.length)
                array = cloneArray();
            for (int i = numElements; i > index; i--)
                array[i] = array[i - 1];
            array[index] = element;
            numElements++;
            isSorted = checkSorted(numElements);
            return true;
        }
        else
            isSorted = checkSorted(numElements);
            return false;
    }

    @Override
    public void clear() {
        numElements = 0;
        array = (T[]) new Comparable[2];
        isSorted = checkSorted(numElements);
    }

    @Override
    public T get(int index) {
        if(index>= numElements || index<0){
            return null;
        }
        return array[index];
    }

    @Override
    public int indexOf(T element) {
        if(element == null){
            return -1;
        }
        for (int i = 0; i < numElements; i++){
            if(array[i].compareTo(element) == 0) {
                if (i > -1 && i <= numElements) {
                    isSorted = false;
                    return i;
                }
            }



        }
        return -1;
    }

    @Override
    public boolean isEmpty() {
        if (numElements == 0)
            return true;
        else
            return false;
    }

    @Override
    public int size() {
        return this.numElements;
    }

    @Override
    public void sort() {
        if(!isSorted){
            int i  = 1;
            int j = i-1;
            T temp;
            for(i=1;i<numElements;i++){
                temp = get(i);
                for(j = i-1; j>=0 && temp.compareTo(get(j))<0;j--){
                    array[j+1] = array[j];

                }
                array[j+1] = temp;
            }
            isSorted = true;

        }

    }

    @Override
    public T remove(int index) {
        if(index>numElements-1 || index < 0){
            return null;
        }
        T temp = array[index];
        for (int i = index; i < numElements; i++)
            array[i] = array[i+1];
        numElements--;
        isSorted = checkSorted(numElements);
        return temp;
    }

    @Override
    public void equalTo(T element) {
        if(element == null){
            return;
        }
        int i = 0;
        int count = 0;
        while(i<numElements){
            if(get(i).compareTo(element) != 0){
                array[i] = null;
                count++;
            }
            i++;
        }
        int temp = 0;
        T var;
        for(int j = 0; j<numElements; j++){
            if(array[j] != null){
                var = array[j];
                array[j] = null;
                array[temp++] = var;
            }
        }
        this.numElements -= count;
        isSorted = checkSorted(numElements);


    }

    @Override
    public void reverse() {
        int middle = numElements/2;
        int temp = middle;
        for (int i = 0; i < numElements/2; i++){
            if(numElements % 2 == 0) {
                T element = array[temp];
                array[temp] = array[middle - 1];
                array[middle - 1] = element;
                temp++;
                middle--;
            }
            else{
                T element  = array[temp+1];
                array[temp + 1] = array[middle];
                array[middle] = element;
                middle--;
                temp++;
            }
        }
        isSorted = checkSorted(numElements);


    }

    @Override
    public void merge(List<T> otherList) {
        ArrayList<T> other = (ArrayList<T>) otherList;
        if(!isSorted){
            sort();
        }
        if(!other.isSorted){
            other.sort();
        }
        int first = 0;
        for(int i =0; i<other.size();i++){
            int j = first;
            for(; j<numElements && other.get(i).compareTo(get(j))>0;j++);
            if(j==numElements){
                add(other.get(i));
            }
            else{
                add(j, other.get(i));
                first = j;
            }
            isSorted = true;
        }




    }

    @Override
    public void pairSwap() {
        if(numElements == 0 || numElements == 1){
            return;
        }
        for(int i = 0; i<numElements-1; i = i+2){
            T element = array[i];
            array[i] = array[i+1];
            array[i+1] = element;

        }
    }

    @Override
    public boolean isSorted() {
        return isSorted;
    }
    private T[] cloneArray() {
        T[] arrayClone = (T[]) new Comparable[array.length*2];
        for (int i = 0; i < numElements; i++) {
            arrayClone[i] = array[i];
        }
        return arrayClone;
    }
    public String toString() {
        String string = "";
        for (int i = 0; i < numElements; i++)
            string += array[i].toString() + "\n";
        return string;
    }
    private boolean checkSorted(int numElements){
        if(numElements == 0 || numElements == 1){
            return true;
        }
        if(get(numElements-1).compareTo(get(numElements-2))<0 ){
            return false;
        }
        return checkSorted(numElements-1);

    }

}
