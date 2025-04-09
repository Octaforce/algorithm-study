# 피보나치 수 3

'''
수식
[F(n)   F(n-1)] = [1 1]^(n-1)
[F(n-1) F(n-2)]   [1 0]
'''

def calculate(n, arr):
    if n == 1:
        return arr
    
    if n == 0:
       return [[1, 0], [0, 1]]

    if n % 2 == 0:
        half = calculate(n // 2, arr)
        return multiply(half, half)
    
    else:
        half = calculate(n // 2, arr)
        temp = multiply(half, half)
        return multiply(arr, temp)


def multiply(arr1, arr2):
    new_arr = [[0,0],[0,0]]
    for i in range(2):
        for j in range(2):
            calculated = 0
            for k in range(2):
                calculated += arr1[i][k] * arr2[k][j]
            new_arr[i][j] = calculated % 1000000

    return new_arr


input_num = int(input())

# 기본행렬을 n-1제곱해야함
matrix = [[1, 1], [1, 0]]
result = calculate(input_num - 1, matrix)
print(result[0][0])