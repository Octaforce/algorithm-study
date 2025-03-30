# 호텔
# 배낭 알고리즘, DP
# C 명을 만족하는 최소의 비용 구하기

C, N = map(int, input().split())
cities = []
for _ in range(N):
    # 비용, 늘어나는 고객(가치)
    money, value = map(int, input().split())
    cities.append([money, value])

# dp[i] = 고객을 i명 늘리기 위한 최소 비용
max_customer = C + 100  # 100보다 작거나 같은 값이라고 했으니까..
dp = [float('inf') for _ in range(max_customer)]
dp[0] = 0

for i in range(1, max_customer):
    for money, value in cities:
        if i >= value:
            dp[i] = min(dp[i], dp[i - value] + money)

print(min(dp[C:]))