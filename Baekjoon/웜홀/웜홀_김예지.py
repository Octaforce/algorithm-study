# 웜홀
## 벨만포드 알고리즘
## 어느 지점이던, 다시 도착했을 때 시간이 줄으면 출력  -> 음수 사이클이 존재하면 YES

import sys
input = sys.stdin.readline
    
    
def belman(warp, N):
    dis = [10001] * (N + 1)  # 거리 계산하는 배열
    dis[1] = 0
    for i in range(N):
        for j in range(len(warp)):
            start, end, time = warp[j]
            if dis[end] > dis[start] + time:
                dis[end] = dis[start] + time
                if i == N - 1:   # 마지막에 업데이트가 된다? == 음수 사이클
                    return True
    
    return False


TC = int(input())
for _ in range(TC):
    N, M, W = map(int, input().split())   # 지점, 도로, 웜홀
    warp = []

    for _ in range(M):
        S, E, T = map(int, input().split())   # 시작, 도착, 드는 시간
        warp.append((S, E, T))
        warp.append((E, S, T)) # 도로는 양방향...
    
    for _ in range(W):
        S, E, T = map(int, input().split())   # 시작, 도착, 줄어드는 시간
        warp.append((S, E, -1 * T))

    temp = belman(warp, N)
    
    if temp:
        print('YES')
    else:
        print('NO')
