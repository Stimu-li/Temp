#include <stdio.h>
#include <stdlib.h>

#define SIZE 5  

int stack[SIZE], top = -1;

void push(int item) {
    if (top == SIZE - 1) {
        printf("\nStack is Full! Overflow!\n");
        return;
    }
    stack[++top] = item;
    printf("\nPushed: %d\n", item);
}

int pop() {
    if (top == -1) {
        printf("\nStack is Empty! Underflow!\n");
        return -1;
    }
    return stack[top--];
}

void display() {
    if (top == -1) {
        printf("\nStack is Empty!\n");
        return;
    }
    printf("\nStack contents:\n");
    for (int i = top; i >= 0; i--)
        printf("%d\n", stack[i]);
}

int main() {
    int choice, item;
    
    printf("\nStack Implementation using Array\n");

    while (1) {
        printf("\nMenu:\n1. Push\n2. Pop\n3. Display\n4. Exit\nEnter your choice: ");
        scanf("%d", &choice);

        switch (choice) {
            case 1:
                printf("\nEnter item to push: ");
                scanf("%d", &item);
                push(item);
                break;
            case 2:
                item = pop();
                if (item != -1)
                    printf("\nPopped: %d\n", item);
                break;
            case 3:
                display();
                break;
            case 4:
                printf("\nExiting program...\n");
                exit(0);
            default:
                printf("\nInvalid choice! Try again.\n");
        }
    }
    
    return 0;
}
