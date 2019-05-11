/*
Riley Roth
CSC310
Homework 3 - Stacks
 */
package homework_3_csc310;
import java.util.*;


public class Homework_3_CSC310 
{
    
    public static void main(String[] args) 
    {
        Scanner in = new Scanner(System.in);
        int input;
        //this first part is just a menu
        do {
            System.out.print("Menu:\n1) Use min_stack\n2) Evaluate infix "
                    + "expression\n3) Evaluate postfix expression\n0) Quit\n"
                    + "Enter an option: ");
            input = in.nextInt();
            in.reset();
            System.out.println("\n\n");
            
            if (input == 1)
            {
                int st;
                min_stack m = new min_stack();
                do{
                    System.out.println("Press enter.");
                    
                    System.out.println("1) Length\n2) Is it empty?\n3) Push\n4) Pop"
                            + "\n5) Top\n6) Get_min\n7) Output stack and data\n"
                            + "0) Exit\nEnter an option: ");
                    st = in.nextInt();
                    if (st == 1)
                    {
                        System.out.println(m.len());
                    }
                    else if (st == 2)
                    {
                        System.out.println(m.is_empty());
                    }
                    else if (st == 3)
                    {
                        if (m.len() == 20)
                        {
                            System.out.println("Too many elements, cannot push.");
                        }
                        else
                        {
                            int a = 5;
                            System.out.print("Enter an int: ");
                            a = in.nextInt();
                            m.push(a);
                        }
                    }
                    else if (st == 4)
                    {
                        if (m.is_empty())
                        {
                            System.out.println("Cannot pop, stack is empty.");
                        }
                        else
                        {
                            System.out.println(m.pop());
                        }
                    }
                    else if (st == 5)
                    {
                        if (m.is_empty())
                        {
                            System.out.println("Cannot use top, stack is empty.");
                        }
                        else
                        {
                            System.out.println(m.top());
                        }
                    }
                    else if (st == 6)
                    {
                        if (m.is_empty())
                        {
                            System.out.println("Cannot output min, stack is empty.");
                        }
                        else
                        {
                            System.out.println(m.get_min());
                        }
                    }
                    else if (st == 7)
                    {
                        m.print_stack();
                    }
                    else
                    {
                        System.out.println("Wrong input.");
                    }
                    System.out.println("\n\n");
                } while (st != 0);
            }
            
            else if (input == 2)
            {
                Scanner str_scn = new Scanner(System.in);
                String i;
                System.out.print("Input an infix expression (do not use spaces): ");
                i = str_scn.nextLine();
                System.out.println("Answer: " + arithmetic_expression.infix(i));
            }
            
            else if (input == 3)
            {
                Scanner str_scn = new Scanner(System.in);
                String i;
                System.out.println("Input a postfix expression (do not use spaces): ");
                i = str_scn.nextLine();
                System.out.println("Answer: " + arithmetic_expression.postfix(i));
            }
            
            else if (input == 0)
            {
                System.out.println("Goodbye!");
            }
            
            else
            {
                System.out.println("Wrong input.");
            }
            System.out.println("\n\n\n\n");
        } while(input != 0);
        
        //Problem 1: min_stack testing
        min_stack m = new min_stack();
        m.push(5);
        m.push(3);
        System.out.println(m.len());
        System.out.println(m.pop());
        System.out.println(m.is_empty());
        System.out.println(m.pop());
        System.out.println(m.is_empty());
        //System.out.println(m.pop());
        m.push(7);
        m.push(9);
        System.out.println(m.top());
        m.push(4);
        System.out.println(m.len());
        System.out.println(m.pop());
        m.push(6);
        m.push(8);
        System.out.println(m.pop());
        m.print_stack();
        System.out.println(m.get_min());
        m.pop();
        System.out.println(m.get_min());
        m.push(1);
        System.out.println(m.get_min());
        m.pop();
        System.out.println(m.get_min());
        System.out.println("\n\n\n\n");
        
        
        
        
        
        
        //Problem 2: evaluate arithmetic expression in infix notation
        System.out.println("Problem 2:");
        System.out.println(arithmetic_expression.infix("3+4*(5+6)+7")); //54
        System.out.println(arithmetic_expression.infix("2*3+4/2"));     //8
        System.out.println(arithmetic_expression.infix("2*(4+6)/5"));   //4
        System.out.println(arithmetic_expression.infix("2*(3+4/2)"));   //10
        System.out.println(arithmetic_expression.infix("(2*3)+(8/4)")); //8
        System.out.println(arithmetic_expression.infix("(2*(3+4))/7")); //2
        System.out.println(arithmetic_expression.infix("4*(5+(6/3))")); //28
        System.out.println(arithmetic_expression.infix("4*5+(6/3)"));   //22
        System.out.println("\n\n\n");
        
        //Problem 3: evaluate arithmetic expression in postfix notation
        System.out.println("Problem 3: ");
        System.out.println(arithmetic_expression.postfix("3456+*+7+")); //54
        System.out.println(arithmetic_expression.postfix("23*42/+"));   //8
        System.out.println(arithmetic_expression.postfix("246+*5/"));   //4
        System.out.println(arithmetic_expression.postfix("2342/+*"));   //10
        System.out.println(arithmetic_expression.postfix("23*84/+"));   //8
        System.out.println(arithmetic_expression.postfix("234+*7/"));   //2
        System.out.println(arithmetic_expression.postfix("4563/+*"));   //28
        System.out.println(arithmetic_expression.postfix("4563/-*"));   //28
        System.out.println("\n\n\n");
        
    }
}

class min_stack 
{
    //Note: to write these methods in constant time, there has to exist a 
    //maximum capacity for the stack.
    
    final int CAP = 20; //this is the capacity of the stack
    
    int[] stack = new int[CAP]; 
    //this establishes the object as an single dimensional int array
    
    int[] minimum = new int[CAP];
    //This establishes a stack that will hold minimum values to help multiple
    //methods run in constant time while also keeping track of the minimum.
    
    int min_int = 0; //this holds the current minimum
    int top_s = -1; //this holds the current last element in stack array
    //Note: if this is equal to -1, then there are no elements in the stack
    int top_m = -1; //this holds the current last element in minimmum array
    
    //I dont use a constructor because there really is not anything to initialize.
    
    int len() //returns length
    {
        return top_s + 1;
    }
    
    boolean is_empty() //returns whether stack is empty or not
    {
        return top_s == -1;
    }
    
    void push(int e) //adds new element to end of stack
    {
        if (top_s == (CAP - 1)) //exception when stack is at capacity
        {
            throw new IndexOutOfBoundsException("Cannot push more than 20 ints.");
        }
        if (top_s == -1) //When entering the first element in the stack
        {
            this.min_int = e;
            this.minimum[0] = e;
            top_m++;            
        }
        else if (this.min_int >= e) //When entering a new element and it is the minimum
        {
            min_int = e;
            this.minimum[++top_m] = e;
        }
        
        this.stack[++top_s] = e; //Always assign e to the next placeholder
    }
    
    int pop() 
    {
        int r; //return value
        
        if (top_s == -1) //when the stack is empty
        {
            throw new IllegalStateException("Stack is empty, cannot pop.");
        }
        
        if (this.stack[top_s] == min_int) //when popping the current minimum
        {
            //note: this case is why we have the minimum array in the first place
            //it holds the last minimum when the current minimum is popped.
            r = min_int;
            this.minimum[top_m--] = 0;
            if (top_m != -1)
                min_int = this.minimum[top_m]; 
            else
                min_int = 0;
        }
        
        else //otherwise, just assign the current top element of the stack to r
        {
            r = this.stack[top_s];
            
        }
        
        //Always assign the current top to 0 for garbage collection
        this.stack[top_s--] = 0;
        return r;
    }
    
    int top() //returns top element without removing it from the stack
    {
        if (top_s == -1)
        {
            throw new IllegalStateException("Stack is empty, no top.");
        }
        else
        {
            return this.stack[top_s];
        }
    }
    
    int get_min() //returns current min
    {
        return min_int;
    }
    
    void print_stack() //This method is purely to make sure the program is working 
    {                 //properly. It outputs all the data members.
        System.out.print("Stack:  ");
        for (int i = 0; i < this.stack.length; i++)
        {
            System.out.print(this.stack[i] + "  ");
        }
        System.out.print("\nMinimum:  ");
        for (int i = 0; i < this.minimum.length;i++)
        {
            System.out.print(this.minimum[i] + "  ");
        }
        System.out.println("\nmin_int " + min_int);
        System.out.println("top_s " + top_s);
        System.out.println("top_m " + top_m);
    }
}

class str_stack
{
    //This is a stack class that deals with a stack of chars. I constructed it
    //to solve probems 2 and 3. They use elements other than integers and need
    //a different type of stack than min_stack.
    //Everything here is self-explanatory (except for the last method). This class 
    //contains the standard methods for a stack, it just uses strings.
    final int CAP = 20;
    String[] stack = new String[CAP];
    int top = -1;
    
    int length()
    {
        return top + 1;
    }
    
    boolean is_empty()
    {
        return top == -1;
    }
    
    void push(String e)
    {
        if (top + 1 == CAP)
        {
            throw new IndexOutOfBoundsException("Cannot push more than 20 chars.");
        }
        
        this.stack[++top] = e;
    }
    
    String pop()
    {
        if (top + 1 == CAP)
        {
            throw new IllegalStateException("Stack is empty, cannot pop.");
        }
        String e = this.stack[top];
        this.stack[top--] = "\0";
        return e;
    }
    
    String top()
    {
        if (top == -1)
        {
            return "\0";
        }
        return this.stack[top];
    }
    
    //This method is not standard for a stack, but is mainly for error checking
    //when running a str_stack
    void output()
    {
        System.out.print("Stack: ");
        for (int i = 0; i < 20; i++)
        {
            System.out.print(stack[i] + " ");
        }
        System.out.println();
    }
    
}

class arithmetic_expression
{
    //I made this private because it should only be used on operators, and it
    //is only used within loops analyzing characters in the user input strings.
    //This method is essential to my program, as it basically applies order of 
    //operations in its comparisons later on.
    static private int precedence(String op)
    {
        //Parenthesis - Exponents - Multiplication/Division - Addition/Subtraction
        if (op.equals("+") || op.equals("-")) //+ and - have lowest precedence
        {
            return 0;
        }
        
        else if (op.equals("*") || op.equals("/")) //* and / are next
        {
            return 1;
        }
        
        else if (op.equals("(") || op.equals(")")) //() are next (no exponents)
        {
            return 2;
        }
        return -1; //this should only be hit when there in a non-operator as an input
    }
    
    static double infix(String e)
    {
        //So this method essentially just converts infix to postfix and uses the 
        //postfix method to actually solve the problem. It would essentially need 
        //be converted anyway, so there is no reason to repeat the calculation 
        //part of the code.
        
        //So essentially, I use the Shunting-Yard algorithm to convert to postfix,
        //then I use the postfix method to calculate the expression.
        
        //This holds the operators
        str_stack operator = new str_stack();
        
        //This holds the postfix conversion
        String postfix = "";
        
        
        for (int i = 0; i < e.length(); i++) //iterate through the string
        {
            //
            String temp = e.charAt(i) + "";
            
            //when temp is an operator
            if (temp.equals("+") || temp.equals("-") || temp.equals("*") || temp.equals("/") )
            {
                //push if temp is of greater precedence
                if (precedence(temp) > precedence(operator.top()))
                {
                    operator.push(temp);
                }
                //if temp is of lesser precedence,
                else
                {
                    //place all lesser precedence operators in the postfix string
                    while (precedence(operator.top()) >= precedence(temp))
                    {
                        //unless it is an open parenthesis
                        if ((operator.top()).equals("("))
                        {
                            break;
                        }
                        else
                        {
                            postfix += operator.pop();
                        }
                    }
                    //then push the current temp
                    operator.push(temp);
                }
            }
            
            //if it is an open parenthesis, push
            else if (temp.equals("("))
            {
                operator.push(temp);
            }
            
            //when it is a closed parenthesis,
            else if (temp.equals(")"))
            {
                //add operators to postfix until you hit the open parenthesis
                while (!(operator.top()).equals("("))
                {
                    postfix += operator.pop();
                }
                operator.pop(); //discard of the open parenthesis
                //although this isn't directly stated, temp is discarded
            }
            
            //if there is an int, add to postfix
            else if (temp.equals("0") || temp.equals("1") || temp.equals("2") || temp.equals("3") || 
                    temp.equals("4") || temp.equals("5") || temp.equals("6") || temp.equals("7") ||
                    temp.equals("8") || temp.equals("9"))
            {
                postfix += temp;
            }
            
            //error when temp is not an int, operator, or parenthesis.
            else
            {
                throw new InputMismatchException("Wrong input: not a number or operator.");
            }
        }
        
        //Then just empty out the rest of operator into postfix.
        while (!operator.is_empty())
        {
            postfix += operator.pop();
        }
        
        //Then I let postfix calculate the expression
        return postfix(postfix);
    }
    
    static double postfix(String e)
    {
        //For this method, I used another algorithm I found online. I will credit
        //it here: https://www.geeksforgeeks.org/stack-set-4-evaluation-postfix-expression/
        //I will explain it here. I had to fix the pseudocode to fit the input of 
        //my program, which was a little odd due to the stack containing strings.
        //Also, i have to use doubles, as division is not closed in the integers.
        //So, many string to double conversions occur in my code.
        
        str_stack numbers = new str_stack(); //This holds everything but operators
        String res = ""; //This just holds the result
        
        //iterate through e
        for (int i = 0; i < e.length(); i++)
        {
            //temp holds the particular substring/char of e.
            String temp = e.charAt(i) + "";
            
            //When temp is an operator,
            if (temp.equals("*") || temp.equals("/") || temp.equals("+") || temp.equals("-"))
            {
                String r; //r will hold the result of the operation acting on
                          //two elements of  numbers.
                //if temp is multiplication,
                if (temp.equals("*"))
                {
                    //pop the top two elements of numbers and evaluate the expression
                    double a = Double.parseDouble(numbers.pop());
                    double b = Double.parseDouble(numbers.pop());
                    r = Double.toString(b * a);
                }
                //The next few if/else statements are the same, but for other operators
                else if (temp.equals("/"))
                {
                    double a = Double.parseDouble(numbers.pop());
                    double b = Double.parseDouble(numbers.pop());
                    r = Double.toString(b / a);
                }
                else if (temp.equals("+"))
                {
                    double a = Double.parseDouble(numbers.pop());
                    double b = Double.parseDouble(numbers.pop());
                    r = Double.toString(b + a);
                }
                //This is for subtraction
                else
                {
                    double a = Double.parseDouble(numbers.pop());
                    double b = Double.parseDouble(numbers.pop());
                    r = Double.toString(b - a);
                }
                //i holds the index of temp in e, so i + 1 == e.length() means
                //that we are evaluating the final element of e
                if (i + 1 == e.length())
                {
                    //so r should hold the overall result
                    //if postfix is written correctly, this should always work,
                    //as the last character should always be an operator
                    res = r;
                }
                //push r to numbers when finished
                numbers.push(r);
                
                //note: for this part of the code, if there is an error due to 
                //an empty stack, the user did not enter postfix correctly.
                //If there is an operator in the postfix string e, then 
            }
            
            //when temp is not an operator,
            else
            {
                //push to numbers so that the operators further in e can correctly
                //evaluate these numbers
                //Note that, if the last element in e is a number, res will remain 
                //empty. However, this is a fault of the user, as the last element
                //should never be a number in postfix.
                numbers.push(temp);
            }
        }
        
        //if res is the empty string, the user input a string that is not in postfix.
        if (res.equals(""))
        {
            throw new InputMismatchException("String argument not in postfix notation.");
        }
        
        return Double.valueOf(res); //This outputs the result of the expression
    }
}
