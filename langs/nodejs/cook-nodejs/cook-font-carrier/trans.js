const fs = require('fs');
const svg = fs.readFileSync('src/1.svg').toString();

function svgToSymbol(svg) {
    svg = svg.replace(/>\s+</g, '><');
    svg = svg.replace(/<defs>.*<\/defs>/g, '');
    let g = /<g[^>]*>([\s\S]+)<\/g>/.exec(svg);
    if (g == null) {
        return /<svg[^>]*>([\s\S]+)<\/svg>/.exec(svg)[1];
    } else {
        return g[1];
    }
}

// console.log(svg.search(/<g[^>]*>([\s\S]+)<\/g>/))
// console.log(svg.search(/<path[^>]*>([\s\S]+)<\/path>/))

// console.log(svg.indexOf('</g>'))
// console.log(svg.match(/<\/path>/g))

// function isColorfulIcon(svg) {
//    return !(svg.indexOf('</g>') < 0 && svg.match(/<\/path>/g) == null);
// }

// console.log(isColorfulIcon(svg));

// let s = "hello world";
// console.log(s.match(/o/g));


// console.log(svgToSymbol(svg))
