'use strict';

var CryptoJS = require('crypto-js');

var data = "SGVsbG8sIFdvcmxkIQ==";
var words = CryptoJS.enc.Base64.parse(data);
var hex = CryptoJS.enc.Hex.stringify(words);
console.log(data, '->', words, '->', hex);