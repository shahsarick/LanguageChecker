package dataStructures;
/**
 * 
 * @author Sarick
 * A basic array implementation of the stack class.
 */

public class Stack{
	//size of the stack array
	int maxSize;
	//top of the stack
	int top;
	char[] charArray;
	
	//constructor that takes the argument as the max size n it will need to be
	public Stack(int n) {
		maxSize = n;
		charArray = new char[maxSize];
		top = 0;
	}
	      
	// Push an item to the top of the stack.
	public void push(char Character){
		if (top < maxSize){
			charArray[top] = Character;
			//add 1 to top for the next character to be inserted.
			top++;
		} else {
			System.out.println("Stack Overflow");
		}
		
		
	}
	
	
	public char peek(){
		
		if (!this.empty()){
			
			return (charArray[top-1]);
		
		} else {
			return '0';
			
		}

	}
	
	public char pop(){
		
		if(!this.empty()){
			char temp = this.peek();
			//charArray[top-1]=(Character) null;
			top--;
			return temp;
		} else {
			return '0';
			
		}
		
	}
	
	
	public boolean empty(){
		if (top==0){
			return true;
			
		} else {
			return false;
		}
		
	}
	
}
