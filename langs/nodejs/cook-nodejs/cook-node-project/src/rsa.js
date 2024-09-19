const NodeRSA = require('node-rsa')
// const crypto = require("crypto");
// const JSEncrypt = require('jsencrypt')
const privateKey = 'MIICdQIBADANBgkqhkiG9w0BAQEFAASCAl8wggJbAgEAAoGBAI6FDzV8kQoj9vT6Iq0rT9Y3vBwwkq4YvlCHO5EPIUsIANso+QHu4Yp+RDVANye2NnyTj6bHagYEoXkTW9IH5sPtI/t+RKAOZ0LMYtIlfThD0f3nG4R8WxNfBbx0aHi5AMW391MhjZYDHxGcaGIkUqrrL2aP61yh+fMttA1/LY+hAgMBAAECgYBuMZNA17+NB6G6aGzHV+WyzAU2Bphi497ChM0Zq4kial2/Fj7xr7HTUy2Jvszmd4xJZg579VOUs5/l7YHhMxrI2sUadaenkdJ/LmdHICRT4BAFdDnM78UsY1/YgtoA06r63ZkaE6PJ2UPMCgcYMqV5OkAFwt8oTDSGlBb4EZuUAQJBAMbR+tolF96tHPb96BKbowIytRQZpC3KMutnzY74davY4BvAnvzoUTs0d89Xi+1+YxmI0UahejGdyMEWOryNJYkCQQC3gf3B8Kmaic3hz1ghslsdY5n1sE8piJH/9owvToc54MVQc9sfFC9f+e6v8yjblHFeaoYTL6NUz9MUmeHAgatZAkB+VGnaNnuGR+UBo6/UMwROnz2jue8yESptnZVlZMYQHUu5FplvBYan4dzG6E/G5em+Dcs739qusB0hYyiLKfxRAkAiGKweqenJhgtUBqOYdzxIxKXpqZ272N1P0u6PJ6cmkOX4od438xcuXREFbkfMLNO3uFE7JWHSs17D+CejDjTZAkAWlJVrwvcgdPDun/xF2FK8YoonJFPJxFD/jrVPuoZ+HxYa9sytwuaAU8jTv8WDF4er1ZO4N3TENE+SedxuH0zW'
// const encrypted = 'YLCMSlMXoQvS1mHLqeDBZ8GmTSbRBYQNS2qwDmyfTinFQc3Ja8DYu/emY4yWmWLqK0ly84ApE4CAEiDbxsGYrPmbspRShFZi+N6hXd6Lyj9avwVR7tuqFqUPJj+fF2V2Ii4Dp6VSgy8qizKfFBE0JFgC9cqIcR1IuS0kRQk+DfE='
// const encrypted = 'l48jNrAl4eQZzPR+7FbTIgHMxFMa9J9Gnb6qTlX/oYty456VTrJ2dRWPF6PwsagAV6ViEUBtOfxS176et8AioNwbwxxgCHCtrxR5fpu98/J8oUygNCZeYzxpjHf1jK7cKV69od/ecZ0O+WpTbCT593qaqUFgmj1nAH/BEe1bgPXnPI1oc3GMbVVkhcTqwvmq2mErL6WJ7obCogTwTOwZ2g=='

// var privateKeyBF = Buffer.from(privateKey, 'base64')
// var privateKeyBase64 = privateKeyBF.toString('hex')
// var encryptedBF = Buffer.from(encrypted, 'base64')
// var encryptedBase64 = encryptedBF.toString('base64')
// console.log(encryptedBase64)

var key = NodeRSA(privateKey, 'pkcs8-private-pem',
{'environment': 'node', 'encryptionScheme': 'pkcs1', 'signingScheme': 'sha256'});
// key.importKey(privateKeyBase64, 'pkcs8-private-der');
// key.importKey(privateKeyBF, 'pkcs8-private-der');
// key.importKey(privateKey, 'pkcs8', {'environment': 'node', 'signingScheme': 'sha256'})
// key.importKey(privateKeyBase64, {signingScheme: 'sha256'});
// const pk = key.exportKey();
// console.log(pk)


// return 

// key = NodeRSA(pk, {signingScheme: 'sha256'})

// const prk = console.log(pk)
// const key = NodeRSA(privateKey, 'pkcs8')
// key.importKey(privateKey, 'components')

var javaData= 'UgnRdVY7thSMN8dM5gg+wcMu7g8PmQclLFnoswkT9J8aB8RfU+LQhfKupRVifipg03LvVGedqQmXyF5Gz8AoDIW22q5kpLxpdS3sZdf2kQY6snDSHubtPszG+/jB05fYaCGCPvoJw1oD+HHWl6dF5GvTYvLcZykRAJtvr5vCjTg='
var nodeData = 'iQMK9U8wYsLdhssRVRVY0CA40SdM3PEP7KhuP74ICPA7AH0ckUw5Rq9C8RKAIBh0fO8hIYC+kPN9xUCKNUlFGy09xtBEOgRybg8JZZC8mEjeA0hHMbQePI4fxQa74IWYY646ihj7KCtaE55yE6G8yV7hRPFyjdPJieXEKhSJsmM='
var jsData = 'hOv454LZNZhPoS2nbtnH7S1phhkdVVX0NtF7xx37H4tNKelTguQ0i/obZXBGVRC7FoP0CAP7lrqo/VlT276D9kLXHUGSl/9FW1Q4fNcj4iMZedjcQJaZNOSZ4Vlti/9cXUeuUXaGG6vS3V49EfMy/FELFy+zpbijcnrIAar6uh4='

console.log(key.decrypt(javaData, 'utf-8'))
console.log(key.decrypt(nodeData, 'utf-8'))
console.log(key.decrypt(jsData, 'utf-8'))

var encryptedData = key.encrypt('hello world', 'base64')
console.log(encryptedData)

// var rsa = JSEncrypt()
// rsa.setPrivateKey(privateKey)
// console.log(rsa.decrypt(encrypted))

// const decryptedData = crypto.privateDecrypt(
//     {
//       key: prk,
//       // In order to decrypt the data, we need to specify the
//       // same hashing function and padding scheme that we used to
//       // encrypt the data in the previous step
//       padding: crypto.constants.RSA_PKCS1_OAEP_PADDING,
//       oaepHash: "sha256",
//     },
//     encrypted
//   );
  
  // The decrypted data is of the Buffer type, which we can convert to a
  // string to reveal the original data
//   console.log("decrypted data: ", decryptedData.toString());