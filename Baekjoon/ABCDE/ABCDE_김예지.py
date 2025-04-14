# ABCDE

'''
친구 관계가
A - B - C - D - E
총 4번 체크
'''
def check_friend(info, cnt, checked, now):
    if cnt == 4:  # 친구 관계를 4번 확인했으면 성공
        return True
    
    for friend in info[now]:
        if friend not in checked:
            checked.append(friend)
            if check_friend(info, cnt + 1, checked, friend):
                return True
            checked.pop()
    
    return False

N, M = map(int, input().split())
friend_info = [[] for _ in range(N)]  # N명의 친구 정보

for _ in range(M):
    a, b = map(int, input().split())
    friend_info[a].append(b)
    friend_info[b].append(a)

result = 0
for p in range(N):
    if check_friend(friend_info, 0, [p], p):
        result = 1
        break

print(result)