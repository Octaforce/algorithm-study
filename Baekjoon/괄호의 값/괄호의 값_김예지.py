# 괄호의 값

def cal(string):
    stack = []

    for char in string:
        if char in '([':
            stack.append(char)
        elif char == ')':
            # 짝이 안맞는다면?
            if not stack or stack[-1] == '[':
                return 0

            if stack[-1] == '(':
                stack.pop()
                stack.append(2)
            else:
                temp = 0
                # 앞에 있는 숫자들 다 더해
                while stack and type(stack[-1]) == int:
                    temp += stack.pop()
                
                if not stack or stack[-1] == '[':
                    return 0
            
                stack.pop()
                stack.append(temp * 2)

        elif char == ']':
            # 짝이 안맞는다면?
            if not stack or stack[-1] == '(':
                return 0

            if stack[-1] == '[':
                stack.pop()
                stack.append(3)
            else:
                temp = 0
                # 앞에 있는 숫자들 다 더해
                while stack and type(stack[-1]) == int:
                    temp += stack.pop()
                
                if not stack or stack[-1] == '(':
                    return 0
            
                stack.pop()
                stack.append(temp * 3)
        
    for cont in stack:
        if type(cont) != int:
            return 0
        
    return sum(stack)



input_string = input()
print(cal(input_string))