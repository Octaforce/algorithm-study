def solution(expressions):
    candidate = [i for i in range(2, 10)]
    unknown = []
    
    for expression in expressions:
        A, operation, B, equal, C = expression.split(' ')
        
        if C != 'X':
            a1, a2 = int(A) // 10, int(A) % 10
            b1, b2 = int(B) // 10, int(B) % 10
            c1, c2, c3 = int(C) // 100, (int(C) % 100) // 10, int(C) % 10
            
            remove_list = []
            for n in candidate:
                AA = a1 * n + a2
                BB = b1 * n + b2
                CC = c1 * n * n + c2 * n + c3
                
                if a1 >= n or a2 >= n or b1 >= n or b2 >= n or c1 >= n or c2 >= n or c3 >= n:
                    remove_list.append(n)
                elif operation == '+' and AA + BB != CC:
                    remove_list.append(n)
                elif operation == '-' and AA - BB != CC:
                    remove_list.append(n)
                    
            for remove_num in remove_list:
                candidate.remove(remove_num)
                    
        else:
            a1, a2 = int(A) // 10, int(A) % 10
            b1, b2 = int(B) // 10, int(B) % 10
            
            remove_list = []
            for n in candidate:
                if a1 >= n or a2 >= n or b1 >= n or b2 >= n:
                    remove_list.append(n)
            for remove_num in remove_list:
                candidate.remove(remove_num)
                
            unknown.append([A, operation, B])
    
    result = []
    print(candidate)
    if len(candidate) == 1:
        n = candidate[0]
        for A, operation, B in unknown:
            a1, a2 = int(A) // 10, int(A) % 10
            b1, b2 = int(B) // 10, int(B) % 10
            AA = a1 * n + a2
            BB = b1 * n + b2
            if operation == '+':
                c1 = (AA + BB) // (n * n)
                c2 = ((AA + BB) % (n * n)) // n
                c3 = (AA + BB) % n
                result.append(' '.join([A, operation, B, "=", str(c1 * 100 + c2 * 10 + c3)]))
            else:
                c1 = (AA - BB) // (n * n)
                c2 = ((AA - BB) % (n * n)) // n
                c3 = (AA - BB) % n
                result.append(' '.join([A, operation, B, "=", str(c1 * 100 + c2 * 10 + c3)]))
    else:
        for A, operation, B in unknown:
            calculated = []
            for n in candidate:
                a1, a2 = int(A) // 10, int(A) % 10
                b1, b2 = int(B) // 10, int(B) % 10
                AA = a1 * n + a2
                BB = b1 * n + b2
                if operation == '+':
                    c1 = (AA + BB) // (n * n)
                    c2 = ((AA + BB) % (n * n)) // n
                    c3 = (AA + BB) % n
                    calculated.append(c1 * 100 + c2 * 10 + c3)
                else:
                    c1 = (AA - BB) // (n * n)
                    c2 = ((AA - BB) % (n * n)) // n
                    c3 = (AA - BB) % n
                    calculated.append(c1 * 100 + c2 * 10 + c3)
            if len(set(calculated)) == 1:
                result.append(' '.join([A, operation, B, "=", str(calculated[0])]))
            else:
                result.append(' '.join([A, operation, B, "=", '?']))
    return result
            