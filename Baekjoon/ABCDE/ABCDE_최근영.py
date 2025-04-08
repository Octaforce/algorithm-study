import sys
# 백트래킹
def DFS(s,depth):
    # 최대 연결된 인원이 5명인 경우 1을 출력 후 탈출
    if depth == 5:
        print(1)
        exit()
    # 연결된 인원들 탐색하며 백트래킹
    for j in matrix[s]:
        if visited[j] == False:
            visited[j] = True
            DFS(j,depth+1)
            visited[j] = False

input = sys.stdin.readline

N, M = map(int,input().split())

matrix = [[] for _ in range(N)]
# 양방향 그래프 구성
for _ in range(M):
    start, end = map(int,input().split())
    matrix[start].append(end)
    matrix[end].append(start)
# 0번 사람부터 N-1 번 사람까지 탐색해가며 연결 깊이 체크
for i in range(N):
    visited = [False] * N
    visited[i] = True
    DFS(i,1)
else:
    print(0)