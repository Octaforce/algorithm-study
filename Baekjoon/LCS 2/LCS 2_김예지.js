// LCS2
// 백준 9252

// input
// ACAYKP
// CAPCAK

// output
// 4
// ACAK
const fs = require("fs");
// const [str1, str2] = fs.readFileSync("/dev/stdin").toString().trim().split(/\r?\n/);
const [str1, str2] = fs.readFileSync('./input.txt').toString().trim().split(/\r?\n/);

const dp = Array(str2.length + 1).fill().map(() => Array(str1.length + 1).fill(0));

for (let i = 1; i < str2.length + 1; i++) {
    for (let j = 1; j < str1.length + 1; j++) {
        if (str1[j - 1] === str2[i - 1]) {
            dp[i][j] = dp[i - 1][j - 1] + 1
        } else {
            dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1])
        }
    }
}

let result = '';
let a = str2.length;
let b = str1.length;

while (a > 0 && b > 0) {
    if (dp[a][b] === dp[a - 1][b]) {
        a -= 1;
    } else if (dp[a][b] === dp[a][b - 1]) {
        b -= 1;
    } else {
        result += str2[a - 1];
        a -= 1;
        b -= 1;
    }
}

console.log(dp[str2.length][str1.length]);
if (dp[str2.length][str1.length] > 0) {
    console.log([...result].reverse().join(''));
}