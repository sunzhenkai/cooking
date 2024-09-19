import { deepMerge } from "./util.mjs"

let a = {
    a: 1,
    b: {
        c: 2
    }
}

let b = {
    a: 2,
    b: {
        d: 3
    }
}

let c = deepMerge(a, b)
console.log(c)
