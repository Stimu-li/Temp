%%

[a-zA-Z_][a-zA-Z0-9_]*  { printf("It is an Identifier\n"); }

.  { printf("It is not an Identifier\n"); }

%%

int yywrap(void) {
    return 1;
}

int main() {
    yylex();
    return 0;
}
