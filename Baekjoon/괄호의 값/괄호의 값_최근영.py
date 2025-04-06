word = input()

stack = []

for i in word:
    ans = 0
    if stack:
        if i == ")":
            check = stack.pop()
            while check != "(" and check != "[" and stack:
                ans += check
                check = stack.pop()
            if check == "(" and ans != 0:
                stack.append(ans*2)
            elif check == "(" and ans == 0:
                stack.append(2)
            elif check == "[":
                print(0)
                break
            elif check != "(" and not stack:
                print(0)
                break
        elif i == "]":
            check = stack.pop()
            while check != "(" and check != "[" and stack:
                ans += check
                check = stack.pop()
            if check == "[" and ans != 0:
                stack.append(ans*3)
            elif check == "[" and ans == 0:
                stack.append(3)
            elif check == "(":
                print(0)
                break
            elif check != "[" and not stack:
                print(0)
                break
        else:
            stack.append(i)
    else:
        if i == ")" or i == "]":
            print(0)
            break
        else:
            stack.append(i)
else:
    if "(" in stack or "[" in stack:
        print(0)
    else:
        print(sum(stack))