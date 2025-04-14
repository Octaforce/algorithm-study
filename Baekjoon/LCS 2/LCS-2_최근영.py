import sys
sys.setrecursionlimit(10000)

def backtracking(indexs,value):
    global answer
    global word
    if value == 0:
        print(matrix[-1][-1])
        print(answer[::-1])
        exit()
    
    if matrix[indexs[0]-1][indexs[1]] == value:
        backtracking([indexs[0]-1,indexs[1]],value)
    elif matrix[indexs[0]][indexs[1]-1] == value:
        backtracking([indexs[0],indexs[1]-1],value)
    elif matrix[indexs[0]-1][indexs[1]] < value and matrix[indexs[0]][indexs[1]-1] < value:
        answer += word[indexs[1]-1]
        backtracking([indexs[0]-1,indexs[1]-1],value-1)


word = input()
word_second = input()

first_length = len(word)
second_length = len(word_second)

matrix = [[0] * (first_length+1) for _ in range(second_length+1)]

for i in range(second_length):
    for j in range(first_length):
        if word_second[i] == word[j]:
            matrix[i+1][j+1] = matrix[i][j] + 1
        else:
            matrix[i+1][j+1] = max(matrix[i][j+1],matrix[i+1][j])
if matrix[-1][-1] == 0:
    print(0)
else:
    answer = ""
    backtracking([second_length,first_length],matrix[-1][-1])