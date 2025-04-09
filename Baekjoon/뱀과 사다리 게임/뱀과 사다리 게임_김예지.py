# 뱀과 사다리 게임

'''
사다리 만나면 올라가고, 뱀 만나면 내려감
게임의 목표는 1번 칸에서 시작해서 100번 칸에 도착하는 것
게임판의 상태가 주어졌을 때, 100번 칸에 도착하기 위해 주사위를 굴려야 하는 횟수의 최솟값
'''

from collections import deque

N, M = map(int, input().split())  # N이 사다리, M이 뱀 개수
stairs = []
for _ in range(N):
    x, y = map(int, input().split())
    stairs.append([x, y])

snakes = []
for _ in range(M):
    u, v = map(int, input().split())
    snakes.append([u, v])

q = deque([[1, 0]])
while q:
    now, cnt = q.popleft()
    if now == 100:
        print(cnt)
        break
    
    # 주사위 굴리기
    for i in range(1, 7):
        is_append = False
        for [x, y] in stairs:
            if now + i == x:
                q.append([y, cnt + 1])
                is_append = True
        for [u, v] in snakes:
            if now + i == u:
                q.append([v, cnt + 1])
                is_append = True

        if is_append == False and now + i < 101:
            q.append([now + i, cnt + 1])