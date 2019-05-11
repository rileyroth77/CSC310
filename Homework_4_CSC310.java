
package homework_4_csc310;
import java.util.*;

public class Homework_4_CSC310 {
    public static void main(String[] args) 
    {
        //problem 1
        circular_queue q = new circular_queue(5);
        System.out.println("is_empty() method test: " + q.is_empty());
        System.out.println("is_full() method test: " + q.is_full());
        q.insert_front(3);
        q.insert_front(2);
        q.insert_rear(4);
        q.insert_rear(7);
        q.output();
        System.out.println("is_empty() method test: " + q.is_empty());
        q.insert_front(3);
        System.out.println("is_full() method test: " + q.is_full());
        q.delete_front();
        q.delete_rear();
        q.output();
        System.out.println("Front value: " + q.get_front() + "\nRear value: " + q.get_rear());
        System.out.println("size() method test: " + q.size() + "\n\n\n\n");
        
        
        
        //problem 2
        linked_list n = new linked_list(3);
        n.add_tail(4);
        n.add_tail(5);
        n.print_list();
        linked_list m = new linked_list(4);
        m.add_tail(5);
        m.add_tail(6);
        m.print_list();
        
        linked_list merge = linked_list.merge(n, m);
        
        merge.print_list();
        System.out.println("\n\n\n");
        
        
        
        //problem 3
        list_queue lq = new list_queue();
        System.out.println(lq.is_empty());
        lq.enqueue(1);
        lq.enqueue(2);
        lq.enqueue(3);
        System.out.println(lq.len());
        System.out.println(lq.is_empty());
        lq.print_list();
        System.out.println(lq.dequeue());
        System.out.println(lq.dequeue());
        lq.print_list();
        System.out.println(lq.first());
    }
}

class circular_queue
{
    int f, r, n; //f holds front, r holds rear, n holds overall size
    boolean empty; //holds whether empty or not
    int[] queue; //holds queue
    
    circular_queue(int k)
    {
        queue = new int[k];
        f = 0; 
        r = 0;
        n = k;
        empty = true;
    }
    
    int size() //formula used in class
    {
        return (n - f + r) % n;
    }
    
    boolean is_empty() 
    {
        return empty;
    }
    
    boolean is_full() //if f == r, queue is empty or full
    {
        if (f == r && empty == false) //so if empty is false and f == r,
        {
            return true;
        }
        //otherwise
        return false;
    }
    
    int get_front() //returns front
    {
        if (this.is_empty())
        {
            throw new IllegalStateException("No front, queue is empty.");
        }
        return queue[f];
    }
    
    int get_rear() //returns rear
    {
        if (this.is_empty())
        {
            throw new IllegalStateException("No front, queue is empty.");
        }
        return queue[r - 1];
    }
    
    void delete_front() 
    {
        if (this.is_empty())
        {
            throw new IllegalStateException("Cannot delete items from empty queue.");
        }
        queue[f] = 0; //set to 0 for garbage collection
        f = (f + 1) % n; //adjust front
        if (f == r) //when deleting, if f == r, queue is empty
        {
            empty = true;
        }
    }
    
    void delete_rear()
    {
        //I had to use a trick - modulus does not work properly in java
        //ex: -1 mod 20 = -1, not 19.
        //I'll point out where the fix is
        if (this.is_empty())
        {
            throw new IllegalStateException("Cannot delete items from empty queue.");
        }
        //here, I use the fix for the modulus. This just works like a normal modulus
        queue[(((r - 1) % n) + n) % n] = 0; //garbage collection again
        r = (((r - 1) % n) + n) % n; //adjust rear
        if (f == r)
        {
            empty = true;
        }    
    }
    
    void insert_front(int k)
    {
        if (this.is_full())
        {
            throw new IllegalStateException("Cannot add items to full queue.");
        }
        //I had to use the trick again
        queue[(((f - 1) % n) + n) % n] = k; //add new element
        f = (((f - 1) % n) + n) % n; //adjust front
        if (this.is_empty())
        {
            empty = false;
        }
    }
    
    void insert_rear(int k)
    {
        if (this.is_full())
        {
            throw new IllegalStateException("Cannot add items to full queue.");
        }
        queue[r] = k; //add new element
        r = (r + 1) % n; //adjust rear
        if (this.is_empty())
        {
            empty = false;
        }
    }
    
    void output() //just prints the queue
    {
        System.out.print("Queue: ");
        for (int i = 0; i < n; i++)
        {
            System.out.print(" " + queue[i] + " ");
        }
        System.out.println();
        System.out.println("Front: " + f + "\nRear: " + r + "\nEmpty: " + empty + "\nSize: " + n);
    }
    
}

class node
{
    int num; //holds value
    node next; //also holds pointer to next node
    
    node(int n) //constructor
    {
        num = n;
        next = null;
    }
    
    void next(node x) //allows you to change pointer
    {
        next = x;
    }
}

class linked_list
{
    node start; //holds starting node
    int size; //holds list size
    
    linked_list() //constructor
    {
        start = new node(0);
        size = 1;
    }
    
    linked_list(int n) //another constructor for versatility
    {
        start = new node(n);
        size = 1;
    }
    
    void add_tail(int n)
    {
        node tail = new node(n); //the new tail
        
        if (start.next != null) //if not the first tail added
        {
            node end = start.next;
            
            while (end.next != null) //iterate to find the last node
            {
                end = end.next;
            }
            
            end.next(tail); //then add the tail
        }
        
        else //if the first node added, just add to the start.
        {
            start.next(tail);
        }
        
        size++; //adjust size
    }
    
    void add_head(int n)
    {
        
    }
    
    void delete_tail()
    {
        if (size == 0)
        {
            throw new IllegalStateException("Cannot delete empty list.");
        }
            
        node end = start; //these hold the current and new end
        node new_end = start;
        
        while (end.next != null) //find the current end
        {
            end = end.next;
        }
        end.num = 0; //garbage collection
        
        while (new_end.next != end) //find the new end (just before the current end
        {
            new_end = new_end.next;
        }
        
        new_end.next(null); //change pointer on new end
        
        size--; //adjust size
    }
    
    static linked_list merge(linked_list a, linked_list b)
    {
        node a_node = a.start; //first list's node
        node b_node = b.start; //second list's node
        linked_list merge = new linked_list(0); //new linked_list
        int i = 0; //iterates through values
        
        for (i = 0; i < a.size + b.size; i++) //the merged list will have size a.size + b.size
        {
            node merge_node; //holds next node for merged list
            if (a_node == null) //when a is null
            {
                merge_node = b_node; //just empty b into the list
                b_node = b_node.next;
            }
            else if (b_node == null) //same for b
            {
                merge_node = a_node;
                a_node = a_node.next;
            }
            else //when both aren't null
            {
                if (a_node.num < b_node.num) //next node is a when a is smaller
                {
                    merge_node = a_node;
                    a_node = a_node.next;
                }
                else //otherwise next node is b
                {
                    merge_node = b_node;
                    b_node = b_node.next;
                }
            }
            //I did this next part because of how I wrote my constructor. 
            //linked_list initializes with a value, so this is corrected here
            if (i == 0) 
            {
                merge.start.num = merge_node.num;
            }
            else
            {
                merge.add_tail(merge_node.num);
            }
        }
        
        return merge;
    }
    
    void print_list() //just print the list
    {
        node iter = start;
        System.out.print("List: ");
        for (int i = 0; i < size; i++)
        {
            System.out.print(iter.num + " ");
            if (iter.next != null)
            {
                iter = iter.next;
            }
        }
        System.out.println();
    }
}

class list_queue extends linked_list //to use linked list for queue, I just entend existing class
{ 
    void enqueue(int n) //enqueue is just add_tail()
    {
        this.add_tail(n);
    }
    
    int dequeue() 
    {
        int n = this.start.num; //hold start's value
        this.start.num = 0; //garbage collection
        this.start = this.start.next; //adjust start
        this.size--; //adjust size
        return n;
    }
    
    int len() //size already holds length
    {
        return size;
    }
    
    int first() //returns first node's value
    {
        return start.num;
    }
    
    boolean is_empty() 
    {
        return size == 0;
    }
    
    boolean search(int x) //iterates through list and finds element
    {
        node n = this.start;
        for (int i = 0; i < size; i++)
        {
            if (n.num == x)
            {
                return true;
            }
            n = n.next;
        }
        return false;
    }
}