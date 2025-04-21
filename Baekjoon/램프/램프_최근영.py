import sys

input = sys.stdin.readline

N, M = map(int,input().split())

matrix = []

for _ in range(N):
    matrix.append(input().strip())

K = int(input())

matrix_dict = {}

for mat in matrix:
    zero = mat.count('0')
    if zero <= K and (K - zero) % 2 == 0:
        if mat in matrix_dict:
            matrix_dict[mat] += 1
        else:
            matrix_dict[mat] = 1

if matrix_dict:
    print(max(matrix_dict.values()))
else:
    print(0)