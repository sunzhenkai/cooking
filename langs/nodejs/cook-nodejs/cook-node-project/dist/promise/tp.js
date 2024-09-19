'use strict';

async function init() {
    return new Promise(function (resolve, reject) {
        console.log('北京银行-页面初始化中 - 1');
        setTimeout(function () {
            getEncrypt().then(function () {});
        }, 1000);
        resolve("100");
    });
}

async function getEncrypt() {
    await setTimeout(function () {}, 1000);
    console.log('请求解密后 - 2');
}

async function exec(f) {
    return new Promise(function (resolve, reject) {
        setTimeout(function () {
            f();
            resolve('done');
        }, 1000);
    });
}

async function execFixed(f) {
    return new Promise(function (resolve, reject) {
        setTimeout(function () {
            res = f();
            if (res instanceof Promise) {
                res.then(function () {
                    resolve('done');
                });
            }
        }, 1000);
    });
}

async function getEncryptedData() {
    res = await execFixed(init);
    console.log('最终结果 3');
}

getEncryptedData();