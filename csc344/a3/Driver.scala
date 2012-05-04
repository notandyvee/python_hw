import scala.collection.mutable.ArrayBuffer;
import scala.collection.mutable.LinkedHashMap;
object Driver {
	def main(args: Array[String]) {

	val str = "(and x (or x (and y (not z)))";
	val str2 = "(and (and z 0) (or x 1)))";
	val str3 = "(and (and (or x 1) z) z)"; 
	val str4 = "(and (and y z) (and x w))";
	val str5 = "(and (and a (and g z)) x)";
	val str6 = "(or x y)";
	val str7 = "(or x (or y z))";	
	val str8 = "(not x)";
	val str9 = "(not (and y z))";
	val str10 = "((x 1) (y 0))";
	val str11 = "((y 0))";
	
	
	
	var i = 0;
	var ho = new ArrayBuffer[String]();
	
	
	def stringParse ( exp: String, expreshHolder: ArrayBuffer[String] ): ArrayBuffer[String] = { 
		var returnString = false; if(expreshHolder.length != 0) returnString = true;
		var str: String = exp;
		var temp = expreshHolder;
		var b = 0;
		var arrayCounter = if(expreshHolder.length == 0) 0 else expreshHolder.length;
		
		if(str(b) == '(') {
			b+=1;
			while(str(b) == ' ' || str(b)==')'){b = b + 1;}
			if(str(b) == 'a') {
				temp += str(b).toString;
				b+=1; 
				temp(arrayCounter) = temp(arrayCounter) + str(b).toString;
				b+=1; 
				temp(arrayCounter) = temp(arrayCounter) + str(b).toString;
				b+=1; 
				while(str(b) == ' ' || str(b) == ')') {b+=1;}
				if(str(b) == '(') { 
					
					temp = stringParse(str.substring(b), temp); 
					str = temp(temp.length-1); 
					temp.remove(temp.length-1);
					b = 0;
					arrayCounter = temp.length; 
					} else {
						temp += str(b).toString; 
						arrayCounter+=1; b+=1; }
						
				while(str(b) == ' ' || str(b) == ')') {b+=1;}
					if(str(b) == '(') { 
						temp = stringParse(str.substring(b), temp); 
						str = temp(temp.length-1);
						temp.remove(temp.length-1);
						b = 0;
						arrayCounter = temp.length; 
						} else {
							temp += str(b).toString; 
							arrayCounter+=1; b+=1; }
				
				
			} else if(str(b) == 'o') {
				temp += str(b).toString;
				b+=1; 
				temp(arrayCounter) = temp(arrayCounter) + str(b).toString; 
				b+=1; 
				 
				while(str(b) == ' ' || str(b) == ')') {b+=1;}
				if(str(b) == '(') { 
					temp = stringParse(str.substring(b), temp); 
					str = temp(temp.length-1); 
					temp.remove(temp.length-1);
					b = 0;
					arrayCounter = temp.length; 
					} else {
						temp += str(b).toString; 
						arrayCounter+=1; b+=1; }
						
				while(str(b) == ' ' || str(b) == ')') {b+=1;} 
					if(str(b) == '(') {
						 
						temp = stringParse(str.substring(b), temp); 
						str = temp(temp.length-1);
						temp.remove(temp.length-1);
						b = 0;
						arrayCounter = temp.length; 
						} else {
							temp += str(b).toString; 
							arrayCounter+=1; b+=1; }
			} else if(str(b) == 'n') {
				
				
				temp += str(b).toString; 
				b+=1; 
				temp(arrayCounter) = temp(arrayCounter) + str(b).toString; 
				b+=1; 
				temp(arrayCounter) = temp(arrayCounter) + str(b).toString; 
				b+=1; 
				while(str(b) == ' ' || str(b) == ')') {b+=1;}
				if(str(b) == '(') { 
					temp = stringParse(str.substring(b), temp); 
					str = temp(temp.length-1); 
					temp.remove(temp.length-1);
					b = 0;
					arrayCounter = temp.length; 
					} else {
						temp += str(b).toString; 
						arrayCounter+=1; b+=1; }
						
			}
			
			if(returnString == false) temp; else {temp += str.substring(b+1); temp;}
	
		} else { var fail = new ArrayBuffer[String]; fail +="failed"; fail;}		
	
	}


	def bindingParser( exp: String, expreshHolder: ArrayBuffer[String]): ArrayBuffer[String] = {
		val str = exp;
		var temp = expreshHolder;
		var i = 0;
		var secondOne = false;
		for(thing <- str ) {
			if(thing != '(' && thing != ')' && thing != ' ') {
				temp += thing.toString;
			}
		}
		
		temp;	
	}


def simplify(kickass: ArrayBuffer[LinkedHashMap[String, String]], startedFrom: Int): String = {
	val symHolder: Array[String] = new Array[String](2);
	var leftSym = "";
	var rightSym = "";
	var exp: ArrayBuffer[LinkedHashMap[String, String]] = kickass;
	var result: String = "";

	if(exp(0).get("and") != None) { 
	
		if((exp(1).get("and") == None && exp(1).get("or") == None && exp(1).get("not") == None) && (exp(2).get("and") == None && exp(2).get("or") == None && exp(2).get("not") == None)) {
			
			for((k, v) <- exp(1)) { 
				symHolder(0) = v;
			}
			for((k, v) <- exp(2)) { 
				symHolder(1) = v;
			}
			
			if(symHolder(0) == "0" || symHolder(1) == "0") {
				result = "0"; 
			} else if (symHolder(0) == "1") {
				result = symHolder(1); 
			} else if(symHolder(1) == "1") {
				result = symHolder(0); 
			}
		} else
		if((exp(1).get("and") == None && exp(1).get("or") == None && exp(1).get("not") == None) && (None.equals(exp(2).get("and")) == false  || None.equals(exp(2).get("or")) == false || None.equals(exp(2).get("not")) == false)) {
			
			for((k, v) <- exp(1)) { 
				symHolder(0) = v;
			}
			leftSym = symHolder(0);
			exp.remove(0);
			exp.remove(0);
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
			}
		} else 
		if((exp(1).get("and")!= None || exp(1).get("or")!= None || exp(1).get("not")!= None) && (exp(exp.length-1).get("and") == None && exp(exp.length-1).get("or") == None && exp(exp.length-1).get("not") == None) && (exp(exp.length-2).get("not") == None)) {
			
			for((k, v) <- exp(exp.length-1)) {
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
			}
			
		} else {
			var copy: ArrayBuffer[LinkedHashMap[String, String]] = new ArrayBuffer[LinkedHashMap[String, String]]();
			exp.remove(0);
			for(i <- exp) {
				copy += i;
			}
			
			var place = 0;
			var numSyms = 0;
			var typeSym = 0;
			while((numSyms == 2 && (typeSym == 2 || typeSym == 1))||(numSyms == 3 && typeSym > 2)) {
				if(copy(place).get("and") != None || copy(place).get("or") != None ) {
					typeSym += 1;
					numSyms = 0; 
					place+=1;
				} else if(copy(place).get("not") != None) {
					place+=1;
				} else {
					numSyms += 1;
					place+=1;
				}
			}
			var end = copy.length-1;
			while(end >= place) {
				copy.remove(end);
				end = end - 1;
			}
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
			
		}	
		
		
	} 
	
	
	
	if(exp(0).get("or") != None) { 
	
		if((exp(1).get("and") == None && exp(1).get("or") == None && exp(1).get("not") == None) && (exp(2).get("and") == None && exp(2).get("or") == None && exp(2).get("not") == None)) {
			
			for((k, v) <- exp(1)) {
				symHolder(0) = v;
			}
			for((k, v) <- exp(2)) {
				symHolder(1) = v;
			}
			
			if(symHolder(0) == "1" || symHolder(1) == "1") {
				result = "1"; 
			} else if (symHolder(0) == "0") {
				result = symHolder(1); 
			} else if(symHolder(1) == "0") {
				result = symHolder(0);
			}
		} else
		if((exp(1).get("and") == None && exp(1).get("or") == None && exp(1).get("not") == None) && (None.equals(exp(2).get("and")) == false  || None.equals(exp(2).get("or")) == false || None.equals(exp(2).get("not")) == false)) {
			
			for((k, v) <- exp(1)) { 
				symHolder(0) = v;
			}
			leftSym = symHolder(0);
			exp.remove(0);
			exp.remove(0);
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
			}
		} else 
		if((exp(1).get("and")!= None || exp(1).get("or")!= None || exp(1).get("not")!= None) && (exp(exp.length-1).get("and") == None && exp(exp.length-1).get("or") == None && exp(exp.length-1).get("not") == None) && (exp(exp.length-2).get("not") == None)) {
			
			for((k, v) <- exp(exp.length-1)) {
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
			}
			
		} else {
			var copy: ArrayBuffer[LinkedHashMap[String, String]] = new ArrayBuffer[LinkedHashMap[String, String]]();
			exp.remove(0);
			for(i <- exp) {
				copy += i;
			} 
			
			var place = 0;
			var numSyms = 0;
			var typeSym = 0;
			while((numSyms == 2 && (typeSym == 2 || typeSym == 1))||(numSyms == 3 && typeSym > 2)) {
				if(copy(place).get("and") != None || copy(place).get("or") != None ) {
					typeSym += 1;
					numSyms = 0;
					place+=1; 
				} else if(copy(place).get("not") != None) {
					place+=1;
				} else {
					numSyms += 1;
					place+=1;
				}
			}
			var end = copy.length-1;
			while(end >= place) {
				copy.remove(end);
				end = end - 1;
			}
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
			
		}	
		
		
	}
	
	
	if(exp(0).get("not") != None) { 
		
		if((exp(1).get("and") == None && exp(1).get("or") == None && exp(1).get("not") == None)) {
			for((k, v) <- exp(1)) {
				symHolder(0) = v;
			}
			leftSym = symHolder(0);
			if(leftSym == "0") result="1"
			else if(leftSym == "1") result = "0";
			else result = "`"+leftSym;
			
		} else if((exp(1).get("and") != None || exp(1).get("or") != None || exp(1).get("not") != None)){
			exp.remove(0);
			leftSym = simplify(exp, startedFrom +1);
			if(leftSym == "0") result="1"
			else if(leftSym == "1") result = "0";
			else result = "`"+leftSym;
		}
	}	
	if(startedFrom == 1) {
		if(result(0).toString == "`") {
		result = "Not " + result(1).toString;
		result;} else result;
		
	} else result;
	
}


	
	ho = stringParse("(and x (or x (and y (not z)))", ho);
	for(test <- ho){ print(test); print(" ");}
	println("");
	
	val hold = bindingParser( "((y 0))", new ArrayBuffer[String]);
	for(test <- hold){ print(test); print(" ")} 
	 println("");
	val bindings = new LinkedHashMap[String, String]();
	var z = 0;
	while(z < hold.length) {
		bindings(hold(z)) = hold(z+1);
		z+=2;
	}
	
	
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
				}
					
		}
			if(neverMatched == true) {
				var temp = new LinkedHashMap[String, String]();
				temp(sym) = sym;
				complete += temp;
			}
		
	}	
	 var work = 0;
	while(work < complete.length) {
		for((k, v) <- complete(work)) {
			print("Key: "); print(k); print(" "); print("Value: "); println(v);
		}
		work+=1;
	}
	
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
	
		
		
	}
}