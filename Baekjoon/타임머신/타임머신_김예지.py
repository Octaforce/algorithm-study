N, M = map(int,input().split())
# 간선 정보 리스트
bus = []
# 최단거리 저장할 리스트
dis = [float('inf') for _ in range(N + 1)]
dis[1] = 0      # 시작점은 거리 0

for _ in range(M):
    a, b, c = map(int,input().split())
    bus.append((a, b, c))

# 음수사이클 판별용
isNegativeCycle = False

for i in range(N):
    for j in range(M):
        now, next, cost = bus[j]
        if dis[now] != float('inf') and dis[next] > dis[now] + cost:
            dis[next] = dis[now] + cost
            # 음수사이클 판별용
            if i == N - 1:
                isNegativeCycle = True


if isNegativeCycle:
    print(-1)
else:
    for idx in range(2, N + 1):
        if dis[idx] != float('inf'):
            print(dis[idx])
        else:
            print(-1)