#include <stdio.h>
#include <string.h>

#define MAX_VARS 50

typedef struct {
    int type;  // 1 = int, 2 = float
    char var[10];
} SymTable;

SymTable sT[MAX_VARS];
int count = 0;

void addVariables(char *line) {
    char type[10], var[10];
    sscanf(line, "%s", type);
    line += strlen(type) + 1;

    char *token = strtok(line, ", ;");
    while (token) {
        sT[count].type = (strcmp(type, "int") == 0) ? 1 : 2;
        strcpy(sT[count++].var, token);
        token = strtok(NULL, ", ;");
    }
}

int getType(char *var) {
    for (int i = 0; i < count; i++) {
        if (strcmp(sT[i].var, var) == 0)
            return sT[i].type;
    }
    return -1; // Undeclared variable
}

int checkExpression(char *expr) {
    char var[10];
    int expectedType = 0;

    char *token = strtok(expr, " +=;");
    while (token) {
        int type = getType(token);
        if (type == -1) continue; // Ignore non-variable tokens

        if (expectedType == 0)
            expectedType = type;
        else if (expectedType != type)
            return 0; // Type mismatch

        token = strtok(NULL, " +=;");
    }
    return 1;
}

int main() {
    int N;
    char line[50], expr[50];

    printf("Enter number of declarations: ");
    scanf("%d", &N);
    getchar(); // Consume newline

    while (N--) {
        fgets(line, sizeof(line), stdin);
        addVariables(line);
    }

    printf("Enter expression: ");
    fgets(expr, sizeof(expr), stdin);

    printf(checkExpression(expr) ? "Correct\n" : "Semantic error\n");

    return 0;
}
