# 백준 1253 좋다
# 투포인터

N = int(input())
nums = list(map(int, input().split()))

nums.sort()

result = 0

for i in range(N):
    target = nums[i]

    left, right = 0, N - 1   
    
    while left < right:
        # 지금 확인중인 숫자는 넘어가게
        if left == i:
            left += 1
            continue
        if right == i:
            right -= 1
            continue
            
        sum_of_left_right = nums[left] + nums[right]
        
        if sum_of_left_right == target:
            result += 1
            break
        elif sum_of_left_right < target:
            left += 1
        else:
            right -= 1

print(result)


'''
# 처음시도 -> nums가 0이상인줄 알고 찾으려는 숫자 앞에서만 진행함.
result = 0

for i in range(2, N):
    target = nums[i]

    left, right = 0, i - 1
    
    while left < right:
        sum_of_left_right = nums[left] + nums[right]
        if sum_of_left_right == target:
            result += 1
            break
        elif sum_of_left_right < target:
            left += 1
        else:
            right -= 1

print(result)
'''
