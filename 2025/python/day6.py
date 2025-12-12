def read_input():
    with open("day6input.txt", "r", encoding="utf-8") as file:
    #with open("test-input6.txt", "r", encoding="utf-8") as file:
        lines = [line.strip() for line in file]

    numbers = []
    operators = []

    for line in lines:
        values = line.split()
        if values[0].isdigit():
            numbers.append(values)
        else:
            operators = values

    return numbers, operators

def spaces(count: int):
    response = ""
    for x in range(count):
        response += " "
    return response

def read_input2():
    with open("day6input.txt", "r", encoding="utf-8") as file:
    #with open("test-input6.txt", "r", encoding="utf-8") as file:
        lines = [line.replace("\n", "") for line in file]

    longest_line = 0
    for line in lines:
        if len(line) > longest_line:
            longest_line = len(line)

    for index, line in enumerate(lines):
        if len(line) < longest_line:
            lines[index] = line + spaces(longest_line - len(line))

    cols = longest_line - 1

    values = []
    operators = []
    for index in range(cols, -1, -1):
        value = ""
        for row, line in enumerate(lines):
            if row == len(lines) - 1:
                if line[index] == "*" or line[index] == "+":
                    operators.append(line[index])
                values.append(value)
                value = ""
            elif line[index].isdigit():
                value += line[index]

    numbers = []
    number_group_index = 0
    for value in values:
        if number_group_index > len(numbers) - 1:
            numbers.append([])
        if value == "":
            number_group_index += 1
        else:
            numbers[number_group_index].append(value)

    return numbers, operators

def add_numbers(values: list):
    result = 0
    for value in values:
        valueInt = int(value)
        result += valueInt
    return result

def multiply_numbers(values: list):
    result = 1
    for value in values:
        valueInt = int(value)
        result *= valueInt
    return result

def part1():
    numbers, operators = read_input()

    firstRow = numbers[0]
    results = []
    for index, value in enumerate(firstRow):
        values = []
        for row in numbers:
            values.append(row[index])
        operator = operators[index]
        if operator == "*":
            results.append(multiply_numbers(values))
        elif operator == "+":
            results.append(add_numbers(values))

    print(add_numbers(results))

def part2():
    numbers, operators = read_input2()

    results = []
    for index, values in enumerate(numbers):
        operator = operators[index]
        if operator == "*":
            results.append(multiply_numbers(values))
        elif operator == "+":
            results.append(add_numbers(values))

    print(add_numbers(results))

if __name__ == "__main__":
    part1()
    part2()
