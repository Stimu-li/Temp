%{
    #include <stdio.h>
    #include <stdlib.h>

    void yyerror(char *);
    int yylex(void);
    int sym[26]; // Array to store variable values
%}

%token INTEGER VARIABLE
%left '+' '-'
%left '*' '/'

%%
program:
    program statment '\n'
    | /* NULL */
    ;

statment:
    expression { printf("Result = %d\n", $1); }
    | VARIABLE '=' expression { sym[$1] = $3; printf("Assigned %d to variable\n", $3); }
    ;

expression:
    INTEGER { $$ = $1; }
    | VARIABLE { $$ = sym[$1]; }
    | expression '+' expression { $$ = $1 + $3; }
    | expression '-' expression { $$ = $1 - $3; }
    | expression '*' expression { $$ = $1 * $3; }
    | expression '/' expression { $$ = $1 / $3; }
    | '(' expression ')' { $$ = $2; }
    ;
%%

void yyerror(char *s) {
    fprintf(stderr, "Error: %s\n", s);
}

int main(void) {
    return yyparse();
}
