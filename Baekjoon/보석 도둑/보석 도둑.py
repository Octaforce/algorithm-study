# 보석도둑

# 힙을 쓰자

import heapq

N, K = map(int, input().split())
dia = []
for _ in range(N):
    m, v = map(int, input().split())
    heapq.heappush(dia, (m, v))  # 무게가 가벼운 순서대로 들어감

bag = []
for _ in range(K):
    c = int(input())
    bag.append(c)

# 용량이 작은 가방부터 
bag.sort()

temp = []
result = 0
for limit in bag:
    while dia and dia[0][0] <= limit:
        heapq.heappush(temp, -dia[0][1]) # 가치가 무거운 순으로 들어가야함
        heapq.heappop(dia) # 보석 넣은건 리스트에서 빼줘
    if temp:
        result -= heapq.heappop(temp)  # 마이너스로 처리했으니까 빼줘야함

print(result)