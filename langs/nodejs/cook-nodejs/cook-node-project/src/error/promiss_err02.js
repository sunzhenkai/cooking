function return_promiss() {
    return new Promise((resolve, reject) => {
        reject("request failed")
    })
}

async function getSecret(params) {
    return return_promiss().then((res)=> {}).catch((error) => {
        throw new Error(`catched ${error}`);
    })
}

async function getKeyByName() {
    const r = await getSecret();
    return r;
}

async function getKeyByNameV2() {
    return getSecret().then(() => {}).catch((error) => {
        return "error occur"
    })
}

async function callGetKeyByName(params) {
    try {
        let r = await getKeyByName();
        console.log('result: ', r)
    } catch (error) {
        console.error("error is catched", error)
    }
}

callGetKeyByName();