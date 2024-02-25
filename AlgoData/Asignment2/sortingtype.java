import java.lang.reflect.Array;
import java.util.Random;
class sortingtype{

    public static boolean binary_search(int[] array, int key) {
int first = 0;
int last = array.length;
while (first <= last) {
// jump to the middle 
int index =(first+last)/2;
if (array[index] == key) {
break;



}

if (key > last || key < first ){
    return false;
     
}

if (array[index] < key && index < last) {
// The index position holds something that is less than
// what we're looking for, what is the first possible page?
first = index;
continue;
}
if (array[index] > key && index > first) {
// The index position holds something that is larger than
// what we're looking for, what is the last possible page?
last = index;
continue;
}
// Why do we land here? What should we do?
if (first > last){
    return false;

}
else{continue;}


} 
return true;
}


private static int[] sort(int n) {
Random rnd = new Random();
int[] array = new int[n];
int nxt = 0;
for (int i = 0; i < n ; i++) {
nxt += rnd.nextInt(10) + 1;
array[i] = nxt;
}
for (int j = 0; j < array.length - 1; j++){
    if (array[j] > array[j+1]){
         int tempVar = array[j];
        array[j] = array[j+1];   
        array[j+1] = tempVar;

    }
}
return array;
}

public static boolean search_unsorted(int[] array, int key) {
for (int index = 0; index < array.length ; index++) {
if (array[index] == key) {
return true;
}
}
return false;
}


// Helper method to print an array

public static void main(String[] args) {
    int[] array1 = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
    int key1 = 4;
    boolean result1 = binary_search(array1, key1);
    System.out.println("Key 20 found in array1: " + result1);
}



}


