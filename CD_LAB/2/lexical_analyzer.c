#include <stdio.h>
#include <string.h>
#include <stdlib.h>
#include <ctype.h>

char * operators = "+-*/=<>%";
char * delimiters = "(){},;[]";
char * keywords[] = {
  "int",
  "void",
  "main",
  "char",
  "float"
};

char identifiers[20][20], operatorsList[20][20], delimitersList[20], keywordsList[20][20];
char literals[20][20], constants[20][20];

int idCount = 0, opCount = 0, delCount = 0, keyCount = 0, litCount = 0, constCount = 0;

int isOperator(char ch) {
  return strchr(operators, ch) != NULL;
}

int isDelimiter(char ch) {
  return strchr(delimiters, ch) != NULL;
}

int isKeyword(char * str) {
  for (int i = 0; i < 5; i++)
    if (strcmp(str, keywords[i]) == 0)
      return 1;
  return 0;
}

void analyzeLexical(char * str) {
  int i = 0, j;
  char buffer[50];

  while (str[i] != '\0') {
    if (isalpha(str[i])) { // Identifier or Keyword
      j = 0;
      while (isalpha(str[i]) || isdigit(str[i]) || str[i] == '_')
        buffer[j++] = str[i++];
      buffer[j] = '\0';

      if (isKeyword(buffer))
        strcpy(keywordsList[keyCount++], buffer);
      else
        strcpy(identifiers[idCount++], buffer);
    } else if (isdigit(str[i])) { // Constant
      j = 0;
      while (isdigit(str[i]))
        buffer[j++] = str[i++];
      buffer[j] = '\0';
      strcpy(constants[constCount++], buffer);
    } else if (str[i] == '"') { // Literal String
      j = 0;
      buffer[j++] = str[i++];
      while (str[i] != '"' && str[i] != '\0')
        buffer[j++] = str[i++];
      buffer[j++] = str[i++];
      buffer[j] = '\0';
      strcpy(literals[litCount++], buffer);
    } else if (isOperator(str[i])) { // Operator
      j = 0;
      while (isOperator(str[i]))
        buffer[j++] = str[i++];
      buffer[j] = '\0';
      strcpy(operatorsList[opCount++], buffer);
    } else if (isDelimiter(str[i])) { // Delimiter
      delimitersList[delCount++] = str[i++];
    } else {
      i++;
    }
  }
}

void printResults() {
  printf("\n--- Lexical Analysis Results ---\n");

  printf("\n Identifiers:\n");
  for (int i = 0; i < idCount; i++)
    printf("  %s\n", identifiers[i]);

  printf("\n Keywords:\n");
  for (int i = 0; i < keyCount; i++)
    printf("  %s\n", keywordsList[i]);

  printf("\n Operators:\n");
  for (int i = 0; i < opCount; i++)
    printf("  %s\n", operatorsList[i]);

  printf("\n Delimiters:\n");
  for (int i = 0; i < delCount; i++)
    printf("  %c\n", delimitersList[i]);

  printf("\n Constants:\n");
  for (int i = 0; i < constCount; i++)
    printf("  %s\n", constants[i]);

  printf("\n Literals:\n");
  for (int i = 0; i < litCount; i++)
    printf("  %s\n", literals[i]);

  printf("\n-------------------------------\n");
}

int main() {
  char str[1000];

  printf("Enter the code snippet:\n");
  fgets(str, sizeof(str), stdin);

  analyzeLexical(str);
  printResults();

  return 0;
}