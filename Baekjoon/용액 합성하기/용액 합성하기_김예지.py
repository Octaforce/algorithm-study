# 용액 합성하기

N = int(input())
potions = list(map(int, input().split())) # 오름차순으로 주어짐

p1, p2 = 0, N - 1   # 맨 앞, 맨 뒤 포인터
ans = potions[p1] + potions[p2]

while p1 < p2:
    temp = potions[p1] + potions[p2]

    # 최소값 업데이트
    if abs(ans) >= abs(temp):
        ans = temp
    
    # 현재 합에 따라서 포인터 옮겨주기
    if temp > 0:
        p2 -= 1
    elif temp < 0:
        p1 += 1
    else:
        break

print(ans)