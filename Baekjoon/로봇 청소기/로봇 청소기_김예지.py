# 로봇 청소기

'''
d 0 위 1 동 2 남 3 서
반시계로 90도 회전하면 0 -> 3 -> 2 -> 1 -> 0 이순서로 회전하네
'''

# 북, 동, 남, 서 순서
dire = [[-1, 0], [0, 1], [1, 0], [0, -1]]  # dire[0] = 위

N, M = map(int, input().split())
r, c, d = map(int, input().split())
room = []
for _ in range(N):
    room.append(list(map(int, input().split())))

'''
청소 안함 = 0
벽 = 1
청소 완료 = 2
'''
cnt = 0

while True:
    # 1. 현재 칸이 청소되지 않은 경우, 현재 칸을 청소한다.
    if room[r][c] == 0:
        room[r][c] = 2
        cnt += 1
    
    # 2. 현재 칸 중심으로 청소 안된 곳이 있는지 확인(반시계 90도 기준으로 확인)
    found = False
    for i in range(4):
        new_d = (d - i + 3) % 4 
        nr, nc = r + dire[new_d][0], c + dire[new_d][1]
        
        # 빈칸이고 청소 안했다면 거기로 전진
        if room[nr][nc] == 0:
            r = nr
            c = nc
            d = new_d
            found = True
            break

    if found:
        continue

    # 3. 한바퀴 돌았는데도 갈곳이 없으면? -> 뒤로 돌아
    back_d = (d + 2) % 4  # 현재 기준으로 뒤방향
    br, bc = r + dire[back_d][0], c + dire[back_d][1]
    if room[br][bc] == 2: 
        r = br
        c = bc
    else:
        break

print(cnt)