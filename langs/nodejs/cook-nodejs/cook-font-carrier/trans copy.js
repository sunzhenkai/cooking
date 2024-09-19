const parseString = require('xml2js').parseString;
const fs = require('fs');
const { result } = require('lodash');
const svg = fs.readFileSync('src/1.svg').toString()
var xml2js = require('xml2js');
var builder = new xml2js.Builder();

// console.log(svg)

parseString(svg, (err, result) => {
    console.log(result.svg.path[0]);
    // console.log(builder.buildObject(result.svg.path[0]));
})

function svgToSymbol(svg) {

}