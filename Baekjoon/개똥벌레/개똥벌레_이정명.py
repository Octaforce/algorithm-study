import sys
input = sys.stdin.readline

N, H = map(int,input().split())

CT = [0 for _ in range(H)]
CB = [0 for _ in range(H)]

for i in range(N):
    h = int(input())
    if i % 2 == 0:
        CT[h-1] += 1
    else:
        CB[h-1] += 1

# 위에서 아래로 누적합 (종유석)
for i in range(H-2, -1, -1):
    CT[i] += CT[i+1]

# 아래에서 위로 누적합 (석순)
for i in range(H-2, -1, -1):
    CB[i] += CB[i+1]


result = [N, 0]

for i in range(H):
    C = CT[i] + CB[H - i - 1]
    if C < result[0]:
        result[0] = C
        result[1] = 1
    elif C == result[0]:
        result[1] += 1

print(result[0], result[1])