%{ 
#include <stdio.h>
#include <stdlib.h>
#include <ctype.h>

void yyerror(const char *);
int yylex(void);
%} 

%token VARIABLE INTEGER 
%left '+' '-' 
%left '*' '/' 

%%
program: 
    program statement '\n' 
    | /* NULL */ 
    ;

statement: 
    expression { printf("Valid Expression\n"); } 
    ;

expression: 
    VARIABLE  
    | INTEGER  
    | expression '+' expression 
    | expression '-' expression 
    | expression '*' expression 
    | expression '/' expression 
    | '(' expression ')' 
    ;

%% 

void yyerror(const char *s) { 
    fprintf(stderr, "Syntax Error: %s\n", s); 
    exit(1);
} 

int main(void) { 
    printf("Enter an expression to validate:\n");
    return yyparse(); 
}
