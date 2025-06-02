import heapq
import sys
input = sys.stdin.readline

N = int(input())

# 작은 절반을 담는 최대힙 (음수로 저장해서 최대힙 효과)
max_heap = []  
# 큰 절반을 담는 최소힙
min_heap = []

for i in range(N):
    num = int(input())
    
    # 첫 번째 원소는 max_heap에
    if not max_heap:
        heapq.heappush(max_heap, -num)
    else:
        # max_heap의 최대값과 비교
        if num <= -max_heap[0]:
            heapq.heappush(max_heap, -num)
        else:
            heapq.heappush(min_heap, num)
    
    # 균형 맞추기: max_heap이 min_heap보다 2개 이상 많으면 안됨
    if len(max_heap) > len(min_heap) + 1:
        heapq.heappush(min_heap, -heapq.heappop(max_heap))
    elif len(min_heap) > len(max_heap):
        heapq.heappush(max_heap, -heapq.heappop(min_heap))
    
    # 중간값은 항상 max_heap의 루트 (더 많거나 같은 개수)
    print(-max_heap[0])