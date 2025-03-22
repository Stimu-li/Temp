
# Running Compiled file
```bash
./valid
```


# Compiling the values

```bash
yacc -d valid.y # -d is used to create the header file for the c for better linking and usage.

lex valid.l
gcc -o valid y.tab.c lex.yy.c -ll #-ll is used to link the lex library

# To run the compiled file 
./valid
```

# Necessary packages should be there in putty 

```bash
flex bison 
```