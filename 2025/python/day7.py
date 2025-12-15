def find_splitter_positions(line: str):
    splitter_positions = []
    for index, char in enumerate(line):
        if char == "^":
            splitter_positions.append(index)
    return splitter_positions

def read_input():
    with open("day7input.txt", "r", encoding="utf-8") as file:
    #with open("test-input7.txt", "r", encoding="utf-8") as file:
        lines = [line.strip() for line in file]

    beam_start_index = 0
    splitter_lines = []
    for line in lines:
        if "S" in line:
            beam_start_index = line.find("S")
        elif "^" in line:
            splitter_lines.append(find_splitter_positions(line))

    return beam_start_index, splitter_lines

def split_beams_as_set(beam_input: list, splitter_line: list):
    output = set()
    number_of_splits = 0
    for beam in beam_input:
        if beam in splitter_line:
            output.add(beam - 1)
            output.add(beam + 1)
            number_of_splits += 1
        else:
            output.add(beam)
    return output, number_of_splits

def split_beams_as_dict(beam_input: dict, splitter_line: list):
    output = {}
    for key, value in beam_input.items():
        if key in splitter_line:
            if (key - 1) in output:
                output[key - 1] += value
            else:
                output[key - 1] = value
            if (key + 1) in output:
                output[key + 1] += value
            else:
                output[key + 1] = value
        else:
            if key in output:
                output[key] += value
            else:
                output[key] = value
    return output

def part1():
    beam_start_index, splitter_lines = read_input()

    beam_input = [beam_start_index]
    total_number_of_splits = 0
    for splitter_line in splitter_lines:
        output, number_of_splits = split_beams_as_set(beam_input, splitter_line)
        beam_input = list(output)
        total_number_of_splits += number_of_splits

    print(total_number_of_splits)

def part2():
    beam_start_index, splitter_lines = read_input()

    beam_input = {beam_start_index: 1}
    for splitter_line in splitter_lines:
        output = split_beams_as_dict(beam_input, splitter_line)
        beam_input = output

    total = sum(beam_input.values())

    print(total)

if __name__ == "__main__":
    part1()
    part2()
