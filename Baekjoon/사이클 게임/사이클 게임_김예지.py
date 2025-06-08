# 사이클 게임
## 유니온파인드
import sys
input = sys.stdin.readline
sys.setrecursionlimit(10**6)

def find(x):
    if parent[x] != x:
        parent[x] = find(parent[x])
    return parent[x]

def union(x, y):
    root_x = find(x)
    root_y = find(y)
    if root_x != root_y:
        parent[root_y] = root_x
        return False
    else:
        return True

n, m = map(int, input().split())
parent = [i for i in range(n)]
finished = False

for num in range(1, m + 1):
    if not finished:
        a, b = map(int, input().split())
        if union(a, b):
            print(num)
            finished = True

if not finished:
    print(0)