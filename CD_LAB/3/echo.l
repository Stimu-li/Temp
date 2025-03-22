%%
.   ECHO;  // Echo any character
\n  ECHO;  // Echo newline characters
%%

int yywrap(void) {
    return 1;
}

int main() {
    printf("Start typing:\n");
    yylex();
    return 0;
}
