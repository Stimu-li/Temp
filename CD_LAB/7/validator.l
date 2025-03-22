%{ 
#include "y.tab.h" 
#include <stdlib.h> 
#include <stdio.h>
#include <ctype.h>

void yyerror(const char *); 
%} 

%% 

[a-zA-Z][a-zA-Z0-9]* { 
    printf(" Valid Identifier: %s\n", yytext);
    return VARIABLE; 
} 

[0-9]+ { 
    printf("Valid Number: %s\n", yytext);
    return INTEGER; 
} 

[-+()=*/] { return *yytext; } 

[ \t] ; /* Ignore whitespace */ 

[0-9]+[a-zA-Z]+ { 
    yyerror("Invalid Identifier: Cannot mix numbers and letters incorrectly!"); 
} 

. { 
    yyerror(" Syntax Error: Unknown character!"); 
}

%% 

int yywrap(void) { 
    return 1; 
} 
