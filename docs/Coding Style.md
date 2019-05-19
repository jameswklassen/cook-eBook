# Coding Style

## Naming
All shared variables shall be lowercase with underscores seperating words (Android Default)
```java
recipe_id
```

All inner class variable names (not shared) shall be camel case.
```java
recipeId
```

All Interface classes shall be prefixed with 'i'.
```java
iDatabase
```

## Format
All opening curly brackets shall sit *beside* the line, not under, followed by a newline.
```java
public boolean doMethod() {
```

All indents are four spaces. All indenting is done with tabs.

Matching braces always line up vertically in the same column as their construct.

All if, while and for statements must use braces even if they control just one statement.
```java
if(condition) {
    do thing
}
```

All classes shall be setup as follows:
```java
class Order {
    //fields
    
    //constructors
    
    //methods
    
    @override
    //methods
}
```
