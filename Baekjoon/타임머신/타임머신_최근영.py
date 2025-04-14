def bellman(start):
    global N,M
    distance[start] = 0

    for i in range(N):
        for j in range(M):
            current, next, cost = nodes[j]
            if distance[current] != 10**9 and distance[current] + cost < distance[next]:
                distance[next] = distance[current] + cost
                
                if i == N - 1:
                    return 0
                
    return 1

N, M = map(int,input().split())

nodes = []

for _ in range(M):
    s, e, c = map(int,input().split())
    nodes.append((s,e,c))

distance = [10**9] * (N+1)

if bellman(1):
    for i in range(2, N+1):
        if distance[i] == 10**9:
            print(-1)
        else:
            print(distance[i])
else:
    print(-1)