
package homework_6_csc310;

public class Homework_6_CSC310 
{
    public static void main(String[] args) 
    {
        priority_queue x = new priority_queue();
        x.print_pq();
        for (int i = 0; i < 15; i++)
        {
            int a = (int) (Math.random() * 10);
            x.add(pq_object.key_val(a, i + 1));
            x.print_pq();
        }
        for (int i = 0; i < 5; i++)
        {
            x.remove_min();
            x.print_pq();
        }
        for (int i = 0; i < 10; i++)
        {
            int a = (int) (Math.random() * 100);
            x.add(pq_object.key_val(a, i + 1));
            x.print_pq();
        }
        x.print_pq();
        
        int[] a = new int[15];
        for (int i = 0; i < 15; i++)
        {
            a[i] = (int) (Math.random() * 100);
        }
        System.out.print("\n\n\n[");
        for(int i = 0; i < 15; i++)
        {
            System.out.print(" " + a[i] + " ");
        }
        System.out.println("]");
        a = priority_queue.insertion_sort(a);
        System.out.print("\n\n\n[");
        for(int i = 0; i < 15; i++)
        {
            System.out.print(" " + a[i] + " ");
        }
        System.out.println("]");
        
        
        //Problem 2
        int[] k = new int[10];
        for (int i = 0; i < k.length; i++)
        {
            k[i] = (int)(Math.random() * 100);
        }
        Max_Heap.heap_sort(k);
        for (int i = 0; i < k.length; i++)
        {
            System.out.print(k[i] + " ");
        }
        
        
        
        //Problem 3
        System.out.println("\n\n\n");
        int[] hp = new int[5];
        hp[0] = 10;
        hp[1] = 9;
        hp[2] = 8;
        hp[3] = 7;
        hp[4] = 6;
        Max_Heap mh = new Max_Heap(hp);
        mh.insert(11);
        for (int i = 0; i < mh.size; i++)
        {
            System.out.print(mh.heap[i] + " ");
        }
        System.out.println("\n\n");
        
        
    }
}

class pq_object //This class holds the items in priority_queue
{
    //Each has a key and value
    int key; 
    int value;
    boolean is_null; //This helps later on
    
    pq_object() //initialize null pq_object
    {
        is_null = true;
    }
    
    pq_object(int k, int v) //initialize pq_object with key and value
    {
        key = k;
        value = v;
        is_null = false;
    }
    
    static pq_object empty_pq() //return null pq_object
    {
        pq_object x = new pq_object();
        return x;
    }
    
    static pq_object key_val(int k, int v) //return pq_object with key/value
    {
        pq_object p = new pq_object(k, v);
        return p;
    }
}

class priority_queue //this stores the priority queue - note that this works circularly
{
    pq_object[] pq; //array of pq_objects
    //Final array size at 20, so queue of size 20 is max. Works for the purpose 
    //of this homework assignment, and would probably be 
    final int ARR_SIZE = 20; 
    int size = 0; //This keeps track of the number of elements in the priority_queue
    int top = 0; //This keeps track of the top element of the queue
    int rear = 0; //This keeps track of the rear element of the queue
    
    priority_queue() //initializes empty priority queue
    {
        pq = new pq_object[ARR_SIZE]; 
        for (int i = 0; i < ARR_SIZE; i++) //populate with null pq_objects
        {
            pq_object x = new pq_object();
            pq[i] = x;
        }
    }
    
    void add(pq_object x) //add a new pq_object to the priority_queue
    {
        if (x.is_null) //Throw error is x is null
        {
            throw new IllegalStateException("Cannot insert null pq_object into priority queue.");
        }
        else if (size == 0) //If priority_queue is empty, put x at top
        {
            pq[top] = x; 
            rear = (rear + 1) % ARR_SIZE; //update rear
            size++; //update size
        }
        else if (size == ARR_SIZE) //If priority_queue is full, throw error
        {
            throw new IndexOutOfBoundsException("Priority queue is full, cannot add."); 
        }
        else //otherwise...
        {
            rear = (rear + 1) % ARR_SIZE; //update rear
            size++; //update size
            int iter = top; //new iterator to move through queue
            int c = -1; //c just holds index of where x should be
            for (int i = 0; i < ARR_SIZE; i++)
            {
                //When the next item is null, it is the last item. This should have
                //already iterated through the rest, meaning this is where x goes
                if (pq[iter].is_null) 
                {
                    c = iter;
                    break;
                }
                //When the object's key is smaller or equal to x's key, just 
                //update iter and continue.
                else if (pq[iter].key <= x.key)
                {
                    iter = (iter + 1) % ARR_SIZE; 
                    continue;
                }
                //Otherwise, the next key is greater than x's key. This will be the
                //index where x should go.
                else
                {
                    c = iter;
                    break;
                }
            }
            //create new pq_objects to hold two values
            pq_object temp1, temp2;
            temp1 = pq[c]; //temp1 holds the pq_object to be moved
            pq[c] = x; //place x in the correct index
            //This calculates the distance from top to rear circularly. This is 
            //needed to bound the number of iterations of the next loop.
            int end = (ARR_SIZE - c + rear) % ARR_SIZE; 
            for (int i = 1; i < end; i++)
            {
                temp2 = pq[(i + c) % ARR_SIZE]; //place next object in temp2
                pq[(i + c) % ARR_SIZE] = temp1; //place temp1 in this index
                temp1 = temp2; //place temp2 in temp1
            }
        }
        
    }
    
    pq_object remove_min() //This is a sorted priority queue, so remove_min() is easy
    {
        pq_object a; //pq_object for storage later on
        if (size == 0) //When size = 0, throw error
        {
            throw new IllegalStateException("Cannot remove min of an empty priority queue.");
        }
        else //otherwise,
        {
            a = pq[top]; //place top pq_object in a
            pq[top] = new pq_object(); //replace top with null pq_object
            top = (top + 1) % ARR_SIZE; //update top (circularly)
            size--; //update size
        }
        return a;
    }
    
    //Since this is a sorted priority queue, insertion sort simply places pq_objects
    //in the queue, then takes the first one out one by one.
    static int[] insertion_sort(int[] a) 
    {
        priority_queue pq = new priority_queue(); //new priority queue
        for (int i = 0; i < a.length; i++) //iterate through list
        {
            pq.add(new pq_object(a[i], i)); //add each item to priority queue
        }
        for (int i = 0; i < a.length; i++) //iterate through priority queue now
        {
            a[i] = pq.remove_min().key; //remove min from priority queue
        }
        return a; //a is sorted, so return.
    }
    
    void print_pq() //This just helps us see the priority queue
    {
        System.out.print("[ ");
        int iter = top;
        for (int i = 0; i < size; i++)
        {
            //System.out.print(i);
            if (iter != (rear + ARR_SIZE - 1) % ARR_SIZE)
            {
                System.out.print("(" + pq[iter].key + ", " + pq[iter].value + "), ");
            }
            else
            {
                System.out.print("(" + pq[iter].key + ", " + pq[iter].value + ") ");
            }
            iter = (iter + 1) % ARR_SIZE;
        }
        System.out.println("]");
    }
}

class Heap //General heap class - we can implement min and max in different subclasses
{
    int[] heap = new int[255]; //supports up to depth 8, could be changed if needed
    int size; //holds current size of heap
    
    Heap() //initialize empty heap
    {
        size = 0;
    }
    
    Heap(int[] x) //initialize heap from int array
    {
        size = x.length; 
        for (int i = 0; i < x.length; i++)
        {
            heap[i] = x[i];
        }
    }
    
    boolean is_empty() //detects if heap is empty
    {
        return size == 0;
    }
    
    int size() //returns size
    {
        return size;
    }
    
    //This outputs the index of a parent, given the input of the index of a child.
    //This is just helpful for other methods, so I made it protected
    protected static int parent_index(int x, int size) 
    {
        if (x >= size || x < 0) //Check to see if the index is even valid
        {
            throw new IndexOutOfBoundsException("Invalid index.");
        }
        else if (x == 0) //Root has no parent.
        {
            throw new IllegalStateException("Root node has no parent.");
        }
        else //Otherwise,
        {
            if (x % 2 == 0) //if even,
            {
                return (x - 2) / 2;
            }
            else //if odd,
            {
                return (x - 1) / 2;
            }
        }
    }
}

class Min_Heap extends Heap //Min_Heap for problem 3
{
    Min_Heap() //initialize empty min_heap
    {
        size = 0;
    }
    
    Min_Heap(int[] x) //initialize a Min_Heap with an int array
    {
        size = x.length;
        for (int i = 0; i < x.length; i++)
        {
            heap[i] = x[i];
        }
    }
    
    void insert(int k) //insert new element k
    {
        size++; //update size
        heap[size - 1] = k; //place element at the end of the heap
        //if there are already elements in the heap,
        if (size != 1)
        {
            upheap(); //upheap, which preserves the property of the heap.
        }
        //otherwise, there is nothing to adjust.
    }
    
    int find_min()
    {
        if (is_empty()) //No min if heap is empty
        {
            throw new IllegalStateException("No min of empty heap.");
        }
        return heap[0]; //Min will always be the root
    }
    
    void del_min()
    {
        heap[0] = heap[size - 1]; //place last element at root
        heap[size - 1] = 0; //reset index
        size--; //update size
        //When there are already elements in the heap,
        if (size > 1)
        {
            downheap(); //downheap to preserve properties of heap
        }
        //Otherwise, there is nothing to adjust.
    }
    
    void upheap()
    {
        int i_c = size - 1, i_p; //i_1 holds child index, i_2 holds parent index
        i_p = parent_index(i_c, size); //initialize i_2
        while (heap[i_c] < heap[i_p] ) //while child is less than parent,
        {
            //switch child and parent
            int temp = heap[i_c]; 
            heap[i_c] = heap[i_p];
            heap[i_p] = temp;
            //when parent is not root,
            if(i_p != 0)
            {
                i_c = i_p;  //place parent index in child
                i_p = parent_index(i_c, size); //place grandparent index in parent
                //now we are ready for another iteration of the loop
            }
            //when parent is root,
            else
            {
                //stop
                break;
            }
        }
    }
    
    void downheap()
    {
        int i_p = 0; //holds parent index
        int i_c = smaller_child(1, heap, size); //holds index of smallest child
        while (heap[i_p] > heap[i_c])
        {
            int temp = heap[i_p];
            heap[i_p] = heap[i_c];
            heap[i_c] = temp;
            i_p = i_c;
            if (2 * i_p + 1 >= size)
            {
                break;
            }
            i_c = smaller_child((2 * i_p + 1), heap, size);
        }
    }
    
    static Min_Heap build_heap(int[] n)
    {
        Min_Heap x = new Min_Heap(n);
        return x;
    }
        
    static int smaller_child(int lc, int[] hp, int size)
    {
        int rc = lc + 1;
        if (lc > size - 1)
        {
            System.out.println(lc + " " + rc + " " + size);
            throw new IndexOutOfBoundsException("Invalid index.");
        }
        else if (rc > size - 1)
        {
            return lc;
        }
        else
        {
            if (hp[lc] <= hp[rc])
            {
                return lc;
            }
            else
            {
                return rc;
            }
        }
    }
    
}

class Max_Heap extends Heap
{
    Max_Heap()
    {
        size = 0;
    }
    
    Max_Heap(int[] x)
    {
        size = x.length;
        for (int i = 0; i < x.length; i++)
        {
            heap[i] = x[i];
        }
    }
    
    void insert(int k)
    {
        size++;
        heap[size - 1] = k;
        if (size != 1)
        {
            upheap();
        }
    }
    
    int find_max()
    {
        if (is_empty())
        {
            throw new IllegalStateException("No min of empty heap.");
        }
        return heap[0];
    }
    
    void del_max()
    {
        heap[0] = heap[size - 1];
        heap[size - 1] = 0;
        size--;
        if (size > 1)
        {
            downheap();
        }
    }
    
    void upheap()
    {
        int i_1 = size - 1, i_2;
        i_2 = parent_index(i_1, size);
        while (heap[i_1] > heap[i_2] )
        {
            int temp = heap[i_1];
            heap[i_1] = heap[i_2];
            heap[i_2] = temp;
            if(i_2 != 0)
            {
                i_1 = i_2;
                i_2 = parent_index(i_1, size);
            }
            else
            {
                break;
            }
        }
    }
    
    void downheap()
    {
        int i_r = 0;
        int i_c = larger_child(1, heap, size);
        while (heap[i_r] < heap[i_c])
        {
            int temp = heap[i_r];
            heap[i_r] = heap[i_c];
            heap[i_c] = temp;
            i_r = i_c;
            if (2 * i_r + 1 >= size)
            {
                break;
            }
            i_c = larger_child((2 * i_r + 1), heap, size);
        }
    }
    
    static Max_Heap build_heap(int[] n)
    {
        Max_Heap x = new Max_Heap(n);
        return x;
    }
        
    static int larger_child(int lc, int[] hp, int size)
    {
        int rc = lc + 1;
        if (lc > size - 1)
        {
            throw new IndexOutOfBoundsException("Invalid index.");
        }
        else if (rc > size - 1)
        {
            return lc;
        }
        else
        {
            if (hp[lc] >= hp[rc])
            {
                return lc;
            }
            else
            {
                return rc;
            }
        }
    }
    
    
    static int[] heap_sort(int[] k)
    {
        Max_Heap x = new Max_Heap();
        for (int i = 0; i < k.length; i++)
        {
            x.insert(k[i]);
        }
        for (int i = k.length - 1; i >= 0; i--)
        {
            k[i] = x.find_max();
            x.del_max();
        }
        return k;
    }
}

