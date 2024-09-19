const fs = require('fs');
const fontCarrier = require('font-carrier');
const font = fontCarrier.create();
const icons = [];
const dir = 'src';
const {Glyph} = require('font-carrier');

console.log(new Glyph({d: ''}));

let files = fs.readdirSync(dir);
files.forEach(name => {
    let svg = fs.readFileSync(dir + '/' + name, 'utf8');
    icons.push({name, svg});
});

icons.forEach(icon => {
    font.setGlyph(icon.name, {
        glyphName: icon.name,
        svg: icon.svg
    });
});

icons.map(icon => {
    let glyph = font.getGlyph([icon.name]);
    // console.log(glyph);
    console.log(glyph.get('glyphName'));
    // let opt = glyph.options;
    // console.log(opt);

})