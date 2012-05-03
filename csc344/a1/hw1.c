#include <stdio.h>
#include <string.h>
#include <stdbool.h>
#include <stdlib.h>
#define k_num_of_keywords 35 
#define k_length_of_string 20
int main(int argc, const char * argv[]) {
	char toPrintOrNot[40];
	bool print = false;
	FILE *fp;
	int c; 
	char *keywords[k_num_of_keywords];
	int key_counter = 0;
	char temp[k_length_of_string];
	short count = 0; 
	fp = fopen("./keywords", "r");
	if(NULL == fp){
		printf("Failed to create file!!\n");
		exit(0);
	}
	while((c = fgetc(fp)) != EOF){
		if(c == '\n') {
			temp[count] = '\0';
			int len = strlen(temp);
			keywords[key_counter] = (char *)malloc(sizeof(char) * (len +1));
			memcpy(keywords[key_counter], temp, len +1);
			key_counter++;
			int x;
			for(x = 0; x < count; x++)
				temp[x] = '\0';
			count = 0;	
		} else  {
			temp[count] = c;
			count++;
		}
	}
	fclose(fp); 	
	printf("Would you like the optional keywords to be printed out: (Y)es or (N)o: ");
	fgets(toPrintOrNot, 20, stdin);
	int promptLength = strlen(toPrintOrNot);
	toPrintOrNot[promptLength - 1] = '\0';
	if(toPrintOrNot[0] == 'y' || toPrintOrNot[0] == 'Y')
		print = true;
	FILE *f = fopen("testFunction", "r");
	bool key = false;
	char *keywords_holder[100];
	int key_holder_count = 0;
	char *iden_holder[100];
	int iden_holder_count = 0;
	char line[1000];
	char *token;
	int i, j;
	bool comma = false;
	int num_of_commas = 0;
	bool prob_cast = false;
	while(fgets(line, 1000, f) != NULL) {	
		token = strtok(line," \t\n{}()&*+=;");	
		while(token != NULL) {
			if(key==true) {
				int length = strlen(token);
				int death;
				for(death = 0; death < length+1; death++) {
					if(token[death] == ',') {
						comma = true;
						num_of_commas++;
					}	
				}
				if(comma == false) { 
					for(i = 0; i < key_counter; i++) {
						if(strcmp(token, keywords[i]) == 0) {
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
					key_holder_count--;
					free(keywords_holder[key_holder_count]);
					prob_cast = false;	
				}		
			} else {
				int len2 = strlen(token);
				char *strings[num_of_commas+1];
				int h;
				for(h = 0; h < num_of_commas + 1; h++) {
					strings[h] = (char *)malloc(sizeof(char) * 100);
					}
				int strings_counter = 0;
				int character_index = 0;
				
				int abs;
				for(abs = 0; abs < len2+1; abs++) {
					if(token[abs] != ',') {
						strings[strings_counter][character_index] = token[abs];	
						character_index++;
					} else {
						strings[strings_counter][character_index] = '\0';
						strings_counter++;
						character_index = 0;
					}		
				}
				strings[strings_counter][character_index] = '\0';
				character_index = 0;
				strings_counter++;
				
				int l;
				for(l = 0; l < strings_counter; l++) {
					int len = strlen(strings[l]);
					iden_holder[iden_holder_count] = (char *)malloc(sizeof(char) * (len +1));
					strcpy(iden_holder[iden_holder_count], strings[l]);
					iden_holder_count++;
					}
					comma = false;
			}
				key = false;		 
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
		}
	}	
	printf("\nIdentifiers: ");
	for(j =0; j < iden_holder_count; j++)
		if(j == iden_holder_count - 1) {
		printf("%s\n\n", iden_holder[j]);
	} else {
		printf("%s, ", iden_holder[j]);
	}
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
}