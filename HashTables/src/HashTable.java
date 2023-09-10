import java.io.File;
import java.io.FileNotFoundException;
import java.util.Hashtable;
import java.util.Scanner;
//Written by Dylan Nieminski ; niemi170 and James Sargsyan ; sargs005
public class HashTable<T>{
    NGen<T>[] hashTable;
    private int total = 0; // number of unique items

    //TODO: Create a constructor that takes in a length and initializes the hash table array
    public HashTable(int length){
        this.hashTable = new NGen[length];
    }
    //TODO: Implement a simple hash function
    public int hash1(T item) { //takes in item, converts to string and gets first letter and gets a corresponding number from the word
        return item.toString().charAt(0) % hashTable.length;
    }

    //TODO: Implement a second (different and improved) hash function
    //
    // each individual
    public int hash2(T item) {
        //same as hash1 but improved because the final number is dependent on all the letters not just the first, making it a more wide range in where things are placed
        //this one works better for the general case and specific case because it can take in any type of words from a document and distribute them somewhat well
        int count = 0;
        for(int i = 0; i < item.toString().length(); i++){
            count += item.toString().charAt(i);
        }
        return count % hashTable.length;
    }

    //TODO: Implement the add method which adds an item to the hash table using your best performing hash function
    // Does NOT add duplicate items
    public void add(T item) {
        if (item == null){ // case1: item is null
            return;
        }
        for (int i = 0; i < hashTable.length; i++){ // case 2: item is a dupe
            NGen<T> ptr1 = hashTable[i];
            while (ptr1 != null){
                if (item.toString().equals(ptr1.getData())){
                    return;
                }
                ptr1 = ptr1.getNext();
            }
        }
        NGen<T> Node = new NGen<T>(item, null); // the added node
        NGen<T> ptr = hashTable[hash2(item)];
        if (hashTable[hash2(item)] == null){ //case3:  if no other item is there, make new
            hashTable[hash2(item)] = Node;
        }else{ // case4: add to the next link in linear linked list
            while(ptr.getNext() != null){ // get end of link
                ptr = ptr.getNext();
            }
            ptr.setNext(Node);
        }
        total += 1;
    }

    // ** Already implemented -- no need to change **
    // Adds all words from a given file to the hash table using the add(T item) method above
    @SuppressWarnings("unchecked")
    public void addWordsFromFile(String fileName) {
        Scanner fileScanner = null;
        String word;
        try {
            fileScanner = new Scanner(new File(fileName));
        }
        catch (FileNotFoundException e) {
            System.out.println("File: " + fileName + " not found.");
            System.exit(1);
        }
        while (fileScanner.hasNext()) {
            word = fileScanner.next();
            word = word.replaceAll("\\p{Punct}", ""); // removes punctuation
            this.add((T) word);
        }
    }

    //TODO: Implement the display method which prints the indices of the hash table and the number of words "hashed"
    // to each index. Also prints:
    // - total number of unique words
    // - number of empty indices
    // - number of nonempty indices
    // - average collision length
    // - length of longest chain
    public void display() {
        int empty = 0;
        int longest = 0;
        for(int i = 0; i < hashTable.length; i++){
            NGen<T> ptr = hashTable[i]; // loops through linear list
            int count = 0;
            if(ptr != null){
                while(ptr!= null){
                    ptr = ptr.getNext();
                    count ++;
                    if(count > longest){ //for longest in hashtable
                        longest = count;
                    }
                }
            }else{
                empty ++; // for empty spots in hashtable
            }
            System.out.println("Index " + i + ": " + count + " items");
        }
        System.out.println("# unique items: "+ total);
        System.out.println("# empty indices: "+ empty);
        System.out.println("# nonempty indices: "+ (hashTable.length - empty));
        System.out.println("average collision length: "+ ((double)total/((double)hashTable.length - empty)));
        System.out.println("length of longest chain: "+ longest);
    }

    // TODO: Create a hash table, store all words from "canterbury.txt", and display the table
    //  Create another hash table, store all words from "keywords.txt", and display the table
    public static void main(String args[]) {
        HashTable hash = new HashTable(150); // <=150
        hash.addWordsFromFile("canterbury.txt");
        hash.display();
        // longest length <= 7


        System.out.println();
        HashTable hash1 = new HashTable(30); // <= 500
        hash1.addWordsFromFile("keywords.txt");
        hash1.display();
        // longest length <= 7
    }
}
