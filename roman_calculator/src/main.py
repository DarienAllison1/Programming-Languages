from calculator import calculate
import sys

if __name__ == '__main__':
    expression = " ".join(sys.argv[1:])
    result = calculate(expression)
    print(result)
