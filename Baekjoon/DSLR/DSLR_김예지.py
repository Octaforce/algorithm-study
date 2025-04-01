'''
D: D 는 n을 두 배로 바꾼다. 결과 값이 9999 보다 큰 경우에는 10000 으로 나눈 나머지를 취한다. 그 결과 값(2n mod 10000)을 레지스터에 저장한다.
S: S 는 n에서 1 을 뺀 결과 n-1을 레지스터에 저장한다. n이 0 이라면 9999 가 대신 레지스터에 저장된다.
L: L 은 n의 각 자릿수를 왼편으로 회전시켜 그 결과를 레지스터에 저장한다. 이 연산이 끝나면 레지스터에 저장된 네 자릿수는 왼편부터 d2, d3, d4, d1이 된다.
R: R 은 n의 각 자릿수를 오른편으로 회전시켜 그 결과를 레지스터에 저장한다. 이 연산이 끝나면 레지스터에 저장된 네 자릿수는 왼편부터 d4, d1, d2, d3이 된다.
'''

from collections import deque
import sys

input = sys.stdin.readline

def calculate(num):
    D = (num * 2) % 10000
    
    if num == 0:
        S = 9999
    else:
        S = num - 1

    d1 = num // 1000
    left1 = (num % 1000) * 10
    L = left1 + d1

    d4 = num % 10
    left2 = num // 10
    R = d4 * 1000 + left2

    return [D, S, L, R]

paths = ['D', 'S', 'L', 'R']


def bfs(A, B):
    visited = [0 for _ in range(10001)]
    visited[A] = 1
    q = deque([[A, '']])
    
    while q:
        num, path = q.popleft()
        new_nums = calculate(num)
        
        for i in range(4):
            if new_nums[i] == B:
                print(path + paths[i])
                return

            if visited[new_nums[i]] == 0:
                visited[new_nums[i]] = 1
                q.append([new_nums[i], path + paths[i]])


T = int(input())
for _ in range(T):
    A, B  = map(int, input().split())
    bfs(A, B)




'''
옛날에 풀었던 코드 (실행시간 6000ms)
import sys
from collections import deque
T = int(input())

for _ in range(T):
    A, B = map(int,sys.stdin.readline().rstrip().split())

    visited = [False for i in range(10001)]
    deq = deque()
    deq.append([A,''])
    visited[A] = True

    while deq:
        num, command = deq.popleft()

        if num == B:
            print(command)
            break

        d = num * 2 % 10000
        if not visited[d]:
            visited[d] = True
            deq.append([d, command + 'D'])

        s = (num - 1) % 10000
        if not visited[s]:
            visited[s] = True
            deq.append([s, command + 'S'])

        l = num // 1000 + (num % 1000)*10
        if not visited[l]:
            visited[l] = True
            deq.append([l, command + 'L'])

        r = num // 10 + (num % 10) * 1000
        if not visited[r]:
            visited[r] = True
            deq.append([r, command + 'R'])
'''