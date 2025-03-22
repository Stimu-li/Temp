%{ 
#include <stdio.h>
#include <stdlib.h>

void yyerror(const char *s);
int yylex(void);
int sym[26];  // Symbol table
%} 

%token INTEGER VARIABLE 
%left '+' '-' 
%left '*' '/' 

%%
program: 
    program statement '\n' 
    | /* NULL */ 
    ;

statement: 
    expression { printf(" Valid Expression\n"); } 
    | VARIABLE '=' expression { printf(" Valid Assignment\n"); sym[$1] = $3; } 
    ;

expression: 
    INTEGER { $$ = $1; }
    | VARIABLE { $$ = sym[$1]; } 
    | expression '+' expression { $$ = $1 + $3; } 
    | expression '-' expression { $$ = $1 - $3; } 
    | expression '*' expression { $$ = $1 * $3; } 
    | expression '/' expression { 
        if ($3 == 0) {
            yyerror(" Error: Division by zero!");
        } else {
            $$ = $1 / $3; 
        }
      } 
    | '(' expression ')' { $$ = $2; } 
    ;

%% 

void yyerror(const char *s) { 
    fprintf(stderr, " Invalid Expression: %s\n", s); 
    exit(1);
} 

int main(void) { 
    printf("Enter an expression to validate:\n");
    return yyparse(); 
}
