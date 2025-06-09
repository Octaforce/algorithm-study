# 상어 초등학교

N = int(input())  # 3 <= N <= 20

likes = [None for _ in range(N * N + 1)]  # 학생마다 좋아하는 학생기록
orders = []  # 배치 순서

for _ in range(N * N):
    input_arr = list(map(int, input().split()))
    likes[input_arr[0]] = input_arr[1:]
    orders.append(input_arr[0])

dire = [[0, 1], [0, -1], [1, 0], [-1, 0]]  # 상하좌우
sit_position = [[0 for _ in range(N)] for _ in range(N)]  # 배치도

# 우선순위: 좋아하는 학생 인접, 빈칸이 많은 칸, 행 번호 작은 칸, 열 번호 작은 칸
for stu in orders:
    priority = []
    for i in range(N):
        for j in range(N):
            if sit_position[i][j] == 0: # 비어있다면
                cnt_friends, cnt_empty = 0, 0
                # 인접한 칸에 친구 확인
                for dy, dx in dire:
                    if 0 <= i + dy < N and 0 <= j + dx < N:
                        if sit_position[i + dy][j + dx] in likes[stu]:
                            cnt_friends += 1
                        elif sit_position[i + dy][j + dx] == 0:
                            cnt_empty += 1
                priority.append((cnt_friends, cnt_empty, i, j))

    # 우선순위대로 정렬
    priority.sort(key=lambda x: (-x[0], -x[1], x[2], x[3]))
    selected_y, selected_x = priority[0][2], priority[0][3]
    sit_position[selected_y][selected_x] = stu

satisfaction = 0

for i in range(N):
    for j in range(N):
        stu = sit_position[i][j]
        now = 0
        for dy, dx in dire:
            if 0 <= i + dy < N and 0 <= j + dx < N:
                if sit_position[i + dy][j + dx] in likes[stu]:
                    now += 1
        if now:
            satisfaction += 10 ** (now - 1)

print(satisfaction)

'''
def find_and_place_student(stu, max_empty):
    for i in range(N):
        for j in range(N):
            if cal_empty[i][j] == max_empty and sit_position[i][j] == 0:
                sit_position[i][j] = stu
                for dy, dx in dire:
                    if 0 <= i + dy < N and 0 <= j + dx < N:
                        cal_empty[i + dy][j + dx] -= 1
                return True  # 배치 성공
    return False  # 배치 실패


N = int(input())  # 3 <= N <= 20

likes = [None for _ in range(N * N + 1)]  # 학생마다 좋아하는 학생기록
orders = []  # 배치 순서

for _ in range(N * N):
    input_arr = list(map(int, input().split()))
    likes[input_arr[0]] = input_arr[1:]
    orders.append(input_arr[0])

dire = [[0, 1], [0, -1], [1, 0], [-1, 0]]  # 상하좌우
cal_empty = [[0 for _ in range(N)] for _ in range(N)]  # 각 칸마다 인접한 빈칸을 기록
sit_position = [[0 for _ in range(N)] for _ in range(N)]  # 배치도

max_empty = 4

# 초기 빈칸 계산
for i in range(N):
    for j in range(N):
        for dy, dx in dire:
            if 0 <= i + dy < N and 0 <= j + dx < N:
                cal_empty[i][j] += 1

# 순서대로 배치하자
for stu in orders:
    isComplete = False  # 배치 완료했는지 체크용
    # 1번 조건: 비어있는 칸 중에서 좋아하는 학생이 인접한 칸에 가장 많은 칸
    # 좋아하는 학생들 기준으로 인접한 칸 계산
    find_friends = [[0 for _ in range(N)] for _ in range(N)]
    for liked_stu in likes[stu]:
        for i in range(N):
            for j in range(N):
                if sit_position[i][j] == liked_stu:
                    for dy, dx in dire:
                        if 0 <= i + dy < N and 0 <= j + dx < N:
                            if sit_position[i + dy][j + dx] == 0:
                                find_friends[i + dy][j + dx] += 1
    
    # 계산했을 때 가장 큰 칸에 넣어주기
    maxval = 0  # 인접한 친구의 수
    max_empty_kan = 0  # 여러개일때 인접한 칸중에서 비어있는 칸이 많은 칸으로
    py, px = 0, 0
    for i in range(N):
        for j in range(N):
            if find_friends[i][j] > maxval and sit_position[i][j] == 0:
                maxval = find_friends[i][j]
                max_empty_kan = cal_empty[i][j]
                py, px = i, j
            elif find_friends[i][j] == maxval:
                if cal_empty[i][j] > max_empty_kan:
                    max_empty_kan = cal_empty[i][j]
                    py, px = i, j
    
    if maxval != 0:
        sit_position[py][px] = stu
        for dy, dx in dire:
            if 0 <= py + dy < N and 0 <= px + dx < N:
                cal_empty[py + dy][px + dx] -= 1  # 빈칸 계산
        
        isComplete = True
        
    # 못 넣었으면 인접한 칸 중 비어있는 칸이 가장 많은 칸으로, 여러개면 행이 작을 수록 열이 작을수록
  
    # while not isComplete:
    #     found = False
    #     for i in range(N):
    #         for j in range(N):
    #             if cal_empty[i][j] == max_empty and sit_position[i][j] == 0:
    #                 sit_position[i][j] = stu
    #                 for dy, dx in dire:
    #                     if 0 <= i + dy < N and 0 <= j + dx < N:
    #                         cal_empty[i + dy][j + dx] -= 1
    #                 isComplete = True
    #                 found = True
    #                 break  # 내부 루프 탈출
    #         if found:
    #             break  # 외부 루프 탈출
    #     if not found:
    #         max_empty -= 1


    # 이부분을 함수로 따로 빼서 하면 좀 더 깔끔할듯? (위의 방법 176ms, 이 방법 164ms)
    while not isComplete:
        if find_and_place_student(stu, max_empty):
            isComplete = True
        else:
            max_empty -= 1
    

satisfaction = 0

for i in range(N):
    for j in range(N):
        stu = sit_position[i][j]
        now = 0
        for dy, dx in dire:
            if 0 <= i + dy < N and 0 <= j + dx < N:
                if sit_position[i + dy][j + dx] in likes[stu]:
                    now += 1
        if now:
            satisfaction += 10 ** (now - 1)

print(satisfaction)
'''