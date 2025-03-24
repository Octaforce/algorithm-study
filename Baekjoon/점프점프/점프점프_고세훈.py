from collections import deque

def solution(n, A, s):
    visited = [0] * n
    queue = deque()
    queue.append(s - 1)  # 0-indexed
    visited[s - 1] = 1

    while queue:
        now = queue.popleft()
        jump = A[now]

        for i in [now + jump, now - jump]:
            if 0 <= i < n and not visited[i]:
                visited[i] = 1
                queue.append(i)

    return sum(visited)

n = int(input())
A = list(map(int, input().split()))
s = int(input())

print(solution(n, A, s))
