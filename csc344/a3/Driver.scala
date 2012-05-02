import scala.collection.mutable.ArrayBuffer;
import scala.collection.mutable.LinkedHashMap;
object Driver {
	def main(args: Array[String]) {
		
		
		
	
/*********************************
*	This part parses expression	*
*********************************/
	 //simplest case
	val str = "(and x (or x (and y (not z)))";
	val str2 = "(and (and z 0) (or x 1)))"; //case with expression on th right side
	val str3 = "(and (and (or x 1) z) z)"; //case with expression ont he left side
	val str4 = "(and (and y z) (and x w))"; //(and (and y (and (not (and x y)) z))(and x y))
	val str5 = "(and (and a (and g z)) x)";
	val str6 = "(or x y)";
	val str7 = "(or x (or y z))";	
	val str8 = "(not x)";
	val str9 = "(not (and y z))";
	val str10 = "((x 1) (y 0))";
	val str11 = "((y 0))";
	
	
	
	var i = 0; //just counter used to loop through the finished parsed array to make a list
	//var position = 0; //this is used for when passing it in the parser to start off at zero
	var ho = new ArrayBuffer[String]();//finished array should be here
	
	
	def stringParse ( exp: String, expreshHolder: ArrayBuffer[String] ): ArrayBuffer[String] = { //takes two arguments, string, arraybuffer
		var returnString = false; if(expreshHolder.length != 0) returnString = true;
		var str: String = exp; //string that it traverses through
		var temp = expreshHolder; //holder of the arraybuffer which holds the expressions without paren
		var b = 0; //used for substring and to make 'and', 'or', 'not'
		var arrayCounter = if(expreshHolder.length == 0) 0 else expreshHolder.length;//used just to follow which is the index currently pointed to by the array to complete 'and' 'not' 'or'
		
		if(str(b) == '(') {
			b+=1;
			while(str(b) == ' ' || str(b)==')'){b = b + 1;} //just used to skip any spaces or right parens that my be in the way
			if(str(b) == 'a') {
				temp += str(b).toString; //put 'a' in this index of ArrayBuffer
				b+=1; 
				temp(arrayCounter) = temp(arrayCounter) + str(b).toString; //concatenates 2nd char in and
				b+=1; 
				temp(arrayCounter) = temp(arrayCounter) + str(b).toString; //concatenates 3rd char in and
				b+=1; 
				while(str(b) == ' ' || str(b) == ')') {b+=1;} //used to skip any whitespace that may be there
				if(str(b) == '(') { 
					//println("This prints out what is inside temp"); for(test<- temp) println(test);//**************************************
					temp = stringParse(str.substring(b), temp); 
					str = temp(temp.length-1); 
					temp.remove(temp.length-1);
					b = 0;
					arrayCounter = temp.length; 
					} else {
						temp += str(b).toString; 
						arrayCounter+=1; b+=1; }
						
				while(str(b) == ' ' || str(b) == ')') {b+=1;} //used to skip any whitespace that may be there
					if(str(b) == '(') {
						//println("What str(b) is which shouldn't be which is why the recursion case is running"); println(str);
						//println("This prints out what is inside temp"); for(test<- temp) println(test);//*************************************** 
						temp = stringParse(str.substring(b), temp); 
						str = temp(temp.length-1);
						temp.remove(temp.length-1);
						b = 0;
						arrayCounter = temp.length; 
						} else {
							//println("What str(b) is the second running time"); println(str);//**************************************************
							temp += str(b).toString; 
							arrayCounter+=1; b+=1; }
				
				
			} else if(str(b) == 'o') {
				temp += str(b).toString; //put 'a' in this index of ArrayBuffer
				b+=1; 
				temp(arrayCounter) = temp(arrayCounter) + str(b).toString; //concatenates 2nd char in and
				b+=1; 
				 
				while(str(b) == ' ' || str(b) == ')') {b+=1;} //used to skip any whitespace that may be there
				if(str(b) == '(') { 
					//println("This prints out what is inside temp"); for(test<- temp) println(test);//**************************************
					temp = stringParse(str.substring(b), temp); 
					str = temp(temp.length-1); 
					temp.remove(temp.length-1);
					b = 0;
					arrayCounter = temp.length; 
					} else {
						temp += str(b).toString; 
						arrayCounter+=1; b+=1; }
						
				while(str(b) == ' ' || str(b) == ')') {b+=1;} //used to skip any whitespace that may be there
					if(str(b) == '(') {
						//println("What str(b) is which shouldn't be which is why the recursion case is running"); println(str);
						//println("This prints out what is inside temp"); for(test<- temp) println(test);//*************************************** 
						temp = stringParse(str.substring(b), temp); 
						str = temp(temp.length-1);
						temp.remove(temp.length-1);
						b = 0;
						arrayCounter = temp.length; 
						} else {
							//println("What str(b) is the second running time"); println(str);//**************************************************
							temp += str(b).toString; 
							arrayCounter+=1; b+=1; }
			} else if(str(b) == 'n') {
				
				
				temp += str(b).toString; //put 'a' in this index of ArrayBuffer
				b+=1; 
				temp(arrayCounter) = temp(arrayCounter) + str(b).toString; //concatenates 2nd char in and
				b+=1; 
				temp(arrayCounter) = temp(arrayCounter) + str(b).toString; //concatenates 3rd char in and
				b+=1; 
				while(str(b) == ' ' || str(b) == ')') {b+=1;} //used to skip any whitespace that may be there
				if(str(b) == '(') { 
					//println("This prints out what is inside temp"); for(test<- temp) println(test);//**************************************
					temp = stringParse(str.substring(b), temp); 
					str = temp(temp.length-1); 
					temp.remove(temp.length-1);
					b = 0;
					arrayCounter = temp.length; 
					} else {
						temp += str(b).toString; 
						arrayCounter+=1; b+=1; }
						
			}//do the 'not', and 'or' parts right after this
			
			if(returnString == false) temp; else {temp += str.substring(b+1); temp;} //this checks whether it needs to return a string in the end in order to keep track of where in the string we are currently reading
	
		} else { var fail = new ArrayBuffer[String]; fail +="failed"; fail;}		
	
	}//end of stringParse Method
	
/****************************
*	end of expression parser *
****************************/

/*****************************************
*				Start binding parser		 	  *	
*****************************************/

	def bindingParser( exp: String, expreshHolder: ArrayBuffer[String]): ArrayBuffer[String] = {
		val str = exp;
		var temp = expreshHolder;
		var i = 0;
		var secondOne = false;
		for(thing <- str ) {
			if(thing != '(' && thing != ')' && thing != ' ') {/*CONSIDER USING MAP INSTEAD TO EASILY ACCES WHAT BELONGS TO WHAT AND NOT HAVE TO GUESS ANYTHING*/
				temp += thing.toString;
			}
		}
		
		temp;
		
		
	}//end of bindingParser function

/*****************************************
*				End of binding parser		  *
*****************************************/





def simplify(kickass: ArrayBuffer[LinkedHashMap[String, String]], startedFrom: Int): String = {
	val symHolder: Array[String] = new Array[String](2);
	var leftSym = "";
	var rightSym = "";
	var exp: ArrayBuffer[LinkedHashMap[String, String]] = kickass;
	var result: String = "";

	



	
	if(exp(0).get("and") != None) { /*******************************************************************************************************************************************/
	
		if((exp(1).get("and") == None && exp(1).get("or") == None && exp(1).get("not") == None) && (exp(2).get("and") == None && exp(2).get("or") == None && exp(2).get("not") == None)) {
			
			for((k, v) <- exp(1)) { //this oen is for the left symbol
				symHolder(0) = v;
			}
			for((k, v) <- exp(2)) { //this oen is for the right symbol
				symHolder(1) = v;
			}
			
			if(symHolder(0) == "0" || symHolder(1) == "0") {
				result = "0"; /*println("should be putting zero into result");*/
			} else if (symHolder(0) == "1") {
				result = symHolder(1); /*println("should be putting opposite of left side");*/
			} else if(symHolder(1) == "1") {
				result = symHolder(0); /*println("should be putting opposite if right side");*/
			}
				//end of if statement with the simplest case
		} else
		if((exp(1).get("and") == None && exp(1).get("or") == None && exp(1).get("not") == None) && (None.equals(exp(2).get("and")) == false  || None.equals(exp(2).get("or")) == false || None.equals(exp(2).get("not")) == false)) {
			
			for((k, v) <- exp(1)) { //this oen is for the left symbol
				symHolder(0) = v;
			}
			leftSym = symHolder(0);
			exp.remove(0);//removes the and
			exp.remove(0);//removes the first symbol
			rightSym = simplify(exp, startedFrom +1);
			
			if(rightSym.length == 1){
				
			if(leftSym == "0" || rightSym == "0") {
				result = "0";
			} else if(leftSym == "1") {
				result = rightSym;
			} else if(rightSym == "1") {
				result = leftSym;
			} 
			
			} else {
				if(rightSym(0).toString == "`") result = rightSym;
				//println("Should be running this for when a not has been found with a `");
			}
				
			//end of case with the symbol on the left and another expression on the right
		} else 
		if((exp(1).get("and")!= None || exp(1).get("or")!= None || exp(1).get("not")!= None) && (exp(exp.length-1).get("and") == None && exp(exp.length-1).get("or") == None && exp(exp.length-1).get("not") == None) && (exp(exp.length-2).get("not") == None)) {
			
			for((k, v) <- exp(exp.length-1)) { //this oen is for the left symbol
				symHolder(0) = v;
			}
			rightSym = symHolder(0);
			exp.remove(exp.length-1);
			exp.remove(0);
			leftSym = simplify(exp, startedFrom +1);
			
			if(leftSym.length == 1) {
			
			if(leftSym == "0" || rightSym == "0") {
				result = "0";
			} else if(leftSym == "1") {
				result = rightSym;
			} else if(rightSym == "1") {
				result = leftSym;
			}
			
			} else {
				if(leftSym(0).toString == "`") result = leftSym;
				//println("Should be running this for when a not has been found with a `");
			}
			//end of case wih the symbol on the right side and expression on the left
			
		} else {
			var copy: ArrayBuffer[LinkedHashMap[String, String]] = new ArrayBuffer[LinkedHashMap[String, String]]();
			exp.remove(0);//just removes the first and since we know we are anding the result anyways
			for(i <- exp) {
				copy += i;
			} //this for loop makes a copy
			
			var place = 0; //this is used to check where the place here the last symbol is located on the left expression within an exprssion
			var numSyms = 0;
			var typeSym = 0;
			while((numSyms == 2 && (typeSym == 2 || typeSym == 1))||(numSyms == 3 && typeSym > 2)) {
				if(copy(place).get("and") != None || copy(place).get("or") != None ) {
					typeSym += 1;
					numSyms = 0; //resets this because the symbols should only reach two when it is two in a row meaning the end of the whole left expression
					place+=1; //increments the place in the ArrayBuffer
				} else if(copy(place).get("not") != None) {
					place+=1;
				} else {
					numSyms += 1;
					place+=1;
				}
			}//end of loop to figure out the left expression from the right one
			
			//Get the left expression by erasing the right side
			var end = copy.length-1;
			while(end >= place) {
				copy.remove(end);
				end = end - 1;
			}
			//Get the right expression by erasing the left side
			var start = 0;
			while(start < place) {
				exp.remove(0);
				start+=1;
			}
			
			leftSym = simplify(copy, startedFrom +1);
			rightSym = simplify(exp, startedFrom +1);
			
			if(leftSym.length == 1 && rightSym.length == 1) {
			
			if(leftSym == "0" || rightSym == "0") {
				result = "0";
			} else if(leftSym == "1") {
				result = rightSym;
			} else if(rightSym == "1") {
				result = leftSym;
			}
			
			}else {
				if(leftSym(0).toString == "`" && rightSym.length == 1) result = leftSym;
				else if(leftSym.length == 1 && rightSym(0).toString == "`") result = rightSym;
			}
			
		}//end of case with the expressions on both sides	
		
		
	} //end of AND if********************************************************************************************************************************************************
	
	
	
	if(exp(0).get("or") != None) { /*******************************************************************************************************************************************/
	
		if((exp(1).get("and") == None && exp(1).get("or") == None && exp(1).get("not") == None) && (exp(2).get("and") == None && exp(2).get("or") == None && exp(2).get("not") == None)) {
			
			for((k, v) <- exp(1)) { //this oen is for the left symbol
				symHolder(0) = v;
			}
			for((k, v) <- exp(2)) { //this oen is for the right symbol
				symHolder(1) = v;
			}
			
			if(symHolder(0) == "1" || symHolder(1) == "1") {
				result = "1"; /*println("should be putting zero into result");*/
			} else if (symHolder(0) == "0") {
				result = symHolder(1); /*println("should be putting opposite of left side");*/
			} else if(symHolder(1) == "0") {
				result = symHolder(0); /*println("should be putting opposite if right side");*/
			}
				//end of if statement with the simplest case
		} else
		if((exp(1).get("and") == None && exp(1).get("or") == None && exp(1).get("not") == None) && (None.equals(exp(2).get("and")) == false  || None.equals(exp(2).get("or")) == false || None.equals(exp(2).get("not")) == false)) {
			
			for((k, v) <- exp(1)) { //this oen is for the left symbol
				symHolder(0) = v;
			}
			leftSym = symHolder(0);
			exp.remove(0);//removes the and
			exp.remove(0);//removes the first symbol
			rightSym = simplify(exp, startedFrom +1);
			
			if(rightSym.length == 1) {
			
			if(leftSym == "1" || rightSym == "1") {
				result = "1";
			} else if(leftSym == "0") {
				result = rightSym;
			} else if(rightSym == "0") {
				result = leftSym;
			}	
			
			} else {
				if(rightSym(0).toString == "`") result = leftSym;
				//println("Should be running this for when a not has been found with a `");
			}
			//end of case with the symbol on the left and another expression on the right
		} else 
		if((exp(1).get("and")!= None || exp(1).get("or")!= None || exp(1).get("not")!= None) && (exp(exp.length-1).get("and") == None && exp(exp.length-1).get("or") == None && exp(exp.length-1).get("not") == None) && (exp(exp.length-2).get("not") == None)) {
			
			for((k, v) <- exp(exp.length-1)) { //this oen is for the left symbol
				symHolder(0) = v;
			}
			rightSym = symHolder(0);
			exp.remove(exp.length-1);
			exp.remove(0);
			leftSym = simplify(exp, startedFrom +1);
			
			if(leftSym.length == 1) {
			
			
			if(leftSym == "1" || rightSym == "1") {
				result = "1";
			} else if(leftSym == "0") {
				result = rightSym;
			} else if(rightSym == "0") {
				result = leftSym;
			}
			
			} else {
				if(leftSym(0).toString == "`") result = rightSym;
				//println("Should be running this for when a not has been found with a `");
			}
			//end of case wih the symbol on the right side and expression on the left
			
		} else {
			var copy: ArrayBuffer[LinkedHashMap[String, String]] = new ArrayBuffer[LinkedHashMap[String, String]]();
			exp.remove(0);//just removes the first and since we know we are anding the result anyways
			for(i <- exp) {
				copy += i;
			} //this for loop makes a copy
			
			var place = 0; //this is used to check where the place here the last symbol is located on the left expression within an exprssion
			var numSyms = 0;
			var typeSym = 0;
			while((numSyms == 2 && (typeSym == 2 || typeSym == 1))||(numSyms == 3 && typeSym > 2)) {
				if(copy(place).get("and") != None || copy(place).get("or") != None ) {
					typeSym += 1;
					numSyms = 0; //resets this because the symbols should only reach two when it is two in a row meaning the end of the whole left expression
					place+=1; //increments the place in the ArrayBuffer
				} else if(copy(place).get("not") != None) {
					place+=1;
				} else {
					numSyms += 1;
					place+=1;
				}
			}//end of loop to figure out the left expression from the right one
			
			//Get the left expression by erasing the right side
			var end = copy.length-1;
			while(end >= place) {
				copy.remove(end);
				end = end - 1;
			}
			//Get the right expression by erasing the left side
			var start = 0;
			while(start < place) {
				exp.remove(0);
				start+=1;
			}
			
			leftSym = simplify(copy, startedFrom +1);
			rightSym = simplify(exp, startedFrom +1);
			
			if(leftSym.length == 1 && rightSym.length == 1) {
			
			if(leftSym == "1" || rightSym == "1") {
				result = "1";
			} else if(leftSym == "0") {
				result = rightSym;
			} else if(rightSym == "0") {
				result = leftSym;
			}
			
			} else {
				if(leftSym(0).toString == "`") result = rightSym;
				else if(rightSym(0).toString == "`") result = leftSym;
			}
			
		}//end of case with the expressions on both sides	
		
		
	} //end of OR if********************************************************************************************************************************************************
	
	
	if(exp(0).get("not") != None) { /*******************************************************************************************************************************************/
		
		if((exp(1).get("and") == None && exp(1).get("or") == None && exp(1).get("not") == None)) {
			for((k, v) <- exp(1)) { //this one is for the left symbol
				symHolder(0) = v;
			}
			leftSym = symHolder(0);
			if(leftSym == "0") result="1"
			else if(leftSym == "1") result = "0";
			else result = "`"+leftSym;/*ADDED THIS PART TO RETURN A NOTTED VARIABLE SO I KNOW WHETHER ITS NOTTED OR NOT*/
			
		} else if((exp(1).get("and") != None || exp(1).get("or") != None || exp(1).get("not") != None)){
			exp.remove(0);
			leftSym = simplify(exp, startedFrom +1);
			if(leftSym == "0") result="1"
			else if(leftSym == "1") result = "0";
			else result = "`"+leftSym; /*ADDED THIS TO RETURN A NOTTED VARIABLE INCASE THERE IS NOT 1 OR 0*/
		}
		
	
	
	}//end of NOT if************************************************************************************************************************************
	
	
		
	if(startedFrom == 1) {
		if(result(0).toString == "`") {
		result = "Not " + result(1).toString;
		result;} else result;
		
	} else result;
	
}//end of simplify function





















	
	ho = stringParse("(and x (or x (and y (not z)))", ho); //holds symbols
	for(test <- ho){ print(test); print(" ");}
	println("");
	
	val hold = bindingParser( "((y 0))", new ArrayBuffer[String]);
	for(test <- hold){ print(test); print(" ")} //holds bindings
	 println("");
	//right below is used to turn the bindings into a mapping to make it easier to traverse
	val bindings = new LinkedHashMap[String, String]();
	var z = 0;
	while(z < hold.length) {
		bindings(hold(z)) = hold(z+1);
		z+=2;
	}
	
	
	//map the strings together in some way and then make the resulting mapping into new expression trees.
	
	/******************************************************************************/
	
	for((k, v) <- bindings) {
		print("Key: "); print(k); print(" "); print("Value: "); println(v);
	}
	println("");
	println("Completed array to parse and end this:");
	
	
	
	

	val complete = new ArrayBuffer[LinkedHashMap[String, String]]();
	
	for(sym <- ho) {
		var neverMatched = true;
		for((k, v) <- bindings) {
			
			if(sym == k) {
				var temp = new LinkedHashMap[String, String](); 
				temp(sym) = v;
				complete+=temp;
				neverMatched = false;
				 /*THIS ONE CHECKS IF THEY ARE EQUAL AND ADDS THE KEY VALUE PAIR TO THE ARRAY*/
				}
					
		}//inner for loop for bindings
			if(neverMatched == true) {
				var temp = new LinkedHashMap[String, String](); //THIS ONE CHECKS IF THEY ARE NOT EQUAL AND MAKES THE KEY VALUE PAIR JUST THE SAME KEY
				temp(sym) = sym;
				complete += temp;
			}
		
	}//end of main for loop
	
	
	
	 var work = 0;
	while(work < complete.length) {
		for((k, v) <- complete(work)) {
			print("Key: "); print(k); print(" "); print("Value: "); println(v);
		}
		work+=1;
	}
	
	/********************This actually does the operations which come out witht the simplified versions*******************/
	
	val copy = new ArrayBuffer[LinkedHashMap[String, String]]();
	for(i <- complete) {
		copy+=i;
	}
	
	println("");
	val death = simplify(copy,1);
	print("RESULT IS: ")
	println(death);
	
	println("");
	println("Using this to show that after the simplification the key and values are still there:");
	
	var moreWork = 0;
	while(moreWork < complete.length) {
		for((k, v) <- complete(moreWork)) {
			print("Key: "); print(k); print(" "); print("Value: "); println(v);
		}
		moreWork+=1;
	}
	
		
		
	}//end of main method
}//end of tree class