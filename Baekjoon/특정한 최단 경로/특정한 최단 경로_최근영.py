def dijkstra(start):
    distance = [10**9 for _ in range(N+1)]
    distance[start] = 0
    q = []
    heapq.heappush(q,[distance[start],start])
    while q:
        next = heapq.heappop(q)
        if distance[next[1]] < next[0]:
            continue

        for dis, node in graph[next[1]]:
            next_cost = dis + next[0]
            if next_cost < distance[node]:
                distance[node] = next_cost
                heapq.heappush(q,[next_cost,node])
    return distance

import sys
import heapq

input = sys.stdin.readline

N, E = map(int,input().split())


graph = [[] for _ in range(N+1)]

for _ in range(E):
    start, end, cost = map(int,input().split())
    graph[start].append([cost,end])
    graph[end].append([cost,start])

base, target = map(int,input().split())

start_node = dijkstra(1) # 1번 출발
base_node = dijkstra(base) # base 노드 출발
target_node = dijkstra(target) # target 노드 출발

answer = 10**9
answer = min(start_node[base] + base_node[target] + target_node[N],start_node[target] + target_node[base] + base_node[N])
if answer >= 10**9:
    print(-1)
else:
    print(answer)