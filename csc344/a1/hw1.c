// CSC 344 HW

/*********************
*    My Includes     *
*********************/
#include <stdio.h>
#include <string.h>
#include <stdbool.h>
#include <stdlib.h>

/********************
*    My Defines     *
********************/
#define k_num_of_keywords 35 //length of keywords char pointer array
#define k_length_of_string 20 //length of each string in each char pointer index




int main(int argc, const char * argv[]) {
	char toPrintOrNot[40];
	bool print = false;
	
	FILE *fp; //holds file pointer
	int c; //holds the character as it reads
	
	char *keywords[k_num_of_keywords]; //array of character pointers
	
	/**************************************
	**   VARIABLES BELOW CAN BE USED FOR **
	**   THE REMAINDER OF THIS PROGRAM	 **
	**************************************/
	int key_counter = 0;/*THIS CAN BE USED TO ACCESS EVERY STRING IN THE KEYWORDS ARRAY*/
	
	char temp[k_length_of_string]; //temp holder to hold individual strings before it is added to keywords array
	
	/*THIS VARIABLE CAN BE USED ANYWHERE 
	JUST TO HELP TERMINATE A STRING*/short count = 0; //counter to know when the end of a string is reached and add a terminating zero

	/***************************************
	*				       							*
	*   Step one: open file with keywords  *
	*				      							 *
	***************************************/
	fp = fopen("./keywords", "r");
	if(NULL == fp){
		printf("Failed to create file!!\n");
		exit(0);//if file isn't opened exit program because need the file open
	}
	
	/*************************************
	*    Step two: read the file a       *
	*    character at a time and put     *
	*    every keyword in a string array *
	*************************************/
	while((c = fgetc(fp)) != EOF){
	//starts reading a char at a time until 1 string is created to compare
	//and then it is added to the character pointer array
	//char temp[k_length_of_string];
	//short count = 0;

		if(c == '\n') {
			temp[count] = '\0';
			int len = strlen(temp);
			keywords[key_counter] = (char *)malloc(sizeof(char) * (len +1));
			memcpy(keywords[key_counter], temp, len +1);
			key_counter++;
			//now empty temp to reuse it again incase there are more strings left
			int x;
			for(x = 0; x < count; x++)
				temp[x] = '\0';
			count = 0;
			
		} else  {
			temp[count] = c;
			count++;
		}

	}//end of while loop for reading the whole file

	fclose(fp); //pretty sure this closes the file struct I opened for reading the keywords file
	
	
	
	
	printf("Would you like the optional keywords to be printed out: (Y)es or (N)o: ");
	fgets(toPrintOrNot, 20, stdin);
	int promptLength = strlen(toPrintOrNot);
	toPrintOrNot[promptLength - 1] = '\0';
	if(toPrintOrNot[0] == 'y' || toPrintOrNot[0] == 'Y')
		print = true;
	
	
	//let us open a new file
	FILE *f = fopen("testFunction", "r");
	
	bool key = false; //use this to check if I ever read a keyword to then know the very next thing must be an identifier
	
	char *keywords_holder[100];
	int key_holder_count = 0;
	
	char *iden_holder[100];
	int iden_holder_count = 0;
	
	char line[1000];
	char *token;
	int i, j;
	
	//******************************ADDED VARIABLES UNDER THIS**************************************
	bool comma = false;
	int num_of_commas = 0;
	bool prob_cast = false;
	//******************************ADDED VARIABLES ABOVE THIS
	
	
	
	while(fgets(line, 1000, f) != NULL) {
		
		token = strtok(line," \t\n{}()&*+=;");
		//printf("Very first thing in token: %s\n", token);
			
		
	//*****************************************************************************************************
		
		while(token != NULL) { //lets make sure token has a string token

			if(key==true) {
				
		//**********************************ADDED PART UNDER THIS*****************************************************************
				int length = strlen(token);
				int death;
				for(death = 0; death < length+1; death++) {
					if(token[death] == ',') {
						comma = true;
						num_of_commas++;
					}
					
				}//test for loop
				
		//*********************************^^^^ADDED PART ABOVE THIS^^^^^***********************************************************
		
		
				
		//*********************************ADDED PART UNDER THIS***************************************************************
				
				if(comma == false) { 
		//**********************************^^^^ADDED PART ABOVE THIS^^^^**************************************************************
					for(i = 0; i < key_counter; i++) {
						if(strcmp(token, keywords[i]) == 0) { //This one might not even be necessary because it doesn't take functions into account
							prob_cast = true;
						}	
					}
					
					for(i = 0; i < iden_holder_count; i++) {
						if(strcmp(token, iden_holder[i]) == 0) {
							prob_cast = true;
						}	
					}
				
				
				if(prob_cast == false){
				int len = strlen(token);
				iden_holder[iden_holder_count] = (char *)malloc(sizeof(char) * (len +1));
				memcpy(iden_holder[iden_holder_count], token, len +1);
				iden_holder_count++;
				} else {
					key_holder_count--; //go back to have access to the last thing put in which was a keyword and delete it
					free(keywords_holder[key_holder_count]); //frees up the malloc that was created and in essence making sure its gone
					prob_cast = false;	
				}
				
		//*************************ADDED PART UNDER HERE********************************************************		
			} else {
				int len2 = strlen(token);
				char *strings[num_of_commas+1];
				int h;
				for(h = 0; h < num_of_commas + 1; h++) {
					strings[h] = (char *)malloc(sizeof(char) * 100);
					}
				int strings_counter = 0; //keeps track of how many strings got split
				int character_index = 0; //keeps track of what character its on when in a specififc string index
				
				int abs;
				for(abs = 0; abs < len2+1; abs++) {
					if(token[abs] != ',') {
						strings[strings_counter][character_index] = token[abs];	
						character_index++;
					} else {
						strings[strings_counter][character_index] = '\0'; //gotta make sure to terminate the string
						strings_counter++; //now make sure to begin a new string
						character_index = 0;
					}
						
				}
				strings[strings_counter][character_index] = '\0'; //the last string would not be terminated, so this makes sure it does
				character_index = 0;
				strings_counter++;
				
				int l;
				for(l = 0; l < strings_counter; l++) {
					int len = strlen(strings[l]);
					iden_holder[iden_holder_count] = (char *)malloc(sizeof(char) * (len +1));
					strcpy(iden_holder[iden_holder_count], strings[l]);
					iden_holder_count++;
					}
					comma = false; //Temporarily taking off to see if this can be put somewhere else to ensure spaces after comma do not affect whether it knows they are still identifiers
			}
			
		//*********************^^^ADDED PART ABOVE THIS^^^^*****************************************************		
				key = false; //Temporarily taking off to see if this can be put somewhere else to ensure spaces after comma do not affect whether it knows they are still identifiers
				 
			}
			
			for(i = 0; i < key_counter; i++) {
				if(strcmp(token, keywords[i]) == 0) {
					
					int len = strlen(token);
					keywords_holder[key_holder_count] = (char *)malloc(sizeof(char) * (len +1));
					memcpy(keywords_holder[key_holder_count], token, len +1);
					key_holder_count++;
					key = true;
					
				}	
			}
			token = strtok(NULL, " \t\n{}()&*+=;");
			
			
			
		}//end of inner while
		
		
		
	}//end of outer while
	
	/**************************************************************
	*		Finally Print all identifiers and optional keywords	  *
	**************************************************************/
	
	printf("\nIdentifiers: ");
	for(j =0; j < iden_holder_count; j++)
		if(j == iden_holder_count - 1) {
		printf("%s\n\n", iden_holder[j]);
	} else {
		printf("%s, ", iden_holder[j]);
	}
	
	//putchar('\n');

if(print == true) {	
	int x;
	printf("Keywords: ");
	for(x = 0; x < key_holder_count; x++){
		if(x == key_holder_count - 1){
		printf("%s\n\n", keywords_holder[x]);
			} else {
		printf("%s, ", keywords_holder[x]);
			}
		}
	}
	
}//end of main
