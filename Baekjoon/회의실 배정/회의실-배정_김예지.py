# 회의실 배정

N = int(input())
arr = [tuple(map(int,input().split())) for _ in range(N)]
arr.sort()
arr.sort(key = lambda x: x[1])      # 종료시간 기준으로 정렬

now = arr[0][1] # 처음 회의가 끝나는 시간
i = 0           # 처음 회의의 인덱스
cnt = 1         # 회의의 개수
while i < N - 1:
    i += 1
    if arr[i][0] >= now:       # 다음 회의의 시작하는 시간이 앞의 회의의 끝나는 시간보다 뒤라면
        cnt += 1               # 회의 하나 더 해주고
        now = arr[i][1]        # 끝나는 시간을 바꿔준다

print(cnt)