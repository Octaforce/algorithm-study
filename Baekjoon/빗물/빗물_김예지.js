// 빗물 
// 백준 14719

// 2차원 세계에 블록이 쌓여있다. 비가 오면 블록 사이에 빗물이 고인다.
// 비는 충분히 많이 온다. 고이는 빗물의 총량은 얼마일까?

// input
// 4 4
// 3 0 1 4

// output
// 5


const fs = require('fs')
const input = fs.readFileSync('/dev/stdin').toString().trim().split('\n')
// const input = fs.readFileSync('./input.txt').toString().trim().split('\n')

const [N, M] = input[0].split(' ').map(Number)
const heights = input[1].split(' ').map(Number)

let water = 0

for (let i = 0; i < heights.length; i++) {
    const left_max = Math.max(...heights.slice(0, i + 1))
    const right_max = Math.max(...heights.slice(i))
    water += Math.min(right_max, left_max) - heights[i]
}

console.log(water)