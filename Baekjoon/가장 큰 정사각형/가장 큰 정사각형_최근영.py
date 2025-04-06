import sys

input = sys.stdin.readline

N, M = map(int,input().split())

matrix = [list(map(int,input().strip())) for _ in range(N)]

max_answer = 0

for i in range(N):
    for j in range(M):
        if matrix[i][j] == 1:
            max_answer = max(max_answer,1)
            index = 1
            while i+index < N and j+index < M and matrix[i+index][j] == 1 and matrix[i][j+index] ==1 and matrix[i+index][j+index] == 1:
                index += 1
            if max_answer < index**2:
                max_answer = index**2
print(max_answer)