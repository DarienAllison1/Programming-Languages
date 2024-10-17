import re
import sys

def roman_to_int(roman):
    """
    Convert a Roman numeral string to an integer.
    """
    roman_values = {'I': 1, 'V': 5, 'X': 10, 'L': 50, 'C': 100, 'D': 500, 'M': 1000}
    total = 0
    prev_value = 0
    
    for char in reversed(roman):
        value = roman_values[char]
        if value >= prev_value:
            total += value
        else:
            total -= value
        prev_value = value
    
    return total

def int_to_roman(num):
    """
    Convert an integer to a Roman numeral string.
    """
    if num > 3999:
        return "You’re going to need a bigger calculator."
    
    roman_numerals = [
        ('M', 1000), ('CM', 900), ('D', 500), ('CD', 400),
        ('C', 100), ('XC', 90), ('L', 50), ('XL', 40),
        ('X', 10), ('IX', 9), ('V', 5), ('IV', 4), ('I', 1)
    ]
    result = ''
    
    for roman, value in roman_numerals:
        while num >= value:
            result += roman
            num -= value
    
    return result

def is_single_numeral(expression):
    """
    Determine if the input is a single Roman numeral.
    """
    return re.fullmatch(r'[IVXLCDM]+', expression.strip()) is not None

def tokenize(expression):
    """
    Tokenizes a Roman numeral expression into individual components.
    """
    tokens = re.findall(r'[IVXLCDM]+|\+|\-|\*|\/', expression)
    return tokens

def evaluate_expression(tokens):
    """
    Evaluates a list of tokens representing a Roman numeral expression.
    """
    total = roman_to_int(tokens[0])
    i = 1
    while i < len(tokens):
        op = tokens[i]
        next_val = roman_to_int(tokens[i + 1])
        if op == '+':
            total += next_val
        elif op == '-':
            total -= next_val
        elif op == '*':
            total *= next_val
        elif op == '/':
            if next_val == 0:
                raise ZeroDivisionError("Cannot divide by zero.")
            total //= next_val
        i += 2
    return total

# Main part of the script to get user input from command-line arguments and show result
if __name__ == "__main__":
    # Combine command-line arguments into a single string for the expression
    input_expression = ' '.join(sys.argv[1:]).strip()

    # Check if it's a single Roman numeral or an equation
    if is_single_numeral(input_expression):
        # If it's a single Roman numeral, convert to an integer and print it
        int_value = roman_to_int(input_expression)
        print(f"({int_value})")  # Output the integer in parentheses
    else:
        # Otherwise, assume it's an equation and evaluate it
        tokens = tokenize(input_expression)
        result = evaluate_expression(tokens)
        roman_result = int_to_roman(result)
        print(f"({roman_result})")  # Output the result as Roman numerals in parentheses
