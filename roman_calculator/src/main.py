from calculator import calculate
import sys
import re

def is_single_numeral(expression):
    """
    Determine if the input is a single Roman numeral.
    """
    return re.fullmatch(r'[IVXLCDM]+', expression.strip()) is not None

if __name__ == '__main__':
    expression = " ".join(sys.argv[1:]).strip()

    if is_single_numeral(expression):

        int_result, _ = calculate(expression)
        print(f"({int_result})") 
    else:
        # Otherwise, assume it's an equation and evaluate it
        _, roman_result = calculate(expression)
        print(f"({roman_result})") 
