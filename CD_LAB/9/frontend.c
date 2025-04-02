#include <stdio.h>
#include <string.h>
#include <ctype.h>
#include <stdlib.h>

#define MAX_QUADS 100

// Structure for Three-Address Code (TAC)
typedef struct {
    int pos;
    char op[5];
    char arg1[10];
    char arg2[10];
    char result[10];
} TAC;

TAC tac[MAX_QUADS];
int tempVarCount = 0, tacIndex = 0, labelCount = 0;

// Generate new temporary variable
void newTempVar(char *temp) {
    sprintf(temp, "t%d", tempVarCount++);
}

// Generate new label
void newLabel(char *label) {
    sprintf(label, "L%d", labelCount++);
}

// Generate TAC instruction
void generateTAC(char *op, char *arg1, char *arg2, char *result) {
    tac[tacIndex].pos = tacIndex + 1;
    strcpy(tac[tacIndex].op, op);
    strcpy(tac[tacIndex].arg1, arg1);
    strcpy(tac[tacIndex].arg2, arg2);
    strcpy(tac[tacIndex].result, result);
    tacIndex++;
}

// Process an assignment statement (e.g., a = 5 + b * c)
void processAssignment(char *line) {
    char left[10], operand1[10], operand2[10], op[3], temp[10];
    int i = 0, j = 0;

    // Extract left-hand side
    while (line[i] != '=' && line[i] != '\0') {
        left[j++] = line[i++];
    }
    left[j] = '\0';
    i++; // Skip '='

    // Process right-hand side
    j = 0;
    while (isalnum(line[i])) operand1[j++] = line[i++];
    operand1[j] = '\0';

    if (line[i] == '\0') {
        generateTAC(":=", operand1, "", left);
        return;
    }

    op[0] = line[i++];
    op[1] = '\0';

    j = 0;
    while (isalnum(line[i])) operand2[j++] = line[i++];
    operand2[j] = '\0';

    newTempVar(temp);
    generateTAC(op, operand1, operand2, temp);
    generateTAC(":=", temp, "", left);
}

// Process an if-condition (e.g., if (a > 10))
void processIfCondition(char *line) {
    char operand1[10], operand2[10], op[3], temp[10], label[10];
    int i = 3, j = 0; // Skip "if ("

    // Extract first operand
    while (isalnum(line[i])) operand1[j++] = line[i++];
    operand1[j] = '\0';

    // Extract operator
    op[0] = line[i++];
    if (line[i] == '=' || line[i] == '<' || line[i] == '>') {
        op[1] = line[i++];
        op[2] = '\0';
    } else {
        op[1] = '\0';
    }

    // Extract second operand
    j = 0;
    while (isalnum(line[i])) operand2[j++] = line[i++];
    operand2[j] = '\0';

    // Generate TAC for condition
    newTempVar(temp);
    generateTAC(op, operand1, operand2, temp);

    // Generate TAC for jump
    newLabel(label);
    generateTAC("if", temp, "goto", label);
}

// Print generated TAC
void printTAC() {
    printf("\n%-5s %-5s %-10s %-10s %-10s\n", "Pos", "Oper", "Arg1", "Arg2", "Result");
    printf("------------------------------------------------\n");

    for (int i = 0; i < tacIndex; i++) {
        printf("%-5d %-5s %-10s %-10s %-10s\n",
               tac[i].pos, tac[i].op, tac[i].arg1, tac[i].arg2, tac[i].result);
    }
}

// Main function to parse a C-style code
int main() {
    char code[10][50];
    FILE *file = fopen("input.txt", "r");
    char line[50];
    int lineCount = 0;

    while (fgets(line, sizeof(line), file)) {
        line[strcspn(line, "\n")] = '\0';
        strcpy(code[lineCount], line);
        lineCount++;
    }
    fclose(file);
    // Process each line
    for (int i = 0; i < lineCount; i++) {
        if (strstr(code[i], "if") != NULL)
            processIfCondition(code[i]);
        else if (strstr(code[i], "=") != NULL)
            processAssignment(code[i]);
    }

    printTAC();

    return 0;
}
