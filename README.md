# Deterministic Finite Automaton (DFA) in Java

This Java program models a DFA (Deterministic Finite Automaton) & uses it to accept or reject strings of the associated language.

# Input

The program should take the DFA description from a text file that is specified as a
command line parameter. If this parameter is missing, the user should be prompted for
the data file. Strings to be tested for inclusion in the language should be entered
interactively by the user.

# Output:
For every string being tested, the program should indicate whether the string is accepted or not.

# Text File Format:

line 1: alphabet - eg. {0,1}	

line 2: states - eg. {a,b,c,d,e}	

line 3: start state - eg. a	

line 4: accept states - eg. {d,e}	

lines 5-: transition fn - eg. (a,0)->b	

(a,1)->c	

etc.	
