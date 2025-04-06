import sys
# 인풋 리스트
num_list = []

input = sys.stdin.readline

N = int(input())

for _ in range(N):
    num_list.append(int(input()))
# 리스트 정렬
num_list.sort()
# + 부분과 - 부분 구분
plus_list = []
minus_list = []
# 정답 및 len 사용하지 않을 변수 선언
ans = 0
plus_length = 0
minus_length = 0
# 반복문 돌며 + 리스트와 - 리스트 길이 및 저장
for i in range(N-1,-1,-1):
    if num_list[i] > 0:
        plus_list.append(num_list[i])
        plus_length += 1
    else:
        minus_list.append(num_list[i])
        minus_length += 1
# 길이가 있는 경우
if plus_length > 0:
    # 짝수인 경우와 홀수인 경우 구분
    check = plus_length % 2
    if check == 1:
        # 홀수인 경우 높은값부터 -1번 전까지 곱 or 합 중 높은값 추가
        for j in range(0,plus_length-2,2):
            ans += max(plus_list[j]*plus_list[j+1],plus_list[j]+plus_list[j+1])
        # 이후 맨 마지막 인덱스 더해줌
        ans += plus_list[-1]
    # 짝수인경우 끝까지 합 or 곱 중 높은 값 더함
    else:
        for j in range(0,plus_length-1,2):
            ans += max(plus_list[j]*plus_list[j+1],plus_list[j]+plus_list[j+1])
# 길이가 있는 경우
if minus_length > 0:
    # 짝, 홀로 나누어 계산
    check = minus_length % 2
    if check == 1:
        # 위와 마찬가지로 끝값 더해줌
        for j in range(minus_length-1,0,-2):
            ans += (minus_list[j]*minus_list[j-1])
        ans += minus_list[0]
    else:
        for j in range(minus_length-1,-1,-2):
            ans += (minus_list[j]*minus_list[j-1])
print(ans)