# 개똥벌레

'''
# 이분탐색
# 위와 아래를 따로 저장 후 정렬
# 각 높이에 대해서 어디부터 부딪히는지를 이분탐색으로 구함

import sys
input = sys.stdin.readline

def find(target, arr):
    left, right = 0, len(arr)
    while left < right:
        mid = (left + right) // 2

        if arr[mid] < target:
            left = mid + 1
        else:
            right = mid

    return left

N, H = map(int, input().split())
up = []
down = []

for i in range(N):
    if i % 2:
        up.append(int(input()))
    else:
        down.append(int(input()))

up.sort()
down.sort()

result = float('inf')
cnt = 1

for h in range(1, H + 1):
    down_count = len(down) - find(h - 0.5, down)
    up_count = len(up) - find(H - h + 0.5, up)

    total_count = down_count + up_count
    if total_count < result:
        result = total_count
        cnt = 1
    elif total_count == result:
        cnt += 1

print(result, cnt)
'''

# 누적합도 가능하다고 함
# 높이가 7인 종유석이 충돌함 -> 높이가 9인애, 8인애도 모두 충돌할꺼임
N, H = map(int, input().split())
up = [0] * (H + 1)
down = [0] * (H + 1)

for i in range(N):
    if i % 2:
        up[int(input())] += 1
    else:
        down[int(input())] += 1

for h in range(H - 1, 0, -1):
    up[h] += up[h + 1]
    down[h] += down[h + 1]

result = N
cnt = 0

for h in range(1, H + 1):
    total_cnt = down[h] + up[H - h + 1]
    if result > total_cnt:
        result = total_cnt
        cnt = 1
    elif result == total_cnt:
        cnt += 1

print(result, cnt)
