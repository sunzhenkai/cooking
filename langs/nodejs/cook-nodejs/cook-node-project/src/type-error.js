function testTypeError() {
    const e = new TypeError('message');
    console.log(e.message);
    e.message = 'new message';
    console.log(e.message);
}

testTypeError()