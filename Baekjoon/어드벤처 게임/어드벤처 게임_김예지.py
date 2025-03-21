# 어드벤처 게임

def dfs(rooms, n):
    stack = [[1, 0]] # 시작 room number, 가지고 있는 돈
    
    # 같은 돈을 소지하고 다시 같은 방에 가는건 막자
    visited = [None] * n
    visited[0] = 0
    
    while stack:
        [now_room_number, now_money] = stack.pop()

        if now_room_number == n:
            return 'Yes'

        for next_room_number in rooms[now_room_number - 1][2]:
            next_idx = next_room_number - 1
            room_type = rooms[next_idx][0]
            room_value = rooms[next_idx][1]

            # 룸 타입 확인
            if room_type == 'E':
                next_money = now_money
            elif room_type == 'L':
                next_money = max(now_money, room_value)
            else:
                if now_money >= room_value:
                    next_money = now_money - room_value
                else:
                    continue

            # 들리지 않았거나, 현재 소지금이 아까보다 많을때만 갈 수 있게
            if visited[next_idx] == None or visited[next_idx] < next_money:
                visited[next_idx] = next_money
                stack.append([next_room_number, next_money])

    
    return 'No'


while True:
    # n = 0이 되면 종료
    n = int(input())
    if n == 0:
        break

    rooms = []
    for _ in range(n):
        input_arr = input().split(' ')
        # rooms에 저장되는 형식 [방 타입(빈방, 레프리콘, 트롤), 돈, 다음 방 정보(list)]
        rooms.append([input_arr[0], int(input_arr[1]), list(map(int, input_arr[2:len(input_arr) - 1]))])

    print(dfs(rooms, n))