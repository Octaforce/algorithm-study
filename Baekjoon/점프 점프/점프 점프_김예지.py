# 점프 점프

# 개굴개굴개굴
# dfs

n = int(input())
stones = list(map(int, input().split()))
s = int(input())

visited = [0 for _ in range(n + 1)]
visited[s] = 1

count = 1
stack = [s]

while stack:
    now = stack.pop()
    for next_stone in [now + stones[now - 1], now - stones[now - 1]]:
        if 1 <= next_stone <= n:
            if visited[next_stone] == 0:
                visited[next_stone] = 1
                count += 1
                stack.append(next_stone)

print(count)

