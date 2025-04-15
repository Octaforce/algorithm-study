def DFS(start):
    global ans
    stack = [(start, 1)]
    visited[start] = start
    while stack:
        now, nop = stack.pop()
        next = students[now]
        if next == start:
            ans -= nop
            break
        if visited[next] == start:
            cnt = 0
            nod = start
            while nod != next:
                cnt += 1
                nod = students[nod]
            ans -= nop
            ans += cnt
            break
        if not visited[next]:
            visited[next] = start
            stack.append((next, nop + 1))


T = int(input())
for _ in range(T):
    N = int(input())
    students = [0] + list(map(int,input().split()))
    visited = [0 for _ in range(N + 1)]
    ans = N
    for i in range(1, N + 1):
        if visited[i] == 0:
            DFS(i)
    print(ans)