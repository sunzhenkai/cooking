const request = require('superagent');

request.post('https://srv.wii.pub:9010/api/passport/v1/thirdAuthOpenIdLogin')
    .type('form')
    .field('username', 'wii')
    .end((e, r) => {
        console.log(e)
        console.log(JSON.stringify(r.body))
    })