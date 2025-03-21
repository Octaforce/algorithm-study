# 뱀
from collections import deque

N = int(input())
board = [[0 for _ in range(N)] for _ in range(N)]

# 뱀의 머리 방향 [오른쪽, 아래, 왼쪽, 위쪽]
directions = [(0, 1), (1, 0), (0, -1), (-1, 0)]
snake = deque([(0, 0)]) # 뱀이 차지하고 있는 위치 -> 큐로 관리
head_idx = 0 # 뱀이 보고 있는 위치 -> 오른쪽(처음엔)

apple_count = int(input())
for _ in range(apple_count):
    y, x = map(int, input().split(' '))
    board[y - 1][x - 1] = 'apple'

l = int(input())
infos = [0 for _ in range(10000)]
for _ in range(l):
    t, dire = input().split(' ')
    infos[int(t)] = dire

count = 0
while count < 10000:
    
    snake_head = snake[0]
 
    # 이번에 방향전환이 있으면
    if infos[count] != 0:
        if infos[count] == 'D':
            head_idx = (head_idx + 1) % 4
        else:
            if head_idx == 0:
                head_idx = 3
            else:
                head_idx -= 1
    

    count += 1 

    # 다음 머리가 갈 위치 = 지금 머리에다가 방향만큼 더해주기
    next_head = [snake_head[0] + directions[head_idx][0], snake_head[1] + directions[head_idx][1]]
    
    # print('시간', count, '방향', head_idx, '다음위치', next_head, '뱀길이', len(snake))
    # 충돌 확인
    if next_head in snake:
        print(count)
        break
    if next_head[0] in [-1, N] or next_head[1] in [-1, N]:
        print(count)
        break
    
    snake.appendleft(next_head)
    if board[next_head[0]][next_head[1]] == 'apple':
        board[next_head[0]][next_head[1]] = 0
        continue
    else:
        snake.pop()

    
        

