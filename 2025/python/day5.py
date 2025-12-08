import sys

def readInputToLists():
    with open("day5input.txt", "r", encoding="utf-8") as file:
    #with open("testInput5.txt", "r", encoding="utf-8") as file:
        lines = [line.strip() for line in file]

    freshIds = []
    current = []

    for line in lines:
        if line == "":
            if current:
                freshIds = current
                current = []
        else:
            current.append(line)

    return (freshIds, current)

def splitRange(id: str):
    idList = id.split("-")
    return (int(idList[0]), int(idList[1]))

def isFresh(idString, freshIds):
    id = int(idString)
    for freshId in freshIds:
        (rangeBegin, rangeEnd) = splitRange(freshId)
        if id >= rangeBegin and id <= rangeEnd:
            return True
    return False

(freshIds, availableIds) = readInputToLists()

# part1
freshTotal = 0
for availableId in availableIds:
    if isFresh(availableId, freshIds):
        freshTotal += 1

print(freshTotal)

# part2
def combineRanges(freshIds: list):
    sortedRanges = sorted(freshIds, key=lambda x: x[0])
    combined = []

    for begin, end in sortedRanges:
        if not combined or combined[-1][1] < begin:
            combined.append((begin, end))
        else:
            combined[-1] = (combined[-1][0], max(combined[-1][1], end))

    return combined

freshRanges = []
for freshId in freshIds:
    (begin, end) = splitRange(freshId)
    freshRanges.append((begin, end))

combinedRanges = combineRanges(freshRanges)

freshTotal2 = 0
for begin, end in combinedRanges:
    freshTotal2 += end - begin + 1

print(freshTotal2)