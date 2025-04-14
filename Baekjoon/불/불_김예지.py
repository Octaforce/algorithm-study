# 불

'''
불이 옮겨진 칸 또는 이제 불이 붙으려는 칸으로 이동할 수 없다. 
상근이가 있는 칸에 불이 옮겨옴과 동시에 다른 칸으로 이동할 수 있다.

각 테스트 케이스마다 빌딩을 탈출하는데 가장 빠른 시간을 출력한다. 
빌딩을 탈출할 수 없는 경우에는 "IMPOSSIBLE"을 출력한다
'''
from collections import deque

directions = [[1, 0], [-1, 0], [0, 1], [0, -1]]

def escape(building, fire_map, sang, w, h):
    q = deque([[sang, 0]]) # 상근이 출발위치
    visited = [[0 for _ in range(w)] for _ in range(h)]
    visited[sang[0]][sang[1]] = 1

    while q:
        now, t = q.popleft()

        for dy, dx in directions:
            ny, nx = now[0] + dy, now[1] + dx

            # 탈출조건 = 건물 밖으로
            if 0 <= ny < h and 0 <= nx < w:
                # 건물 밖이 아니라면 -> 안들리고, 갈수있는 위치인지 확인 후 q에 넣어주기
                if visited[ny][nx] == 0 and building[ny][nx] == ".":
                    # 불 번진거 처리
                    if t + 1 < fire_map[ny][nx] or fire_map[ny][nx] == -1:
                        visited[ny][nx] = 1
                        q.append([[ny, nx], t + 1])
            else:
                return t + 1
        
    return "IMPOSSIBLE"





def find(building, w, h):
    fires = []
    sang = None
    for i in range(h):
        for j in range(w):
            if building[i][j] == '@':
                sang = [i, j]
            if building[i][j] == '*':
                fires.append([i, j])

    fire_map = [[-1 for _ in range(w)] for _ in range(h)]
    fire_q = deque()
    for fire in fires:
        fire_map[fire[0]][fire[1]] = 0
        fire_q.append([fire[0], fire[1], 0])

    while fire_q:
        fy, fx, t = fire_q.popleft()
        
        for dy, dx in directions:
            ny, nx = fy + dy, fx + dx
            if 0 <= ny < h and 0 <= nx < w and fire_map[ny][nx] == -1 and building[ny][nx] != '#':
                fire_map[ny][nx] = t + 1
                fire_q.append([ny, nx, t + 1])
    


    return fire_map, sang

T = int(input())
for _ in range(T):
    w, h = map(int, input().split(' '))
    building = []
    for _ in range(h):
        building.append(list(input()))
    
    # 상근이랑  불의 위치 구하기
    fires, sang = find(building, w, h)
    if sang == None:
        print('IMPOSSIBLE')
    else:
        print(escape(building, fires, sang, w, h))