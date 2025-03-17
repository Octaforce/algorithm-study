N = int(input())
num_list = list(map(int,input().split()))

# 문제에서 최소 한 개 이상의 수를 선택해야기 때문에 N이 1인 경우 예외처리 진행
if N == 1:
    print(num_list[0])
# N이 2개 이상인 경우
else:
    # 첫 누적합을 구할 리스트를 생성
    dp = [0] * N
    # 누적합을 구하기 위한 리스트는 첫 인덱스 값으로 선언
    dp[0] = num_list[0]
    # 1번 인덱스부터 N 까지 반복
    for i in range(1,N):
        # 현재 값에 i 인덱스 값을 초기값 선언
        dp[i] = num_list[i]
        # 그 자리에 이전값 + 현재 값, 현재 자리값을 비교해서 max 값 저장
        dp[i] = max(dp[i-1]+num_list[i], num_list[i])
    # 수를 1개를 제외한 경우리스트 생성
    answer = [0] * N
    # 이 값또한 0번 인덱스로 선언해줘야 함
    answer[0] = num_list[0]
    # 1개의 수를 제외한 경우를 반복문 진행하면서 체크
    for i in range(1,N):
        answer[i] = max(dp[i-1],answer[i-1]+num_list[i])
    # 두 리스트에서 max 값을 찾아서 출력
    print(max(max(dp),max(answer)))