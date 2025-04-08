import sys

input = sys.stdin.readline

C, N = map(int,input().split())

dp = [10**9] * (C+101)

moneys = []
# 초기 들어온 값들을 dp 에 사람수와 비용으로 초기값 선언
for _ in range(N):
    cost, people = map(int,input().split())
    moneys.append((cost,people))
    dp[people] = min(cost,dp[people])
# 이후 C 값은 최대 1000 보다 작고 비용은 100 값보다 작거나 같은 자연수 이므로 범위를 최대 1101로 설정
for i in range(1,C+101):
    # 현재 비용에서 들어오는 비용 값을 뺀값이 양수면 해당 비용에 대해서 dp 값 갱신해줌
    for c, p in moneys:
        if i - p >= 1:
            dp[i] = min(dp[i-p]+dp[p],dp[i])
# 고객이 최소 C 명 이상이기 때문에 이후 값들중 최소값 탐색
print(min(dp[C:]))