from collections import deque
import sys

input = sys.stdin.readline

def bfs():
    visited = [False] * 101
    queue = deque()
    queue.append((1, 0))
    visited[1] = True

    while queue:
        current, count = queue.popleft()

        if current == 100:
            return count

        for i in range(1, 7):  # 주사위 1~6
            next_pos = current + i
            if next_pos <= 100 and not visited[next_pos]:
                visited[next_pos] = True
                if next_pos in move:
                    queue.append((move[next_pos], count + 1))
                    visited[move[next_pos]] = True
                else:
                    queue.append((next_pos, count + 1))

n, m = map(int, input().split())
# 딕셔너리로 시간 복잡도 감소
move = {}

for _ in range(n + m):
    x, y = map(int, input().split())
    move[x] = y

print(bfs())