
from collections import deque
import sys

dire = [(1, 0), (-1, 0), (0, 1), (0, -1)]

def BFS(starty, startx):
    q = deque([(1, starty, startx, False)])
    result = []
    visited1 = [[0 for _ in range(M)] for _ in range(N)]
    visited2 = [[0 for _ in range(M)] for _ in range(N)]
    while q:
        dis, cy, cx, isPunch = q.popleft()
        if cy == N - 1 and cx == M - 1:
            result.append(dis)
        for dy, dx in dire:
            ny, nx = dy + cy, dx + cx
            if 0 <= ny < N and 0 <= nx < M:
                # 이미 뿌고 왔을 때
                if isPunch and visited2[ny][nx] == 0:
                    if MAP[ny][nx] == 0:
                        visited2[ny][nx] = 1
                        q.append((dis + 1, ny, nx, isPunch))
                elif isPunch == False and visited1[ny][nx] == 0:
                    if MAP[ny][nx]:
                        visited2[ny][nx] = 1
                        q.append((dis + 1, ny, nx, True))
                    else:
                        visited1[ny][nx] = 1
                        q.append((dis + 1, ny, nx, isPunch))
    if result == []:
        return -1
    else:
        return min(result)

N, M = map(int, sys.stdin.readline().split())
MAP = []
for _ in range(N):
    arr = list(map(int,sys.stdin.readline().strip()))
    MAP.append(arr)
ans = BFS(0, 0)
print(ans)