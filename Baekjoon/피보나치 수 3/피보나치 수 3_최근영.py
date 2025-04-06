from collections import deque

N = int(input())

dp = deque([0,1])

for i in range(2,N+1):
    dp.append((dp[-1]+dp[-2])%1000000)
    dp.popleft()
print(dp[-1])