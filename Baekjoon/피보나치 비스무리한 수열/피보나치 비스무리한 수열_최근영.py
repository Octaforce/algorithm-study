N = int(input())

dp = [0,1,1,1]

for _ in range(4,N+1):
    dp.append(dp[-1]+dp[-3])

print(dp[-1])