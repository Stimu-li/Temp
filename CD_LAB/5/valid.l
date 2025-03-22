%{ 
#include "y.tab.h" 
#include <stdlib.h> 
#include <stdio.h>

void yyerror(const char *); 
%} 

%% 

[a-z] { 
    printf("Identifier: %s\n", yytext);
    return VARIABLE; 
} 

[0-9]+ { 
    yylval = atoi(yytext); 
    return INTEGER; 
} 

[-+()=*/\n] { return *yytext; } 

[ \t] ; /* Ignore whitespace */ 

. { 
    yyerror("Unknown character found!"); 
}

%% 

int yywrap(void) { 
    return 1; 
} 
