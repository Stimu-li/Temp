
# Running Compiled file
```bash
./validator
```


# Compiling the values

```bash
yacc -d validator.y # -d is used to create the header file for the c for better linking and usage.

lex validator.l
gcc -o validator y.tab.c lex.yy.c -ll #-ll is used to link the lex library

# To run the compiled file 
./validator
```

# Necessary packages should be there in putty 

```bash
flex bison 
```