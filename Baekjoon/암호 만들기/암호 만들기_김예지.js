const fs = require('fs')
const input = fs.readFileSync('/dev/stdin').toString().trim().split('\n')
// const input = fs.readFileSync('./input.txt').toString().trim().split('\n')

const [L, C] = input[0].split(' ').map(Number)
const letters = input[1].split(' ')
letters.sort()  // 사전순 출력을 위해 정렬

// 문제 조건에 1개 이상의 모음 / 2개 이상의 자음이 있어야 함
// 그래서 모음을 세어주는 함수를 따로 만듦
function countVowels(string){
    const vowels = ['a', 'e', 'i', 'o', 'u']
    let num_of_vowel = 0
    for (let i = 0; i < string.length; i++) {
        if (vowels.includes(string[i])) {
            num_of_vowel += 1
        }
    }
    return num_of_vowel
}


// 백트래킹 -> password에 다음에 올 문자들을 하나씩 추가해서 다시 함수 돌리기
// password = 현재까지 저장된 비밀번호
// L,C, letters = 문제에서 주어진거 
// idx = 다음 문자를 어디서부터 넣을지 정하기 위한 index
function backTraking(password, L, idx, letters, C) {
    if (password.length == L) {
        num_of_vowel = countVowels(password)
        if (num_of_vowel >= 1 && password.length - num_of_vowel >= 2) {
            console.log(password)
        }  
        return
    }

    for (let i = idx + 1; i < C; i++) {
        const newPassword = password + letters[i]
        backTraking(newPassword, L, i, letters, C)
    }
}

backTraking('', L, -1, letters, C)