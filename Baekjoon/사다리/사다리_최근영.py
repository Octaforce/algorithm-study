import math

x, y, c = map(float,input().split())

left = 0
right = min(x,y)
answer = 0

while right - left > 0.000001:
    middle = (left + right)/2
    h1 = math.sqrt((x**2 - middle**2))
    h2 = math.sqrt((y**2 - middle**2))
    check = h1*h2/(h1+h2)
    if check >= c:
        answer = middle
        left = middle
    else:
        right = middle

print("{}".format(round(answer,3)))