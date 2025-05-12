# 과외맨

from collections import deque
import sys 
input = sys.stdin.readline

dire = [[0, 1], [0, -1], [1, 0], [-1, 0]]

N = int(input())
tiles = [[0 for _ in range(2 * N)] for _ in range(N)]  # 타일 배치 저장
nums = [[0 for _ in range(2 * N)] for _ in range(N)]   # 타일 번호는 따로 저장

i, j = 0, 0  # tiles에 넣기 위한 인덱스
for tile_num in range(1, N*N-N//2 + 1):
    n1, n2 = map(int, input().split())
    tiles[i][j] = n1
    tiles[i][j + 1] = n2
    nums[i][j] = tile_num
    nums[i][j + 1] = tile_num
    j += 2
    if j == 2 * N or j == 2 * N - 1:
        j = 0
        i += 1
        if i % 2 == 1:
            j = 1

# 타일 위치 미리 저장
tile_positions = [[] for _ in range(N*N-N//2 + 2)]
for i in range(N):
    for j in range(2 * N):
        if nums[i][j] != 0:
            tile_positions[nums[i][j]].append((i, j))

# 타일 그래프 생성
graph = [[] for _ in range(N*N-N//2 + 2)]
for tile_num in range(1, N*N-N//2 + 1):
    seen = set()
    for cy, cx in tile_positions[tile_num]:
        for dy, dx in dire:
            ny, nx = cy + dy, cx + dx
            if 0 <= ny < N and 0 <= nx < 2 * N and nums[ny][nx] != 0:
                next_tile = nums[ny][nx]
                # 같은 타일이거나 같은 숫자를 공유하면 이동 가능
                if tile_num == next_tile or tiles[cy][cx] == tiles[ny][nx]:
                    if next_tile not in seen:
                        seen.add(next_tile)
                        graph[tile_num].append(next_tile)

def bfs():
    # 부모 노드만 저장
    parent = [-1] * (N*N-N//2 + 2)
    parent[1] = 1  # 시작점
    
    q = deque([1])
    visited = [False] * (N*N-N//2 + 2)
    visited[1] = True
    
    max_reachable = 1
    
    while q:
        current = q.popleft()
        max_reachable = max(max_reachable, current)
        
        for next_tile in graph[current]:
            if not visited[next_tile]:
                visited[next_tile] = True
                parent[next_tile] = current
                q.append(next_tile)
    
    # 경로 역추적
    path = []
    current = max_reachable
    while parent[current] != current:
        path.append(current)
        current = parent[current]
    path.append(1)
    path.reverse()
    
    print(len(path))
    print(' '.join(map(str, path)))

bfs()


'''
처음코드 -> 시간 초과...

from collections import deque

dire = [[0, 1], [0, -1], [1, 0], [-1, 0]]

N = int(input())
tiles = [[0 for _ in range(2 * N)] for _ in range(N)]  # 타일 배치 저장
nums = [[0 for _ in range(2 * N)] for _ in range(N)]   # 타일 번호는 따로 저장

i, j = 0, 0  # tiles에 넣기 위한 인덱스
for tile_num in range(1, N*N-N//2 + 1):
    n1, n2 = map(int, input().split())
    tiles[i][j] = n1
    tiles[i][j + 1] = n2
    nums[i][j] = tile_num
    nums[i][j + 1] = tile_num
    j += 2
    if j == 2 * N or j == 2 * N - 1:
        j = 0
        i += 1
        if i % 2 == 1:
            j = 1


def print_ans(path):
    temp = path.split(' ')
    result = list(dict.fromkeys(temp))
    print(len(result))
    print(' '.join(result))

# 이동 가능한 조건
# nums가 같거나(같은 타일), 숫자가 같거나
def bfs(last_tile):
    q = deque([[(0, 0), '1']])
    visited = [[0 for _ in range(2 * N)] for _ in range(N)]
    visited[0][0] = 1

    while q:
        (cy, cx), path = q.popleft()
        
        if nums[cy][cx] == last_tile:
            print_ans(path)
            return True

        for dy, dx in dire:
            ny, nx = cy + dy, cx + dx
            if 0 <= ny < N and 0 <= nx < 2 * N:
                if visited[ny][nx] == 0:
                    if nums[cy][cx] == nums[ny][nx] or tiles[cy][cx] == tiles[ny][nx]:
                        visited[ny][nx] = 1
                        q.append([(ny, nx), path + ' ' + str(nums[ny][nx])])

    return False


max_tile = N*N-N//2 + 1
while True:
    if bfs(max_tile) == True:
        break
    else:
        max_tile -= 1


'''