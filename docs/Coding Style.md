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

## Documentation
As a rule of thumb, code should be self-explanatory 99% of the time. If you feel the need to comment, there's a good chance you can write it in a simpler, better way.

Of course, sometimes you do need to comment to clarify something, or point out something that isn't obvious. However, they should be used minimally.
