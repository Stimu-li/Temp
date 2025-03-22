
# Running Compiled file
```bash
./calc
```


# Compiling the values

```bash
yacc -d calc.y # -d is used to create the header file for the c for better linking and usage.

lex calc.l
gcc -o calc y.tab.c lex.yy.c -ll #-ll is used to link the lex library

# To run the compiled file 
./calc
```

# Necessary packages should be there in putty 

```bash
flex bison 
```