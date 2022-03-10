'use strict';

function testTypeError() {
    var e = new TypeError('message');
    e.message = 'new message';
    console.log(e.message);
    alert(e.message);
}

testTypeError();