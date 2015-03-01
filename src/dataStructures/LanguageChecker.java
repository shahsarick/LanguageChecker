package dataStructures;

/**
 * 
 * @author Sarick Shah
 * This class uses methods created in the Stack class in order to determine whether a sequence 
 * of A's and B's falls into any of 4 languages which are:
 * L1: An equal number of A's and B's in any order.
 * L2: AnBn, while n>0
 * L3: AnB2n, while n>0
 * L4: (AnBm)p while n,m,p > 0.
 * L5: (AnBm)p while n != m & n, m>0 & p>1
 */
//testing github stuff
public class LanguageChecker{	
	
	private char[] characterArray;	
	
	//Read the string into an Array and convert any Lower case characters into upper case.
	
	public LanguageChecker(String line){
		//Make sure the string isn't null. 
		if (line == null){
			System.out.println("Error: Your string is null.");
		} else{
		this.characterArray = line.toCharArray();
		}
	}
	

	/**
	 * 
	 * @return Return true if equal number of A's and B's in any order.
	 */
	private boolean isL1(){
		// set len equal to the length of the array
		final int len = characterArray.length;
		// create a new stack which is equal to the length of the array, 
		//(also the same length as the input string).		
		Stack a_stack1 = new Stack(len);
		
		for (char c : characterArray){
			// if the array contains anything other than A or B, return false.
			if (c != 'A' && c !='B'){
				System.out.println("Error, you must only use A and B characters!");
				return false;
			} else {
				//We do this until the end of the array. If the top of the stack is the same,
				// as input, or the stack is empty, push the character into a_stack. 
				//If the character is different, pop.
				if(a_stack1.peek() == c || a_stack1.empty()){
					a_stack1.push(c);
				} else {
					a_stack1.pop();
				}
			}			
		}
		//if there are an equal number of a's and b's, the stack will be empty at the end of the array.
		if (a_stack1.empty()){
			return true;
		} else {
			return false;
		}		
	}
	/**
	 * 
	 * @return Returns true if the string follows AnBn where n>0.
	 */
	
	private boolean isL2(){
		final int len = characterArray.length;
		//Create a new stack which is equal to the length of the array.
		Stack a_stack2 = new Stack(len);
		boolean firstCheck = false;
		for (char c : characterArray){
			if (c != 'A' && c !='B'){
				return false;
			} 	
			//if the character is an A go through this loop.
			else if(c == 'A'){
				while(firstCheck == true){
					return false;
				}
				// if stack is empty or the top of stack is equal A, push.
				if(a_stack2.empty()||a_stack2.peek()== c){
						a_stack2.push(c);					
						
				}					
			}
			//if the character is a B, go through this loop
			else if(c =='B'){
				//if the stack is empty, the first character is a B which violates L2
				//and returns a false
				if (a_stack2.empty()){
					return false;
				}
				//if the top of the stack is an A, pop, and set the nextCheck to true
				//this stops it from reading any A's after the B's which would violate L2. 
				if (a_stack2.peek()=='A'){
					a_stack2.pop();						
					firstCheck = true;
				}
			}
		}
		// If there was an equal number of B's as A's, you would return an empty stack.		
		if(a_stack2.empty()){
			return true;
		} 
		else {
			return false;
		}	
		
	}
	/**
	 * 
	 * @return Return true if the string follows AnB2n n>0.
	 */
	private boolean isL3(){
		final int len = characterArray.length;
		//create a stack to hold A's and another stack to hold B's
		Stack a_stack3 = new Stack(len);
		Stack b_stack3 = new Stack(len);
		
		boolean firstCheck = false;
		for (char c : characterArray){
			if (c != 'A' && c !='B'){
				return false;
			} 	
			// if stack for A's is empty or the top of stack is equal A, push.
			else if(c == 'A'){
				while(firstCheck == true){
					return false;
				}			
				if(a_stack3.empty()||a_stack3.peek()== c){
						a_stack3.push(c);					
						
				}					
			}
			// When the character is a B and the A stack isn't empty, 
			// push the B value into the B stack until the end of the array.
			// Set the check value to true because no A's can follow.
			else if(c =='B'){				
				if (a_stack3.empty()){
					return false;
				}				
				if (a_stack3.peek()=='A'){
					b_stack3.push(c);
					firstCheck = true;
				}
			}
		}
		//If the A stack and B stack are filled, we then pop one A and 2 B's
		while (!a_stack3.empty() && !b_stack3.empty()){
			a_stack3.pop();
			b_stack3.pop();
			//make sure there aren't B <= A .
			if (b_stack3.empty()){
				return false;
			} else {
				b_stack3.pop();
			}
		}	
		//If both stacks are empty, it follows AnB2n
		if(a_stack3.empty() && b_stack3.empty()){
			return true;
		} 
		else {
			return false;
		}			
	}
	/**
	 * A copy method we will use to store tempStacks in order
	 * to compare and make sure the string fulfills L4 requirements.
	 * @param Stack s which is set to the maxSize from the Stack Class
	 * @return Return the tempStack which contains the copied items.
	 */	
	static private Stack copy(Stack s){
		Stack tempStack = new Stack(s.maxSize);
		
		while(!s.empty()){
			tempStack.push(s.pop());
		}		
		return tempStack;
	}
	
	int m = 0;
	int n = 0;
	int p = 0;
	
	private boolean isL4(){

		
		final int len = characterArray.length;

		boolean firstCheck = true;
		Stack a_stack4 = new Stack(len);
		Stack b_stack4 = new Stack(len);
		//the temp stacks we get using the copy method in order to check against.
		Stack tempA = copy(a_stack4);
		Stack tempB = copy(b_stack4);
		// a stack that carries the full input.
		Stack fullStack = new Stack(len);
		int d = 0;
		//fill up the full stack here as long as the characters are only A's and B's.
		for (char c : characterArray){

			if (c != 'A' && c != 'B'){
				return false;
			} else {
				fullStack.push(c);
				d = fullStack.top;
			}
		}
		//If the string starts with B's return false.
		if (fullStack.peek() != 'B'){
			return false;
		}
		//the temp stack holds the stack we iterate through, then we compare and as 
		//long as they're equal, L4 will be true. If the stack is empty we should also break the loop, and if 
		//our check statement is false, we should break the loop as well.

		while (firstCheck || (tempA.top == a_stack4.top && !fullStack.empty() && tempB.top == b_stack4.top)){
			//if the fullstack is already empty, p = 1 so break. 
			if (fullStack.empty()){
				break;
			} 
			//copy the initially empty a and b stacks to the temp stacks.
			tempA = copy(a_stack4);
			tempB = copy(b_stack4);
			a_stack4 = new Stack(len);
			b_stack4 = new Stack(len);

			//check to make sure the top position of the fullstack is less than the character array length. 
			if (fullStack.top < len){
				firstCheck = false;
			} 
			// while the top of the fullstack is a B, push B onto the B stack and pop off the fullStack.
			while (fullStack.peek() == 'B'){
				// first substring push all B characters to stack. This is m.
				b_stack4.push(fullStack.pop());				
			}
			// while the top of the fullstack is an A, push A onto the A stack and pop off the fullStack.
			while (fullStack.peek() == 'A' ){
				// push all A's to stack, this is n.
				a_stack4.push(fullStack.pop());	

			}


			// set the values for m n and p.
			m = b_stack4.top;
			n = a_stack4.top;
			if(m + n != 0){
				p = d / (m+n);
			} 
		}
		//at the end, the fullstack should be empty, the a and b stacks should be equal to the temps, and m and n shouldn't be equal.
		if((fullStack.empty() || a_stack4.top == tempA.top && b_stack4.top == tempB.top)  && n > 0 && m > 0 && p > 0){
			return true;
		} else {
			return false;
		}
	}
		
	/**
	 * In this Lanuage, we want to test for repeating pattern of A's and B's where n!= m & n,m>0 & p>1
	 * @return True if the string follows these parameters.
	 */
	private boolean isL5(){		
        final int len = characterArray.length;	
        boolean firstCheck = true;
	    Stack a_stack5 = new Stack(len);
	    Stack b_stack5 = new Stack(len);
	    Stack tempA = copy(a_stack5);
	    Stack tempB = copy(b_stack5);
	    Stack fullStack = new Stack(len);
	    int d = 0;
	    
	    for(char c : characterArray){
			
	    	fullStack.push(c);
	    	d = fullStack.top;
	    }
	        	
	    while(firstCheck ||(tempA.top == a_stack5.top && !fullStack.empty() && tempB.top == b_stack5.top)){
		    tempA = copy(a_stack5);
		    tempB = copy(b_stack5);
	    	a_stack5 = new Stack(len);
		    b_stack5 = new Stack(len);
		    
		    if(fullStack.top < len){
		    	firstCheck = false;
		    }
		
		    while (fullStack.peek() == 'B') {
		        /* first substring, push all B's to stack, this is m */
		        b_stack5.push(fullStack.pop());
		        
		    }	
		    
		    while (fullStack.peek() == 'A') {
		        /* first substring, push all As to stack, this is n */
		        a_stack5.push(fullStack.pop());			        
		    }
	    }
	    m = b_stack5.top;
		n = a_stack5.top;
		if(m + n != 0){
			p = d / (m+n);
		} 
	       	    
	    if(fullStack.top == 0 && (a_stack5.top == tempA.top && b_stack5.top == tempB.top && m!= n)){
	    	return true;
	    }
	    else{
	    	return false;
	    }	
	
	}
	
	
	/**
	 * This is the method that will be called upon in the main method, to tell us which languages the string is.
	 * @return Returns true or false depending on the language. If language 4, it also gives the m,n, and p coefficients.
	 */
	public void languageTest(){
		if(isL1() == true){
			System.out.println("L1 = true");
		} else {
			System.out.println("L1 = false");
		}
		if(isL2() == true){
			System.out.println("L2 = true");
		} else {
			System.out.println("L2 = false");
		}
		if(isL3() == true){
			System.out.println("L3 = true");
		} else {
			System.out.println("L3 = false");
		}
		if(isL4() == true){		 
			System.out.println("L4 = true");
			System.out.println("n= " + n);
			System.out.println("m = "+ m);
			System.out.println("p = "+ p);
		} else {
			System.out.println("L4 = false");
		}
		if(isL5() == true){		 
			System.out.println("L5 = true");
			System.out.println("n= " + n);
			System.out.println("m = "+ m);
			System.out.println("p = "+ p);
		} else {
			System.out.println("L5 = false");
		}
	}
	
}

