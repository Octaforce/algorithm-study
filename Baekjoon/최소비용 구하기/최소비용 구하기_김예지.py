# 최소비용 구하기
from collections import deque
import sys
input = sys.stdin.readline

N = int(input())  # 도시 개수
M = int(input())  # 버스 개수

# DP -> N * N 의 크기, DP[a] = 시작도시에서 a도시까지의 최소거리, 초기 값은 무한대로 두기
dp = [float('inf') for _ in range(N + 1)]

buses = [[float('inf') for _ in range(N + 1)] for _ in range(N + 1)]
for _ in range(M):
    d1, d2, cost = map(int, input().split())
    buses[d1][d2] = min(buses[d1][d2], cost)

start, end = map(int, input().split())  # 출발 도시, 도착 도시
dp[start] = 0

q = deque([[start, 0]])
while q:
    now, cost = q.popleft()
    
    for i in range(N + 1):
        next_cost = buses[now][i]
        if next_cost != float('inf'):
            if dp[i] > next_cost + cost:
                dp[i] = next_cost + cost
                q.append([i, next_cost + cost])

print(dp[end])