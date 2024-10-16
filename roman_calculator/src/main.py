from calculator import calculate
import sys

if __name__ == '__main__':
    expression = " ".join(sys.argv[1:])
    #expression = input("Enter a Roman numeral expression: ")
    result = calculate(expression)
    print(result)
